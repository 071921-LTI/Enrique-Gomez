package com.lntinfotech.services;

import java.util.List;

import com.lntinfotech.models.Item;
import com.lntinfotech.models.Offer;

public interface OfferService {
    public abstract boolean makeOffer(Offer offer);
    public abstract boolean acceptOffer(Item item, Offer offer);
    public abstract List<Offer> getAllOffers();
}
