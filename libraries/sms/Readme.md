## SMS listener

## Use as Flow
```kotlin
val context : Context = ...

val smsFlow = SmsFlow(context)
smsFlow.collect { fullBodySms: String ->
    // TODO logic here
}
```

```kotlin
val smsFlow = SmsFlow(context) { body -> body[0] }
smsFlow.collect { firstSymbol: Char ->
    // TODO logic here
}
```

Essential downloads
-------------
[![Maven Central](https://img.shields.io/maven-central/v/me.adkhambek.pack/sms.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.adkhambek.pack/sms)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.adkhambek.pack:sms:${latest_version}")
}
```
