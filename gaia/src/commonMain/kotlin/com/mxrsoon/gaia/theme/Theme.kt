package com.mxrsoon.gaia.theme

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.mxrsoon.gaia.resources.Inter_Black
import com.mxrsoon.gaia.resources.Inter_BlackItalic
import com.mxrsoon.gaia.resources.Inter_Bold
import com.mxrsoon.gaia.resources.Inter_BoldItalic
import com.mxrsoon.gaia.resources.Inter_ExtraBold
import com.mxrsoon.gaia.resources.Inter_ExtraBoldItalic
import com.mxrsoon.gaia.resources.Inter_ExtraLight
import com.mxrsoon.gaia.resources.Inter_ExtraLightItalic
import com.mxrsoon.gaia.resources.Inter_Italic
import com.mxrsoon.gaia.resources.Inter_Light
import com.mxrsoon.gaia.resources.Inter_LightItalic
import com.mxrsoon.gaia.resources.Inter_Medium
import com.mxrsoon.gaia.resources.Inter_MediumItalic
import com.mxrsoon.gaia.resources.Inter_Regular
import com.mxrsoon.gaia.resources.Inter_SemiBold
import com.mxrsoon.gaia.resources.Inter_SemiBoldItalic
import com.mxrsoon.gaia.resources.Inter_Thin
import com.mxrsoon.gaia.resources.Inter_ThinItalic
import com.mxrsoon.gaia.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

/**
 * Defines theming for components of the Multi design system in this hierarchy.
 *
 * @param colorScheme A complete definition of the color theme for this hierarchy
 * @param typography A set of text styles to be used as this hierarchy's typography system
 */
@Composable
fun GaiaTheme(
    colorScheme: ColorScheme = GaiaTheme.colorScheme,
    typography: Typography = GaiaTheme.typography.withDefaultFontFamily(DefaultFontFamily),
    content: @Composable () -> Unit
) {
	val rememberedColorScheme = remember {
		// Explicitly creating a new object here so we don't mutate the initial [colorScheme]
		// provided, and overwrite the values set in it.
		colorScheme.copy()
	}.apply {
		updateColorSchemeFrom(colorScheme)
	}

	val selectionColors = rememberTextSelectionColors(rememberedColorScheme)

	CompositionLocalProvider(
		LocalColorScheme provides rememberedColorScheme,
		LocalTextSelectionColors provides selectionColors,
		LocalTypography provides typography,
		LocalContentColor provides rememberedColorScheme.onBackground
	) {
		ProvideTextStyle(
			value = typography.bodyLarge,
			content = content
		)
	}
}


/**
 * Contains functions to access the current theme values provided at the call site's position in
 * the hierarchy.
 */
object GaiaTheme {

	/**
	 * Retrieves the current [ColorScheme] at the call site's position in the hierarchy.
	 */
	val colorScheme: ColorScheme
		@Composable
		@ReadOnlyComposable
		get() = LocalColorScheme.current

	/**
	 * Retrieves the current [Typography] at the call site's position in the hierarchy.
	 */
	val typography: Typography
		@Composable
		@ReadOnlyComposable
		get() = LocalTypography.current
}

private val DefaultFontFamily
	@OptIn(ExperimentalResourceApi::class)
	@Composable
	get() = FontFamily(
        Font(Res.font.Inter_Thin, weight = FontWeight.Thin, style = FontStyle.Normal),
		Font(Res.font.Inter_ThinItalic, weight = FontWeight.Thin, style = FontStyle.Italic),
		Font(Res.font.Inter_ExtraLight, weight = FontWeight.ExtraLight, style = FontStyle.Normal),
		Font(Res.font.Inter_ExtraLightItalic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
		Font(Res.font.Inter_Light, weight = FontWeight.Light, style = FontStyle.Normal),
		Font(Res.font.Inter_LightItalic, weight = FontWeight.Light, style = FontStyle.Italic),
		Font(Res.font.Inter_Regular, weight = FontWeight.Normal, style = FontStyle.Normal),
		Font(Res.font.Inter_Italic, weight = FontWeight.Normal, style = FontStyle.Italic),
		Font(Res.font.Inter_Medium, weight = FontWeight.Medium, style = FontStyle.Normal),
		Font(Res.font.Inter_MediumItalic, weight = FontWeight.Medium, style = FontStyle.Italic),
		Font(Res.font.Inter_SemiBold, weight = FontWeight.SemiBold, style = FontStyle.Normal),
		Font(Res.font.Inter_SemiBoldItalic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
		Font(Res.font.Inter_Bold, weight = FontWeight.Bold, style = FontStyle.Normal),
		Font(Res.font.Inter_BoldItalic, weight = FontWeight.Bold, style = FontStyle.Italic),
		Font(Res.font.Inter_ExtraBold, weight = FontWeight.ExtraBold, style = FontStyle.Normal),
		Font(Res.font.Inter_ExtraBoldItalic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
		Font(Res.font.Inter_Black, weight = FontWeight.Black, style = FontStyle.Normal),
		Font(Res.font.Inter_BlackItalic, weight = FontWeight.Black, style = FontStyle.Italic)
	)

@Composable
internal fun rememberTextSelectionColors(colorScheme: ColorScheme): TextSelectionColors {
	val primaryColor = colorScheme.primary

	return remember(primaryColor) {
		TextSelectionColors(
			handleColor = primaryColor,
			backgroundColor = primaryColor.copy(alpha = TextSelectionBackgroundOpacity),
		)
	}
}

internal const val TextSelectionBackgroundOpacity = 0.4f