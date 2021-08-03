package com.lntinfotech.services;

import java.util.List;

import com.lntinfotech.models.Offer;

public interface OfferService {
    public abstract boolean makeOffer(Offer offer);
    public abstract boolean acceptOffer(int offerId, Offer offer);
    public abstract List<Offer> getAllOffers();
    public abstract Offer getOffer(int offerId);
    public abstract boolean rejectOffer(int offer);
    public abstract List<Offer> getAcceptedOffersByCustomer(int customerId);
    public abstract List<Offer> getAllPayments();
}
