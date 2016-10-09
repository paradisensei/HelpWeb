package com.aidar.document;

import com.aidar.model.Community;
import com.aidar.model.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by paradise on 06.05.16.
 */
public class CommunitiesPdfBuilder extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Community> communities = (List<Community>) model.get("communities");

        document.add(new Paragraph("All communities"));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{2.0f, 3.0f, 3.0f, 2.0f});
        table.setSpacingBefore(10);
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(BaseColor.WHITE);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Founder", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Created at", font));
        table.addCell(cell);

        for (Community c : communities) {
            table.addCell(c.getName());
            table.addCell(c.getDescription());
            User founder = c.getFounder();
            table.addCell(founder.getName() + founder.getSurname());
            table.addCell(c.getCreatedAt().toString());
        }

        document.add(table);
    }

}
