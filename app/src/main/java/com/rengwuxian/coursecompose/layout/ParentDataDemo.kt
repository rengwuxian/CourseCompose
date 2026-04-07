package com.rengwuxian.coursecompose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.ParentDataModifierNode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun ParentDataDemo() {
  ParentDataLayout {
    Box(Modifier.clip(CircleShape).background(Color.Gray))
    Box(Modifier.clip(CircleShape).background(Color.Blue).core())
    Box(Modifier.clip(CircleShape).background(Color.Gray))
  }
}

@Composable
fun CustomBadgedBox(
  badge: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit) {
  Layout(listOf(
    { Box { content() } },
    { Box { badge() } }
  ), modifier) { measurables, constraints ->
    val contentMeasurable = measurables[0][0]
    val badgeMeasurable = measurables[1][0]

    val contentPlaceable = contentMeasurable.measure(constraints)
    val badgePlaceable = badgeMeasurable.measure(constraints)

    val totalWidth = contentPlaceable.width + badgePlaceable.width / 2
    val totalHeight = contentPlaceable.height + badgePlaceable.height / 2

    layout(totalWidth, totalHeight) {
      contentPlaceable.placeRelative(x = 0, y = badgePlaceable.height / 2)
      badgePlaceable.placeRelative(x = contentPlaceable.width - badgePlaceable.width / 2, y = 0)
    }
  }
}

@Preview
@Composable
private fun BadgedBoxDemo() {
  CustomBadgedBox({
    Text(
      text = "99+",
      color = Color.White,
      fontSize = 12.sp,
      modifier = Modifier
        .background(Color.Red, shape = CircleShape)
        .border(1.5.dp, Color.White, CircleShape)
        .padding(horizontal = 6.dp, vertical = 2.dp)
    )
  }) {
    Box(
      modifier = Modifier
        .size(48.dp)
        .background(Color.LightGray, shape = RoundedCornerShape(12.dp)),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = Icons.Default.Notifications,
        contentDescription = "通知",
        tint = Color.DarkGray
      )
    }
  }
}

data class DevInfo(val isCore: Boolean? = null, val id: String? = null)

class CustomParentDataNode(var devInfo: DevInfo) : Modifier.Node(), ParentDataModifierNode {
  override fun Density.modifyParentData(parentData: Any?): DevInfo {
    if (parentData == null) return devInfo
    val innerData = parentData as DevInfo
    return DevInfo(
      isCore = devInfo.isCore ?: innerData.isCore,
      id = devInfo.id ?: innerData.id,
    )
  }
}

data class CustomParentDataModifier(val devInfo: DevInfo) : ModifierNodeElement<CustomParentDataNode>() {
  override fun create(): CustomParentDataNode = CustomParentDataNode(devInfo)

  override fun update(node: CustomParentDataNode) {
    node.devInfo = devInfo
  }
}

fun Modifier.core() = this.then(CustomParentDataModifier(DevInfo(isCore = true)))
fun Modifier.id(id: String) = this.then(CustomParentDataModifier(DevInfo(id = id)))

@Composable
fun ParentDataLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Layout(content, modifier) { measurables, constraints ->
    val placeables = measurables.map {
      val isCore = (it.parentData as? DevInfo)?.isCore
      val size = (if (isCore == true) 64 else 40).dp.roundToPx()
      it.measure(Constraints.fixed(size, size))
    }
    val width = placeables.sumOf { it.width }
    val height = placeables.maxOf { it.height }
    layout(width, height) {
      var offsetX = 0
      placeables.forEach { placeable ->
        val offsetY = (height - placeable.height) / 2
        placeable.placeRelative(offsetX, offsetY)
        offsetX += placeable.width
      }
    }
  }
}


















