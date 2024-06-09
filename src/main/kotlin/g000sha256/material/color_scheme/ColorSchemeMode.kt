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

import g000sha256.material.color_scheme.annotation.Keep

/**
 * Represents one of two modes for creating Material 3 color schemes.
 *
 * This sealed class defines the possible modes:
 * - [ColorSchemeMode.Dark]: Represents a dark color scheme.
 * - [ColorSchemeMode.Light]: Represents a light color scheme.
 *
 * @see [buildColorScheme]
 */
@Keep
sealed class ColorSchemeMode private constructor() {

    /**
     * Represents a dark color scheme.
     *
     * @see [ColorSchemeMode]
     * @see [buildColorScheme]
     */
    @Keep
    data object Dark : ColorSchemeMode() {

        override fun toString(): String {
            return "ColorSchemeMode.Dark"
        }

    }

    /**
     * Represents a light color scheme.
     *
     * @see [ColorSchemeMode]
     * @see [buildColorScheme]
     */
    @Keep
    data object Light : ColorSchemeMode() {

        override fun toString(): String {
            return "ColorSchemeMode.Light"
        }

    }

}