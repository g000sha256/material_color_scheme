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

package g000sha256.material.color_scheme.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

internal fun Color.getHue(): Float {
    val hct = toHct()
    return hct[0]
}

internal fun Color.changeTone(tone: Float): Color {
    val hct = toHct()
    val hue = hct[0]
    val chroma = hct[1]
    return createColor(hue, chroma, tone)
}

internal fun createColor(hue: Float, chroma: Float, tone: Float): Color {
    val argb = ColorUtils.M3HCTToColor(hue, chroma, tone)
    return Color(argb)
}

private fun Color.toHct(): FloatArray {
    val argb = toArgb()
    val array = FloatArray(size = 3)
    ColorUtils.colorToM3HCT(argb, array)
    return array
}