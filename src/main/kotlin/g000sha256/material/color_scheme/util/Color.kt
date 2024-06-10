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
import hct.Cam16
import hct.HctSolver

internal fun Color.getHue(): Float {
    val cam16 = toCam16()
    return cam16.hue.toFloat()
}

internal fun Color.changeTone(tone: Float): Color {
    val cam16 = toCam16()
    val hue = cam16.hue.toFloat()
    val chroma = cam16.chroma.toFloat()
    return createColor(hue, chroma, tone)
}

internal fun createColor(hue: Float, chroma: Float, tone: Float): Color {
    val doubleHue = hue.toDouble()
    val doubleChroma = chroma.toDouble()
    val doubleTone = tone.toDouble()
    val argb = HctSolver.solveToInt(doubleHue, doubleChroma, doubleTone)
    return Color(argb)
}

private fun Color.toCam16(): Cam16 {
    val argb = toArgb()
    return Cam16.fromInt(argb)
}