package com.lntinfotech.models;

import java.util.Objects;

public class Offer {

    private int id;
    private int customerId;
	private int itemId;
    private double offerAmount;
    private boolean isAccepted;
    private Item item;
    private User user;

    public Offer(int id) {
        super();

        this.id = id;
    }

    public Offer(int customerId, int itemId, double offerAmount) {
        super();

        this.customerId = customerId;
        this.itemId = itemId;
        this.offerAmount = offerAmount;
    }

    public Offer(int id, int customerId, int itemId, double offerAmount, boolean isAccepted, Item item, User user) {
        super();

        this.id = id;
        this.customerId = customerId;
        this.itemId = itemId;
        this.offerAmount = offerAmount;
        this.isAccepted = isAccepted;
        this.item = item;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(double offerAmount) {
        this.offerAmount = offerAmount;
    }

    public boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(customerId, id, isAccepted, item, itemId, offerAmount, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		return customerId == other.customerId && id == other.id && isAccepted == other.isAccepted
				&& Objects.equals(item, other.item) && itemId == other.itemId
				&& Double.doubleToLongBits(offerAmount) == Double.doubleToLongBits(other.offerAmount)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", customerId=" + customerId + ", itemId=" + itemId + ", offerAmount=" + offerAmount
				+ ", isAccepted=" + isAccepted + ", item=" + item + ", user=" + user + "]";
	}
    
}
