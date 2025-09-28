package nl.ing.api.contacting.util.exception.syntax;

import nl.ing.api.contacting.util.exception.ContactingBusinessError;

public final class Result {
    private Result() {}

    public static <A> Either<ContactingBusinessError, A> right(A value) {
        return Either.right(value);
    }

    public static <A> Either<ContactingBusinessError, A> left(ContactingBusinessError error) {
        return Either.left(error);
    }
}

