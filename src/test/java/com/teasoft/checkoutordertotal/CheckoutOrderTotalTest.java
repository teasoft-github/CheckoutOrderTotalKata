package com.teasoft.checkoutordertotal;

import org.junit.Assert;
import org.junit.Test;

public class CheckoutOrderTotalTest {
    private static final double PRICE_MAX_DELTA = 0.001;
    private final CheckoutOrderTotal checkoutOrderTotal = new CheckoutOrderTotal();

    @Test
    public void canAddMultipleItemsToInventory() {
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Ketchup", 3.00);
        Assert.assertEquals(3.00, this.checkoutOrderTotal.getInventoryItemPrice("Ketchup"), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canScanMultipleItems() {
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Ketchup", 3.00);
        this.checkoutOrderTotal.addItemToOrder("Soup");
        this.checkoutOrderTotal.addItemToOrder("Ketchup");
        this.checkoutOrderTotal.addItemToOrder("Soup");
        Assert.assertEquals(5.00, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canScanMultipleByWeightItem() {
        this.checkoutOrderTotal.addSoldByWeightItemToInventory("Ground Beef", 4.50);
        this.checkoutOrderTotal.addSoldByWeightItemToInventory("Bananas", 0.60);
        this.checkoutOrderTotal.addItemToOrder("Ground Beef", 1.5);
        this.checkoutOrderTotal.addItemToOrder("Bananas", 1.2);
        Assert.assertEquals(7.47, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddMarkdown() {
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Ketchup", 3.00);
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Mayo", 5.00);
        this.checkoutOrderTotal.addMarkdownToInventoryItem("Soup", 0.20);
        this.checkoutOrderTotal.addMarkdownToInventoryItem("Ketchup", 0.90);
        this.checkoutOrderTotal.addItemToOrder("Soup");
        this.checkoutOrderTotal.addItemToOrder("Ketchup");
        this.checkoutOrderTotal.addItemToOrder("Mayo");
        this.checkoutOrderTotal.addItemToOrder("Soup");
        Assert.assertEquals(8.70, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddBuyThreeGetTwoAt30PercentOffSpecial() {
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addBuyNGetMAtXOffSpecial("Soup", 3, 2, 0.30);
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 1.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 2.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 3.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.70, total 3.70
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.70, total 4.40
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 5.40
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 6.40
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 7.40
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.70, total 8.10
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.70, total 8.80
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 9.80
        Assert.assertEquals(9.80, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddBuyOneGetOneFreeSpecial() {
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addBuyNGetMAtXOffSpecial("Soup", 1, 1, 1.00);
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 1.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.00, total 1.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 2.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.00, total 2.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 3.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.00, total 3.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 4.00
        Assert.assertEquals(4.00, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddBuyTwoGetOneHalfOffSpecial() {
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addBuyNGetMAtXOffSpecial("Soup", 2, 1, 0.50);
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 1.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 2.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.50, total 2.50
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 3.50
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 4.50
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.50, total 5.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 6.00
        Assert.assertEquals(6.00, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAdd3For5Special() {
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Soup", 3.00);
        this.checkoutOrderTotal.addNForXSpecial("Soup", 3, 5.00);
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.60, total 0.60
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.60, total 1.20
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.60, total 1.80
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.60, total 2.40
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.60, total 3.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.60, total 3.60
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.60, total 4.20
        Assert.assertEquals(4.20, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddBuyTwoGetOneFreeLimitSixSpecial() {
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addBuyNGetMAtXOffSpecial("Soup", 2, 1, 1.00, 6);
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 1.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 2.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.00, total 2.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 3.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 4.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.00, total 4.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 5.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 6.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 7.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 8.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 9.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 10.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 11.00
        Assert.assertEquals(11.00, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAdd3For5Limit6Special() {
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Soup", 3.00);
        this.checkoutOrderTotal.addNForXSpecial("Soup", 3, 5.00, 6);
        this.checkoutOrderTotal.addItemToOrder("Soup"); // 1, Item price 0.60, total 0.60
        this.checkoutOrderTotal.addItemToOrder("Soup"); // 2, Item price 0.60, total 1.20
        this.checkoutOrderTotal.addItemToOrder("Soup"); // 3, Item price 0.60, total 1.80
        this.checkoutOrderTotal.addItemToOrder("Soup"); // 4, Item price 0.60, total 2.40
        this.checkoutOrderTotal.addItemToOrder("Soup"); // 5, Item price 0.60, total 3.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // 6, Item price 0.60, total 3.60
        this.checkoutOrderTotal.addItemToOrder("Soup"); // 7, Item price 3.00, total 6.60
        this.checkoutOrderTotal.addItemToOrder("Soup"); // 8, Item price 3.00, total 9.60
        this.checkoutOrderTotal.addItemToOrder("Soup"); // 9, Item price 3.00, total 12.60
        Assert.assertEquals(12.60, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canRemoveScannedItemWithSpecial() {
        this.checkoutOrderTotal.addSoldPerUnitItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addBuyNGetMAtXOffSpecial("Soup", 2, 2, 0.50);
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 1.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 2.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.50, total 2.50
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.50, total 3.00
        this.checkoutOrderTotal.removeItemFromOrder(1); // Item price 0.50, total 2.50
        Assert.assertEquals(2.5, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddBuy2PoundsGet1PoundHalfOffSpecial() {
        this.checkoutOrderTotal.addSoldByWeightItemToInventory("Ground Beef", 4.00);
        this.checkoutOrderTotal.addBuyNGetMAtXOffSpecial("Ground Beef", 2, 1, 0.50);
        this.checkoutOrderTotal.addItemToOrder("Ground Beef", 3.0);
        Assert.assertEquals(10.0, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddBuy2PoundsGet1PoundHalfOffSpecialWith4Pounds() {
        this.checkoutOrderTotal.addSoldByWeightItemToInventory("Ground Beef", 4.00);
        this.checkoutOrderTotal.addBuyNGetMAtXOffSpecial("Ground Beef", 2, 1, 0.50);
        this.checkoutOrderTotal.addItemToOrder("Ground Beef", 4.0);
        Assert.assertEquals(14.0, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddBuy2PoundsGet1PoundHalfOffSpecialWith5Pounds() {
        this.checkoutOrderTotal.addSoldByWeightItemToInventory("Ground Beef", 4.00);
        this.checkoutOrderTotal.addBuyNGetMAtXOffSpecial("Ground Beef", 2, 1, 0.50);
        this.checkoutOrderTotal.addItemToOrder("Ground Beef", 5.0);
        Assert.assertEquals(18.0, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddBuy2PoundsGet1PoundHalfOffSpecialWith6Pounds() {
        this.checkoutOrderTotal.addSoldByWeightItemToInventory("Ground Beef", 4.00);
        this.checkoutOrderTotal.addBuyNGetMAtXOffSpecial("Ground Beef", 2, 1, 0.50);
        this.checkoutOrderTotal.addItemToOrder("Ground Beef", 6.0);
        Assert.assertEquals(20.0, this.checkoutOrderTotal.getTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }
}
