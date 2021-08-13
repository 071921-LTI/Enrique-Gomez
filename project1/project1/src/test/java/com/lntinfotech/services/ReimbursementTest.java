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
public class ReimbursementTest {
    @Mock
    private UserDao ud;

    @InjectMocks
    private ReimbursementService as = new ReimbursementServiceImpl();

    @Test
    public void getAllPendingRequestsSuccess() {

    }

    @Test
    public void getAllPendingRequestsFail() {

    }

    @Test
    public void getRequestsByEmployeeIdSuccess() {

    }

    @Test
    public void getRequestsByEmployeeIdFail() {
        
    }

    @Test
    public void getAllResolvedRequestsSuccess() {

    }

    @Test
    public void getAllResolvedRequestsFail() {

    }

    @Test
    public void submitReimbursementRequestSuccess() {

    }

    @Test
    public void submitReimbursementRequestFail() {

    }

    @Test
    public void getPendingReimbursementsByEmployeeIdSuccess() {

    }
    
    @Test
    public void getPendingReimbursementsByEmployeeIdFail() {

    }

    @Test
    public void getResolvedReimbursementsByEmployeeIdSuccess() {

    }

    @Test
    public void getResolvedReimbursementsByEmployeeIdFail() {
        
    }
}