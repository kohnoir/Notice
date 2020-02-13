package com.example.notice;

import java.text.DateFormat;
import java.util.Date;

public class Note {
    private String textHeading;
    private String textBody;
    private String currentTime;

    public Note(String textHeading, String textBody, String currentTime) {
        this.textHeading = textHeading;
        this.textBody = textBody;
        this.currentTime = currentTime;
    }

    public String getTextHeading() {
        return textHeading;
    }

    public void setTextHeading(String textHeading) {
        this.textHeading = textHeading;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
