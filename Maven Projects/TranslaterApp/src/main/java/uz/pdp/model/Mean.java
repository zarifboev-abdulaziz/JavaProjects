
package uz.pdp.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Mean {

    @Expose
    private String text;

    public String getText() {
        return text;
    }

    public static class Builder {

        private String text;

        public Mean.Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Mean build() {
            Mean mean = new Mean();
            mean.text = text;
            return mean;
        }

    }

}
