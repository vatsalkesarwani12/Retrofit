package e.vatsalkesarwani.myapplication;

import com.google.gson.annotations.SerializedName;

public class post {

    private int userId;

    private int id;

    private String title;

    @SerializedName("body")
    private String text;

    public post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
