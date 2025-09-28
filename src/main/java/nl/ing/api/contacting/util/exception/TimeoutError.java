package nl.ing.api.contacting.util.exception;


import java.time.Duration;

/**
 * Equivalent to Scala's case class TimeoutException(duration: Duration).
 */
record TimeoutError(Duration duration) implements ContactingBusinessError {
    public TimeoutError {
        if (duration == null) {
            throw new IllegalArgumentException("Duration must not be null");
        }
    }
}