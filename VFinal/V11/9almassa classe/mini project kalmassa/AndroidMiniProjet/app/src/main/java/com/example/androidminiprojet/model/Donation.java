package com.example.androidminiprojet.model;

import java.util.ArrayList;

public class Donation {
    private String titre;
    private ArrayList typedonation;
    private String description;

    public Donation(String titre,ArrayList typedonation, String description) {
        this.titre = titre;
        this.typedonation = typedonation;
        this.description = description;
    }

    public String getTitre() {return titre;}

    public void setTitre(String titre) {this.titre = titre;}

    public ArrayList getTypedonation() {
        return typedonation;
    }

    public String getDescription() {
        return description;
    }

    public void setTypedonation(ArrayList typedonation) {
        this.typedonation = typedonation;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
