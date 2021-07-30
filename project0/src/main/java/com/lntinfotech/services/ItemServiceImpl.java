package com.lntinfotech.services;

import com.lntinfotech.daos.ItemPostgres;

import java.util.List;

import com.lntinfotech.daos.ItemDao;
import com.lntinfotech.exceptions.ItemNotFoundException;
import com.lntinfotech.models.Item;

public class ItemServiceImpl implements ItemService{

    private ItemDao id = new ItemPostgres();
    @Override
    public Item getItem(int itemId) throws ItemNotFoundException {
        return id.getItemById(itemId);
    }

    @Override
    public int addItem(Item item) {
        return id.addItem(item);
    }

    @Override
    public List<Item> getAllItems() {
        return id.getAllItems();
    }
    
}
