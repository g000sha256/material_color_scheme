# Dynamic Material 3 color scheme builder for Compose

![License](https://img.shields.io/static/v1?color=green&label=Platform&message=Android)
![License](https://img.shields.io/static/v1?color=orange&label=Platform&message=JVM)
![License](https://img.shields.io/static/v1?color=blue&label=Platform&message=iOS)
![License](https://img.shields.io/static/v1?color=white&label=Platform&message=MacOS)
![License](https://img.shields.io/static/v1?color=yellow&label=Platform&message=JS)

This library provides a builder for creating dynamic color schemes according to the Material 3 guidelines.
You no longer have to export large sets of colors. You can also change the theme of your application in runtime.

## Installation

### Dependency

```kotlin
implementation("dev.g000sha256:material-color-scheme:1.4.0")
```

> [!NOTE]
> The artifact coordinates have been changed from `com.github.g000sha256:material_color_scheme`
> to `dev.g000sha256:material-color-scheme`

### Repository

```kotlin
mavenCentral()
```

> [!NOTE]
> The repository has been changed from `maven("https://jitpack.io")` to `mavenCentral()`

## Examples

### How to build a color scheme using any primary color

```kotlin
val isDark: Boolean = TODO()
val primaryColor: Color = TODO()
val updatedColorScheme = remember(isDark, primaryColor) {
    return@remember buildColorScheme(
        mode = if (isDark) ColorSchemeMode.Dark else ColorSchemeMode.Light,
        primary = primaryColor
    )
}
MaterialTheme(colorScheme = updatedColorScheme) {
    // content
}
```

### How to build a color scheme using custom colors

```kotlin
val isDark: Boolean = TODO()
val primaryColor: Color = TODO()
val secondaryColor: Color = TODO()
val tertiaryColor: Color = TODO()
val neutralColor: Color = TODO()
val neutralVariantColor: Color = TODO()
val errorColor: Color = TODO()
val updatedColorScheme = remember(isDark, primaryColor, secondaryColor, tertiaryColor, neutralColor, neutralVariantColor, errorColor) {
    return@remember buildColorScheme(
        mode = if (isDark) ColorSchemeMode.Dark else ColorSchemeMode.Light,
        primary = primaryColor,
        secondary = secondaryColor,
        tertiary = tertiaryColor,
        neutral = neutralColor,
        neutralVariant = neutralVariantColor,
        error = errorColor
    )
}
MaterialTheme(colorScheme = updatedColorScheme) {
    // content
}
```

### How to override generated colors

```kotlin
val isDark: Boolean = TODO()
val primaryColor: Color = TODO()
val outlineColor: Color = TODO()
val updatedColorScheme = remember(isDark, primaryColor, outlineColor) {
    val scheme = buildColorScheme(
        mode = if (isDark) ColorSchemeMode.Dark else ColorSchemeMode.Light,
        primary = primaryColor
    )
    return@remember scheme.copy(
        outline = outlineColor,
        // override other colors here
    )
}
MaterialTheme(colorScheme = updatedColorScheme) {
    // content
}
```

### How to animate changes

```kotlin
val isDark: Boolean = TODO()
val primaryColor: Color = TODO()
val updatedColorScheme = remember(isDark, primaryColor) {
    return@remember buildColorScheme(
        mode = if (isDark) ColorSchemeMode.Dark else ColorSchemeMode.Light,
        primary = primaryColor
    )
}
val animatedColorScheme = animateColorScheme(updatedColorScheme)
MaterialTheme(colorScheme = animatedColorScheme) {
    // content
}
```

## Color scheme settings

Color scheme settings are valid for 05.05.2024

### Hue

|     | P (Primary) | S (Secondary) | T (Tertiary) | N (Neutral) | NV (Neutral Variant) | E (Error) |
|-----|:-----------:|:-------------:|:------------:|:-----------:|:--------------------:|:---------:|
| Hue |      -      |       -       |     +60      |      -      |          -           |     -     |

### Chroma

|        | P (Primary) | S (Secondary) | T (Tertiary) | N (Neutral) | NV (Neutral Variant) | E (Error) |
|--------|:-----------:|:-------------:|:------------:|:-----------:|:--------------------:|:---------:|
| Chroma |     36      |      16       |      24      |      4      |          8           |     -     |

### Tone

#### Dark

<img src="images/dark.png" />

#### Light

<img src="images/light.png" />

## Resources

#### Web pages

- [Material 3 color system](https://m3.material.io/styles/color/system/overview)
- [Material theme builder](https://material-foundation.github.io/material-theme-builder)
- [Science of HCT color](https://material.io/blog/science-of-color-design)

#### Figma

- [Material 3 Design Kit](https://www.figma.com/community/file/1035203688168086460)
- [Material theme builder playground](https://www.figma.com/community/plugin/1034969338659738588/material-theme-builder)

#### Figma plugins

- [HCT color picker](https://www.figma.com/community/plugin/1227923985322908257/hct-color-picker)
- [Material theme builder](https://www.figma.com/community/plugin/1034969338659738588/material-theme-builder)