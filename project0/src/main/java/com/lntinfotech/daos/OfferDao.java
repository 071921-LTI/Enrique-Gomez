package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.models.Offer;

public interface OfferDao {
    public abstract Offer getOffer(int offerId);
    public abstract boolean makeOffer(Offer offer);
    public abstract List<Offer> getAllOffers();
    public abstract boolean acceptOffer(int offerId, Offer offer);
    public abstract boolean rejectOffer(int offer);
}