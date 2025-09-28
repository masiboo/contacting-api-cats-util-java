package nl.ing.api.contacting.util.exception;

import nl.ing.api.contacting.util.exception.syntax.*;
/**
 * Represents a business error in contacting an API.
 * Converted from Scala trait/case classes to Java interfaces and records.
 *
 * @author Masum Islam
 */
public sealed interface ContactingBusinessError
        permits RuntimeError, TimeoutError {
}
