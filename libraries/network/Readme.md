## Network state listener

Monitors network connectivity status as a Kotlin `Flow<Boolean>`.

Supported transports: WiFi, Cellular, Ethernet, VPN.

**Requires** `ACCESS_NETWORK_STATE` permission and `minSdk 23`.

## Use as Flow
```kotlin
val context: Context = ...

val networkStatusFlow = NetworkStatusFlow(context)
networkStatusFlow.collect { isConnected: Boolean ->
    // TODO logic here
}
```

```kotlin
val context: Context = ...
val cm = context.getSystemService<ConnectivityManager>()!!

val networkStatusFlow = NetworkStatusFlow(cm)
networkStatusFlow.collect { isConnected: Boolean ->
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
