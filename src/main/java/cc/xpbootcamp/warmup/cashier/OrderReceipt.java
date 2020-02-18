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

        // print headers
        output.append("======Printing Orders======\n");

        // prints lineItems
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItemInformation(lineItem));
        }

        // prints the sales tax
        output.append("Sales Tax").append('\t').append(order.totalSalesTax());

        // print total amount with tax
        output.append("Total Amount").append('\t').append(order.totalAmountWithTax());

        return output.toString();
    }

    private String lineItemInformation(LineItem lineItem) {
        return String.format("%s\t%.1f\t%s\t%.1f\n", lineItem.getDescription(), lineItem.getPrice(), lineItem.getQuantity(), lineItem.totalAmount());
    }
}