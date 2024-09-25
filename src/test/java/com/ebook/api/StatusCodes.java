package com.ebook.api;

public enum StatusCodes {
    CODE_200(200, "HTTP/1.1 200 OK"),
    CODE_405(405, "HTTP/1.1 405 Method Not Allowed");

    private final int statusCode;
    private final String statusLine;

    StatusCodes(int statusCode, String statusLine) {
        this.statusCode = statusCode;
        this.statusLine = statusLine;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public String  getStatusLine(){
        return statusLine;
    }
}
