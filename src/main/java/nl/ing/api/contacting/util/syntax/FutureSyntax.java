package nl.ing.api.contacting.util.syntax;


import java.util.concurrent.CompletableFuture;

/**
 * Provides static utility methods for converting CompletableFuture to IO.
 */
public final class FutureSyntax {
    private FutureSyntax() {}

    public static <A> IO<A> toIO(CompletableFuture<A> future) {
        return IO.fromFuture(() -> future);
    }
}