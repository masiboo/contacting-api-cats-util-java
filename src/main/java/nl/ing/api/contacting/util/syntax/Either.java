package nl.ing.api.contacting.util.syntax;

import java.util.function.Consumer;

public sealed interface Either<L, R> permits Left, Right {

    static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }

    void ifRight(Consumer<R> action);

    void ifLeft(Consumer<L> action);

    boolean isRight();

    boolean isLeft();

    R getRight(); // unsafe getter, use only if isRight() == true

    L getLeft();  // unsafe getter, use only if isLeft() == true
}




