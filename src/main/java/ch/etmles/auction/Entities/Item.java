package ch.etmles.auction.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItem")
    private long id;

    @Column(name = "refItem", unique = true, nullable = false)
    private String reference;

    @Column(name = "nameItem", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "idCategorie", nullable = false)
    @JsonIgnore
    private Category category;

    @Column(name = "descriptionItem", nullable = false)
    private String description;

    @Column(name = "initialPriceItem", nullable = false)
    private BigDecimal initialPrice;

    @Column(name = "lastBidItem")
    private BigDecimal lastBid;

    public Item() {

    }

    public Item(String name, Category category, String description, BigDecimal initialPrice) {
        this();
        this.name = name;
        this.category = category;
        this.description = description;
        this.initialPrice = initialPrice;
        this.lastBid = BigDecimal.ZERO;
    }

    @PrePersist
    @PreUpdate
    public void ensureReference() {
        if (this.reference == null) {
            this.reference = UUID.randomUUID().toString();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("category")
    public Long getCategoryId() {
        return category.getId();
    }

    public Category getCategory() {
        return category;
    }

    // Utiliser un service pour obtenir l'objet Category à partir de l'identifiant
    public void setCategory(Category category) {
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
        if (!(o instanceof Item item)) return false;
        return id == item.id &&
                Objects.equals(reference, item.reference) &&
                Objects.equals(name, item.name) &&
                Objects.equals(category, item.category) &&
                Objects.equals(description, item.description) &&
                Objects.equals(initialPrice, item.initialPrice) &&
                Objects.equals(lastBid, item.lastBid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reference, name, category, description, initialPrice, lastBid);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", reference='" + reference.substring(0, 8) + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", initialPrice=" + initialPrice +
                ", lastBid=" + lastBid +
                '}';
    }
}
