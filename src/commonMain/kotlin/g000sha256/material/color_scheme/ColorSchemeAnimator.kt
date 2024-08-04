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

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import g000sha256.material.color_scheme.util.ColorScheme

/**
 * Animating [ColorScheme] changes.
 *
 * @param scheme The new [ColorScheme].
 * @param spec The animation that will be used to change each [Color] through time. [spring] by default.
 *
 * @return A new [ColorScheme] with updated colors.
 *
 * @see [ColorScheme]
 * @see [AnimationSpec]
 * @see [Color]
 * @see [spring]
 */
@Composable
public fun animateColorScheme(
    scheme: ColorScheme,
    spec: AnimationSpec<Color> = spring(stiffness = Spring.StiffnessLow)
): ColorScheme {
    return ColorScheme(
        primary = animateColor(scheme.primary, spec),
        onPrimary = animateColor(scheme.onPrimary, spec),
        primaryContainer = animateColor(scheme.primaryContainer, spec),
        onPrimaryContainer = animateColor(scheme.onPrimaryContainer, spec),
        inversePrimary = animateColor(scheme.inversePrimary, spec),
        secondary = animateColor(scheme.secondary, spec),
        onSecondary = animateColor(scheme.onSecondary, spec),
        secondaryContainer = animateColor(scheme.secondaryContainer, spec),
        onSecondaryContainer = animateColor(scheme.onSecondaryContainer, spec),
        tertiary = animateColor(scheme.tertiary, spec),
        onTertiary = animateColor(scheme.onTertiary, spec),
        tertiaryContainer = animateColor(scheme.tertiaryContainer, spec),
        onTertiaryContainer = animateColor(scheme.onTertiaryContainer, spec),
        surface = animateColor(scheme.surface, spec),
        onSurface = animateColor(scheme.onSurface, spec),
        surfaceVariant = animateColor(scheme.surfaceVariant, spec),
        onSurfaceVariant = animateColor(scheme.onSurfaceVariant, spec),
        inverseSurface = animateColor(scheme.inverseSurface, spec),
        inverseOnSurface = animateColor(scheme.inverseOnSurface, spec),
        error = animateColor(scheme.error, spec),
        onError = animateColor(scheme.onError, spec),
        errorContainer = animateColor(scheme.errorContainer, spec),
        onErrorContainer = animateColor(scheme.onErrorContainer, spec),
        outline = animateColor(scheme.outline, spec),
        outlineVariant = animateColor(scheme.outlineVariant, spec),
        scrim = animateColor(scheme.scrim, spec),
        surfaceBright = animateColor(scheme.surfaceBright, spec),
        surfaceDim = animateColor(scheme.surfaceDim, spec),
        surfaceContainer = animateColor(scheme.surfaceContainer, spec),
        surfaceContainerHigh = animateColor(scheme.surfaceContainerHigh, spec),
        surfaceContainerHighest = animateColor(scheme.surfaceContainerHighest, spec),
        surfaceContainerLow = animateColor(scheme.surfaceContainerLow, spec),
        surfaceContainerLowest = animateColor(scheme.surfaceContainerLowest, spec)
    )
}

@Composable
private fun animateColor(color: Color, spec: AnimationSpec<Color>): Color {
    val colorState = animateColorAsState(color, spec)
    return colorState.value
}