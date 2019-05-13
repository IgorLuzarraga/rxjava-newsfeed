package com.example.springboot.vaadin.rxjavanewsfeed.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class NewsMessage {
    private String dateTime;
    private String message;
    private LocalDateTime localDateTime;

    public NewsMessage(LocalDateTime localDateTime, String message) {
        this.localDateTime = localDateTime;
        this.message = message;

        formatDateTime();
    }

    public String getNewsMessage(){
        return (dateTime + " --> " + getMessage());
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsMessage that = (NewsMessage) o;
        return Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(message, that.message) &&
                Objects.equals(localDateTime, that.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, message, localDateTime);
    }

    @Override
    public String toString() {
        return "NewsMessage{" +
                "dateTime='" + dateTime + '\'' +
                ", message='" + message + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }

    private void formatDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTime = localDateTime.format(formatter);
    }
}
