package com.interview.shoppingbasket;

public class PromotionCheckoutStep implements CheckoutStep {

    private final PromotionsService promotionsService;

    public PromotionCheckoutStep(PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        checkoutContext.setPromotionList(promotionsService.getPromotions(checkoutContext.getBasket()));
    }
}
