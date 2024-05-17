package ch.etmles.auction.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;

//TODO A class with a french name ?
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue
    private long id_utilisateur;
    private String nom;
    private String prenom;
    private String adresse_rue;
    private String adresse_localite;
    private String email;
    private String telephone;
    private String password;
    @OneToMany
    private ArrayList<Item> listeItems_ventes;
    @OneToMany
    private ArrayList<Item> listeItems_achats;

    public Utilisateur() {}
    public Utilisateur(String nom, String prenom, String adresse_rue, String adresse_localite, String email, String telephone, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse_rue = adresse_rue;//TODO naming convention !!!!!
        this.adresse_localite = adresse_localite;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.listeItems_ventes = new ArrayList<>();
        this.listeItems_achats = new ArrayList<>();
    }
}
