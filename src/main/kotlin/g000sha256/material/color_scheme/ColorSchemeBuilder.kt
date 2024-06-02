/*
 * Copyright 2024 Georgii Ippolitov (g000sha256)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Keep

package g000sha256.material.color_scheme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import g000sha256.material.color_scheme.annotation.Keep
import g000sha256.material.color_scheme.util.changeTone
import g000sha256.material.color_scheme.util.createColor
import g000sha256.material.color_scheme.util.getHue

/**
 * This method builds a dynamic Material 3 color scheme based on the provided colors and mode
 * using the HCT color space.
 *
 * @param mode The color scheme mode is either [ColorSchemeMode.Dark] or [ColorSchemeMode.Light],
 * determining whether to generate a dark or light color scheme.
 * @param primary The primary color of the scheme. This color will be used as the basis
 * for generating other colors.
 * @param secondary The secondary color of the scheme (optional, derived from primary if null).
 * @param tertiary The tertiary color of the scheme (optional, derived from primary if null).
 * @param neutral The neutral color of the scheme (optional, derived from primary if null).
 * @param neutralVariant A variant of the neutral color (optional, derived from primary if null).
 * @param error The error color of the scheme.
 *
 * @return A [ColorScheme] object representing the generated color scheme.
 *
 * @see [Color]
 * @see [ColorSchemeMode]
 * @see [ColorSchemeMode.Dark]
 * @see [ColorSchemeMode.Light]
 */
@Keep
fun buildColorScheme(
    mode: ColorSchemeMode,
    primary: Color,
    secondary: Color? = null,
    tertiary: Color? = null,
    neutral: Color? = null,
    neutralVariant: Color? = null,
    error: Color = Color(red = 255, green = 0, blue = 0)
): ColorScheme {
    val primaryHue = primary.getHue()
    val primaryChroma = 36F

    val secondaryHue = secondary?.getHue() ?: primaryHue
    val secondaryChroma = 16F

    val tertiaryHue = tertiary?.getHue() ?: (primaryHue + 60F)
    val tertiaryChroma = 24F

    val neutralHue = neutral?.getHue() ?: primaryHue
    val neutralChroma = 4F

    val neutralVariantHue = neutralVariant?.getHue() ?: primaryHue
    val neutralVariantChroma = 8F

    when (mode) {
        ColorSchemeMode.Dark -> {
            return createDarkColorScheme(
                primaryHue = primaryHue,
                primaryChroma = primaryChroma,
                secondaryHue = secondaryHue,
                secondaryChroma = secondaryChroma,
                tertiaryHue = tertiaryHue,
                tertiaryChroma = tertiaryChroma,
                neutralHue = neutralHue,
                neutralChroma = neutralChroma,
                neutralVariantHue = neutralVariantHue,
                neutralVariantChroma = neutralVariantChroma,
                error = error
            )
        }
        ColorSchemeMode.Light -> {
            return createLightColorScheme(
                primaryHue = primaryHue,
                primaryChroma = primaryChroma,
                secondaryHue = secondaryHue,
                secondaryChroma = secondaryChroma,
                tertiaryHue = tertiaryHue,
                tertiaryChroma = tertiaryChroma,
                neutralHue = neutralHue,
                neutralChroma = neutralChroma,
                neutralVariantHue = neutralVariantHue,
                neutralVariantChroma = neutralVariantChroma,
                error = error
            )
        }
    }
}

