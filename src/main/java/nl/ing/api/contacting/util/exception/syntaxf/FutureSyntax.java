package nl.ing.api.contacting.util.exception.syntaxf;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import nl.ing.api.contacting.util.exception.ContactingBusinessError;
import nl.ing.api.contacting.util.exception.syntax.Either;
import nl.ing.api.contacting.util.exception.syntax.ResultT;

import java.util.Optional;
import java.util.concurrent.*;

/**
 *
 * @author Masum Islam
 */
public final class FutureSyntax {

    private FutureSyntax() {
        // Utility class
    }

    /**
     * Add a callback to a CompletableFuture.
     */
    public static <A> void callback(
            CompletableFuture<A> future,
            java.util.function.Consumer<Either<Throwable, A>> cb
    ) {
        future.handle((result, ex) -> {
            if (ex == null) {
                cb.accept(Either.right(result));
            } else {
                cb.accept(Either.left(ex));
            }
            return null;
        });
    }

    /**
     * Convert a Guava ListenableFuture into a CompletableFuture.
     */
    public static <T> CompletableFuture<T> fromListenableFuture(
            ListenableFuture<T> lf,
            Executor executor
    ) {
        CompletableFuture<T> cf = new CompletableFuture<>();
        Futures.addCallback(lf, new FutureCallback<>() {
            @Override
            public void onSuccess(T result) {
                cf.complete(result);
            }

            @Override
            public void onFailure(Throwable t) {
                cf.completeExceptionally(t);
            }
        }, executor);
        return cf;
    }

    /**
     * Convert a Java CompletableFuture into ResultT with error mapping.
     */
    public static <A> ResultT<A> fromFuture(
            CompletableFuture<A> future,
            ContactingBusinessError ifFailure
    ) {
        CompletableFuture<Either<ContactingBusinessError, A>> wrapped =
                future.thenApply(a -> Either.<ContactingBusinessError, A>right(a))
                        .exceptionally(ex -> Either.<ContactingBusinessError, A>left(ifFailure));

        return new ResultT<>(wrapped);
    }

    /**
     * Convert Optional inside a future into ResultT.
     */
    public static <A> ResultT<A> fromFutureOptional(
            CompletableFuture<Optional<A>> future,
            ContactingBusinessError ifNone
    ) {
        CompletableFuture<Either<ContactingBusinessError, A>> wrapped =
                future.thenApply(opt ->
                        opt.<Either<ContactingBusinessError, A>>map(Either::right)
                                .orElseGet(() -> Either.left(ifNone))
                );

        return new ResultT<>(wrapped);
    }
}

