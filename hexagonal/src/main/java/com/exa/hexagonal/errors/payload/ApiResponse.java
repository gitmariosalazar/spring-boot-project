package com.exa.hexagonal.errors.payload;


import java.util.Date;

public class ApiResponse {
    private Date time=new Date();
    private String message;
    private String url;

    public ApiResponse(String message, String url){
        this.message=message;
        this.url=url.replace("uri=","");
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
