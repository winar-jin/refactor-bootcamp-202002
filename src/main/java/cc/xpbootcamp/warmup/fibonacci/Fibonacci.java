package cc.xpbootcamp.warmup.fibonacci;

public class Fibonacci {

    public long calculate(int position) {
        if (position == 1 || position == 2) {
            return 1;
        }
        return this.calculate(position - 1) + this.calculate(position - 2);
    }
}
