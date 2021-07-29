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
        // TODO Auto-generated method stub
        return new Item(1, "a cool item", 1.99, false);
    }

    @Override
    public int addItem(Item item) {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public List<Item> getAllItems() {
        return id.getAllItems();
    }
    
}
