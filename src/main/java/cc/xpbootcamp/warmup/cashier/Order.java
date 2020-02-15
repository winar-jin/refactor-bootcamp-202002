package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;
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

    public BigDecimal totalSalesTax() {
        // calculate sales tax @ rate of 10%
        return lineItems.stream()
                        .map(lineItem -> lineItem.totalAmount().multiply(BigDecimal.valueOf(.10)))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalAmountWithTax() {
        BigDecimal totalAmount = lineItems.stream()
                                          .map(LineItem::totalAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmount.add(totalSalesTax());
    }
}
