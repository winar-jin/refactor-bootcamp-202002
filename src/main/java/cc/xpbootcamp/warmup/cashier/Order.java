package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {
    private List<LineItem> lineItems;
    private Date createdDate;

    public Order(Date createdDate, List<LineItem> lineItems) {
        this.lineItems = lineItems;
        this.createdDate = Objects.nonNull(createdDate) ? createdDate : new Date();
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public String getCreatedDate() {

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月dd日");

        return String.format("%s,%s", simpleDateFormat.format(createdDate), weekDays[createdDate.getDay()]);
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
