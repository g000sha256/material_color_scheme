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
import com.materialkolor.hct.Hct
import com.materialkolor.hct.HctSolver

internal fun Color.getHue(): Float {
    val hct = toHct()
    return hct.hue.toFloat()
}

internal fun Color.changeTone(tone: Float): Color {
    val hct = toHct()
    val doubleTone = tone.toDouble()
    val argb = HctSolver.solveToInt(hct.hue, hct.chroma, doubleTone)
    return Color(argb)
}

internal fun createColor(hue: Float, chroma: Float, tone: Float): Color {
    val doubleHue = hue.toDouble()
    val doubleChroma = chroma.toDouble()
    val doubleTone = tone.toDouble()
    val argb = HctSolver.solveToInt(doubleHue, doubleChroma, doubleTone)
    return Color(argb)
}

private fun Color.toHct(): Hct {
    val argb = toArgb()
    return Hct.fromInt(argb)
}