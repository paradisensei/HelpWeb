package com.aidar.document;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by paradise on 30.04.16.
 */
public abstract class AbstractPdfView extends AbstractView {

    public AbstractPdfView() {
        setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        ByteArrayOutputStream out = createTemporaryOutputStream();
        Document document = newDocument();
        PdfWriter writer = newWriter(document, out);
        prepareWriter(model, writer, request);
        buildPdfMetadata(model, document, request);
        document.open();
        buildPdfDocument(model, document, writer, request, response);
        document.close();
        writeToResponse(response, out);
    }

    protected Document newDocument() {
        return new Document(PageSize.A4);
    }

    protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
        return PdfWriter.getInstance(document, os);
    }

    protected void prepareWriter(Map<String, Object> model,
                                 PdfWriter writer, HttpServletRequest request)
            throws DocumentException {
        writer.setViewerPreferences(getViewerPreferences());
    }

    protected int getViewerPreferences() {
        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
    }

    protected void buildPdfMetadata(Map<String, Object> model,
                                    Document document, HttpServletRequest request) {
    }

    protected abstract void buildPdfDocument(Map<String, Object> model, Document document,
                                             PdfWriter writer,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws Exception;

}
