package com.teasoft.checkoutordertotal;

public class SpecialNone extends Special {

    public SpecialNone(InventoryItem inventoryItem) {
        super(inventoryItem);
    }

    @Override
    public double computeSpecialPrice(double totalQuantityInOrder) {
        // No special. Simply return the per-unit price.
        return (totalQuantityInOrder * this.inventoryItem.getPrice());
    }
}
