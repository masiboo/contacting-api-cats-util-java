package nl.ing.api.contacting.util.exception.syntax;

import nl.ing.api.contacting.util.exception.ContactingBusinessError;

import java.util.function.Function;

@FunctionalInterface
public interface ErrorMappingFunction<A> {
    Either<ContactingBusinessError, A> apply(Throwable throwable);

    default boolean isDefinedAt(Throwable throwable) {
        return true; // Customize if needed
    }
}
