## Network state listener

## Use as Flow
```kotlin
val context : Context = ...

val networkStatusFlow = NetworkStatusFlow(context)
networkStatusFlow.collect { state: Boolean ->
    // TODO logic here
}
```

```kotlin
val context : Context = ...
val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

val networkStatusFlow = NetworkStatusFlow(cm)
networkStatusFlow.collect { state: Boolean ->
    // TODO logic here
}
```

Essential downloads
-------------
[![Maven Central](https://img.shields.io/maven-central/v/com.adkhambek.pack/network.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.adkhambek.pack/network)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.adkhambek.pack:network:${latest_version}")
}
```
