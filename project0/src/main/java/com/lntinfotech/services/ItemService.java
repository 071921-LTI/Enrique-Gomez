package com.lntinfotech.services;

import com.lntinfotech.exceptions.ItemNotFoundException;
import com.lntinfotech.models.Item;

public interface ItemService {
    public abstract Item getItem(int itemId) throws ItemNotFoundException;
    public abstract boolean addItem(Item item);
}
