package uz.pdp.fileuploadanddownload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileOriginalName;
    private long size;
    private String contentType;

    //This field for Uploading file to system!!!
    private String name;

    public Attachment(String fileOriginalName, long size, String contentType) {
        this.fileOriginalName = fileOriginalName;
        this.size = size;
        this.contentType = contentType;
    }
}
