package com.mxrsoon.gaia.components.textfield

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mxrsoon.gaia.theme.GaiaTheme
import com.mxrsoon.gaia.components.icon.Icon

@Composable
fun TextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isError: Boolean = false,
    isFocused: Boolean = false,
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null
) {
    val borderColor = when {
        isError -> GaiaTheme.colorScheme.error
        isFocused -> GaiaTheme.colorScheme.primary
        else -> GaiaTheme.colorScheme.outline
    }

    val placeholderOffsetY by animateDpAsState(
        targetValue = if (isFocused || value.text.isNotEmpty()) (-20).dp else 0.dp,
        animationSpec = tween(durationMillis = 300)
    )

    val placeholderFontSize by animateFloatAsState(
        targetValue = if (isFocused || value.text.isNotEmpty()) 12f else 16f,
        animationSpec = tween(durationMillis = 300)
    )

    val placeholderColor = if (isFocused) GaiaTheme.colorScheme.primary else GaiaTheme.colorScheme.onSurfaceDim

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = GaiaTheme.colorScheme.background,
                shape = RoundedCornerShape(8.dp)
            )
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon?.let {
            Box(modifier = Modifier.padding(end = 8.dp)) {
                it()
            }
        }

        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = placeholder,
                style = GaiaTheme.typography.bodyMedium.copy(
                    color = placeholderColor,
                    fontSize = placeholderFontSize.sp,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.offset(y = placeholderOffsetY)
            )

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = GaiaTheme.typography.bodyMedium.copy(color = GaiaTheme.colorScheme.onBackground)
            )
        }

        trailingIcon?.let {
            Box(modifier = Modifier.padding(start = 8.dp)) {
                it()
            }
        }
    }
}