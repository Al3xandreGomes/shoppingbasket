package com.interview.shoppingbasket;

public interface PricingService {
    default double getPrice(String itemCode) {
        double itemPrice;
        switch (itemCode) {
            case "productCode1":
                itemPrice = 10.0d;
                break;
            case "productCode2":
                itemPrice = 20.0d;
                break;
            default:
                itemPrice = 1.0;
                break;
        }
        return itemPrice;
    }
}
