package asp;
public class Book {
    private String title;
    private String author;
    private String memberName;

    public Book() {}

    public Book(String title, String author, String memberName) {
        this.title = title;
        this.author = author;
        this.memberName = memberName;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getMemberName() { return memberName; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
}
