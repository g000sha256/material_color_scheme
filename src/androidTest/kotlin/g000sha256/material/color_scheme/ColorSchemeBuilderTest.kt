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

package g000sha256.material.color_scheme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import org.junit.Assert
import org.junit.Test

class ColorSchemeBuilderTest {

    @Test
    fun dark_mode() {
        // Colors generated via https://material-foundation.github.io/material-theme-builder
        val expectedColorScheme = ColorScheme(
            primary = Color(red = 165, green = 211, blue = 149),
            onPrimary = Color(red = 17, green = 56, blue = 11),
            primaryContainer = Color(red = 40, green = 80, blue = 32),
            onPrimaryContainer = Color(red = 192, green = 239, blue = 176),
            inversePrimary = Color(red = 64, green = 104, blue = 54),
            secondary = Color(red = 187, green = 203, blue = 178),
            onSecondary = Color(red = 38, green = 52, blue = 34),
            secondaryContainer = Color(red = 60, green = 75, blue = 55),
            onSecondaryContainer = Color(red = 215, green = 232, blue = 205),
            tertiary = Color(red = 160, green = 207, blue = 210),
            onTertiary = Color(red = 0, green = 55, blue = 57),
            tertiaryContainer = Color(red = 30, green = 77, blue = 80),
            onTertiaryContainer = Color(red = 188, green = 235, blue = 238),
            background = Color(red = 18, green = 20, blue = 16),      // surface
            onBackground = Color(red = 226, green = 227, blue = 220), // onSurface
            surface = Color(red = 18, green = 20, blue = 16),
            onSurface = Color(red = 226, green = 227, blue = 220),
            surfaceVariant = Color(red = 67, green = 72, blue = 63),
            onSurfaceVariant = Color(red = 195, green = 200, blue = 188),
            surfaceTint = Color(red = 165, green = 211, blue = 149),  // primary
            inverseSurface = Color(red = 226, green = 227, blue = 220),
            inverseOnSurface = Color(red = 47, green = 49, blue = 45),
            error = Color(red = 255, green = 180, blue = 168),
            onError = Color(red = 105, green = 1, blue = 0),
            errorContainer = Color(red = 147, green = 1, blue = 0),
            onErrorContainer = Color(red = 255, green = 218, blue = 212),
            outline = Color(red = 141, green = 147, blue = 135),
            outlineVariant = Color(red = 67, green = 72, blue = 63),
            scrim = Color(red = 0, green = 0, blue = 0),
            surfaceBright = Color(red = 56, green = 58, blue = 53),
            surfaceDim = Color(red = 18, green = 20, blue = 16),
            surfaceContainer = Color(red = 30, green = 32, blue = 28),
            surfaceContainerHigh = Color(red = 40, green = 43, blue = 39),
            surfaceContainerHighest = Color(red = 51, green = 53, blue = 49),
            surfaceContainerLow = Color(red = 26, green = 28, blue = 24),
            surfaceContainerLowest = Color(red = 13, green = 15, blue = 11)
        )

        val actualColorScheme = buildColorScheme(
            mode = ColorSchemeMode.Dark,
            primary = Color(red = 0, green = 255, blue = 0)
        )

        compare(expectedColorScheme, actualColorScheme)
    }

