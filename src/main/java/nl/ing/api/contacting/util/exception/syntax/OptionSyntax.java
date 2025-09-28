package nl.ing.api.contacting.util.exception.syntax;

import nl.ing.api.contacting.util.exception.ContactingBusinessError;

import java.util.Optional;

public final class OptionSyntax {

    private OptionSyntax() {
        // Utility class
    }

    public static <A> Either<ContactingBusinessError, A> toResult(
            Optional<A> optional,
            ContactingBusinessError ifNone
    ) {
        return optional
                .<Either<ContactingBusinessError, A>>map(Either::right) // force correct generic type
                .orElseGet(() -> Either.left(ifNone));
    }

    public static <A> EitherT<A> toResultT(
            Optional<A> optional,
            ContactingBusinessError ifNone
    ) {
        return EitherT.fromEither(toResult(optional, ifNone));
    }


    public static <F, A> ResultT<A> toResultF(
            Optional<A> optional,
            ContactingBusinessError ifNone,
            Applicative<F> applicative
    ) {
        return ResultT.fromEither(toResult(optional, ifNone));
    }
}
