package com.aidar.document;

import com.aidar.model.Request;
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
 * Created by paradise on 05.05.16.
 */
public class RequestsPdfBuilder extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Request> requests = (List<Request>) model.get("requests");

        document.add(new Paragraph("All requests"));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{3.0f, 3.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(BaseColor.WHITE);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Needy", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Volunteer", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Created at", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Service type", font));
        table.addCell(cell);

        for (Request r : requests) {
            User user = r.getNeedy();
            table.addCell(user.getName() + " " + user.getSurname());
            user = r.getVolunteer();
            table.addCell(user != null ? user.getName() + " " + user.getSurname() : "No yet");
            table.addCell(r.getCreatedAt().toString());
            table.addCell(r.getServiceType().getRepresentation());
        }

        document.add(table);
    }

}
