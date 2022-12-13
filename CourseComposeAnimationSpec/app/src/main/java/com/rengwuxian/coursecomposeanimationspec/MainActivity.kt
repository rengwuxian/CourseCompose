package com.rengwuxian.coursecomposeanimationspec

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposeanimationspec.ui.theme.CourseComposeAnimationSpecTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    var big by mutableStateOf(false)

    setContent {
      val size by animateDpAsState(if (big) 96.dp else 48.dp) // State
      val size1 = remember(big) { if (big) 96.dp else 48.dp }
      val float1 = remember(big) { if (big) 100f else 50f }
      val floatAnim = remember { Animatable(float1) }
      val offset = remember(big) { if (!big) (-48).dp else 100.dp }
      val anim = remember { Animatable(size1, Dp.VectorConverter) }
      val offsetAnim = remember { Animatable(offset, Dp.VectorConverter) }
      LaunchedEffect(big) {
//        anim.snapTo(if (big) 192.dp else 0.dp)
//        anim.animateTo(size1, TweenSpec(easing = FastOutLinearInEasing))
//        offsetAnim.animateTo(offset, tween())
//        anim.animateTo(size1, snap(1000))
        /*anim.animateTo(size1, keyframes {
          durationMillis = 450
          delayMillis = 500
          48.dp at 0 with FastOutLinearInEasing
          144.dp at 150 with FastOutSlowInEasing
          20.dp at 300
        })*/
//        anim.animateTo(48.dp, spring(0.1f, Spring.StiffnessMedium), 2000.dp)
        /*anim.animateTo(size1, repeatable(2, tween(), RepeatMode.Reverse,
          StartOffset(300, StartOffsetType.FastForward)*/
        anim.animateTo(size1, infiniteRepeatable(tween(), RepeatMode.Reverse,
          StartOffset(300, StartOffsetType.FastForward))
        )
        floatAnim.animateTo(200f, FloatSpringSpec())
      }
      Box(
        Modifier
          .size(anim.value)
          .background(Color.Green)
          .clickable {
            big = !big
          })
      /*Box(Modifier.fillMaxSize()
        .clickable {
          big = !big
        }) {
        Box(
          Modifier
            .offset(offsetAnim.value, offsetAnim.value)
            .size(48.dp)
            .background(Color.Green))
      }*/
    }
  }
}