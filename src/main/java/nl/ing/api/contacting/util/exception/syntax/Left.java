package nl.ing.api.contacting.util.exception.syntax;

public record Left<L, R>(L value) implements Either<L, R> {}
