package ch.etmles.auction.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBid")
    private long id;

    @ManyToOne
    @JoinColumn(name = "idItem", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "idAppUser", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn (name="idAuction", nullable = false)
    private Auction auction;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "bidTime", nullable = false)
    private LocalDateTime bidTime;

    public Bid() {}

    public Bid(Item item, AppUser appUser, Auction auction, BigDecimal amount, LocalDateTime bidTime) {
        this.item = item;
        this.appUser = appUser;
        this.auction = auction;
        this.amount = amount;
        this.bidTime = bidTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Auction getAuction() {
        return auction;
    }
    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }

}
