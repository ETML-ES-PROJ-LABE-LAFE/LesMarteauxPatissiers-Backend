package ch.etmles.auction.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Entity
//@JsonPropertyOrder({ "idCategorie", "nom", "sousCategories", "lots" })
public class Categorie {

    @Id
    @GeneratedValue
    @Column(name = "id_categorie")
    private long id;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy="categorieParent")
    @JsonManagedReference
    private List<Categorie> sousCategories;

    @ManyToOne
    @JoinColumn(name = "Det_id_categorie", referencedColumnName = "id_categorie")
    @JsonBackReference
    private Categorie categorieParent;

    @OneToMany(mappedBy="categorie")
    @JsonManagedReference
    private List<Item> lots;

    public Categorie() {}

    public Categorie(String nom) {
        this.nom = nom;
    }


    public long getIdCategorie() {
        return id;
    }

    public void setIdCategorie(long idCategorie) {
        this.id = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie getCategorieParent() {
        return categorieParent;
    }

    public void setCategorieParent(Categorie categorieParent) {
        this.categorieParent = categorieParent;
    }

    public List<Categorie> getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(List<Categorie> sousCategories) {
        this.sousCategories = sousCategories;
    }

    public List<Item> getLots() {
        return lots;
    }

    public void setLots(List<Item> lots) {
        this.lots = lots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorie that = (Categorie) o;
        if (id != that.id) return false;
        if (!Objects.equals(nom, that.nom)) return false;
        if (!Objects.equals(categorieParent, that.categorieParent)) return false;
        if (!Objects.equals(sousCategories, that.sousCategories)) return false;
        if (!Objects.equals(lots, that.lots)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (categorieParent != null ? categorieParent.hashCode() : 0);
        result = 31 * result + (sousCategories != null ? sousCategories.hashCode() : 0);
        result = 31 * result + (lots != null ? lots.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idCategorie=" + id +
                ", nom='" + nom + '\'' +
                ", categorieParent=" + (categorieParent != null ? categorieParent.getNom() : "aucune") +
                ", sousCategories=" + (sousCategories != null ? sousCategories.stream().map(Categorie::getNom).collect(Collectors.toList()) : "aucune") +
                ", lots=" + (lots != null ? lots.size() : 0) + " éléments" +
                '}';
    }

}
