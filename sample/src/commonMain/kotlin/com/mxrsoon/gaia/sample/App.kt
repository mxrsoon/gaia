package com.mxrsoon.gaia.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mxrsoon.gaia.components.button.Button
import com.mxrsoon.gaia.components.header.NavigationHeader
import com.mxrsoon.gaia.components.header.NavigationIcon
import com.mxrsoon.gaia.components.text.Text
import com.mxrsoon.gaia.theme.GaiaTheme
import com.mxrsoon.gaia.theme.darkColorScheme
import com.mxrsoon.gaia.theme.lightColorScheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun App() {
    GaiaTheme(
        colorScheme = when (isSystemInDarkTheme()) {
            true -> darkColorScheme()
            else -> lightColorScheme()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GaiaTheme.colorScheme.background)
                .safeDrawingPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationHeader(
                modifier = Modifier.fillMaxWidth(),
                title = "Sample",
                showDivider = true,
                leadingIcon = null,
                trailingIcon = null,
                onLeadingIconClick = {},
                onTrailingIconClick = {}
            )

            Spacer(Modifier.weight(1f))

            Text("Hello, world!")

            Spacer(Modifier.weight(1f))

            Button(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                onClick = {}
            ) {
                Text("Continue")
            }
        }
    }
}
