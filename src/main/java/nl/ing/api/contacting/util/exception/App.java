package nl.ing.api.contacting.util.exception;

import nl.ing.api.contacting.util.exception.syntax.Either;
import nl.ing.api.contacting.util.exception.syntax.Left;
import nl.ing.api.contacting.util.exception.syntax.OptionSyntax;
import nl.ing.api.contacting.util.exception.syntax.Right;

import java.time.Duration;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        // Example 1: Optional contains a value
        Optional<String> someValue = Optional.of("Hello");
        Either<ContactingBusinessError, String> result1 =
                OptionSyntax.toResult(someValue, new TimeoutError(Duration.ZERO));

        printResult(result1); // should print Right(Hello)

        // Example 2: Optional is empty
        Optional<String> noneValue = Optional.empty();
        Either<ContactingBusinessError, String> result2 =
                OptionSyntax.toResult(noneValue, new RuntimeError(new Exception("run time")));

        printResult(result2); // should print Left(RuntimeError[Value missing!])
    }

    private static void printResult(Either<ContactingBusinessError, String> result) {
        if (result instanceof Left<ContactingBusinessError, String>(ContactingBusinessError value)) {
            System.out.println("Left(" + value + ")");
        } else if (result instanceof Right<ContactingBusinessError, String>(String value)) {
            System.out.println("Right(" + value + ")");
        }
    }
}
