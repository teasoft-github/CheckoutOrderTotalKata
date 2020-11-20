package com.teasoft.checkoutordertotal;

public class ScannedItem {
    // Since double is used to store per-unit item quantity, limit the maximum number of quantity to an integer
    // value that can be stored without loss of data into a double (less than 2^52 - 1). We'll use a smaller
    // number.
    private final static double MAX_ALLOWED_QUANTITY = 100000.0;
    private InventoryItem inventoryItem;
    private double quantity;

    public ScannedItem(InventoryItem inventoryItem, double quantity) {
        this.setInventoryItem(inventoryItem);
        this.setQuantity(quantity);
    }

    public static void validateQuantity(double quantity) {
        if (quantity <= 0.0) throw new IllegalArgumentException("Quantity cannot be zero or less than zero");
        if (quantity >= ScannedItem.MAX_ALLOWED_QUANTITY)
            throw new IllegalArgumentException("Quantity cannot be zero or less than zero");
    }

    public InventoryItem getInventoryItem() {
        return this.inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        if (inventoryItem == null) throw new IllegalArgumentException("Inventory item cannot be empty");
        this.inventoryItem = inventoryItem;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        ScannedItem.validateQuantity(quantity);
        this.quantity = quantity;
    }
}
