## Text - String wrapper between business logic layer and ui layer

## Plain Text

```kotlin
val text: Text = Text.PlainText("Text here")
// or shorter version
val text: Text = Text("Text here")
```

## Resource Text

```kotlin
val text: Text = Text.ResText(R.string.app_name)
// or shorter version
val text: Text = Text(R.string.app_name)

// <string name="hi">Hi %s</string>
val text: Text = Text.ResText(R.string.hi, "Adam")
// or shorter version
val text: Text = Text(R.string.hi, "Adam")
```

## Extensions

For using on UI layer

```kotlin
val text: Text = TODO()
context.getString(text)
// or
view.getString(text)
// for TextView
textView.setText(text)
```

-------------

Essential downloads
-------------
[![Maven Central](https://img.shields.io/maven-central/v/me.adkhambek.pack/text.svg?label=Maven%20Central)](https://search.maven.org/artifact/me.adkhambek.pack/text)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation("me.adkhambek.pack:text:${latest_version}")
}
```

Other dependency
-------------
[![Maven Central](https://img.shields.io/maven-central/v/me.adkhambek.pack/text-ktx.svg?label=Maven%20Central)](https://search.maven.org/artifact/me.adkhambek.pack/text-ktx)
```groovy
dependencies {
    //  if you want with extensions
    implementation("me.adkhambek.pack:text-ktx:${latest_version}")
}
```
