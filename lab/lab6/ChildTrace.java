import java.util.List;
import java.util.ArrayList;

class ChildTrace<T> extends Trace<T> {
    private ChildTrace(List<T> values) {
        super(values);
    }

    @SafeVarargs
    public static <X> ChildTrace<X> of(X value, X... args) {
        return new ChildTrace<>(Trace.makeValues(value, args));
    }
}
