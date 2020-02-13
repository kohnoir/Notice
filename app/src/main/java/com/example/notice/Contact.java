package com.example.notice;

public class Contact {
    String description;
    String name;
    String number;

    public Contact(String description, String name, String number) {
        this.description = description;
        this.name = name;
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
