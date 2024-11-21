package domain;

public class WiseSaying {
    private int id;
    private String content;
    private String author;

    WiseSaying(String content, String author) {
        this.content = content;
        this.author = author;
    }

    WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    static public WiseSaying of(int id, String content, String author) {
        return new WiseSaying(id, content, author);
    }
}
