package uz.pdp;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import uz.pdp.model.Post;

import java.awt.*;
import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("src/main/resources/posts.json");
        Gson gson = new Gson();

        List<Post> postList = gson.fromJson(reader, new TypeToken<List<Post>>(){}.getType());

        XSSFWorkbook workbook = new XSSFWorkbook();
        File file = new File("src/main/resources/Posts.xlsx");


        XSSFSheet spreadsheet = workbook.createSheet("Sheet1");

        XSSFRow row1= spreadsheet.createRow(0);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        row1.createCell(0).setCellValue("User Id");
        row1.createCell(1).setCellValue("Post id");
        row1.createCell(2).setCellValue("Title");
        row1.createCell(3).setCellValue("Body");



        for (int i = 0; i < postList.size(); i++) {
            Post post = postList.get(i);

            XSSFRow row= spreadsheet.createRow(i + 1);
            row.setRowStyle(style);

            row.createCell(0).setCellValue(post.getUserId());
            row.getCell(0).setCellStyle(style);
            row.createCell(1).setCellValue(post.getId());
            row.getCell(1).setCellStyle(style);
            row.createCell(2).setCellValue(post.getTitle());
            row.getCell(2).setCellStyle(style);
            row.createCell(3).setCellValue(post.getBody());
            row.getCell(3).setCellStyle(style);

            spreadsheet.autoSizeColumn(0);
            spreadsheet.autoSizeColumn(1);
            spreadsheet.autoSizeColumn(2);
            spreadsheet.autoSizeColumn(3);

        }

        InputStream myImage = new FileInputStream("src/main/resources/image (3).jpg");
        byte[] bytes = IOUtils.toByteArray(myImage);
        int my_picture_id = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
        myImage.close();
        CreationHelper helper = workbook.getCreationHelper();
        Drawing drawing = spreadsheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(1); //Column B
        anchor.setRow1(104); //Row 3
        anchor.setCol2(4); //Column C
        anchor.setRow2(130); //Row 4

        Picture pict = drawing.createPicture(anchor, my_picture_id);



        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();

        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }
}
