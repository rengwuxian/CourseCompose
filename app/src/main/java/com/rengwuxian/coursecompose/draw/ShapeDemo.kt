package com.rengwuxian.coursecompose.draw

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
private fun TicketDemo() {
  val notchFraction = 0.7f // 槽口的位置
  val shape = remember(notchFraction) {
    TicketShape(cornerRadius = 12.dp, notchRadius = 8.dp, notchFraction = notchFraction)
  }

  Row(
    Modifier
      .padding(20.dp)
      .width(380.dp)
      .height(160.dp)
      .clip(shape) // 外层通过 clip 切出票的形状
  ) {
    // 左边部分：电影介绍
    Column(
      Modifier
        .weight(notchFraction)
        .fillMaxHeight()
        .background(Color(0xFF2196F3))
        .padding(20.dp)
    ) {
      Text(
        text = "史诗大电影",
        color = Color.White.copy(alpha = 0.8f),
        fontSize = 12.sp,
        letterSpacing = 2.sp
      )
      // 1. 把主标题推上去
      Spacer(Modifier.weight(1f))
      Text(
        text = "被重写的人",
        color = Color.White,
        fontSize = 28.sp,
        fontWeight = FontWeight.ExtraBold,
        letterSpacing = (-1).sp
      )
      Spacer(Modifier.height(10.dp))
      Text(
        text = "当程序员遇上 AI 变革，\n是机遇，是末日，还是浴火重生？",
        color = Color.White.copy(alpha = 0.9f),
        fontSize = 13.sp,
        lineHeight = 18.sp
      )
    }
    // 右边部分：座位信息
    Column(
      Modifier
        .weight(1f - notchFraction)
        .fillMaxHeight()
        .background(Color(0xFFE91E63))
        .padding(vertical = 20.dp, horizontal = 12.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(Modifier.weight(0.5f))
      // 模拟二维码框
      Box(
        modifier = Modifier
          .size(54.dp)
          .background(Color.White)
          .padding(4.dp)
      ) {
        val cornerSize = 8.dp
        Box(Modifier.size(cornerSize).align(Alignment.TopStart).background(Color.Black))
        Box(Modifier.size(cornerSize).align(Alignment.TopEnd).background(Color.Black))
        Box(Modifier.size(cornerSize).align(Alignment.BottomStart).background(Color.Black))
        Box(Modifier.padding(cornerSize).fillMaxSize().background(Color.Black))
      }
      Spacer(Modifier.weight(1f))
      Text(
        text = "8 排 · 24 座",
        color = Color.White,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(Modifier.height(4.dp))
      Text(
        text = "10.24 · 19:30",
        color = Color.White.copy(alpha = 0.7f),
        fontSize = 11.sp
      )
    }
  }
}

class TicketShape(
  private val cornerRadius: Dp,
  private val notchRadius: Dp,
  private val notchFraction: Float = 0.7f,
) : Shape {

  override fun createOutline(
    size: Size,
    layoutDirection: LayoutDirection,
    density: Density,
  ): Outline {
    val width = size.width
    val height = size.height

    val r = with(density) {
      cornerRadius.toPx().coerceAtMost(minOf(width, height) / 2f)
    }
    val notchR = with(density) {
      notchRadius.toPx().coerceAtMost(width / 2f).coerceAtMost(height / 2f)
    }
    val notchCenterX = (width * notchFraction).coerceIn(notchR, width - notchR)

    // 1. 基础圆角矩形
    val baseRect = Path().apply {
      addRoundRect(RoundRect(0f, 0f, width, height, CornerRadius(r)))
    }

    // 2. 顶部的圆形缺口
    val topNotch = Path().apply {
      addOval(Rect(notchCenterX - notchR, -notchR, notchCenterX + notchR, notchR))
    }

    // 3. 底部的圆形缺口
    val bottomNotch = Path().apply {
      addOval(Rect(notchCenterX - notchR, height - notchR, notchCenterX + notchR, height + notchR))
    }

    // 通过减法运算，得到最终结果
    val finalPath = baseRect - topNotch - bottomNotch

    return Outline.Generic(finalPath)
  }
}