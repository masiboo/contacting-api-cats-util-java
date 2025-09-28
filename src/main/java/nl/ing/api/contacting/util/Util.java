package nl.ing.api.contacting.util;


import nl.ing.api.contacting.util.exception.ContactingBusinessError;
import nl.ing.api.contacting.util.syntax.Either;
import nl.ing.api.contacting.util.syntax.ResultT;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 *
 * @author Masum
 */
public final class Util {

    private Util() {
    }

    public static <A> Either<ContactingBusinessError, A> result(A value) {
        return Either.right(value);
    }

    public static <A> Either<ContactingBusinessError, A> resultLeft(ContactingBusinessError error) {
        return Either.left(error);
    }

    public static <A> ResultT<A> resultT(A value) {
        return ResultT.fromEither(Either.right(value));
    }

    public static <A> ResultT<A> resultTLeft(ContactingBusinessError error) {
        return ResultT.fromEither(Either.left(error));
    }

    public static <A> ResultT<A> resultF(CompletableFuture<A> future, ContactingBusinessError errorIfFailed) {
        return ResultT.fromFuture(future, errorIfFailed);
    }

    @FunctionalInterface
    public interface ErrorMappingFunction<A> extends Function<Throwable, Either<ContactingBusinessError, A>> {}
}

