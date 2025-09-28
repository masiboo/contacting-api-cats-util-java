package nl.ing.api.contacting.util.syntaxf;

import nl.ing.api.contacting.util.exception.ContactingBusinessError;
import nl.ing.api.contacting.util.syntax.Either;
import nl.ing.api.contacting.util.syntax.ResultT;
import nl.ing.api.contacting.util.syntax.Either;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public final class FSyntax {

    private FSyntax() {
    }

    public static <A> ResultT<A> toResultT(Supplier<A> supplier, ContactingBusinessError ifFailure) {
        try {
            A result = supplier.get();
            return ResultT.fromEither(Either.right(result));
        } catch (Exception ex) {
            return ResultT.fromEither(Either.left(ifFailure));
        }
    }

    public static <A> ResultT<A> fromFuture(CompletableFuture<A> future, ContactingBusinessError ifFailure) {
        CompletableFuture<Either<ContactingBusinessError, A>> wrapped =
                future.thenApply(Either::<ContactingBusinessError, A>right)
                        .exceptionally(ex -> Either.<ContactingBusinessError, A>left(ifFailure));
        return new ResultT<>(wrapped);
    }

}
