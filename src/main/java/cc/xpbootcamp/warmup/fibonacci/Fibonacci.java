package cc.xpbootcamp.warmup.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private Map<Integer, Long> results = new HashMap<Integer, Long>() {{
        put(1, 1L);
        put(2, 1L);
    }};

    public long calculate(int position) {
        int startPosition = 3;
        while (startPosition <= position) {
            results.put(startPosition, results.get(startPosition - 1) + results.get(startPosition - 2));
            startPosition++;
        }
        return results.get(position);
    }
}
