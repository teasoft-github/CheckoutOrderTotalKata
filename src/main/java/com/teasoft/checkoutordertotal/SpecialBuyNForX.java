package com.teasoft.checkoutordertotal;

public class SpecialBuyNForX extends Special {
    private double count;
    private double totalPrice;
    private double limitCount;
    private double specialPrice;

    public SpecialBuyNForX(InventoryItem inventoryItem, double count, double totalPrice, double limitCount) {
        super(inventoryItem);
        this.setCount(count);
        this.setTotalPrice(totalPrice);
        this.setLimitCount(limitCount);
    }

    public double getCount() {
        return this.count;
    }

    public void setCount(double count) {
        if (count <= 0.0) throw new IllegalArgumentException("Count must be greater than zero");
        this.count = count;
        this.computeEffectivePrice();
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        if (totalPrice <= 0.0) throw new IllegalArgumentException("Total must be greater than zero");
        this.totalPrice = totalPrice;
        this.computeEffectivePrice();
    }

    public double getLimitCount() {
        return this.limitCount;
    }

    public void setLimitCount(double limitCount) {
        if (limitCount < 0.0) throw new IllegalArgumentException("Limit must be greater or equal to zero");
        this.limitCount = limitCount;
    }

    private void computeEffectivePrice() {
        // Round down to the nearest cent. Protect against division by zero that can occur as the object is constructed.
        if (this.totalPrice > 0.0) this.specialPrice = Math.floor((this.count / this.totalPrice) * 100.0) / 100.00;
    }

    @Override
    public double computeSpecialPrice(double totalQuantityInOrder) {
        double discountedQuantity = Math.min(totalQuantityInOrder, this.limitCount);
        double fullPriceQuantity = totalQuantityInOrder - discountedQuantity;
        double specialPrice = (fullPriceQuantity * this.inventoryItem.getPrice()) +
                (discountedQuantity * this.specialPrice);
        // Round down to the nearest cent
        specialPrice = Math.floor(specialPrice * 100.0) / 100.00;
        return specialPrice;
    }
}
