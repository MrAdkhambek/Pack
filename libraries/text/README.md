## Text - String wrapper between business logic layer and presenter layer
[![Maven Central](https://img.shields.io/maven-central/v/me.adkhambek.pack/text.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22me.adkhambek.gsa%22)


## Plain Text

```kotlin
val text: Text = Text.PlainText("Text here")
// or short version
val text: Text = Text("Text here")
```

## Resource Text

```kotlin
val text: Text = Text.ResText(R.string.app_name)
// or short version
val text: Text = Text(R.string.app_name)

// <string name="hi">Hi %s</string>
val text: Text = Text.ResText(R.string.hi, "Adam")
// or short version
val text: Text = Text(R.string.hi, "Adam")
```