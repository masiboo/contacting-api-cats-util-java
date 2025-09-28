package nl.ing.api.contacting.util.exception.syntax;

import java.util.function.Consumer;

public record Left<L, R>(L value) implements Either<L, R> {

    @Override
    public void ifRight(Consumer<R> action) {
        // Left has no right value → do nothing
    }

    @Override
    public void ifLeft(Consumer<L> action) {
        action.accept(value);
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public R getRight() {
        throw new IllegalStateException("No right value in Left");
    }

    @Override
    public L getLeft() {
        return value;
    }
}