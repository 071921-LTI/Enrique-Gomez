package com.lntinfotech.models;

import java.util.Objects;

public class Item {

    private String itemName;
    private double minimumOffer;
    private boolean isPurchased;

    public Item(String itemName, double minimumOffer, boolean isPurchased) {
        super();

        this.itemName = itemName;
        this.minimumOffer = minimumOffer;
        this.isPurchased = isPurchased;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getMinimumOffer() {
        return minimumOffer;
    }

    public void setMinimumOffer(float minimumOffer) {
        this.minimumOffer = minimumOffer;
    }

    public boolean getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

	@Override
	public int hashCode() {
		return Objects.hash(isPurchased, itemName, minimumOffer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return isPurchased == other.isPurchased && Objects.equals(itemName, other.itemName)
				&& Double.doubleToLongBits(minimumOffer) == Double.doubleToLongBits(other.minimumOffer);
	}

	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", minimumOffer=" + minimumOffer + ", isPurchased=" + isPurchased + "]";
	}

}