@Suppress("SameParameterValue")
private fun createDarkColorScheme(
    primaryHue: Float,
    primaryChroma: Float,
    secondaryHue: Float,
    secondaryChroma: Float,
    tertiaryHue: Float,
    tertiaryChroma: Float,
    neutralHue: Float,
    neutralChroma: Float,
    neutralVariantHue: Float,
    neutralVariantChroma: Float,
    error: Color
): ColorScheme {
    val primary20 = createColor(primaryHue, primaryChroma, tone = 20F)
    val primary30 = createColor(primaryHue, primaryChroma, tone = 30F)
    val primary40 = createColor(primaryHue, primaryChroma, tone = 40F)
    val primary80 = createColor(primaryHue, primaryChroma, tone = 80F)
    val primary90 = createColor(primaryHue, primaryChroma, tone = 90F)

    val secondary20 = createColor(secondaryHue, secondaryChroma, tone = 20F)
    val secondary30 = createColor(secondaryHue, secondaryChroma, tone = 30F)
    val secondary80 = createColor(secondaryHue, secondaryChroma, tone = 80F)
    val secondary90 = createColor(secondaryHue, secondaryChroma, tone = 90F)

    val tertiary20 = createColor(tertiaryHue, tertiaryChroma, tone = 20F)
    val tertiary30 = createColor(tertiaryHue, tertiaryChroma, tone = 30F)
    val tertiary80 = createColor(tertiaryHue, tertiaryChroma, tone = 80F)
    val tertiary90 = createColor(tertiaryHue, tertiaryChroma, tone = 90F)

    val neutral0 = createColor(neutralHue, neutralChroma, tone = 0F)
    val neutral4 = createColor(neutralHue, neutralChroma, tone = 4F)
    val neutral6 = createColor(neutralHue, neutralChroma, tone = 6F)
    val neutral10 = createColor(neutralHue, neutralChroma, tone = 10F)
    val neutral12 = createColor(neutralHue, neutralChroma, tone = 12F)
    val neutral17 = createColor(neutralHue, neutralChroma, tone = 17F)
    val neutral20 = createColor(neutralHue, neutralChroma, tone = 20F)
    val neutral22 = createColor(neutralHue, neutralChroma, tone = 22F)
    val neutral24 = createColor(neutralHue, neutralChroma, tone = 24F)
    val neutral90 = createColor(neutralHue, neutralChroma, tone = 90F)

    val neutralVariant30 = createColor(neutralVariantHue, neutralVariantChroma, tone = 30F)
    val neutralVariant60 = createColor(neutralVariantHue, neutralVariantChroma, tone = 60F)
    val neutralVariant80 = createColor(neutralVariantHue, neutralVariantChroma, tone = 80F)

    val error20 = error.changeTone(tone = 20F)
    val error30 = error.changeTone(tone = 30F)
    val error80 = error.changeTone(tone = 80F)
    val error90 = error.changeTone(tone = 90F)

    return createColorScheme(
        primary = primary80,
        onPrimary = primary20,
        primaryContainer = primary30,
        onPrimaryContainer = primary90,
        inversePrimary = primary40,
        secondary = secondary80,
        onSecondary = secondary20,
        secondaryContainer = secondary30,
        onSecondaryContainer = secondary90,
        tertiary = tertiary80,
        onTertiary = tertiary20,
        tertiaryContainer = tertiary30,
        onTertiaryContainer = tertiary90,
        surface = neutral6, // ColorDarkTokens.Surface = PaletteTokens.Neutral10
        onSurface = neutral90,
        surfaceVariant = neutralVariant30,
        onSurfaceVariant = neutralVariant80,
        inverseSurface = neutral90,
        inverseOnSurface = neutral20,
        error = error80,
        onError = error20,
        errorContainer = error30,
        onErrorContainer = error90,
        outline = neutralVariant60,
        outlineVariant = neutralVariant30,
        scrim = neutral0,
        surfaceBright = neutral24,
        surfaceDim = neutral6,
        surfaceContainer = neutral12,
        surfaceContainerHigh = neutral17,
        surfaceContainerHighest = neutral22,
        surfaceContainerLow = neutral10,
        surfaceContainerLowest = neutral4
    )
}

