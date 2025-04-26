package com.mxrsoon.gaia.components.header

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mxrsoon.gaia.components.icon.Icon
import com.mxrsoon.gaia.components.icon.IconButton
import com.mxrsoon.gaia.components.text.Text
import com.mxrsoon.gaia.resources.Res
import com.mxrsoon.gaia.resources.account_circle_24px
import com.mxrsoon.gaia.resources.arrow_back_ios_new_24px
import com.mxrsoon.gaia.resources.close_24px
import com.mxrsoon.gaia.resources.filter_alt_24px
import com.mxrsoon.gaia.resources.menu_24px
import com.mxrsoon.gaia.theme.GaiaTheme
import com.mxrsoon.gaia.theme.darkColorScheme
import com.mxrsoon.gaia.theme.lightColorScheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NavigationHeader(
    title: String,
    modifier: Modifier = Modifier,
    showDivider: Boolean = false,
    navigationButton: (@Composable () -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    windowInsets: WindowInsets = NavigationHeaderDefaults.windowInsets
) {
    Box(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = NavigationHeaderDefaults.MinHeight)
                .windowInsetsPadding(windowInsets)
                .padding(
                    horizontal = NavigationHeaderDefaults.HorizontalContentPadding,
                    vertical = NavigationHeaderDefaults.VerticalContentPadding
                ),
            horizontalArrangement = Arrangement.spacedBy(NavigationHeaderDefaults.Spacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationButton?.invoke()
                ?: Spacer(Modifier.width(NavigationHeaderDefaults.ExtraHorizontalPadding))

            Text(
                text = title,
                style = GaiaTheme.typography.bodyLarge
            )

            Spacer(Modifier.weight(1f))

            actions?.invoke(this@Row)
                ?: Spacer(Modifier.width(NavigationHeaderDefaults.ExtraHorizontalPadding))
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomCenter),
            visible = showDivider,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(NavigationHeaderDefaults.DividerHeight)
                    .background(GaiaTheme.colorScheme.outlineVariant)
            )
        }
    }
}

@Composable
fun NavigationHeader(
    title: String,
    modifier: Modifier = Modifier,
    showDivider: Boolean = false,
    showBackButton: Boolean = false,
    onBackButtonClick: (() -> Unit)? = null,
    showCloseButton: Boolean = false,
    onCloseButtonClick: (() -> Unit)? = null
) {
    val backButton: (@Composable () -> Unit)? = if (showBackButton) {
        @Composable {
            IconButton(onClick = onBackButtonClick ?: {}) {
                Icon(painterResource(Res.drawable.arrow_back_ios_new_24px), null)
            }
        }
    } else null

    val closeButton: (@Composable RowScope.() -> Unit)? = if (showCloseButton) {
        @Composable {
            IconButton(onClick = onCloseButtonClick ?: {}) {
                Icon(painterResource(Res.drawable.close_24px), null)
            }
        }
    } else null

    NavigationHeader(
        title = title,
        modifier = modifier,
        showDivider = showDivider,
        navigationButton = backButton,
        actions = closeButton
    )
}

@Preview
@Composable
private fun NavigationHeaderDarkPreview() {
    GaiaTheme(colorScheme = darkColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = false
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderDividerDarkPreview() {
    GaiaTheme(colorScheme = darkColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderBackDarkPreview() {
    GaiaTheme(colorScheme = darkColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = false,
                showBackButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderBackDividerDarkPreview() {
    GaiaTheme(colorScheme = darkColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = true,
                showBackButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderCloseDarkPreview() {
    GaiaTheme(colorScheme = darkColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = false,
                showCloseButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderCloseDividerDarkPreview() {
    GaiaTheme(colorScheme = darkColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = true,
                showCloseButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderBackCloseDarkPreview() {
    GaiaTheme(colorScheme = darkColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = false,
                showBackButton = true,
                showCloseButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderBackCloseDividerDarkPreview() {
    GaiaTheme(colorScheme = darkColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = true,
                showBackButton = true,
                showCloseButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderCustomButtonsDarkPreview() {
    GaiaTheme(colorScheme = darkColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = false,
                navigationButton = {
                    IconButton(onClick = {}) {
                        Icon(painterResource(Res.drawable.menu_24px), null)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(painterResource(Res.drawable.filter_alt_24px), null)
                    }

                    IconButton(onClick = {}) {
                        Icon(painterResource(Res.drawable.account_circle_24px), null)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderLightPreview() {
    GaiaTheme(colorScheme = lightColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = false
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderDividerLightPreview() {
    GaiaTheme(colorScheme = lightColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderBackLightPreview() {
    GaiaTheme(colorScheme = lightColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = false,
                showBackButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderBackDividerLightPreview() {
    GaiaTheme(colorScheme = lightColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = true,
                showBackButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderCloseLightPreview() {
    GaiaTheme(colorScheme = lightColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = false,
                showCloseButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderCloseDividerLightPreview() {
    GaiaTheme(colorScheme = lightColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = true,
                showCloseButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderBackCloseLightPreview() {
    GaiaTheme(colorScheme = lightColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = false,
                showBackButton = true,
                showCloseButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderBackCloseDividerLightPreview() {
    GaiaTheme(colorScheme = lightColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = true,
                showBackButton = true,
                showCloseButton = true
            )
        }
    }
}

@Preview
@Composable
private fun NavigationHeaderCustomButtonsLightPreview() {
    GaiaTheme(colorScheme = lightColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Title",
                showDivider = false,
                navigationButton = {
                    IconButton(onClick = {}) {
                        Icon(painterResource(Res.drawable.menu_24px), null)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(painterResource(Res.drawable.filter_alt_24px), null)
                    }

                    IconButton(onClick = {}) {
                        Icon(painterResource(Res.drawable.account_circle_24px), null)
                    }
                }
            )
        }
    }
}

/**
 * Contains the default values used by [NavigationHeader].
 */
object NavigationHeaderDefaults {

    /**
     * The default min height applied for headers.
     */
    internal val MinHeight = 64.dp

    /**
     * The height of the divider below the header.
     */
    internal val DividerHeight = 1.dp

    /**
     * The default spacing applied between elements of the header.
     */
    internal val Spacing = 8.dp

    /**
     * Vertical padding applied around elements of the header.
     */
    internal val VerticalContentPadding = 8.dp

    /**
     * Horizontal padding applied around elements of the header.
     */
    internal val HorizontalContentPadding = 12.dp

    /**
     * Extra horizontal padding applied around elements when no buttons are present in a side of
     * the header.
     */
    internal val ExtraHorizontalPadding = 4.dp

    /**
     * Recommended insets to be used and consumed by the navigation header.
     */
    val windowInsets: WindowInsets
        @Composable
        get() = WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
}