    @Test
    fun light_mode() {
        // Colors generated via https://material-foundation.github.io/material-theme-builder
        val expectedColorScheme = ColorScheme(
            primary = Color(red = 64, green = 104, blue = 54),
            onPrimary = Color(red = 255, green = 255, blue = 255),
            primaryContainer = Color(red = 192, green = 239, blue = 176),
            onPrimaryContainer = Color(red = 0, green = 34, blue = 0),
            inversePrimary = Color(red = 165, green = 211, blue = 149),
            secondary = Color(red = 84, green = 99, blue = 77),
            onSecondary = Color(red = 255, green = 255, blue = 255),
            secondaryContainer = Color(red = 215, green = 232, blue = 205),
            onSecondaryContainer = Color(red = 18, green = 31, blue = 14),
            tertiary = Color(red = 56, green = 101, blue = 104),
            onTertiary = Color(red = 255, green = 255, blue = 255),
            tertiaryContainer = Color(red = 188, green = 235, blue = 238),
            onTertiaryContainer = Color(red = 0, green = 32, blue = 34),
            background = Color(red = 249, green = 250, blue = 243), // surface
            onBackground = Color(red = 26, green = 28, blue = 24),  // onSurface
            surface = Color(red = 249, green = 250, blue = 243),
            onSurface = Color(red = 26, green = 28, blue = 24),
            surfaceVariant = Color(red = 223, green = 228, blue = 215),
            onSurfaceVariant = Color(red = 67, green = 72, blue = 63),
            surfaceTint = Color(red = 64, green = 104, blue = 54),  // primary
            inverseSurface = Color(red = 47, green = 49, blue = 45),
            inverseOnSurface = Color(red = 241, green = 241, blue = 235),
            error = Color(red = 192, green = 1, blue = 0),
            onError = Color(red = 255, green = 255, blue = 255),
            errorContainer = Color(red = 255, green = 218, blue = 212),
            onErrorContainer = Color(red = 65, green = 0, blue = 0),
            outline = Color(red = 115, green = 121, blue = 110),
            outlineVariant = Color(red = 195, green = 200, blue = 188),
            scrim = Color(red = 0, green = 0, blue = 0),
            surfaceBright = Color(red = 249, green = 250, blue = 243),
            surfaceDim = Color(red = 218, green = 218, blue = 212),
            surfaceContainer = Color(red = 238, green = 238, blue = 232),
            surfaceContainerHigh = Color(red = 232, green = 233, blue = 226),
            surfaceContainerHighest = Color(red = 226, green = 227, blue = 220),
            surfaceContainerLow = Color(red = 244, green = 244, blue = 237),
            surfaceContainerLowest = Color(red = 255, green = 255, blue = 255)
        )

        val actualColorScheme = buildColorScheme(
            mode = ColorSchemeMode.Light,
            primary = Color(red = 0, green = 255, blue = 0)
        )

        compare(expectedColorScheme, actualColorScheme)
    }

