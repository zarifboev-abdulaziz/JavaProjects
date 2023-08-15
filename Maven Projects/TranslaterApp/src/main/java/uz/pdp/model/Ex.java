
package uz.pdp.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Ex {

    @Expose
    private String text;
    @Expose
    private List<Tr> tr;

    public String getText() {
        return text;
    }

    public List<Tr> getTr() {
        return tr;
    }

    public static class Builder {

        private String text;
        private List<Tr> tr;

        public Ex.Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Ex.Builder withTr(List<Tr> tr) {
            this.tr = tr;
            return this;
        }

        public Ex build() {
            Ex ex = new Ex();
            ex.text = text;
            ex.tr = tr;
            return ex;
        }

    }

}
