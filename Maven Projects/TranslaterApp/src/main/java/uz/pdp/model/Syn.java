
package uz.pdp.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Syn {

    @Expose
    private String text;

    public String getText() {
        return text;
    }

    public static class Builder {

        private String text;

        public Syn.Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Syn build() {
            Syn syn = new Syn();
            syn.text = text;
            return syn;
        }

    }

}
