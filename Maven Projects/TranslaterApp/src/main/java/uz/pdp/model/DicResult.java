
package uz.pdp.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class DicResult {

    @Expose
    private List<Def> def;
    @Expose
    private Head head;

    public List<Def> getDef() {
        return def;
    }

    public Head getHead() {
        return head;
    }

    public static class Builder {

        private List<Def> def;
        private Head head;

        public DicResult.Builder withDef(List<Def> def) {
            this.def = def;
            return this;
        }

        public DicResult.Builder withHead(Head head) {
            this.head = head;
            return this;
        }

        public DicResult build() {
            DicResult dicResult = new DicResult();
            dicResult.def = def;
            dicResult.head = head;
            return dicResult;
        }

    }

}
