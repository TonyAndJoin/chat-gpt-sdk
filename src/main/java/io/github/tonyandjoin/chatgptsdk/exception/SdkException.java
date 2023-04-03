package io.github.tonyandjoin.chatgptsdk.exception;

/**
 * @author liansx
 */
public class SdkException extends RuntimeException{

    private static final long serialVersionUID = 2359767895161832954L;

    protected String code;

    protected String msg;

    public SdkException(String message){
        super(message);
        this.code = "5000";
        this.msg = message;
    }

    public SdkException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

}
