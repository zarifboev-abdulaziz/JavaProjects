
package uz.pdp.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Def {

    @Expose
    private String pos;
    @Expose
    private String text;
    @Expose
    private List<Tr> tr;

    public String getPos() {
        return pos;
    }

    public String getText() {
        return text;
    }

    public List<Tr> getTr() {
        return tr;
    }

    public static class Builder {

        private String pos;
        private String text;
        private List<Tr> tr;

        public Def.Builder withPos(String pos) {
            this.pos = pos;
            return this;
        }

        public Def.Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Def.Builder withTr(List<Tr> tr) {
            this.tr = tr;
            return this;
        }

        public Def build() {
            Def def = new Def();
            def.pos = pos;
            def.text = text;
            def.tr = tr;
            return def;
        }

    }

}
