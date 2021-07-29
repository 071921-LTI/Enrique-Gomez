package com.lntinfotech.models;

import java.util.Objects;

public class Offer {

    private int id;
    private int customerId;
	private int itemId;
    private float offerAmount;
    private boolean isAccepted;

    public Offer(int id) {
        super();

        this.id = id;
    }

    public Offer(int id, int customerId, int itemId, float offerAmount, boolean isAccepted) {
        super();

        this.id = id;
        this.customerId = customerId;
        this.itemId = itemId;
        this.offerAmount = offerAmount;
        this.isAccepted = isAccepted;
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

    public float getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(float offerAmount) {
        this.offerAmount = offerAmount;
    }

    public boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(customerId, id, isAccepted, itemId, offerAmount);
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
				&& itemId == other.itemId
				&& Float.floatToIntBits(offerAmount) == Float.floatToIntBits(other.offerAmount);
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", customerId=" + customerId + ", itemId=" + itemId + ", offerAmount=" + offerAmount
				+ ", isAccepted=" + isAccepted + "]";
	}
    
}
