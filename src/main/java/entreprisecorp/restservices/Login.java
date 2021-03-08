package entreprisecorp.restservices;

public class Login {
    private final long id;
    private final String content;

    public Login(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
