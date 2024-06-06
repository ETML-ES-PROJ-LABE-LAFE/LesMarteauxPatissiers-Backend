package ch.etmles.auction.DTOs;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BidDTO {

    private long id;
    private long itemId;
    private long appUserId;
    private BigDecimal amount;
    private LocalDateTime bidTime;

    public BidDTO(){}

    public BidDTO(long id, long itemId, long appUserId, BigDecimal amount, LocalDateTime bidTime) {
        this.id = id;
        this.itemId = itemId;
        this.appUserId = appUserId;
        this.amount = amount;
        this.bidTime = bidTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(long appUserId) {
        this.appUserId = appUserId;
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
