package ch.etmles.auction.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private long id ;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category ;

    //@Column(nullable = false)
    //private Utilisateur utilisateur ;

    @Column(nullable = false)
    private String name ;

    private String description ;

    //@Min(value = 0, message = "Le prix initial ne peut pas être négatif")
    @Column(nullable = false)
    private BigDecimal initial_price ;

    private BigDecimal last_bid ;

    private boolean isActive ;

    public Item() {}

    public Item(String name, Category category, String description, BigDecimal initial_price) {

        this.setName(name);
        this.setCategory(category);
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
    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}
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
        if (!(o instanceof Item item)) return false;  // instanceof remplace getClass()
        return id == item.id &&
                Objects.equals(name, item.name) &&
                Objects.equals(category, item.category) &&
                Objects.equals(description, item.description) &&
                Objects.equals(initial_price, item.initial_price) &&
                Objects.equals(last_bid, item.last_bid) &&
                Objects.equals(isActive, item.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.category, this.description, this.initial_price, this.last_bid, this.isActive);
    }

    @Override
    public String toString() {
        return "Item {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", initial_price=" + initial_price +
                ", last_bid=" + last_bid +
                ", isActive=" + isActive +
                '}';
    }
}
