package nl.ing.api.contacting.util.exception.syntax;

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
}

