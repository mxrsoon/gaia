package com.mxrsoon.gaia.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mxrsoon.gaia.components.button.Button
import com.mxrsoon.gaia.components.header.NavigationHeader
import com.mxrsoon.gaia.components.header.NavigationHeaderDefaults
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
            val listState = rememberLazyListState()

            NavigationHeader(
                modifier = Modifier.fillMaxWidth(),
                title = "Sample",
                showDivider = listState.firstVisibleItemScrollOffset != 0,
                showBackButton = true
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                state = listState
            ) {
                items(100) { item ->
                    Text(
                        modifier = Modifier.padding(vertical = 12.dp),
                        text = "Item #$item"
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(NavigationHeaderDefaults.DividerHeight)
                    .background(GaiaTheme.colorScheme.outlineVariant)
            )

            Button(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                onClick = {}
            ) {
                Text("Continue")
            }
        }
    }
}
