package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;

class CheckoutContext {
    private Basket basket;
    private double retailPriceTotal = 0.0;
    private List<Promotion> promotionList = new ArrayList<>();

    public void setRetailPriceTotal(double retailPriceTotal) {
        this.retailPriceTotal = retailPriceTotal;
    }

    CheckoutContext(Basket basket) {
        this.basket = basket;
    }

    public PaymentSummary paymentSummary() {
        return new PaymentSummary(retailPriceTotal);
    }

    public Basket getBasket() {
        return basket;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList.clear();
        this.promotionList.addAll(promotionList);
    }

    public List<Promotion> getPromotionList() {
        return this.promotionList;
    }
}
