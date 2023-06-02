package com.example.androidminiprojet.model;

public class Event {

    private String name;
    private String lieu;
    private String prix;
    private String date_event;
    private String description;

    public Event(String name, String lieu, String prix, String date_event, String description) {
        this.name = name;
        this.lieu = lieu;
        this.prix = prix;
        this.date_event = date_event;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getLieu() {
        return lieu;
    }

    public String getPrix() {
        return prix;
    }

    public String getDate_event() {
        return date_event;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
