public class News {
    //Properties
    private String title;
    private String text;
    private User author;
    private int newsId;
    private boolean isConfirmed;

    //Constructors
    public News() {
    }

    public News(String title, String text, User author, int newsId, boolean isConfirmed) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.newsId = newsId;
        this.isConfirmed = isConfirmed;
    }

    //Getter and Setter

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    //to String

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", newsId=" + newsId +
                ", isConfirmed=" + isConfirmed +
                '}';
    }
}
