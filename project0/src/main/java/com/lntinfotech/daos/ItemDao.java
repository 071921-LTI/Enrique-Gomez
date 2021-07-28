package com.lntinfotech.daos;

import com.lntinfotech.models.Item;

public interface ItemDao {
    public abstract Item getItemById(int itemId);
    public abstract boolean addItem(Item item);
    public abstract int updateItem(Item item);
    public abstract int deleteItem(int itemId);
}
