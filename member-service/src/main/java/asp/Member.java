package asp;

public class Member {
    private String name;

    public Member() {}  // necesar pentru Jackson

    public Member(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }
}
