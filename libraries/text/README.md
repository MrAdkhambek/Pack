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
val text: Text = Text.ResText(R.string.hi, listOf("Adam"))
// or shorter version
val text: Text = Text(R.string.hi, "Adam")
```

## Plural Text

```kotlin
// <plurals name="items">
//     <item quantity="one">%d item</item>
//     <item quantity="other">%d items</item>
// </plurals>

val text: Text = Text.PluralText(R.plurals.items, quantity = 5, listOf(5))
// or shorter version
val text: Text = Text(R.plurals.items, quantity = 5, 5)
```

## Extensions

For using on UI layer

```kotlin
val text: Text = TODO()

// Context
context.getString(text)

// Fragment
fragment.getString(text)

// View
view.getString(text)

// TextView
textView.setText(text)
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
    implementation("com.adkhambek.pack:text:${latest_version}")
}
```

Other dependency
-------------
[![Maven Central](https://img.shields.io/maven-central/v/com.adkhambek.pack/text-ktx.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.adkhambek.pack/text-ktx)
```groovy
dependencies {
    //  if you want with extensions
    implementation("com.adkhambek.pack:text-ktx:${latest_version}")
}
```
