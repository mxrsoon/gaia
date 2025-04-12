package com.mxrsoon.gaia.components.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import com.mxrsoon.gaia.resources.Res
import com.mxrsoon.gaia.resources.close_24px
import com.mxrsoon.gaia.theme.GaiaTheme
import com.mxrsoon.gaia.theme.LocalContentColor
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = IconButtonDefaults.shape,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .defaultMinSize(IconButtonDefaults.MinSize)
            .size(IconButtonDefaults.Size)
            .clip(shape)
            .background(
                color = colors.containerColor(enabled).value,
                shape = shape
            )
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides colors.contentColor(enabled).value,
            content = content
        )
    }
}

@Preview
@Composable
private fun IconButtonPreview() {
    GaiaTheme {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            IconButton(onClick = {}) {
                Icon(painterResource(Res.drawable.close_24px), null)
            }
        }
    }
}

@Preview
@Composable
private fun IconButtonDisabledPreview() {
    GaiaTheme {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            IconButton(onClick = {}, enabled = false) {
                Icon(painterResource(Res.drawable.close_24px), null)
            }
        }
    }
}

@Preview
@Composable
private fun IconButtonPrimaryPreview() {
    GaiaTheme {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            IconButton(
                onClick = {},
                colors = IconButtonDefaults.primaryIconButtonColors()
            ) {
                Icon(painterResource(Res.drawable.close_24px), null)
            }
        }
    }
}

@Preview
@Composable
private fun IconButtonPrimaryDisabledPreview() {
    GaiaTheme {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            IconButton(
                onClick = {},
                colors = IconButtonDefaults.primaryIconButtonColors(),
                enabled = false
            ) {
                Icon(painterResource(Res.drawable.close_24px), null)
            }
        }
    }
}

/**
 * Represents the container and content colors used in a button in different states.
 *
 * @see [IconButtonDefaults.iconButtonColors] for the default colors used in a [IconButton].
 */
@Immutable
data class IconButtonColors(
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

/**
 * Contains the default values used by [IconButton].
 */
object IconButtonDefaults {

    private const val DisabledContentOpacity = 0.38f

    /**
     * The default min size applied for all icon buttons. Note that you can override it by applying
     * size modifiers directly on the icon button composable.
     */
    val MinSize = 48.dp

    /**
     * The default size applied for all icon buttons. Note that you can override it by applying
     * size modifiers directly on the icon button composable.
     */
    val Size = 48.dp

    /**
     * Default shape for an icon button.
     */
    val shape: Shape @Composable get() = CircleShape

    /**
     * Creates an [IconButtonColors] that represents the default container and content colors used
     * in an [IconButton].
     *
     * @param containerColor the container color of this [IconButton] when enabled.
     * @param contentColor the content color of this [IconButton] when enabled.
     * @param disabledContainerColor the container color of this [IconButton] when not enabled.
     * @param disabledContentColor the content color of this [IconButton] when not enabled.
     */
    @Composable
    fun iconButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = GaiaTheme.colorScheme.onBackground,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = GaiaTheme.colorScheme.onBackground
            .copy(alpha = DisabledContentOpacity),
    ): IconButtonColors = IconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    /**
     * Creates an [IconButtonColors] that represents the container and content colors used
     * in an [IconButton] that represents a primary action.
     *
     * @param containerColor the container color of this [IconButton] when enabled.
     * @param contentColor the content color of this [IconButton] when enabled.
     * @param disabledContainerColor the container color of this [IconButton] when not enabled.
     * @param disabledContentColor the content color of this [IconButton] when not enabled.
     */
    @Composable
    fun primaryIconButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = GaiaTheme.colorScheme.primary,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = GaiaTheme.colorScheme.onBackground
            .copy(alpha = DisabledContentOpacity),
    ): IconButtonColors = IconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
}