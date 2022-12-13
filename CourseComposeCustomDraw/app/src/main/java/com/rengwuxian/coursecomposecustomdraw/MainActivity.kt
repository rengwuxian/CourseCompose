package com.rengwuxian.coursecomposecustomdraw

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint as AndroidPaint
import android.graphics.RenderNode
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.rotateRad
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Paint
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.nativeCanvas
import android.graphics.Color as AndroidColor
import com.rengwuxian.coursecomposecustomdraw.ui.theme.CourseComposeCustomDrawTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeCustomDrawTheme {
        Modifier.drawWithContent {

          drawContent()

        }
        Box(
          Modifier
            .size(80.dp)
            .drawBehind {

            })
        Canvas(Modifier.size(80.dp)) {

        }
      }
    }
  }
}

@Preview
@Composable
fun CustomImage() {
  val image = ImageBitmap.imageResource(R.drawable.avatar_rengwuxian)
  val paint by remember { mutableStateOf(Paint()) }
  val rotationAnimatable = remember { Animatable(0f) }
  val camera by remember { mutableStateOf(Camera()) }
  LaunchedEffect(Unit) {
    rotationAnimatable.animateTo(360f, infiniteRepeatable(tween(2000)))
  }
  Box(Modifier.padding(50.dp)) {
    Canvas(Modifier.size(100.dp)/*.graphicsLayer {
      rotationX = 45f
      rotationY = 45f
    }*/) {
      drawIntoCanvas {
        it.translate(size.width / 2, size.height / 2)
        it.rotate(-45f)
        camera.save()
        camera.rotateX(rotationAnimatable.value)
        camera.applyToCanvas(it.nativeCanvas)
        camera.restore()
        it.rotate(45f)
        it.translate(- size.width / 2, - size.height / 2)
        it.drawImageRect(image, dstSize = IntSize(size.width.roundToInt(),
          size.height.roundToInt()), paint = paint) 
      }
    }
  }
}

@Preview
@Composable
fun CustomText() {
  Text("rengwuxian", Modifier.drawWithContent {
    drawRect(Color.Yellow)
    drawContent()
    drawLine(Color.Red, Offset(0f, size.height / 2), Offset(size.width, size.height / 2), 2.dp.toPx())
  })
}

class CustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
  val paint = AndroidPaint()
  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    paint.color = AndroidColor.YELLOW
    canvas.save()
    canvas.rotate(45f)
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    canvas.restore()
//    canvas.drawBitmap()
  }
}
