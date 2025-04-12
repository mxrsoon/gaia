package com.mxrsoon.gaia.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mxrsoon.gaia.components.text.Text
import com.mxrsoon.gaia.theme.LocalContentColor
import com.mxrsoon.gaia.theme.GaiaTheme
import com.mxrsoon.gaia.theme.ProvideTextStyle
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Button(
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	shape: Shape = ButtonDefaults.shape,
	colors: ButtonColors = ButtonDefaults.buttonColors(),
	border: BorderStroke? = null,
	contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
	interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
	content: @Composable RowScope.() -> Unit
) {
	val containerColor = colors.containerColor(enabled).value
	val contentColor = colors.contentColor(enabled).value

	CompositionLocalProvider(LocalContentColor provides contentColor) {
		Box(
			modifier = modifier
				.defaultMinSize(
					minWidth = ButtonDefaults.MinWidth,
					minHeight = ButtonDefaults.MinHeight
				)
				.clip(shape)
				.clickable(
					onClick = onClick,
					enabled = enabled,
					role = Role.Button,
					interactionSource = interactionSource,
					indication = null
				)
				.then(if (border != null) Modifier.border(border, shape) else Modifier)
				.background(containerColor, shape)
				.padding(contentPadding),
			contentAlignment = Alignment.Center
		) {
			ProvideTextStyle(value = GaiaTheme.typography.labelLarge.copy(fontSize = 14.sp, fontWeight = FontWeight.Medium)) {
				Row(
					horizontalArrangement = Arrangement.Center,
					verticalAlignment = Alignment.CenterVertically,
					content = content
				)
			}
		}
	}
}

/**
 * Contains the default values used by [Button].
 */
object ButtonDefaults {

	private val HorizontalPadding = 16.dp
	private val VerticalPadding = 12.dp

	private const val DisabledContainerOpacity = 0.12f
	private const val DisabledContentOpacity = 0.38f

	/**
	 * The default content padding used by [Button].
	 */
	val ContentPadding = PaddingValues(
		start = HorizontalPadding,
		top = VerticalPadding,
		end = HorizontalPadding,
		bottom = VerticalPadding
	)

	/**
	 * The default min width applied for all buttons. Note that you can override it by applying
	 * Modifier.widthIn directly on the button composable.
	 */
	val MinWidth = 58.dp

	/**
	 * The default min height applied for all buttons. Note that you can override it by applying
	 * Modifier.heightIn directly on the button composable.
	 */
	val MinHeight = 48.dp

	/**
	 * Default shape for a button.
	 */
	val shape: Shape @Composable get() = RoundedCornerShape(8.dp)

	/**
	 * Creates a [ButtonColors] that represents the default container and content colors used in a
	 * [Button].
	 *
	 * @param containerColor the container color of this [Button] when enabled.
	 * @param contentColor the content color of this [Button] when enabled.
	 * @param disabledContainerColor the container color of this [Button] when not enabled.
	 * @param disabledContentColor the content color of this [Button] when not enabled.
	 */
	@Composable
	fun buttonColors(
		containerColor: Color = GaiaTheme.colorScheme.primary,
		contentColor: Color = GaiaTheme.colorScheme.onPrimary,
		disabledContainerColor: Color = GaiaTheme.colorScheme.onSurface.copy(alpha = DisabledContainerOpacity),
		disabledContentColor: Color = GaiaTheme.colorScheme.onSurface.copy(alpha = DisabledContentOpacity),
	): ButtonColors = ButtonColors(
		containerColor = containerColor,
		contentColor = contentColor,
		disabledContainerColor = disabledContainerColor,
		disabledContentColor = disabledContentColor
	)

	/**
	 * Creates a [ButtonColors] that represents the secondary container and content colors used in a
	 * [Button].
	 *
	 * @param containerColor the container color of this [Button] when enabled.
	 * @param contentColor the content color of this [Button] when enabled.
	 * @param disabledContainerColor the container color of this [Button] when not enabled.
	 * @param disabledContentColor the content color of this [Button] when not enabled.
	 */
	@Composable
	fun secondaryButtonColors(
		containerColor: Color = GaiaTheme.colorScheme.secondary,
		contentColor: Color = GaiaTheme.colorScheme.onSecondary,
		disabledContainerColor: Color = GaiaTheme.colorScheme.onSurface.copy(alpha = DisabledContainerOpacity),
		disabledContentColor: Color = GaiaTheme.colorScheme.onSurface.copy(alpha = DisabledContentOpacity),
	): ButtonColors = ButtonColors(
		containerColor = containerColor,
		contentColor = contentColor,
		disabledContainerColor = disabledContainerColor,
		disabledContentColor = disabledContentColor
	)
}

/**
 * Represents the container and content colors used in a button in different states.
 *
 * @see [ButtonDefaults.buttonColors] for the default colors used in a [Button].
 */
@Immutable
data class ButtonColors(
	private val containerColor: Color,
	private val contentColor: Color,
	private val disabledContainerColor: Color,
	private val disabledContentColor: Color
) {
	/**
	 * Represents the container color for this button, depending on whether the button is [enabled].
	 */
	@Composable
	internal fun containerColor(enabled: Boolean): State<Color> {
		return rememberUpdatedState(if (enabled) containerColor else disabledContainerColor)
	}

	/**
	 * Represents the content color for this button, depending on whether the button is [enabled].
	 */
	@Composable
	internal fun contentColor(enabled: Boolean): State<Color> {
		return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
	}
}

@Preview
@Composable
fun ButtonPreview() {
	GaiaTheme {
		Button(onClick = {}) {
			Text("Hello, world!")
		}
	}
}