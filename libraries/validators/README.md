## Validators

```kotlin
    private val validator: Validator<String?> = PasswordValidator()
    val isValid = validator("Aa12345678").isEmpty()
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