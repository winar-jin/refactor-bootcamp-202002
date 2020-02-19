package cc.xpbootcamp.warmup.cashier;

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

        output.append(order.getCreatedDate()).append("\n");

        // prints lineItems
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.lineItemInformation());
        }

        output.append("-------------------------\n");

        // prints the sales tax
        output.append("税额: ").append(order.totalSalesTax()).append("\n");

        // print total amount with tax
        output.append("总价: ").append(order.totalAmountWithTax());

        return output.toString();
    }
}