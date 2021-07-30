package com.lntinfotech.models;

import java.util.Objects;

public class Item {

    private int id;
    private String itemName;
    private double minimumOffer;
    private boolean isPurchased;

    public Item(int id) {
        super();

        this.id = id;
    }

    public Item(String itemName, double minimumOffer) {
        super();
        this.itemName = itemName;
        this.minimumOffer = minimumOffer;
    }
    public Item(int id, String itemName, double minimumOffer, boolean isPurchased) {
        super();

        this.id = id;
        this.itemName = itemName;
        this.minimumOffer = minimumOffer;
        this.isPurchased = isPurchased;
    }

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

    public void setMinimumOffer(double minimumOffer) {
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
		return Objects.hash(id, isPurchased, itemName, minimumOffer);
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
		return id == other.id && isPurchased == other.isPurchased && Objects.equals(itemName, other.itemName)
				&& Double.doubleToLongBits(minimumOffer) == Double.doubleToLongBits(other.minimumOffer);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", minimumOffer=" + minimumOffer + ", isPurchased="
				+ isPurchased + "]";
	}

}
