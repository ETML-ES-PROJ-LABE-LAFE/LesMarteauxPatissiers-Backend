package ch.etmles.auction.DTOs;

import java.time.LocalDateTime;
import java.util.List;

public class AuctionDTO {

    private long id;
    private long itemId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Long> bids;
    private boolean isActive;
    private LocalDateTime desactivatedTime;

    // Getters and Setters
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
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public List<Long> getBids() {
        return bids;
    }
    public void setBids(List<Long> bids) {
        this.bids = bids;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    public LocalDateTime getDesactivatedTime() {
        return desactivatedTime;
    }
    public void setDesactivatedTime(LocalDateTime desactivatedTime) {
        this.desactivatedTime = desactivatedTime;
    }
}




