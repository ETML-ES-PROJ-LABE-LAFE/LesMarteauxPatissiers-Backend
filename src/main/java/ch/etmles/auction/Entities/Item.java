package ch.etmles.auction.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItem")
    private long id;

    @Column(name = "nameItem", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "idCategorie", nullable = false)
    @JsonIgnore
    private Category category;

    @Column(name = "descriptionItem", nullable = false)
    private String description;

    @Column(name="initialPriceItem",nullable = false)
    private BigDecimal initialPrice;

    @Column(name="lastBidItem")
    private BigDecimal lastBid;

    public Item() {}

    public Item(String name, Category category, String description, BigDecimal initialPrice) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.initialPrice = initialPrice;
        this.lastBid = BigDecimal.ZERO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategorie() {
        return category;
    }

    public void setCategorie(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public BigDecimal getLastBid() {
        return lastBid;
    }

    public void setLastBid(BigDecimal lastBid) {
        this.lastBid = lastBid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return id == item.id &&
                Objects.equals(name, item.name) &&
                Objects.equals(category, item.category) &&
                Objects.equals(description, item.description) &&
                Objects.equals(initialPrice, item.initialPrice) &&
                Objects.equals(lastBid, item.lastBid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, description, initialPrice, lastBid);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", initialPrice=" + initialPrice +
                ", lastBid=" + lastBid +
                '}';
    }
}
