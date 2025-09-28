package nl.ing.api.contacting.util.exception.syntax;

public record Right<L, R>(R value) implements Either<L, R> {}

