package ch.etmles.auction.DTOs;

import java.math.BigDecimal;

public class ItemDTO {
    private long id;
    private String reference;
    private String name;
    private long categoryId;
    private String description;
    private BigDecimal initialPrice;
    private BigDecimal lastBid;

    // Getters and Setters
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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
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
}
