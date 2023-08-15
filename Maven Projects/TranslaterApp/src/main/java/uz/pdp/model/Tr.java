
package uz.pdp.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Tr {

    @Expose
    private List<Ex> ex;
    @Expose
    private List<Mean> mean;
    @Expose
    private String pos;
    @Expose
    private List<Syn> syn;
    @Expose
    private String text;

    public List<Ex> getEx() {
        return ex;
    }

    public List<Mean> getMean() {
        return mean;
    }

    public String getPos() {
        return pos;
    }

    public List<Syn> getSyn() {
        return syn;
    }

    public String getText() {
        return text;
    }

    public static class Builder {

        private List<Ex> ex;
        private List<Mean> mean;
        private String pos;
        private List<Syn> syn;
        private String text;

        public Tr.Builder withEx(List<Ex> ex) {
            this.ex = ex;
            return this;
        }

        public Tr.Builder withMean(List<Mean> mean) {
            this.mean = mean;
            return this;
        }

        public Tr.Builder withPos(String pos) {
            this.pos = pos;
            return this;
        }

        public Tr.Builder withSyn(List<Syn> syn) {
            this.syn = syn;
            return this;
        }

        public Tr.Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Tr build() {
            Tr tr = new Tr();
            tr.ex = ex;
            tr.mean = mean;
            tr.pos = pos;
            tr.syn = syn;
            tr.text = text;
            return tr;
        }

    }

}
