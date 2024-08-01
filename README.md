# Dynamic Material 3 color scheme builder for Compose

![License](https://img.shields.io/static/v1?color=green&label=Platform&message=Android)
![License](https://img.shields.io/static/v1?color=orange&label=Platform&message=JVM)

This library provides a builder for creating dynamic color schemes according to the Material 3 guidelines.
You no longer have to export large sets of colors. You can also change the theme of your application in runtime.

## Installation

#### Dependency

> The artifact coordinates have been changed from `com.github.g000sha256:material_color_scheme`
> to `dev.g000sha256:material-color-scheme`

```kotlin
implementation("dev.g000sha256:material-color-scheme:1.3.0")
```

#### Repository

> The repository has been changed from `maven("https://jitpack.io")` to `mavenCentral()`

```kotlin
mavenCentral()
```

## Examples

#### How to build a color scheme using any primary color

```kotlin
val isDark = booleanResource(id = R.bool.is_dark)
val colorScheme = remember(isDark) {
    return@remember buildColorScheme(
        mode = if (isDark) ColorSchemeMode.Dark else ColorSchemeMode.Light,
        primary = Color(red = 0, green = 255, blue = 0)
    )
}
```

#### How to build a color scheme using custom colors

```kotlin
val isDark = booleanResource(id = R.bool.is_dark)
val colorScheme = remember(isDark) {
    return@remember buildColorScheme(
        mode = if (isDark) ColorSchemeMode.Dark else ColorSchemeMode.Light,
        primary = Color(red = 0, green = 255, blue = 0),
        secondary = Color(red = 115, green = 155, blue = 100),
        tertiary = Color(red = 0, green = 160, blue = 170),
        neutral = Color(red = 145, green = 145, blue = 140),
        neutralVariant = Color(red = 140, green = 145, blue = 135),
        error = Color(red = 255, green = 85, blue = 75)
    )
}
```

#### How to override generated colors

```kotlin
val isDark = booleanResource(id = R.bool.is_dark)
val colorScheme = remember(isDark) {
    val scheme = buildColorScheme(
        mode = if (isDark) ColorSchemeMode.Dark else ColorSchemeMode.Light,
        primary = Color(red = 0, green = 255, blue = 0)
    )
    return@remember scheme.copy(
        outline = Color(red = 50, green = 50, blue = 50)
        // override other colors here
    )
}
```

#### How to apply

```kotlin
MaterialTheme(colorScheme = colorScheme) {
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