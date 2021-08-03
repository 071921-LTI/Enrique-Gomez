package com.lntinfotech;

import java.util.ArrayList;
import java.util.List;

import com.lntinfotech.daos.ItemDao;
import com.lntinfotech.models.Item;
import com.lntinfotech.models.Offer;
import com.lntinfotech.models.User;
import com.lntinfotech.services.ItemService;
import com.lntinfotech.services.ItemServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class MockitoItemTest {
    
    @Mock
    private ItemDao id;

    @InjectMocks
    private ItemService is = new ItemServiceImpl();

    @Test
    public void getAllItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, "random thing", 50, false));
        Mockito.when(id.getAllItems()).thenReturn(items);
        assertEquals(items, is.getAllItems());
    }

    @Test
    public void removeItem() {
        Mockito.when(id.deleteItem(0)).thenReturn("random thing");
        assertEquals("random thing", is.removeItem(0));
    }

    @Test
    public void getItemsByOwner() {
        List<Offer> items = new ArrayList<>();
        items.add(new Offer(1, 1, 1, 50, true, new Item(1, "random thing", 50, true), new User(1, "randomguy", "", "customer")));
        Mockito.when(id.getItemsByOwner(0)).thenReturn(items);
        assertEquals(items, is.getItemsByOwner(0));
    }
}
