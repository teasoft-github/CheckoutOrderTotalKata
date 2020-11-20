package com.teasoft.checkoutordertotal;

public class InventoryItem {
    private String name;
    private double price;
    private boolean soldByWeight;
    private Special special;

    public InventoryItem(String name, double price, boolean soldByWeight) {
        this.setName(name);
        this.setPrice(price);
        this.setSoldByWeight(soldByWeight);
        this.setSpecial(new SpecialNone(this));
    }

    public static void validateName(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be empty");
        if (name.trim().length() == 0) throw new IllegalArgumentException("Name cannot be empty");
    }

    public static void validatePrice(double pricePerUnit) {
        if (pricePerUnit < 0.0) throw new IllegalArgumentException("Price per-unit must be greater than zero");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        InventoryItem.validateName(name);
        this.name = name.trim();
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0.0) throw new IllegalArgumentException("Price cannot be less than 0");
        this.price = price;
    }

    public boolean isSoldByWeight() {
        return this.soldByWeight;
    }

    public void setSoldByWeight(boolean soldByWeight) {
        this.soldByWeight = soldByWeight;
    }

    public Special getSpecial() {
        return this.special;
    }

    public void setSpecial(Special special) {
        if (special == null) throw new IllegalArgumentException("Special cannot be null");
        this.special = special;
    }
}
