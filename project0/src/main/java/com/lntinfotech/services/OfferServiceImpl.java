package com.lntinfotech.services;

import java.util.List;

import com.lntinfotech.daos.OfferDao;
import com.lntinfotech.daos.OfferPostgres;
import com.lntinfotech.models.Offer;

public class OfferServiceImpl implements OfferService{

    private OfferDao od = new OfferPostgres();
    @Override
    public boolean makeOffer(Offer offer) {
        
        return od.makeOffer(offer);
    }

    @Override
    public boolean acceptOffer(int offerId, Offer offer) {
        return od.acceptOffer(offerId, offer);
    }

    @Override
    public List<Offer> getAllOffers() {
        return od.getAllOffers();
    }

    @Override
    public Offer getOffer(int offerId) {
        return od.getOffer(offerId);
    }

    @Override
    public boolean rejectOffer(int offer) {
        return od.rejectOffer(offer);
    }
    
}
