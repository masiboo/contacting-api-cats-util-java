package nl.ing.api.contacting.util.syntax;

import nl.ing.api.contacting.util.exception.exp.ContactingBusinessError;

@FunctionalInterface
public interface ErrorMappingFunction<A> {
    Either<ContactingBusinessError, A> apply(Throwable throwable);

    default boolean isDefinedAt(Throwable throwable) {
        return true; // Customize if needed
    }
}
