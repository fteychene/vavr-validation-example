package validation;

import io.vavr.collection.Seq;

public final class ComposeError implements Error {

    private final String path;
    private final Seq<Error> reasons;

    public ComposeError(String path, Seq<Error> reasons) {
        this.path = path;
        this.reasons = reasons;
    }

    public String getPath() {
        return path;
    }

    public Seq<Error> getReasons() {
        return reasons;
    }

    @Override
    public String toString() {
        return "ComposeError{" +
                "path='" + path + '\'' +
                ", reasons=" + reasons +
                '}';
    }
}
