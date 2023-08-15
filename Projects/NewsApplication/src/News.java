public class News {
    //Fieldlar
    private String title; //Sarlavha
    private String text;  //matni
    private User author;
    private boolean tasdiqlanganmi;
    private int newsId;

    //Constructor
    public News() {
    }

    public News(String title, String text, boolean tasdiqlanganmi, int newsId) {
        this.title = title;
        this.text = text;
        this.tasdiqlanganmi = tasdiqlanganmi;
        this.newsId = newsId;
    }

    //Getter and setter

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTasdiqlanganmi() {
        return tasdiqlanganmi;
    }

    public void setTasdiqlanganmi(boolean tasdiqlanganmi) {
        this.tasdiqlanganmi = tasdiqlanganmi;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
//to String

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", tasdiqlanganmi=" + tasdiqlanganmi +
                ", newsId=" + newsId +
                '}';
    }
}
