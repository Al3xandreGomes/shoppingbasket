package com.interview.shoppingbasket;

import lombok.Data;

import java.util.Objects;

@Data
public class BasketItem {
    private String productCode;
    private String productName;
    private int quantity;
    private double productRetailPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return productCode.equals(that.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode);
    }
}
