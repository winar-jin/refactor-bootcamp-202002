package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {
    private List<LineItem> lineItems;
    private Date createdDate;

    private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(.02);
    private static final int DISCOUNT_DAY_OF_WEEK = 3;
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(.10);

    public Order(Date createdDate, List<LineItem> lineItems) {
        this.lineItems = lineItems;
        this.createdDate = Objects.nonNull(createdDate) ? createdDate : new Date();
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public String createdDate() {

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月dd日");

        return String.format("%s,%s", simpleDateFormat.format(createdDate), weekDays[createdDate.getDay()]);
    }

    public BigDecimal totalAmountWithTaxAndDiscount() {
        return totalAmountWithTax().subtract(discount());
    }

    public BigDecimal discount() {
        if (createdDate.getDay() == DISCOUNT_DAY_OF_WEEK) {
            return totalAmountWithTax().multiply(DISCOUNT_RATE);
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal totalAmountWithTax() {
        BigDecimal totalAmount = lineItems.stream()
                                          .map(LineItem::totalAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmount.add(totalSalesTax());
    }

    public BigDecimal totalSalesTax() {
        return lineItems.stream()
                        .map(lineItem -> lineItem.totalAmount().multiply(TAX_RATE))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
