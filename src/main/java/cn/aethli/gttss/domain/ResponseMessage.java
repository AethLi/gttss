package cn.aethli.gttss.domain;

public class ResponseMessage {
    public static final int STATUS_OK=0;
    public static final int STATUS_FAIL=1;
    public static final int STATUS_ERROR=2;
    int status;
    String message;
    Object model;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public ResponseMessage(int status, Object model) {
        this.status = status;
        this.model = model;
    }

    public ResponseMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseMessage(int status, String message, Object model) {
        this.status = status;
        this.message = message;
        this.model = model;
    }

    public ResponseMessage() {
    }

    public ResponseMessage(int status) {
        this.status = status;
    }
}