    private fun compare(expectedColorScheme: ColorScheme, actualColorScheme: ColorScheme) {
        compare("primary", expectedColorScheme.primary, actualColorScheme.primary)
        compare("onPrimary", expectedColorScheme.onPrimary, actualColorScheme.onPrimary)
        compare("primaryContainer", expectedColorScheme.primaryContainer, actualColorScheme.primaryContainer)
        compare("onPrimaryContainer", expectedColorScheme.onPrimaryContainer, actualColorScheme.onPrimaryContainer)
        compare("inversePrimary", expectedColorScheme.inversePrimary, actualColorScheme.inversePrimary)
        compare("secondary", expectedColorScheme.secondary, actualColorScheme.secondary)
        compare("onSecondary", expectedColorScheme.onSecondary, actualColorScheme.onSecondary)
        compare("secondaryContainer", expectedColorScheme.secondaryContainer, actualColorScheme.secondaryContainer)
        compare("onSecondaryContainer", expectedColorScheme.onSecondaryContainer, actualColorScheme.onSecondaryContainer)
        compare("tertiary", expectedColorScheme.tertiary, actualColorScheme.tertiary)
        compare("onTertiary", expectedColorScheme.onTertiary, actualColorScheme.onTertiary)
        compare("tertiaryContainer", expectedColorScheme.tertiaryContainer, actualColorScheme.tertiaryContainer)
        compare("onTertiaryContainer", expectedColorScheme.onTertiaryContainer, actualColorScheme.onTertiaryContainer)
        compare("background", expectedColorScheme.background, actualColorScheme.background)
        compare("onBackground", expectedColorScheme.onBackground, actualColorScheme.onBackground)
        compare("surface", expectedColorScheme.surface, actualColorScheme.surface)
        compare("onSurface", expectedColorScheme.onSurface, actualColorScheme.onSurface)
        compare("surfaceVariant", expectedColorScheme.surfaceVariant, actualColorScheme.surfaceVariant)
        compare("onSurfaceVariant", expectedColorScheme.onSurfaceVariant, actualColorScheme.onSurfaceVariant)
        compare("surfaceTint", expectedColorScheme.surfaceTint, actualColorScheme.surfaceTint)
        compare("inverseSurface", expectedColorScheme.inverseSurface, actualColorScheme.inverseSurface)
        compare("inverseOnSurface", expectedColorScheme.inverseOnSurface, actualColorScheme.inverseOnSurface)
        compare("error", expectedColorScheme.error, actualColorScheme.error)
        compare("onError", expectedColorScheme.onError, actualColorScheme.onError)
        compare("errorContainer", expectedColorScheme.errorContainer, actualColorScheme.errorContainer)
        compare("onErrorContainer", expectedColorScheme.onErrorContainer, actualColorScheme.onErrorContainer)
        compare("outline", expectedColorScheme.outline, actualColorScheme.outline)
        compare("outlineVariant", expectedColorScheme.outlineVariant, actualColorScheme.outlineVariant)
        compare("scrim", expectedColorScheme.scrim, actualColorScheme.scrim)
        compare("surfaceBright", expectedColorScheme.surfaceBright, actualColorScheme.surfaceBright)
        compare("surfaceDim", expectedColorScheme.surfaceDim, actualColorScheme.surfaceDim)
        compare("surfaceContainer", expectedColorScheme.surfaceContainer, actualColorScheme.surfaceContainer)
        compare("surfaceContainerHigh", expectedColorScheme.surfaceContainerHigh, actualColorScheme.surfaceContainerHigh)
        compare("surfaceContainerHighest", expectedColorScheme.surfaceContainerHighest, actualColorScheme.surfaceContainerHighest)
        compare("surfaceContainerLow", expectedColorScheme.surfaceContainerLow, actualColorScheme.surfaceContainerLow)
        compare("surfaceContainerLowest", expectedColorScheme.surfaceContainerLowest, actualColorScheme.surfaceContainerLowest)
    }

    private fun compare(name: String, expectedColor: Color, actualColor: Color) {
        val expectedArgb = expectedColor.toArgb()
        val expectedRed = getRed(expectedArgb)
        val expectedGreen = getGreen(expectedArgb)
        val expectedBlue = getBlue(expectedArgb)

        val actualArgb = actualColor.toArgb()
        val actualRed = getRed(actualArgb)
        val actualGreen = getGreen(actualArgb)
        val actualBlue = getBlue(actualArgb)

        assertRange(
            "name=$name, expectedRed=$expectedRed, actualRed=$actualRed",
            expectedRed,
            actualRed - 1..actualRed + 1
        )
        assertRange(
            "name=$name, expectedGreen=$expectedGreen, actualGreen=$actualGreen",
            expectedGreen,
            actualGreen - 1..actualGreen + 1
        )
        assertRange(
            "name=$name, expectedBlue=$expectedBlue, actualBlue=$actualBlue",
            expectedBlue,
            actualBlue - 1..actualBlue + 2 // 2 for error color
        )
    }

    private fun getRed(argb: Int): Int {
        return (argb shr 16) and 0xFF
    }

    private fun getGreen(argb: Int): Int {
        return (argb shr 8) and 0xFF
    }

    private fun getBlue(argb: Int): Int {
        return argb and 0xFF
    }

    private fun assertRange(message: String, value: Int, range: IntRange) {
        val condition = range.contains(value)
        Assert.assertTrue(message, condition)
    }

}