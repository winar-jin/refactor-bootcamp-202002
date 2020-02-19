package cc.xpbootcamp.warmup.cashier;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class OrderReceiptTest {
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
    public void shouldPrintLineItemAndSalesTaxInformationWhenCreateDateIsNotOnWednesday() throws ParseException {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};
        Date createdDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-18");
        OrderReceipt receipt = new OrderReceipt(new Order(createdDate, lineItems));

        String output = receipt.printReceipt();

        assertThat(output, containsString("milk, 10.00 x 2, 20.00\n"));
        assertThat(output, containsString("biscuits, 5.00 x 5, 25.00\n"));
        assertThat(output, containsString("chocolate, 20.00 x 1, 20.00\n"));
        assertThat(output, containsString("税额: 6.50\n"));
        assertThat(output, containsString("总价: 71.50"));
    }

    @Test
    public void shouldPrintLineItemAndSalesTaxInformationWhenCreateDateIsOnWednesday() throws ParseException {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("牛奶", 21.50, 2));
            add(new LineItem("小白菜", 10.00, 1));
        }};

        Date createdDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-19");
        OrderReceipt receipt = new OrderReceipt(new Order(createdDate, lineItems));

        String output = receipt.printReceipt();

        assertThat(output, containsString("牛奶, 21.50 x 2, 43.00\n"));
        assertThat(output, containsString("小白菜, 10.00 x 1, 10.00\n"));
        assertThat(output, containsString("税额: 5.30\n"));
        assertThat(output, containsString("折扣: 1.17\n"));
        assertThat(output, containsString("总价: 57.13"));
    }
}