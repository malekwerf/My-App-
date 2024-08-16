package com.example.myapp;

public class Agence {
    private int id;
    private String nom_agence;
    private String adresse_agence;
    private String telephone_agence;
    private String gouvernorat;

    // Constructeur
    public Agence(int id, String nom_agence, String adresse_agence, String telephone_agence, String gouvernorat) {
        this.id = id;
        this.nom_agence = nom_agence;
        this.adresse_agence = adresse_agence;
        this.telephone_agence = telephone_agence;
        this.gouvernorat = gouvernorat;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom_agence;
    }

    public void setNom(String nom_agence) {
        this.nom_agence = nom_agence;
    }

    public String getAdresse() {
        return adresse_agence;
    }

    public void setAdresse(String adresse_agence) {
        this.adresse_agence = adresse_agence;
    }

    public String getTelephone() {
        return telephone_agence;
    }

    public void setTelephone(String telephone_agence) {
        this.telephone_agence = telephone_agence;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }
}
