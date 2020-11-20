package com.teasoft.checkoutordertotal;

public abstract class Special {
    protected InventoryItem inventoryItem;

    public Special(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public InventoryItem getInventoryItem() {
        return this.inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        if (inventoryItem == null) throw new IllegalArgumentException("Inventory item cannot be empty");
        this.inventoryItem = inventoryItem;
    }

    abstract double computeSpecialPrice(double totalQuantityInOrder);
}
