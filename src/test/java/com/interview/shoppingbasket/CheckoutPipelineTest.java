package com.interview.shoppingbasket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CheckoutPipelineTest {

    CheckoutPipeline checkoutPipeline;

    @Mock
    Basket basket;

    @Mock
    CheckoutStep checkoutStep1;

    @Mock
    CheckoutStep checkoutStep2;


    private BasketConsolidationCheckoutStep basketConsolidationCheckoutStep;
    private PromotionCheckoutStep promotionCheckoutStep;
    private RetailPriceCheckoutStep retailPriceCheckoutStep;
    private PromotionsService promotionsService;
    private PricingService pricingService;

    @BeforeEach
    void setup() {
        checkoutPipeline = new CheckoutPipeline();

        promotionsService = Mockito.mock(PromotionsService.class);
        pricingService = Mockito.mock(PricingService.class);

        basketConsolidationCheckoutStep = new BasketConsolidationCheckoutStep();
        promotionCheckoutStep = new PromotionCheckoutStep(promotionsService);
        retailPriceCheckoutStep = new RetailPriceCheckoutStep(pricingService);

        basket = new Basket();
    }

    @Test
    void returnZeroPaymentForEmptyPipeline() {
        PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);

        assertEquals(paymentSummary.getRetailTotal(), 0.0);
    }

    @Test
    void executeAllPassedCheckoutSteps() {
        // Exercise - implement testing passing through all checkout steps
        basket.add("productCode1", "myProduct1", 2);

        Promotion promotion = new Promotion("productCode1", Promotion.PromotionType.TWO_FOR_ONE);
        List<Promotion> promotions = List.of(promotion);

        when(pricingService.getPrice(any())).thenReturn(10d);
        when(promotionsService.getPromotions(any())).thenReturn(promotions);

        checkoutPipeline.addStep(basketConsolidationCheckoutStep);
        checkoutPipeline.addStep(promotionCheckoutStep);
        checkoutPipeline.addStep(retailPriceCheckoutStep);

        // Two for One
        PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);
        double total = paymentSummary.getRetailTotal();
        assertEquals(10d, total);

    }

    @Test
    void executeAllPassedCheckoutSteps50PercentageTest() {
        // Exercise - implement testing passing through all checkout steps
        basket.add("productCode1", "myProduct1", 2);

        Promotion promotion = new Promotion("productCode1", Promotion.PromotionType.FIFTY_PERCENT);
        List<Promotion> promotions = List.of(promotion);

        when(pricingService.getPrice(any())).thenReturn(25d);
        when(promotionsService.getPromotions(any())).thenReturn(promotions);

        checkoutPipeline.addStep(basketConsolidationCheckoutStep);
        checkoutPipeline.addStep(promotionCheckoutStep);
        checkoutPipeline.addStep(retailPriceCheckoutStep);


        // 50% Discount
        PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);
        double total = paymentSummary.getRetailTotal();
        assertEquals(25d, total);
    }

    @Test
    void executeAllPassedCheckoutSteps10PercentageTest() {
        // Exercise - implement testing passing through all checkout steps
        basket.add("productCode1", "myProduct1", 1);

        Promotion promotion = new Promotion("productCode1", Promotion.PromotionType.TEN_PERCENT);
        List<Promotion> promotions = List.of(promotion);

        when(pricingService.getPrice(any())).thenReturn(100d);
        when(promotionsService.getPromotions(any())).thenReturn(promotions);

        checkoutPipeline.addStep(basketConsolidationCheckoutStep);
        checkoutPipeline.addStep(promotionCheckoutStep);
        checkoutPipeline.addStep(retailPriceCheckoutStep);


        // 10% Discount
        PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);
        double total = paymentSummary.getRetailTotal();
        assertEquals(90d, total);

    }

    @Test
    void executeAllPassedCheckoutStepsWithoutDiscountTest() {
        // Exercise - implement testing passing through all checkout steps
        basket.add("productCode1", "myProduct1", 4);

        Promotion promotion = new Promotion("productCode1", Promotion.PromotionType.WITHOUT_PROMOTION);
        List<Promotion> promotions = List.of(promotion);

        when(pricingService.getPrice(any())).thenReturn(10d);
        when(promotionsService.getPromotions(any())).thenReturn(promotions);

        checkoutPipeline.addStep(basketConsolidationCheckoutStep);
        checkoutPipeline.addStep(promotionCheckoutStep);
        checkoutPipeline.addStep(retailPriceCheckoutStep);

        // 10% Discount
        PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);
        double total = paymentSummary.getRetailTotal();
        assertEquals(40d, total);

    }

}
