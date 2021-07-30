package com.lntinfotech.services;

import java.util.List;

import com.lntinfotech.daos.OfferDao;
import com.lntinfotech.daos.OfferPostgres;
import com.lntinfotech.models.Item;
import com.lntinfotech.models.Offer;

public class OfferServiceImpl implements OfferService{

    private OfferDao od = new OfferPostgres();
    @Override
    public boolean makeOffer(Offer offer) {
        
        return od.makeOffer(offer);
    }

    @Override
    public boolean acceptOffer(Item item, Offer offer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Offer> getAllOffers() {
        return od.getAllOffers();
    }
    
}
