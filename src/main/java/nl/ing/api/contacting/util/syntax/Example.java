package nl.ing.api.contacting.util.syntax;

import java.util.concurrent.CompletableFuture;

public class Example {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "Hello from Future");

        IO<String> io = FutureSyntax.toIO(cf);

        // Run async
        io.unsafeRunAsync().thenAccept(System.out::println);

        // Or block (not recommended in prod, but for demo)
        String result = io.unsafeRunSync();
        System.out.println("Sync result: " + result);
    }
}