package ch.etmles.auction.Entities;

//import javax.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "id_lots")
    private long id ;

    //@OneToOne
    //@Column(nullable = false)
    //private Utilisateur utilisateur ;

    @Column(name = "nom",nullable = false)
    private String name ;

    @ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false)
    @JsonIgnore
    private Categorie categorie ;

    private String description ;

    @Column(nullable = false)
    //@Min(value = 0, message = "Le prix initial ne peut pas être négatif")
    private BigDecimal initial_price ;

    private BigDecimal last_bid ;

    //todo: getter and setter pour limage et verifier le datatype....
    private String image ;

    private boolean isActive ;

    public Item() {}

    public Item(String name, Categorie categorie, String description, BigDecimal initial_price) {

        this.setName(name);
        this.setCategorie(categorie);
        this.setDescription(description);
        this.setInitial_price(initial_price);
        this.setLast_bid(BigDecimal.ZERO);
        this.setActive(true);

    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Categorie getCategorie() {return categorie;}
    public void setCategorie(Categorie categorie) {this.categorie = categorie;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public BigDecimal getInitial_price() {return initial_price;}
    public void setInitial_price(BigDecimal price) {this.initial_price = price;}
    public BigDecimal getLast_bid() {return last_bid;}
    public void setLast_bid(BigDecimal price) {this.last_bid = price;}
    public boolean isActive() {return isActive;}
    public void setActive(boolean isActive) {this.isActive = isActive;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return id == item.id &&
                isActive == item.isActive &&  // For boolean, direct comparison is fine
                Objects.equals(name, item.name) &&
                Objects.equals(categorie, item.categorie) &&
                Objects.equals(description, item.description) &&
                Objects.equals(initial_price, item.initial_price) &&
                Objects.equals(last_bid, item.last_bid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.categorie, this.description, this.initial_price, this.last_bid, this.isActive);
    }

    @Override
    public String toString() {
        return "Item {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + categorie +
                ", description='" + description + '\'' +
                ", initial_price=" + initial_price +
                ", last_bid=" + last_bid +
                ", isActive=" + isActive +
                '}';
    }
}
