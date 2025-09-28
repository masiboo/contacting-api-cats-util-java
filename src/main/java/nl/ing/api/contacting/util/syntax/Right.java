package nl.ing.api.contacting.util.syntax;

import java.util.function.Consumer;

public record Right<L, R>(R value) implements Either<L, R> {

    @Override
    public void ifRight(Consumer<R> action) {
        action.accept(value);
    }

    @Override
    public void ifLeft(Consumer<L> action) {
        // Right has no left value → do nothing
    }

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public R getRight() {
        return value;
    }

    @Override
    public L getLeft() {
        throw new IllegalStateException("No left value in Right");
    }
}
