import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

class Trace<T> {
    private List<T> values;

    protected Trace(List<T> values) {
        this.values = new ArrayList<>();
        for (T v : values) {
            this.values.add(v);
        }
    }
    
    @SafeVarargs
    public static <X> List<X> makeValues(X value, X... args) {
        List<X> xs = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            xs.add(args[i]);
        }
        xs.add(value);
        return xs; 
    }

    @SafeVarargs
    public static <X> Trace<X> of(X value, X... args) {
        return new Trace<>(Trace.makeValues(value, args));
    }

    public int lastIndex() {
        return this.values.size() - 1;
    }

    public T get() {
        return this.values.get(lastIndex());
    }

    public List<T> history() {
       List<T> newLst = new ArrayList<>();
       for (T v : this.values) {
            newLst.add(v);
       }
       return newLst;
    }

    public Trace<T> back(int n) {
       List<T> newLst = history();
       for (int i = 0; i < n; i++) {
            if (i == this.values.size() - 1) {
                break;
            }
           newLst.remove(this.values.size() - 1 - i);
       }
       return new Trace<>(newLst);
    }

    @Override
    public boolean equals(Object obj) {
       if (this == obj) {
           return false;
       }

       if (obj instanceof Trace) {
           return equals(obj);
       }

       return false;
    }

    public <X> boolean equals(Trace<X> trace) {
        List<X> traceValues = trace.history();
        if (this.values.size() != traceValues.size()) {
            return false;
       }
        for (int i = 0; i < traceValues.size(); i++) {
            if (traceValues.get(i) != this.values.get(i)) {
                return false;
            }
        }
        return true;
    }

    public Trace<T> map(Function<? super T, ? extends T> fn) {
        List<T> newLst = history();
        T newCurrent = fn.apply(get());
        newLst.add(newCurrent);
        return new Trace<>(newLst);
    }
    
    public Trace<T> flatMap(Function<? super T, ? extends Trace<? extends T>> fn) {
        List<? extends T> newAddLst = fn.apply(get()).history();
        List<T> newLst = history();
        for (T item : newAddLst) {
            if (!newLst.contains(item)) {
                newLst.add(item);
            }
        }
        return new Trace<>(newLst);
    }
}
