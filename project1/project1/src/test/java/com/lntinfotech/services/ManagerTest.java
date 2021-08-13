package com.lntinfotech.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lntinfotech.daos.UserDao;

@ExtendWith(MockitoExtension.class)
public class ManagerTest {
    @Mock
    private UserDao ud;

    @InjectMocks
    private ManagerService as = new ManagerServiceImpl();

    @Test
    public void resolveRequestSuccess() {

    }

    @Test
    public void resolveRequestFail() {
        
    }

}