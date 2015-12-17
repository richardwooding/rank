package co.richardwooding.rank;

/**
 * Created by richardwooding on 2015/12/15.
 */
public class ResultFormatException extends Exception {

    public ResultFormatException() {
    }

    public ResultFormatException(String message) {
        super(message);
    }

    public ResultFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultFormatException(Throwable cause) {
        super(cause);
    }

    public ResultFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
