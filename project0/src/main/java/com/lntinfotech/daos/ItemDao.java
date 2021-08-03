package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.models.Item;
import com.lntinfotech.models.Offer;

public interface ItemDao {
    public abstract Item getItemById(int itemId);
    public abstract List<Item> getAllItems();
    public abstract int addItem(Item item);
    public abstract int updateItem(Item item);
    public abstract String deleteItem(int itemId);
    public abstract List<Offer> getItemsByOwner(int ownerId);
    public abstract boolean payItem(int item);
}
