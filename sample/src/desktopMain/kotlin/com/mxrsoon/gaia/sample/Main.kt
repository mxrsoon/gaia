package com.mxrsoon.gaia.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mxrsoon.gaia.sample.resources.Res
import com.mxrsoon.gaia.sample.resources.app_name
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name)
    ) {
        App()
    }
}