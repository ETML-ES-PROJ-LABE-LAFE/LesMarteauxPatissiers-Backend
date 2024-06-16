package ch.etmles.auction.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAuction")
    private long id;

    @ManyToOne
    @JoinColumn(name = "idItem", nullable = false)
    private Item item;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bid> bids = new ArrayList<>();

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @Column(name = "desactivationDate")
    private LocalDateTime desactivationDate;

    public Auction() {}

    public Auction(Item item) {
        this.item = item;
        this.isActive = true;
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
    public List<Bid> getBids() {
        return bids;
    }
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
    public void addBid(Bid bid) {
        this.bids.add(bid);
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        this.isActive = active;
    }

    public LocalDateTime getDesactivationDate() {
        return desactivationDate;
    }

    public void setDesactivationDate(LocalDateTime desactivationDate) {
        this.desactivationDate = desactivationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return id == auction.id && isActive == auction.isActive && Objects.equals(item, auction.item) && Objects.equals(bids, auction.bids) && Objects.equals(desactivationDate, auction.desactivationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, bids, isActive, desactivationDate);
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", item=" + item +
                ", bids=" + bids +
                ", isActive=" + isActive +
                ", desactivationDate=" + desactivationDate +
                '}';
    }
}

