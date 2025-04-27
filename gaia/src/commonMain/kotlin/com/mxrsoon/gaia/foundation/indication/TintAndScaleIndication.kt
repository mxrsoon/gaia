package com.mxrsoon.gaia.foundation.indication

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.LayoutAwareModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.requireDensity
import androidx.compose.ui.node.requireLayoutDirection
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import com.mxrsoon.gaia.theme.LocalContentColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Stable
fun tintAndScaleIndication(shape: Shape? = null): IndicationNodeFactory =
    shape?.let { ShapedTintAndScaleIndication(shape) } ?: TintAndScaleIndication

private object TintAndScaleIndication : IndicationNodeFactory {

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return TintAndScaleIndicationNode(interactionSource)
    }

    override fun hashCode(): Int = -1

    override fun equals(other: Any?) = other === this
}


@Stable
private class ShapedTintAndScaleIndication(private val shape: Shape) : IndicationNodeFactory {

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return RoundedTintAndScaleIndicationNode(interactionSource, shape)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShapedTintAndScaleIndication) return false

        return shape != other.shape
    }

    override fun hashCode(): Int = shape.hashCode()
}

private class TintAndScaleIndicationNode(
    interactionSource: InteractionSource
) : BaseTintAndScaleIndicationNode(interactionSource) {

    override fun ContentDrawScope.draw() {
        scale(animatedScalePercent.value) {
            this@draw.drawContent()

            if (animatedAlpha.value > 0) {
                drawRect(
                    color = currentValueOf(LocalContentColor),
                    alpha = animatedAlpha.value
                )
            }
        }
    }
}

private class RoundedTintAndScaleIndicationNode(
    interactionSource: InteractionSource,
    private val shape: Shape
) : BaseTintAndScaleIndicationNode(interactionSource), LayoutAwareModifierNode {

    private var outline: Outline? by mutableStateOf(null)

    override fun onRemeasured(size: IntSize) {
        super.onRemeasured(size)

        outline = shape.createOutline(
            size = size.toSize(),
            layoutDirection = requireLayoutDirection(),
            density = requireDensity()
        )
    }

    override fun ContentDrawScope.draw() {
        scale(animatedScalePercent.value) {
            this@draw.drawContent()

            if (animatedAlpha.value > 0) {
                outline?.let { outline ->
                    when (outline) {
                        is Outline.Generic -> {
                            drawPath(
                                path = outline.path,
                                color = currentValueOf(LocalContentColor),
                                alpha = animatedAlpha.value
                            )
                        }

                        is Outline.Rectangle -> {
                            drawRect(
                                topLeft = outline.rect.topLeft,
                                size = outline.rect.size,
                                color = currentValueOf(LocalContentColor),
                                alpha = animatedAlpha.value
                            )
                        }

                        is Outline.Rounded -> {
                            drawPath(
                                path = Path().apply { addRoundRect(outline.roundRect) },
                                color = currentValueOf(LocalContentColor),
                                alpha = animatedAlpha.value
                            )
                        }
                    }
                }
            }
        }
    }
}

private abstract class BaseTintAndScaleIndicationNode(
    private val interactionSource: InteractionSource
) : Modifier.Node(), DrawModifierNode, CompositionLocalConsumerModifierNode {

    protected val animatedScalePercent = Animatable(1f)
    protected val animatedAlpha = Animatable(0f)
    private var isFocused = false
    private var isHovered = false
    private var isPressed = false
    private var isDragged = false

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> { isPressed = true }
                    is PressInteraction.Release -> { isPressed = false }
                    is PressInteraction.Cancel -> { isPressed = false }
                    is HoverInteraction.Enter -> { isHovered = true }
                    is HoverInteraction.Exit -> { isHovered = false }
                    is DragInteraction.Start -> { isDragged = true }
                    is DragInteraction.Stop -> { isDragged = false }
                    is DragInteraction.Cancel -> { isDragged = false }
                    is FocusInteraction.Focus -> { isFocused = true }
                    is FocusInteraction.Unfocus -> { isFocused = false }
                }

                updateAnimation()
            }
        }
    }

    private fun updateAnimation() {
        when {
            isDragged -> animateToDragged()
            isPressed -> animateToPressed()
            isFocused -> animateToFocused()
            isHovered -> animateToHovered()
            else -> animateToResting()
        }
    }

    private fun animateToDragged() {
        coroutineScope.launch { animatedScalePercent.animateTo(0.97f, spring()) }
        coroutineScope.launch { animatedAlpha.animateTo(0.16f, spring()) }
    }

    private fun animateToPressed() {
        coroutineScope.launch { animatedScalePercent.animateTo(0.98f, spring()) }
        coroutineScope.launch { animatedAlpha.animateTo(0.12f, spring()) }
    }

    private fun animateToFocused() {
        coroutineScope.launch { animatedScalePercent.animateTo(0.98f, spring()) }
        coroutineScope.launch { animatedAlpha.animateTo(0.12f, spring()) }
    }

    private fun animateToHovered() {
        coroutineScope.launch { animatedScalePercent.animateTo(0.99f, spring()) }
        coroutineScope.launch { animatedAlpha.animateTo(0.08f, spring()) }
    }

    private fun animateToResting() {
        coroutineScope.launch { animatedScalePercent.animateTo(1f, spring()) }
        coroutineScope.launch { animatedAlpha.animateTo(0f, spring()) }
    }
}