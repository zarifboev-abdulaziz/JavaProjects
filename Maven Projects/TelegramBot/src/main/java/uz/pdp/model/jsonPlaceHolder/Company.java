
package uz.pdp.model.jsonPlaceHolder;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Company {

    @SerializedName("bs")
    private String mBs;
    @SerializedName("catchPhrase")
    private String mCatchPhrase;
    @SerializedName("name")
    private String mName;

    public String getBs() {
        return mBs;
    }

    public void setBs(String bs) {
        mBs = bs;
    }

    public String getCatchPhrase() {
        return mCatchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        mCatchPhrase = catchPhrase;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
