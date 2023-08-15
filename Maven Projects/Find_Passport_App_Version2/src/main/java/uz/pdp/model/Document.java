package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.enums.DocType;

import java.util.UUID;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class Document {
    private UUID id = UUID.randomUUID();
    private DocType documentType;
    private String serialNum;
    private User user;
    private String prize;
    private String imgFile_id;

    public Document(DocType documentType, String serialNum, User user, String prize, String imgURL) {
        this.documentType = documentType;
        this.serialNum = serialNum;
        this.user = user;
        this.prize = prize;
        this.imgFile_id = imgURL;
    }
}
