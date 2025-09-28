package nl.ing.api.contacting.util.exception.syntaxf;

import java.util.function.Function;
import java.util.function.Supplier;

public interface Sync<F> {
    <A> F delay(Supplier<A> thunk);

    <A> F raiseError(Throwable t);

    <A> F flatMap(F fa, Function<A, F> f);

    <A> F map(F fa, Function<A, A> f);
}

