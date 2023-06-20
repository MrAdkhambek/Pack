package com.adkhambek.pack.validator

public fun interface Validator<T> {

    public interface Key

    public operator fun invoke(value: T): Set<Key>

    public infix fun and(next: Validator<T>): Validator<T> {
        return Validator { value ->
            invoke(value) + next(value)
        }
    }
}
