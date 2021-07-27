package com.lntinfotech.models;

import java.util.Objects;

public class Offer {

    private int offeredFor;
	private int itemId;
    private float offerAmount;
    private boolean isAccepted;

    public Offer(int offeredFor, int itemId, float offerAmount, boolean isAccepted) {
        super();

        this.offeredFor = offeredFor;
        this.itemId = itemId;
        this.offerAmount = offerAmount;
        this.isAccepted = isAccepted;
    }

    public int getOfferedFor() {
        return offeredFor;
    }

    public void setOfferedFor(int offeredFor) {
        this.offeredFor = offeredFor;
    }

    public int getitemId() {
        return itemId;
    }

    public void setitemId(int itemId) {
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
		return Objects.hash(isAccepted, offerAmount, itemId, offeredFor);
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
		return isAccepted == other.isAccepted
				&& Float.floatToIntBits(offerAmount) == Float.floatToIntBits(other.offerAmount)
				&& Objects.equals(itemId, other.itemId) && Objects.equals(offeredFor, other.offeredFor);
	}

	@Override
	public String toString() {
		return "Offer [offeredFor=" + offeredFor + ", itemId=" + itemId + ", offerAmount=" + offerAmount
				+ ", isAccepted=" + isAccepted + "]";
	}
    
}
