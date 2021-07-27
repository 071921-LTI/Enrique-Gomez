package com.lntinfotech.daos;

import com.lntinfotech.models.Offer;

public interface OfferDao {
    public abstract Offer getOffer(int offerId);
    public abstract boolean makeOffer(Offer offer);
}
