package com.mxrsoon.gaia.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.text.TextStyle
import com.mxrsoon.gaia.components.text.Text
import com.mxrsoon.gaia.theme.tokens.DefaultTextStyle

/**
 * CompositionLocal containing the preferred [TextStyle] that will be used by [Text] components by default. To set the
 * value for this CompositionLocal, see [ProvideTextStyle] which will merge any missing [TextStyle] properties with the
 * existing [TextStyle] set in this CompositionLocal.
 *
 * @see ProvideTextStyle
 */
val LocalTextStyle = compositionLocalOf(structuralEqualityPolicy()) { DefaultTextStyle }

/**
 * This function is used to set the current value of [LocalTextStyle], merging the given style with the current style
 * values for any missing attributes. Any [Text] components included in this component's [content] will be styled with
 * this style unless styled explicitly.
 *
 * @see LocalTextStyle
 */
@Composable
fun ProvideTextStyle(value: TextStyle, content: @Composable () -> Unit) {
	val mergedStyle = LocalTextStyle.current.merge(value)
	CompositionLocalProvider(LocalTextStyle provides mergedStyle, content = content)
}