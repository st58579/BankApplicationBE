package mk.gridlib.interfaces.functional;

public interface ValueFn<IN, OUT> {
    OUT apply(IN in);
}