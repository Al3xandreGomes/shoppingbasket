package com.interview.shoppingbasket;

import java.util.List;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private double retailTotal;
    private double finalPriceWithPromotion;

    public RetailPriceCheckoutStep(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        List<Promotion> promotions = checkoutContext.getPromotionList();
        retailTotal = 0.0;

        for (BasketItem basketItem : basket.getItems()) {
            int quantity = basketItem.getQuantity();
            double price = pricingService.getPrice(basketItem.getProductCode());
            basketItem.setProductRetailPrice(price);
            retailTotal += quantity * price;

            finalPriceWithPromotion = promotions.stream()
                    .filter(p -> p.getProductCode().equals(basketItem.getProductCode()))
                    .findFirst().map(m -> applyPromotion(m, basketItem, price))
                    .orElse(retailTotal);
        }

        checkoutContext.setRetailPriceTotal(finalPriceWithPromotion);
    }

    public double applyPromotion(Promotion promotion, BasketItem item, double price) {
        /*
         * Implement applyPromotion method
         */
        return promotion.getPromotionType().getPromotionValue(item, price);

    }
}
