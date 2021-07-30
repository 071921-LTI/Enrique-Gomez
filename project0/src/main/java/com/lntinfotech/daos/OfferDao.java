package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.models.Offer;

public interface OfferDao {
    public abstract Offer getOffer(int offerId);
    public abstract boolean makeOffer(Offer offer);
    public abstract List<Offer> getAllOffers();
}