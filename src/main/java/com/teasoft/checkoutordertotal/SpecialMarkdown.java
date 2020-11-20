package com.teasoft.checkoutordertotal;

public class SpecialMarkdown extends Special {
    private double markdown;

    public SpecialMarkdown(InventoryItem inventoryItem, double markdown) {
        super(inventoryItem);
        this.setMarkdown(markdown);
    }

    public double getMarkdown() {
        return this.markdown;
    }

    public void setMarkdown(double markdown) {
        if (markdown <= 0.0) throw new IllegalArgumentException("Markdown must be greater than zero");
        this.markdown = markdown;
    }

    @Override
    public double computeSpecialPrice(double totalQuantityInOrder) {
        // All items are discounted by markdown.
        double markdownPrice = (totalQuantityInOrder * (this.inventoryItem.getPrice() - this.markdown));
        if (markdownPrice <= 0.0) throw new IllegalArgumentException("Marked down price must be greater than zero");
        return markdownPrice;
    }
}
