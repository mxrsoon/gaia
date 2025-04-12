package com.mxrsoon.gaia.components.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mxrsoon.gaia.theme.GaiaTheme

@Composable
fun FixedButtonLayout(
    modifier: Modifier = Modifier,
    showDivider: Boolean = false,
    windowInsets: WindowInsets = FixedButtonLayoutDefaults.windowInsets,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .padding(FixedButtonLayoutDefaults.ContentPadding),
            verticalArrangement = Arrangement.spacedBy(FixedButtonLayoutDefaults.Spacing),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.TopCenter),
            visible = showDivider,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(FixedButtonLayoutDefaults.DividerHeight)
                    .background(GaiaTheme.colorScheme.outlineVariant)
            )
        }
    }
}

/**
 * Contains the default values used by [FixedButtonLayout].
 */
object FixedButtonLayoutDefaults {

    /**
     * The default padding applied to the content.
     */
    val ContentPadding = 24.dp

    /**
     * The default spacing applied between the buttons.
     */
    val Spacing = 8.dp

    /**
     * The height of the divider.
     */
    val DividerHeight = 1.dp

    /**
     * Recommended insets to be used and consumed by the fixed button layout.
     */
    val windowInsets: WindowInsets
        @Composable
        get() = WindowInsets.safeDrawing.only(
            WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
        )
}
