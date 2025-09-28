package nl.ing.api.contacting.util.syntax;

import nl.ing.api.contacting.util.exception.exp.ContactingBusinessError;

public final class EitherT<A> {
    private final Either<ContactingBusinessError, A> value;

    private EitherT(Either<ContactingBusinessError, A> value) {
        this.value = value;
    }

    public static <A> EitherT<A> fromEither(Either<ContactingBusinessError, A> either) {
        return new EitherT<>(either);
    }

    public Either<ContactingBusinessError, A> get() {
        return value;
    }
}


