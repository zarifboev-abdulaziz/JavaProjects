
package uz.pdp.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class FileFromJson {

    @Expose
    private Boolean ok;
    @Expose
    private Result result;

    public Boolean getOk() {
        return ok;
    }

    public Result getResult() {
        return result;
    }

    public static class Builder {

        private Boolean ok;
        private Result result;

        public FileFromJson.Builder withOk(Boolean ok) {
            this.ok = ok;
            return this;
        }

        public FileFromJson.Builder withResult(Result result) {
            this.result = result;
            return this;
        }

        public FileFromJson build() {
            FileFromJson fileFromJson = new FileFromJson();
            fileFromJson.ok = ok;
            fileFromJson.result = result;
            return fileFromJson;
        }

    }

}
