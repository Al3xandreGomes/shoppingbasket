package com.interview.shoppingbasket;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

    // Not yet implemented
    private String productCode;
    private PromotionType promotionType;


    public enum PromotionType {
        TWO_FOR_ONE {
            @Override
            double getPromotionValue(BasketItem basketItem, double price) {
                double discountedPrice = (price * basketItem.getQuantity()) * 0.5;
                //if quantity is odd needs more 0.5 individual product
                if (basketItem.getQuantity() % 2 == 1) {
                    discountedPrice += 0.5 * price;
                }
                return discountedPrice;
            }
        },
        FIFTY_PERCENT {
            @Override
            double getPromotionValue(BasketItem basketItem, double price) {
                return (price * basketItem.getQuantity()) * 0.5;
            }
        },

        TEN_PERCENT {
            @Override
            double getPromotionValue(BasketItem basketItem, double price) {
                return price - (price * basketItem.getQuantity() * 0.1);
            }
        },

        WITHOUT_PROMOTION {
            @Override
            double getPromotionValue(BasketItem basketItem, double price) {
                return basketItem.getQuantity() * price;
            }
        };

        abstract double getPromotionValue(BasketItem basketItem, double price);
    }


}
