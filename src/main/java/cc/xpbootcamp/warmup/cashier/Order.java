package cc.xpbootcamp.warmup.cashier;

import java.util.List;

public class Order {
    private String customerName;
    private String address;
    private List<LineItem> lineItems;

    public Order(String customerName, String address, List<LineItem> lineItems) {
        this.customerName = customerName;
        this.address = address;
        this.lineItems = lineItems;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return address;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public double totalSalesTax() {
        // calculate sales tax @ rate of 10%
        return lineItems.stream()
                        .map(lineItem -> lineItem.totalAmount() * .10)
                        .mapToDouble(Double::doubleValue)
                        .sum();
    }

    public double totalAmountWithTax() {
        double totalAmount = lineItems.stream()
                                      .map(LineItem::totalAmount)
                                      .mapToDouble(Double::doubleValue)
                                      .sum();
        return totalAmount + totalSalesTax();

    }
}
