package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        output.append("===== 老王超市,值得信赖 ======\n");

        output.append(order.createdDate()).append("\n");

        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.lineItemInformation());
        }

        output.append("-------------------------\n");

        output.append("税额: ").append(order.totalSalesTax()).append("\n");

        BigDecimal discount = order.discount();
        if (discount.compareTo(BigDecimal.ZERO) > 0) {
            output.append("折扣: ").append(String.format("%.2f", discount)).append("\n");
        }

        output.append("总价: ").append(String.format("%.2f", order.totalAmountWithTaxAndDiscount()));

        return output.toString();
    }
}