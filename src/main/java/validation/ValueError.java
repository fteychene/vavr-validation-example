package validation;

public final class ValueError implements Error {

    private final String path;
    private final String value;
    private final String reason;

    public ValueError(String field, String value, String error) {
        this.path = field;
        this.value = value;
        this.reason = error;
    }

    public String getPath() {
        return path;
    }

    public String getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "Error{" +
                "path='" + path + '\'' +
                ", value='" + value + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
