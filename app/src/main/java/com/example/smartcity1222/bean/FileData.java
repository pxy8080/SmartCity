package com.example.smartcity1222.bean;

public class FileData {
    /**
     * code : 200
     * fileName : test.txt
     * url : /profile/upload/file/test.txt
     * msg : 操作成功
     */

    private int code;
    private String fileName;
    private String url;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
