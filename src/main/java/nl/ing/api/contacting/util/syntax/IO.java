package nl.ing.api.contacting.util.syntax;


import java.util.function.Supplier;

/**
 * Enhanced IO abstraction for asynchronous computations with cancellation, timeout, and error handling.
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Minimal IO effect type, inspired by Cats Effect IO.
 */
public final class IO<A> {
    private final Supplier<CompletableFuture<A>> thunk;

    private IO(Supplier<CompletableFuture<A>> thunk) {
        this.thunk = thunk;
    }

    public static <A> IO<A> fromFuture(Supplier<CompletableFuture<A>> futureSupplier) {
        return new IO<>(futureSupplier);
    }

    public static <A> IO<A> pure(A value) {
        return new IO<>(() -> CompletableFuture.completedFuture(value));
    }

    public CompletableFuture<A> unsafeRunAsync() {
        return thunk.get();
    }

    public A unsafeRunSync() throws ExecutionException, InterruptedException {
        return thunk.get().get();
    }
}