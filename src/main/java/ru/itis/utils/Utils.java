package ru.itis.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import ru.itis.Person;

import java.io.FileNotFoundException;

public class Utils {
    public static void createPdf(String filePath, Person person, String title) throws FileNotFoundException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(filePath));
        Document document = new Document(pdf);

        Style style = new Style().setFontSize(24);

        document.add(new Paragraph(title).addStyle(style));
        document.add(new Paragraph("first name: " + person.getFirstName()));
        document.add(new Paragraph("last name: " + person.getLastName()));
        document.add(new Paragraph("passport number: " + person.getPasspNumb()));
        document.add(new Paragraph("age: " + person.getAge()));
        document.add(new Paragraph("date of passport: " + person.getDateOfPassp()));
        document.close();
    }
}
