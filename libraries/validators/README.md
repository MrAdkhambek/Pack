## Validators

```kotlin
    private val validator: Validator<String?> = PasswordValidator()
    val isValid = validator("Aa12345678").isEmpty()
```

-------------
Build in Validators
```kotlin
    private val passwordValidator: PasswordValidator = PasswordValidator()
    private val userNameValidator: UserNameValidator = UserNameValidator()
    private val emailValidator: EmailValidator = EmailValidator()


    val result = passwordValidator("Aa12345678") + userNameValidator("Adam") + emailValidator("mr.adkhambek@gmail.com")

    val isEmailValid = result.contains(EmailValidator.KEY)
```


-------------
Essential downloads
-------------
[![Maven Central](https://img.shields.io/maven-central/v/com.adkhambek.pack/validators.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.adkhambek.pack/validators)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.adkhambek.pack:validators:${latest_version}")
}
```