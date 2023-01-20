package com.interview.shoppingbasket;

import lombok.*;

/**
 * The type Promotion.
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

    /**
     * The constant FIFTY_PERCENTAGE_VALUE.
     */
    public static final double FIFTY_PERCENTAGE_VALUE = 0.5;
    /**
     * The constant TEN_PERCATANGE_VALUE.
     */
    public static final double TEN_PERCATANGE_VALUE = 0.1;
    // Not yet implemented
    private String productCode;
    private PromotionType promotionType;


    /**
     * The enum Promotion type.
     */
    public enum PromotionType {
        /**
         * The Two for one.
         */
        TWO_FOR_ONE {
            @Override
            double getPromotionValue(BasketItem basketItem, double price) {
                double discountedPrice = (price * basketItem.getQuantity()) * FIFTY_PERCENTAGE_VALUE;
                if (basketItem.getQuantity() % 2 == 1) {
                    discountedPrice += FIFTY_PERCENTAGE_VALUE * price;
                }
                return discountedPrice;
            }
        },
        /**
         * The Fifty percent.
         */
        FIFTY_PERCENT {
            @Override
            double getPromotionValue(BasketItem basketItem, double price) {
                return (price * basketItem.getQuantity()) * FIFTY_PERCENTAGE_VALUE;
            }
        },

        /**
         * The Ten percent.
         */
        TEN_PERCENT {
            @Override
            double getPromotionValue(BasketItem basketItem, double price) {
                return price - (price * basketItem.getQuantity() * TEN_PERCATANGE_VALUE);
            }
        },

        /**
         * The Without promotion.
         */
        WITHOUT_PROMOTION {
            @Override
            double getPromotionValue(BasketItem basketItem, double price) {
                return basketItem.getQuantity() * price;
            }
        };

        /**
         * Gets promotion value.
         *
         * @param basketItem the basket item
         * @param price      the price
         * @return the promotion value
         */
        abstract double getPromotionValue(BasketItem basketItem, double price);
    }


}
