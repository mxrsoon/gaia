package com.mxrsoon.gaia.components.header

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.unit.dp
import com.mxrsoon.gaia.components.icon.Icon
import com.mxrsoon.gaia.components.icon.IconButton
import com.mxrsoon.gaia.components.text.Text
import com.mxrsoon.gaia.resources.Res
import com.mxrsoon.gaia.resources.arrow_back_ios_new_24px
import com.mxrsoon.gaia.resources.close_24px
import com.mxrsoon.gaia.theme.GaiaTheme
import com.mxrsoon.gaia.theme.darkColorScheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NavigationHeader(
    title: String,
    modifier: Modifier = Modifier,
    showDivider: Boolean = false,
    showBackButton: Boolean = false,
    showCloseButton: Boolean = false,
) {
    Box(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = NavigationHeaderDefaults.MinHeight)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showBackButton) {
                IconButton(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = {}
                ) {
                    Icon(painterResource(Res.drawable.arrow_back_ios_new_24px), null)
                }
            } else {
                Spacer(Modifier.width(12.dp))
            }

            Text(
                text = title,
                style = GaiaTheme.typography.bodyLarge
            )

            Spacer(Modifier.weight(1f))

            if (showCloseButton) {
                IconButton(
                    modifier = Modifier.padding(start = 8.dp),
                    onClick = {}
                ) {
                    Icon(painterResource(Res.drawable.close_24px), null)
                }
            }
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomCenter),
            visible = showDivider
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

@Preview
@Composable
fun NavigationHeaderPreview() {
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
fun NavigationHeaderDividerPreview() {
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
fun NavigationHeaderBackPreview() {
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
fun NavigationHeaderBackDividerPreview() {
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
fun NavigationHeaderClosePreview() {
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
fun NavigationHeaderCloseDividerPreview() {
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
fun NavigationHeaderBackClosePreview() {
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
fun NavigationHeaderBackCloseDividerPreview() {
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