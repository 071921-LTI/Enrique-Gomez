package com.lntinfotech.daos;

import com.lntinfotech.models.Item;

public interface ItemDao {
    public abstract Item getItem(int itemId);
    public abstract boolean addItem(Item item);
}
