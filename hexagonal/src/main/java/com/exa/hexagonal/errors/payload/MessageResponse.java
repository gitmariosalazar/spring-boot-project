package com.exa.hexagonal.errors.payload;



import java.io.Serializable;

public class MessageResponse implements Serializable {
    private String message;
    private Object object;

    public MessageResponse(){}

    public MessageResponse(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "message='" + message + '\'' +
                ", object=" + object +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
