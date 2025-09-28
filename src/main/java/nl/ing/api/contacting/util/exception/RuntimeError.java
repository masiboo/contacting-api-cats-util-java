package nl.ing.api.contacting.util.exception;

/**
 * Equivalent to Scala's case class RuntimeError(e: Throwable).
 */
record RuntimeError(Throwable exception) implements ContactingBusinessError {
    public RuntimeError {
        if (exception == null) {
            throw new IllegalArgumentException("Exception must not be null");
        }
    }
}