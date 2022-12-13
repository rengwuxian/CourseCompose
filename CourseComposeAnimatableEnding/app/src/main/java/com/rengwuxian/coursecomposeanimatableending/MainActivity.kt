package com.rengwuxian.coursecomposeanimatableending

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.rengwuxian.coursecomposeanimatableending.ui.theme.CourseComposeAnimatableEndingTheme
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeAnimatableEndingTheme {
        BoxWithConstraints {
          val anim = remember { Animatable(0.dp, Dp.VectorConverter) }
          val animY = remember { Animatable(0.dp, Dp.VectorConverter) }
          val decay = remember { exponentialDecay<Dp>() }
          /*LaunchedEffect(Unit) {
            delay(1000)
            try {
              anim.animateDecay(2000.dp, decay)
            } catch (e: CancellationException) {
              println("完啦！被打断了")
            }
          }
          LaunchedEffect(Unit) {
            delay(1500)
            anim.animateDecay((-1000).dp, decay)
          }*/
          /*LaunchedEffect(Unit) {
            delay(1000)
            anim.animateDecay(2000.dp, decay)
          }
          LaunchedEffect(Unit) {
            delay(1300)
            anim.stop()
          }*/
          LaunchedEffect(Unit) {
            delay(1000)
            var result = anim.animateDecay(4000.dp, decay)
            while (result.endReason == AnimationEndReason.BoundReached) {
              result = anim.animateDecay(- result.endState.velocity, decay)
            }
          }
          LaunchedEffect(Unit) {
            delay(1000)
            animY.animateDecay(2000.dp, decay)
          }
//          anim.updateBounds(0.dp, upperBound = maxWidth - 100.dp)
          animY.updateBounds(upperBound = maxHeight - 100.dp)
          val paddingX = remember(anim.value) {
            var usedValue = anim.value
            while (usedValue >= (maxWidth - 100.dp) * 2) {
              usedValue -= (maxWidth - 100.dp) * 2
            }
            if (usedValue < maxWidth - 100.dp) {
              usedValue
            } else {
              (maxWidth - 100.dp) * 2 - usedValue
            }
          }
          Box(
            Modifier
              .padding(paddingX, animY.value, 0.dp, 0.dp)
              .size(100.dp)
              .background(Color.Green)
          )
        }
      }
    }
  }
}