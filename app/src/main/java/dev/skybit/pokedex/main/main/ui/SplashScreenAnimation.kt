package dev.skybit.pokedex.main.main.ui

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider

object SplashScreenAnimation {
    const val SPLASH_SCREEN_ZOOM_DURATION = 1800L
    private const val SPLASH_SCREEN_ROTATION_DURATION = 700L
    private const val SPLASH_SCREEN_ROTATION_ANGLE = 1080f

    fun runOnSplashScreenExitAnimation(splashScreen: SplashScreen) {
        splashScreen.setOnExitAnimationListener { screen ->
            val zoomX = createZoomXAnimation(screen)
            val zoomY = createZoomYAnimation(screen)
            val rotationAnimation = createRotationAnimation(screen)

            rotationAnimation.doOnEnd { screen.remove() }

            zoomX.start()
            zoomY.start()
            rotationAnimation.start()
        }
    }

    private fun createZoomXAnimation(screen: SplashScreenViewProvider): ObjectAnimator {
        val zoomX = ObjectAnimator.ofFloat(
            screen.iconView,
            View.SCALE_X,
            0.6f,
            0.0f
        )
        zoomX.interpolator = OvershootInterpolator()
        zoomX.duration = SPLASH_SCREEN_ZOOM_DURATION
        return zoomX
    }

    private fun createZoomYAnimation(screen: SplashScreenViewProvider): ObjectAnimator {
        val zoomY = ObjectAnimator.ofFloat(
            screen.iconView,
            View.SCALE_Y,
            0.3f,
            0.0f
        )
        zoomY.interpolator = OvershootInterpolator()
        zoomY.duration = SPLASH_SCREEN_ZOOM_DURATION
        return zoomY
    }

    private fun createRotationAnimation(screen: SplashScreenViewProvider): ObjectAnimator {
        val rotationAnimation: ObjectAnimator = ObjectAnimator.ofFloat(
            screen.iconView,
            View.ROTATION,
            0f,
            SPLASH_SCREEN_ROTATION_ANGLE
        )
        rotationAnimation.interpolator = OvershootInterpolator()
        rotationAnimation.duration = SPLASH_SCREEN_ROTATION_DURATION
        return rotationAnimation
    }
}
