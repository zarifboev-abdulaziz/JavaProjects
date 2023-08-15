
package uz.pdp.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Result {

    @SerializedName("file_id")
    private String fileId;
    @SerializedName("file_path")
    private String filePath;
    @SerializedName("file_size")
    private Long fileSize;
    @SerializedName("file_unique_id")
    private String fileUniqueId;

    public String getFileId() {
        return fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public String getFileUniqueId() {
        return fileUniqueId;
    }

    public static class Builder {

        private String fileId;
        private String filePath;
        private Long fileSize;
        private String fileUniqueId;

        public Result.Builder withFileId(String fileId) {
            this.fileId = fileId;
            return this;
        }

        public Result.Builder withFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Result.Builder withFileSize(Long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public Result.Builder withFileUniqueId(String fileUniqueId) {
            this.fileUniqueId = fileUniqueId;
            return this;
        }

        public Result build() {
            Result result = new Result();
            result.fileId = fileId;
            result.filePath = filePath;
            result.fileSize = fileSize;
            result.fileUniqueId = fileUniqueId;
            return result;
        }

    }

}
