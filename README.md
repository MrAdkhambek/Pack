# Pack

A collection of lightweight, focused Android/Kotlin libraries designed to solve common problems with minimal dependencies and clean APIs.

| Tool           | Version |
| :---           | :----:  |
| Kotlin         | 2.3.10  |
| Java           | 21      |
| AGP            | 9.0.1   |
| Gradle Wrapper | 9.3.1   |

## Libraries

| Library | Description | Version |
| :--- | :--- | :----: |
| [Text](https://github.com/MrAdkhambek/Pack/tree/main/libraries/text) | Context-free string abstraction for clean architecture | [![Maven Central](https://img.shields.io/maven-central/v/com.adkhambek.pack/text.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.adkhambek.pack/text) |
| [Validators](https://github.com/MrAdkhambek/Pack/tree/main/libraries/validators) | Composable input validators with typed error keys | [![Maven Central](https://img.shields.io/maven-central/v/com.adkhambek.pack/validators.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.adkhambek.pack/validators) |
| [SMS](https://github.com/MrAdkhambek/Pack/tree/main/libraries/sms) | Flow-based SMS retrieval using Google Play Services | [![Maven Central](https://img.shields.io/maven-central/v/com.adkhambek.pack/sms.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.adkhambek.pack/sms) |
| [Network](https://github.com/MrAdkhambek/Pack/tree/main/libraries/network) | Flow-based network connectivity monitoring | [![Maven Central](https://img.shields.io/maven-central/v/com.adkhambek.pack/network.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.adkhambek.pack/network) |

## Installation

Add the dependency for the library you need:

```kotlin
dependencies {
    implementation("com.adkhambek.pack:validators:<version>")
    implementation("com.adkhambek.pack:text:<version>")
    implementation("com.adkhambek.pack:text-ktx:<version>")
    implementation("com.adkhambek.pack:sms:<version>")
    implementation("com.adkhambek.pack:network:<version>")
}
```

## Overview

### Text
A `Text` sealed interface that wraps plain strings, string resources, and plurals — allowing your business logic to work with strings without depending on Android `Context`.

```kotlin
val greeting: Text = Text(R.string.hello, "World")
val plain: Text = Text("Hello, World!")

// Resolve on the UI layer
textView.setText(greeting)
```

### Validators
Composable, functional validators with typed error keys. Built-in support for email, password, and username validation. Chain validators with the `and` operator.

```kotlin
val validator = EmailValidator() and PasswordValidator()
val errors: Set<Validator.Key> = validator.validate(input)
```

### SMS
A Kotlin Flow wrapper around Google Play Services SMS Retriever API for OTP and SMS interception.

```kotlin
SmsFlow(context) { sms -> extractOtp(sms) }
    .collect { otp -> handleOtp(otp) }
```

### Network
A Kotlin Flow that emits real-time network connectivity status using `ConnectivityManager`.

```kotlin
NetworkStatusFlow(context)
    .collect { isConnected -> updateUi(isConnected) }
```

## License

```
Copyright 2023 Adkhambek

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
