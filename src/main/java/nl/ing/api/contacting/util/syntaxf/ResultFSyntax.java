package nl.ing.api.contacting.util.syntaxf;


import nl.ing.api.contacting.util.exception.exp.ContactingBusinessError;
import nl.ing.api.contacting.util.exception.syntax.Either;
import nl.ing.api.contacting.util.exception.syntax.ResultT;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 *
 * @author Masum
 */
public final class ResultFSyntax {

    private ResultFSyntax() {

    }

  public static <A> ResultT<A> onSuccess(ResultT<A> result, Supplier<?> code) {
        CompletableFuture<Either<ContactingBusinessError, A>> future =
                result.get().thenApply(either -> {
                    either.ifRight(a -> code.get());
                    return either;
                });

        return new ResultT<>(future);
    }

    public static <A> ResultT<A> log(ResultT<A> result, String message, Logger logger) {
        return onSuccess(result, () -> {
            logger.info(message);
            return null;
        });
    }

    public static <A> ResultT<A> onFailure(ResultT<A> result, java.util.function.Function<ContactingBusinessError, ?> code) {
        CompletableFuture<Either<ContactingBusinessError, A>> future =
                result.get().thenApply(either -> {
                    either.ifLeft(code::apply);
                    return either;
                });
        return new ResultT<>(future);
    }


    public static <A> ResultT<A> logOnError(ResultT<A> result, String errorMessage, Logger logger) {
        return onFailure(result, cause -> {
            logger.error(cause + " : " + errorMessage);
            return null;
        });
    }

    public static <A> ResultT<A> ifEmpty(ResultT<Optional<A>> result, ContactingBusinessError ifNone) {
        CompletableFuture<Either<ContactingBusinessError, A>> future =
                result.get().thenApply(optEither -> {
                    if (optEither.isRight()) {
                        Optional<A> opt = optEither.getRight();
                        return opt.map(Either::<ContactingBusinessError, A>right).orElseGet(() -> Either.left(ifNone));
                    } else {
                        return Either.<ContactingBusinessError, A>left(optEither.getLeft());
                    }
                });

        return new ResultT<>(future);
    }
}

