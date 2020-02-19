package cc.xpbootcamp.warmup.cashier;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertFalse;

class OrderReceiptTest {
    @Test
    void shouldNotPrintCustomerInformationOnOrder() {
        Order order = new Order(null, new ArrayList<>());
        OrderReceipt receipt = new OrderReceipt(order);

        String output = receipt.printReceipt();

        assertFalse(output.contains("Mr X"));
        assertFalse(output.contains("Chicago, 60601"));
    }

    @Test
    public void shouldPrintTitleAndDividerInEachOrderReceipt() {
        Order order = new Order(null, new ArrayList<>());
        OrderReceipt receipt = new OrderReceipt(order);

        String output = receipt.printReceipt();

        assertThat(output, containsString("===== 老王超市,值得信赖 ======"));
        assertThat(output, containsString("-------------------------"));
    }

    @Test
    public void shouldPrintOrderCreateDate() throws ParseException {
        Date createdDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-19");
        Order order = new Order(createdDate, new ArrayList<>());
        OrderReceipt receipt = new OrderReceipt(order);

        String output = receipt.printReceipt();

        assertThat(output, containsString("2020年2月19日,星期三"));
    }

    @Test
    public void shouldPrintLineItemAndSalesTaxInformation() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(null, lineItems));

        String output = receipt.printReceipt();

        assertThat(output, containsString("milk\t10.0\t2\t20.0\n"));
        assertThat(output, containsString("biscuits\t5.0\t5\t25.0\n"));
        assertThat(output, containsString("chocolate\t20.0\t1\t20.0\n"));
        assertThat(output, containsString("Sales Tax\t6.5"));
        assertThat(output, containsString("Total Amount\t71.5"));
    }
}