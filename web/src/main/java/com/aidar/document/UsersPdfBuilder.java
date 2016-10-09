package com.aidar.document;

import com.aidar.model.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by paradise on 30.04.16.
 */
@Component
public class UsersPdfBuilder extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<User> users = (List<User>) model.get("users");

        document.add(new Paragraph("All users"));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{2.0f, 2.0f, 4.0f, 2.0f});
        table.setSpacingBefore(10);
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(BaseColor.WHITE);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Surname", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);

        for (User user : users) {
            table.addCell(user.getName());
            table.addCell(user.getSurname());
            table.addCell(user.getEmail());
            table.addCell(user.getStatus().getRepresentation());
        }

        document.add(table);
    }

}
