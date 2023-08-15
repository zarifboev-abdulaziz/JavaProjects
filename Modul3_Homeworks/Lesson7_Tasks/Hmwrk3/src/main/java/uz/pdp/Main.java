package uz.pdp;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.BorderCollapsePropertyValue;
import uz.pdp.model.Photo;

import java.awt.*;
import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        Gson gson = new Gson();
        Reader reader = new FileReader("src/main/resources/photos (2).json");

        List<Photo> photoList = gson.fromJson(reader, new TypeToken<List<Photo>>(){}.getType());

        File file = new File("src/main/resources/PhotoList.pdf");
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        pdfDocument.setDefaultPageSize(PageSize.A3);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);

        Paragraph paragraph1 = new Paragraph("Photo List");
        document.add(paragraph1);

        float[] pointColWidth = {150F, 150F, 150F, 150F, 150F};
        Table table = new Table(pointColWidth);

        table.addCell("albumId");
        table.addCell("id");
        table.addCell("title");
        table.addCell("url");
        table.addCell("thumbnailUrl");

        for (Photo photo : photoList) {
            table.addCell(photo.getAlbumId().toString());
            table.addCell(photo.getId().toString());
            table.addCell(photo.getTitle());
            table.addCell(photo.getUrl());
            table.addCell(photo.getThumbnailUrl());
        }

        document.add(table);
        document.close();

        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);

    }
}
