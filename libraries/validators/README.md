## Validators

```kotlin
    private val validator: Validator<String?> = PasswordValidator()
    val isValid = validator.isValid("Aa12345678").isEmpty()
```


-------------
Essential downloads
-------------
[![Maven Central](https://img.shields.io/maven-central/v/com.adkhambek.pack/text.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.adkhambek.pack/text)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.adkhambek.pack:validators:${latest_version}")
}
```

Other dependency
-------------
[![Maven Central](https://img.shields.io/maven-central/v/com.adkhambek.pack/validators.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.adkhambek.pack/validators)
```groovy
dependencies {
    //  if you want with extensions
    implementation("com.adkhambek.pack:text-ktx:${latest_version}")
}
```
