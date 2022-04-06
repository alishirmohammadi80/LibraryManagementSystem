package com.alishirmohammadi.librarymanagementsystem.pdfExporter;

import com.alishirmohammadi.librarymanagementsystem.entity.Book;
import com.lowagie.text.*;
import com.lowagie.text.Font;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class BookPdfExporter {
    private List<Book> listBook;

    public BookPdfExporter(List<Book> listBook) {
        this.listBook = listBook;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);



        cell.setPhrase(new Phrase("نام کتاب", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("شماره سریال", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("شماره شابک", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("توضیحات", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Book book : listBook) {

            table.addCell(book.getName());
            table.addCell(book.getSerialName());
            table.addCell(book.getIsbn());
            table.addCell(book.getDescription());


        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Books", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
