package entreprisecorp.restservices;

/**
 * This class is the response template used to responde to the rest request
 * It's embed success boolean
 */

public class ResponseSuccess {
    private final long id;
    private final String content;
    private final boolean success;

    public ResponseSuccess(long id, String content, boolean success) {
        this.id = id;
        this.content = content;
        this.success = success;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean getSucess() {
        return success;
    }
}

