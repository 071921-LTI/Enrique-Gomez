package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.models.Item;

public interface ItemDao {
    public abstract Item getItemById(int itemId);
    public abstract List<Item> getAllItems();
    public abstract int addItem(Item item);
    public abstract int updateItem(Item item);
    public abstract int deleteItem(int itemId);
}
