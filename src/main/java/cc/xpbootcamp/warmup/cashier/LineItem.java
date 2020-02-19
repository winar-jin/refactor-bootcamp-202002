package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;

public class LineItem {
    private String description;
    private BigDecimal price;
    private int quantity;

    public LineItem(String description, double price, int quantity) {
        this.description = description;
        this.price = BigDecimal.valueOf(price);
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    BigDecimal totalAmount() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    String lineItemInformation() {
        return String.format("%s, %.2f x %s, %.2f\n", getDescription(), getPrice(), getQuantity(), totalAmount());
    }
}