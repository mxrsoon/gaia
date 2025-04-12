package com.mxrsoon.gaia.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mxrsoon.gaia.components.text.Text
import com.mxrsoon.gaia.theme.GaiaTheme
import com.mxrsoon.gaia.theme.darkColorScheme
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class NavigationIcon {
    Back, Close
}

@Composable
fun NavigationHeader(
    title: String,
    modifier: Modifier = Modifier,
    showDivider: Boolean = false,
    leadingIcon: NavigationIcon? = NavigationIcon.Back,
    trailingIcon: NavigationIcon? = null,
    onLeadingIconClick: (() -> Unit)? = null,
    onTrailingIconClick: (() -> Unit)? = null
) {
    Box(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .defaultMinSize(minHeight = NavigationHeaderDefaults.MinHeight),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Leading Icon
            if (leadingIcon != null) {
                // TODO
            } else {
                Spacer(Modifier.width(12.dp))
            }

            // Title
            Text(
                text = title,
                style = GaiaTheme.typography.bodyLarge
            )

            // Trailing Icon
            if (trailingIcon != null) {
                // TODO
            }
        }

        if (showDivider) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(NavigationHeaderDefaults.DividerHeight)
                    .align(Alignment.BottomCenter)
                    .background(GaiaTheme.colorScheme.outlineVariant)
            )
        }
    }
}

@Preview
@Composable
fun NavigationHeaderPreview() {
    GaiaTheme(colorScheme = darkColorScheme()) {
        Box(Modifier.background(GaiaTheme.colorScheme.background)) {
            NavigationHeader(
                title = "Settings",
                showDivider = true,
                onLeadingIconClick = {}
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
    val MinHeight = 64.dp

    /**
     * The height of the divider below the header.
     */
    val DividerHeight = 1.dp
}