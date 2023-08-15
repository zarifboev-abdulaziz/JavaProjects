package uz.pdp;

import com.google.gson.Gson;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import uz.pdp.model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {

    //PDFWriter
    //PdfDocumnet
    //addnewpage
    //documnet from layout
    //close document
    public static void main(String[] args) {
        Gson gson = new Gson();

        try (PdfWriter pdfWriter = new PdfWriter("src/main/resources/Test.pdf")) {

            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            pdfDocument.addNewPage();

            Document document = new Document(pdfDocument);

            Paragraph paragraph = new Paragraph("Hello Java!!!");
            document.add(paragraph);

            float [] colWidth = {150F, 150F, 150F};
            Table table = new Table(colWidth);

            table.addCell(new Cell().add("ID"));
            table.addCell(new Cell().add("UserId"));
            table.addCell(new Cell().add("Name"));

            User[] userList = gson.fromJson(new FileReader("src/main/resources/user.json"), User[].class);


            for (int i = 0; i < userList.length; i++) {
                User user = userList[i];
                table.addCell(new Cell().add(String.valueOf(user.getId())));
                table.addCell(new Cell().add(String.valueOf(user.getName())));
                table.addCell(new Cell().add(String.valueOf(user.getAge())));
            }


            document.add(table);
            document.close();
            System.out.println("PDF file is successfully created!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
