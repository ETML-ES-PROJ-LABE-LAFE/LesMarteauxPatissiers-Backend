package ch.etmles.auction.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private long id;
    @Column(name="nameUser")
    private String nom;
    @Column(name="firstNameUser")
    private String prenom;
    @Column(name="creditUser")
    private BigDecimal credit;

    public AppUser() {}

    public AppUser(String nom, String prenom, BigDecimal credit) {
        this.nom = nom;
        this.prenom = prenom;
        this.credit = credit;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getPrenom() {
        return prenom;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }
    public BigDecimal getCredit() {
        return credit;
    }

}