@Suppress("SameParameterValue")
private fun createLightColorScheme(
    primaryHue: Float,
    primaryChroma: Float,
    secondaryHue: Float,
    secondaryChroma: Float,
    tertiaryHue: Float,
    tertiaryChroma: Float,
    neutralHue: Float,
    neutralChroma: Float,
    neutralVariantHue: Float,
    neutralVariantChroma: Float,
    error: Color
): ColorScheme {
    val primary10 = createColor(primaryHue, primaryChroma, tone = 10F)
    val primary40 = createColor(primaryHue, primaryChroma, tone = 40F)
    val primary80 = createColor(primaryHue, primaryChroma, tone = 80F)
    val primary90 = createColor(primaryHue, primaryChroma, tone = 90F)
    val primary100 = createColor(primaryHue, primaryChroma, tone = 100F)

    val secondary10 = createColor(secondaryHue, secondaryChroma, tone = 10F)
    val secondary40 = createColor(secondaryHue, secondaryChroma, tone = 40F)
    val secondary90 = createColor(secondaryHue, secondaryChroma, tone = 90F)
    val secondary100 = createColor(secondaryHue, secondaryChroma, tone = 100F)

    val tertiary10 = createColor(tertiaryHue, tertiaryChroma, tone = 10F)
    val tertiary40 = createColor(tertiaryHue, tertiaryChroma, tone = 40F)
    val tertiary90 = createColor(tertiaryHue, tertiaryChroma, tone = 90F)
    val tertiary100 = createColor(tertiaryHue, tertiaryChroma, tone = 100F)

    val neutral0 = createColor(neutralHue, neutralChroma, tone = 0F)
    val neutral10 = createColor(neutralHue, neutralChroma, tone = 10F)
    val neutral20 = createColor(neutralHue, neutralChroma, tone = 20F)
    val neutral87 = createColor(neutralHue, neutralChroma, tone = 87F)
    val neutral90 = createColor(neutralHue, neutralChroma, tone = 90F)
    val neutral92 = createColor(neutralHue, neutralChroma, tone = 92F)
    val neutral94 = createColor(neutralHue, neutralChroma, tone = 94F)
    val neutral95 = createColor(neutralHue, neutralChroma, tone = 95F)
    val neutral96 = createColor(neutralHue, neutralChroma, tone = 96F)
    val neutral98 = createColor(neutralHue, neutralChroma, tone = 98F)
    val neutral100 = createColor(neutralHue, neutralChroma, tone = 100F)

    val neutralVariant30 = createColor(neutralVariantHue, neutralVariantChroma, tone = 30F)
    val neutralVariant50 = createColor(neutralVariantHue, neutralVariantChroma, tone = 50F)
    val neutralVariant80 = createColor(neutralVariantHue, neutralVariantChroma, tone = 80F)
    val neutralVariant90 = createColor(neutralVariantHue, neutralVariantChroma, tone = 90F)

    val error10 = error.changeTone(tone = 10F)
    val error40 = error.changeTone(tone = 40F)
    val error90 = error.changeTone(tone = 90F)
    val error100 = error.changeTone(tone = 100F)

    return createColorScheme(
        primary = primary40,
        onPrimary = primary100,
        primaryContainer = primary90,
        onPrimaryContainer = primary10,
        inversePrimary = primary80,
        secondary = secondary40,
        onSecondary = secondary100,
        secondaryContainer = secondary90,
        onSecondaryContainer = secondary10,
        tertiary = tertiary40,
        onTertiary = tertiary100,
        tertiaryContainer = tertiary90,
        onTertiaryContainer = tertiary10,
        surface = neutral98, // ColorLightTokens.Surface = PaletteTokens.Neutral99
        onSurface = neutral10,
        surfaceVariant = neutralVariant90,
        onSurfaceVariant = neutralVariant30,
        inverseSurface = neutral20,
        inverseOnSurface = neutral95,
        error = error40,
        onError = error100,
        errorContainer = error90,
        onErrorContainer = error10,
        outline = neutralVariant50,
        outlineVariant = neutralVariant80,
        scrim = neutral0,
        surfaceBright = neutral98,
        surfaceDim = neutral87,
        surfaceContainer = neutral94,
        surfaceContainerHigh = neutral92,
        surfaceContainerHighest = neutral90,
        surfaceContainerLow = neutral96,
        surfaceContainerLowest = neutral100
    )
}

private fun createColorScheme(
    primary: Color,
    onPrimary: Color,
    primaryContainer: Color,
    onPrimaryContainer: Color,
    inversePrimary: Color,
    secondary: Color,
    onSecondary: Color,
    secondaryContainer: Color,
    onSecondaryContainer: Color,
    tertiary: Color,
    onTertiary: Color,
    tertiaryContainer: Color,
    onTertiaryContainer: Color,
    surface: Color,
    onSurface: Color,
    surfaceVariant: Color,
    onSurfaceVariant: Color,
    inverseSurface: Color,
    inverseOnSurface: Color,
    error: Color,
    onError: Color,
    errorContainer: Color,
    onErrorContainer: Color,
    outline: Color,
    outlineVariant: Color,
    scrim: Color,
    surfaceBright: Color,
    surfaceDim: Color,
    surfaceContainer: Color,
    surfaceContainerHigh: Color,
    surfaceContainerHighest: Color,
    surfaceContainerLow: Color,
    surfaceContainerLowest: Color
): ColorScheme {
    return ColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        inversePrimary = inversePrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        background = surface,     // surface
        onBackground = onSurface, // onSurface
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        surfaceTint = primary,    // primary
        inverseSurface = inverseSurface,
        inverseOnSurface = inverseOnSurface,
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        outline = outline,
        outlineVariant = outlineVariant,
        scrim = scrim,
        surfaceBright = surfaceBright,
        surfaceDim = surfaceDim,
        surfaceContainer = surfaceContainer,
        surfaceContainerHigh = surfaceContainerHigh,
        surfaceContainerHighest = surfaceContainerHighest,
        surfaceContainerLow = surfaceContainerLow,
        surfaceContainerLowest = surfaceContainerLowest
    )
}