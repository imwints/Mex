package io.github.mex.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

object SettingsTransitions : DestinationStyle.Animated() {
    override val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
        get() = {
            slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(300),
            )
        }

    override val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)?
        get() = {
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(300),
            )
        }
}
