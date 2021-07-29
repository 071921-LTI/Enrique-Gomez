package com.lntinfotech.services;

import java.util.List;

import com.lntinfotech.exceptions.ItemNotFoundException;
import com.lntinfotech.models.Item;

public interface ItemService {
    public abstract Item getItem(int itemId) throws ItemNotFoundException;
    public abstract int addItem(Item item);
    public abstract List<Item> getAllItems();
}
