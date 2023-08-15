package uz.pdp;
//3 - Variant

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.poi.xwpf.usermodel.*;
import uz.pdp.model.Comment;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Comment> commentListFromJson = getDataFromJson();

        if (commentListFromJson != null){
            writeDataToWordFile(commentListFromJson);
        }
    }

    private static void writeDataToWordFile(List<Comment> commentListFromJson) {
        XWPFDocument document = new XWPFDocument();

        File file = new File("src/main/resources/Comments.docx");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setFontSize(14);
            run.setText("Comment List");

            XWPFTable table = document.createTable();

            XWPFTableRow tableRow1 = table.getRow(0);

            tableRow1.getCell(0).setText("T/R");
            tableRow1.addNewTableCell().setText("Name");
            tableRow1.addNewTableCell().setText("Email");
            tableRow1.addNewTableCell().setText("Body");

            tableRow1.getCell(0).setColor("AFAFAF");
            tableRow1.getCell(1).setColor("AFAFAF");
            tableRow1.getCell(2).setColor("AFAFAF");
            tableRow1.getCell(3).setColor("AFAFAF");


            for (int i = 0; i < commentListFromJson.size(); i++) {
                XWPFTableRow tableRow = table.createRow();
                Comment comment = commentListFromJson.get(i);
                tableRow.getCell(0).setText(String.valueOf((i + 1)));
                tableRow.getCell(1).setText(comment.getName());
                tableRow.getCell(2).setText(comment.getEmail());
                tableRow.getCell(3).setText(comment.getBody());
            }


            document.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static List<Comment> getDataFromJson() {
        Gson gson = new Gson();
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/comments");
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            List<Comment> commentList = gson.fromJson(bufferedReader, new TypeToken<List<Comment>>(){}.getType());

            return commentList;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
