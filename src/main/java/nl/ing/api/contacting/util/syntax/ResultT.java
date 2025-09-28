package nl.ing.api.contacting.util.syntax;

import nl.ing.api.contacting.util.exception.ContactingBusinessError;

import java.util.concurrent.CompletableFuture;

public final class ResultT<A> {
    private final CompletableFuture<Either<ContactingBusinessError, A>> future;

    public ResultT(CompletableFuture<Either<ContactingBusinessError, A>> future) {
        this.future = future;
    }

    public CompletableFuture<Either<ContactingBusinessError, A>> get() {
        return future;
    }

    public static <A> ResultT<A> fromEither(Either<ContactingBusinessError, A> either) {
        return new ResultT<>(CompletableFuture.completedFuture(either));
    }

    public static <A> ResultT<A> fromFuture(CompletableFuture<A> future, ContactingBusinessError errorIfFailed) {
        CompletableFuture<Either<ContactingBusinessError, A>> wrapped =
                future.<Either<ContactingBusinessError, A>>thenApply(Either::right)
                        .exceptionally(ex -> Either.left(errorIfFailed));
        return new ResultT<>(wrapped);
    }
}

