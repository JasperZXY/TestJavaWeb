package zxy;

import java.util.*;

public class JunitUtils {
    public static void shouldNotHappen() {
        throw new RuntimeException("should not happen!");
    }

    public static <T> Set<T> toSet(T... data) {
        return new HashSet<>(toList(data));
    }

    public static <T> List<T> toList(T... data) {
        return Arrays.asList(data);
    }
}
