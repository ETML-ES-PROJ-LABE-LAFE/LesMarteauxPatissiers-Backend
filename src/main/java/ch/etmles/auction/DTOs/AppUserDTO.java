package ch.etmles.auction.DTOs;
import java.math.BigDecimal;

public class AppUserDTO {

    private long id;
    private String nom;
    private String prenom;
    private BigDecimal credit;

    public AppUserDTO() {}

    public AppUserDTO(long id, String nom, String prenom, BigDecimal credit) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.credit = credit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }
}

