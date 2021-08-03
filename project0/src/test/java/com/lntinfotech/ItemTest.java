package com.lntinfotech;

import com.lntinfotech.daos.ItemDao;
import com.lntinfotech.exceptions.ItemNotFoundException;
import com.lntinfotech.models.Item;
import com.lntinfotech.services.ItemService;
import com.lntinfotech.services.ItemServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

public class ItemTest {
    
    private static ItemService itemService;

    @BeforeAll
    public static void setUp() {
        itemService = new ItemServiceImpl();
    }

    @AfterAll
    public static void tearDown() {
    }

    @Order(1)
    @Test
    public void addItem() {
        int result = itemService.addItem(new Item("random thing", 0));
        assertNotEquals(result, -1);
        itemService.removeItem(result);
    }

    @Order(2)
    @Test
    public void removeItem() {
        int itemId = itemService.addItem(new Item("random thing", 0));
        assertEquals(itemService.removeItem(itemId), "random thing");
    }

    @Order(3)
    @Test
    public void getItem() throws ItemNotFoundException {
        int itemId = itemService.addItem(new Item("random thing", 0));
        assertEquals(itemService.getItem(itemId), new Item(itemId, "random thing", 0, false));
        itemService.removeItem(itemId);
    }
}
