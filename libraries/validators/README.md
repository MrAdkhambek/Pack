# Validators

[![Maven Central](https://img.shields.io/maven-central/v/com.adkhambek.pack/validators.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.adkhambek.pack/validators)

A lightweight, composable validation library for Kotlin/JVM. Define reusable validators with typed error keys and chain them together using the `and` operator.

## Installation

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.adkhambek.pack:validators:${latest_version}")
}
```

## Core Concept

Every validator implements the `Validator<T>` functional interface. Calling a validator returns a `Set<Validator.Key>` — an empty set means the value is valid, otherwise the set contains error keys describing what failed.

```kotlin
public fun interface Validator<T> {
    public interface Key
    public operator fun invoke(value: T): Set<Key>
    public infix fun and(next: Validator<T>): Validator<T>
}
```

## Built-in Validators

### EmailValidator

Validates email format (RFC-like pattern).

```kotlin
val validator = EmailValidator()

validator("user@example.com")   // emptySet() — valid
validator("invalid")            // setOf(EmailValidator.KEY) — invalid
validator(null)                 // setOf(EmailValidator.KEY) — invalid
```

### PasswordValidator

Requires at least 6 characters with uppercase, lowercase, and a digit. Accepts only alphanumeric characters.

```kotlin
val validator = PasswordValidator()

validator("Secret1")    // emptySet() — valid
validator("weak")       // setOf(PasswordValidator.KEY) — invalid
```

You can provide a custom regex pattern:

```kotlin
val custom = PasswordValidator(Pattern.compile("^.{8,}$"))
```

### UserNameValidator

Requires at least 3 alphanumeric characters.

```kotlin
val validator = UserNameValidator()

validator("john123")    // emptySet() — valid
validator("ab")         // setOf(UserNameValidator.KEY) — too short
validator("user@name")  // setOf(UserNameValidator.KEY) — special chars not allowed
```

You can provide a custom regex pattern:

```kotlin
val custom = UserNameValidator(Pattern.compile("^[a-z_]{3,}$"))
```

## Composing Validators

Chain validators with the `and` infix operator. The combined validator collects all error keys from every validator that fails:

```kotlin
val emailValidator = EmailValidator()
val notBlank = Validator<String?> { value ->
    if (value.isNullOrBlank()) setOf(BlankKey) else emptySet()
}

val combined = notBlank and emailValidator

combined(null)                  // setOf(BlankKey, EmailValidator.KEY)
combined("user@example.com")   // emptySet()
```

## Checking Multiple Fields

Use validators together to validate a form and inspect each field's result independently:

```kotlin
val passwordValidator = PasswordValidator()
val userNameValidator = UserNameValidator()
val emailValidator = EmailValidator()

val result = passwordValidator("Aa12345678") +
    userNameValidator("Adam") +
    emailValidator("mr.adkhambek@gmail.com")

val isEmailValid = !result.contains(EmailValidator.KEY)
val isPasswordValid = !result.contains(PasswordValidator.KEY)
val isUserNameValid = !result.contains(UserNameValidator.KEY)
```

## Custom Validators

Create your own validator by implementing the interface or using a lambda:

```kotlin
object TooLongKey : Validator.Key

val maxLength = Validator<String?> { value ->
    if (value != null && value.length > 100) setOf(TooLongKey) else emptySet()
}
```

## License

```
Copyright 2022 Adkhambek

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0
```
