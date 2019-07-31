package gazibaric.ante.fer.hr.gazibaric.ante.fer.hr.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageResponse implements Serializable{

    @SerializedName("url_location")
    private String url = "https://www.google.com";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
