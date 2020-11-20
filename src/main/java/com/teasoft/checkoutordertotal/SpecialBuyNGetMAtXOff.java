package com.teasoft.checkoutordertotal;

public class SpecialBuyNGetMAtXOff extends Special {
    private double prerequisiteCount;
    private double specialCount;
    private double discountPercentage;
    private double limitCount;

    public SpecialBuyNGetMAtXOff(InventoryItem inventoryItem, double prerequisiteCount, double specialCount, double specialOffRate, double limitCount) {
        super(inventoryItem);
        this.setPrerequisiteCount(prerequisiteCount);
        this.setSpecialCount(specialCount);
        this.setDiscountPercentage(specialOffRate);
        this.setLimitCount(limitCount);
    }

    public double getPrerequisiteCount() {
        return this.prerequisiteCount;
    }

    public void setPrerequisiteCount(double prerequisiteCount) {
        if (prerequisiteCount <= 0.0)
            throw new IllegalArgumentException("Prerequisite count must be greater than zero");
        this.prerequisiteCount = prerequisiteCount;
    }

    public double getSpecialCount() {
        return this.specialCount;
    }

    public void setSpecialCount(double specialCount) {
        if (specialCount <= 0.0) throw new IllegalArgumentException("Special count must be greater than zero");
        this.specialCount = specialCount;
    }

    public double getDiscountPercentage() {
        return this.discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        if ((discountPercentage <= 0.0) || (discountPercentage > 1.00))
            throw new IllegalArgumentException("Discount must be greater than 0% and less or equal to 100%");
        this.discountPercentage = discountPercentage;
    }

    public double getLimitCount() {
        return this.limitCount;
    }

    public void setLimitCount(double limitCount) {
        if (limitCount < 0.0) throw new IllegalArgumentException("Limit must be greater or equal to zero");
        this.limitCount = limitCount;
    }

    @Override
    public double computeSpecialPrice(double totalQuantityInOrder) {
        double limitedQuantity = Math.min(totalQuantityInOrder, this.limitCount);
        double discountedQuantity = ((int) (limitedQuantity / (this.prerequisiteCount + this.specialCount))) * this.specialCount;
        discountedQuantity += Math.max(0, (limitedQuantity % (this.prerequisiteCount + this.specialCount)) - this.prerequisiteCount);
        double fullPriceQuantity = totalQuantityInOrder - discountedQuantity;
        double specialPrice = (fullPriceQuantity * this.inventoryItem.getPrice()) +
                (discountedQuantity * this.inventoryItem.getPrice() * (1.0 - this.discountPercentage));
        // Round down to the nearest cent
        specialPrice = Math.floor(specialPrice * 100.0) / 100.00;
        return specialPrice;
    }
}
