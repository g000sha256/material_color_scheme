# Dynamic Material 3 color scheme builder for Compose

[![JitPack](https://jitpack.io/v/g000sha256/material_color_scheme.svg)](https://jitpack.io/#g000sha256/material_color_scheme)

This multiplatform library provides a builder for creating dynamic color schemes according to the
Material 3 guidelines. You no longer have to export large sets of colors. You can also change the
theme of your application in runtime.

## Installation

#### Dependency

```kotlin
implementation("com.github.g000sha256:material_color_scheme:1.1.0")
```

#### Repository

```kotlin
maven("https://jitpack.io")
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

### Naming

| Primary | Secondary | Tertiary | Neutral | Neutral Variant | Error |
|:-------:|:---------:|:--------:|:-------:|:---------------:|:-----:|
|    P    |     S     |    T     |    N    |       NV        |   E   |

### Hue and Chroma

|        | P  | S  |  T  | N | NV | E |
|--------|:--:|:--:|:---:|:-:|:--:|:-:|
| Hue    | -  | -  | +60 | - | -  | - |
| Chroma | 36 | 16 | 24  | 4 | 8  | - |

### Tone

#### Dark

<img src="images/dark.png" />

#### Light

<img src="images/light.png" />

## Roadmap

- Standard, medium and high contrast
- Demo application

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