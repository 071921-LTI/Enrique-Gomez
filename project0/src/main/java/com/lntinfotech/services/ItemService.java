package com.lntinfotech.services;

import java.util.List;

import com.lntinfotech.exceptions.ItemNotFoundException;
import com.lntinfotech.models.Item;
import com.lntinfotech.models.Offer;

public interface ItemService {
    public abstract Item getItem(int itemId) throws ItemNotFoundException;
    public abstract int addItem(Item item);
    public abstract List<Item> getAllItems();
    public abstract String removeItem(int itemId);
    public abstract List<Offer> getItemsByOwner(int ownerId);
    public abstract boolean payForItem(int itemId);
}
