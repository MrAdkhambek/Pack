package com.adkhambek.pack.validator

public fun interface Validator<T> {

    public interface Key

    public fun isValid(value: T): Set<Key>

    public fun and(next: Validator<T>): Validator<T> {
        return Validator { value ->
            isValid(value) + next.isValid(value)
        }
    }
}
