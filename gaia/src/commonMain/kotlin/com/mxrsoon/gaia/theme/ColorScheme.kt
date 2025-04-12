package com.mxrsoon.gaia.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color
import com.mxrsoon.gaia.theme.tokens.DefaultDarkColorTokens
import com.mxrsoon.gaia.theme.tokens.DefaultLightColorTokens

@Stable
class ColorScheme(
    background: Color,
    onBackground: Color,
    surface: Color,
    onSurface: Color,
    onSurfaceDim: Color,
    primary: Color,
    onPrimary: Color,
    secondary: Color,
    onSecondary: Color,
    outline: Color,
    outlineVariant: Color
) {
    var background by mutableStateOf(background, structuralEqualityPolicy())
        private set

    var onBackground by mutableStateOf(onBackground, structuralEqualityPolicy())
        private set

    var surface by mutableStateOf(surface, structuralEqualityPolicy())
        private set

    var onSurface by mutableStateOf(onSurface, structuralEqualityPolicy())
        private set

    var onSurfaceDim by mutableStateOf(onSurfaceDim, structuralEqualityPolicy())
        private set

    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        private set

    var onPrimary by mutableStateOf(onPrimary, structuralEqualityPolicy())
        private set

    var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
        private set

    var onSecondary by mutableStateOf(onSecondary, structuralEqualityPolicy())
        private set

    var outline by mutableStateOf(outline, structuralEqualityPolicy())
        private set

    var outlineVariant by mutableStateOf(outlineVariant, structuralEqualityPolicy())
        private set

    /** Returns a copy of this ColorScheme, optionally overriding some of the values. */
    fun copy(
        background: Color = this.background,
        onBackground: Color = this.onBackground,
        surface: Color = this.surface,
        onSurface: Color = this.onSurface,
        onSurfaceDim: Color = this.onSurfaceDim,
        primary: Color = this.primary,
        onPrimary: Color = this.onPrimary,
        secondary: Color = this.secondary,
        onSecondary: Color = this.onSecondary,
        outline: Color = this.outline,
        outlineVariant: Color = this.outlineVariant
    ): ColorScheme =
        ColorScheme(
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            onSurfaceDim = onSurfaceDim,
            primary = primary,
            onPrimary = onPrimary,
            secondary = secondary,
            onSecondary = onSecondary,
            outline = outline,
            outlineVariant = outlineVariant
        )

    /**
     * Updates the internal values of a given [ColorScheme] with values from the [other]. This allows efficiently updating
     * a subset of [ColorScheme], without recomposing every composable that consumes values from [LocalColorScheme].
     */
    fun updateColorSchemeFrom(other: ColorScheme) {
        background = other.background
        onBackground = other.onBackground
        surface = other.surface
        onSurface = other.onSurface
        onSurfaceDim = other.onSurfaceDim
        primary = other.primary
        onPrimary = other.onPrimary
        secondary = other.secondary
        onSecondary = other.onSecondary
        outline = other.outline
        outlineVariant = other.outlineVariant
    }
}

/**
 * This function tries to match the provided [backgroundColor] to a 'background' color in this [ColorScheme], and then
 * will return the corresponding color used for content. For example, when [backgroundColor] is [ColorScheme.primary],
 * this will return [ColorScheme.onPrimary].
 */
fun ColorScheme.contentColorFor(backgroundColor: Color): Color =
    when (backgroundColor) {
        primary -> onPrimary
        secondary -> onSecondary
        background -> onBackground
        surface -> onSurface
        else -> Color.Unspecified
    }

/**
 * Create a new color scheme. Any colors that aren't specified, will be set to their light default.
 */
fun lightColorScheme(
    background: Color = DefaultLightColorTokens.Background,
    onBackground: Color = DefaultLightColorTokens.OnBackground,
    surface: Color = DefaultLightColorTokens.Surface,
    onSurface: Color = DefaultLightColorTokens.OnSurface,
    onSurfaceDim: Color = DefaultLightColorTokens.OnSurfaceDim,
    primary: Color = DefaultLightColorTokens.Primary,
    onPrimary: Color = DefaultLightColorTokens.OnPrimary,
    secondary: Color = DefaultLightColorTokens.Secondary,
    onSecondary: Color = DefaultLightColorTokens.OnSecondary,
    outline: Color = DefaultLightColorTokens.Outline,
    outlineVariant: Color = DefaultLightColorTokens.OutlineVariant
): ColorScheme =
    ColorScheme(
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        onSurfaceDim = onSurfaceDim,
        primary = primary,
        onPrimary = onPrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        outline = outline,
        outlineVariant = outlineVariant
    )

/**
 * Create a new color scheme. Any colors that aren't specified, will be set to their light default.
 */
fun darkColorScheme(
    background: Color = DefaultDarkColorTokens.Background,
    onBackground: Color = DefaultDarkColorTokens.OnBackground,
    surface: Color = DefaultDarkColorTokens.Surface,
    onSurface: Color = DefaultDarkColorTokens.OnSurface,
    onSurfaceDim: Color = DefaultDarkColorTokens.OnSurfaceDim,
    primary: Color = DefaultDarkColorTokens.Primary,
    onPrimary: Color = DefaultDarkColorTokens.OnPrimary,
    secondary: Color = DefaultDarkColorTokens.Secondary,
    onSecondary: Color = DefaultDarkColorTokens.OnSecondary,
    outline: Color = DefaultDarkColorTokens.Outline,
    outlineVariant: Color = DefaultDarkColorTokens.OutlineVariant
): ColorScheme =
    ColorScheme(
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        onSurfaceDim = onSurfaceDim,
        primary = primary,
        onPrimary = onPrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        outline = outline,
        outlineVariant = outlineVariant
    )

/**
 * CompositionLocal used to pass [ColorScheme] down the tree.
 *
 * Setting the value here is typically done as part of [GaiaTheme], which will automatically handle
 * efficiently updating any changed colors without causing unnecessary recompositions, using
 * [ColorScheme.updateColorSchemeFrom]. To retrieve the current value of this CompositionLocal, use
 * [GaiaTheme.colorScheme].
 */
val LocalColorScheme = staticCompositionLocalOf { lightColorScheme() }