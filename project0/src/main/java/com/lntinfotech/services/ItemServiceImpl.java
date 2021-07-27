package com.lntinfotech.services;

import com.lntinfotech.daos.ItemCollection;
import com.lntinfotech.daos.ItemDao;
import com.lntinfotech.exceptions.ItemNotFoundException;
import com.lntinfotech.models.Item;

public class ItemServiceImpl implements ItemService{

    private ItemDao id = new ItemCollection(); 
    @Override
    public Item getItem(int itemId) throws ItemNotFoundException {
        // TODO Auto-generated method stub
        return new Item("a cool item", 1.99, false);
    }

    @Override
    public boolean addItem(Item item) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
