package nl.ing.api.contacting.util.exception.syntax;

import java.util.function.Function;

public interface Applicative<F> {
    <A> F pure(A value);
    <A, B> F map(F fa, Function<A, B> f);
    <A, B> F ap(F fab, F fa);
}

