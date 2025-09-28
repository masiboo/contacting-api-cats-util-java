package nl.ing.api.contacting.util.exception.syntax;

import nl.ing.api.contacting.util.exception.ContactingBusinessError;

public final class ResultF<F, A> {
    private final F wrapped;

    public ResultF(F wrapped) {
        this.wrapped = wrapped;
    }

    public F get() {
        return wrapped;
    }

    public static <F, A> ResultF<F, A> fromEither(Either<ContactingBusinessError, A> either, Applicative<F> applicative) {
        return new ResultF<>(applicative.pure(either));
    }
}

