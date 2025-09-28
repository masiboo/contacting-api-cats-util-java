package nl.ing.api.contacting.util.exception.syntax;

import nl.ing.api.contacting.util.exception.ContactingBusinessError;

import java.util.Optional;

/**
 * @author Masum Islam
 */
public final class OptionSyntax {

    private OptionSyntax() {
        // Utility class
    }

    public static <A> Either<ContactingBusinessError, A> toResult(Optional<A> optional, ContactingBusinessError ifNone) {
        return optional.map(Either::right).orElseGet(() -> Either.left(ifNone));
    }

    public static <A> EitherT<A> toResultT(Optional<A> optional, ContactingBusinessError ifNone) {
        return EitherT.fromEither(toResult(optional, ifNone));
    }

    public static <F, A> ResultF<F, A> toResultF(
            Optional<A> optional,
            ContactingBusinessError ifNone,
            Applicative<F> applicative
    ) {
        return ResultT.fromEither(toResult(optional, ifNone), applicative);
    }
}

