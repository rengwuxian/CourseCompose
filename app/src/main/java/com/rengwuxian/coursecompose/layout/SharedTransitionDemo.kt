package com.rengwuxian.coursecompose.layout

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rengwuxian.coursecompose.R

@Preview(showBackground = true)
@Composable
private fun SharedTransitionDemo() {
  var isExpanded by remember { mutableStateOf(false) }
  val clicked = { isExpanded = !isExpanded }

  SharedTransitionLayout() {
    AnimatedContent(
      targetState = isExpanded,
      Modifier.safeDrawingPadding(),
      label = "card_transition",
    ) { targetExpanded ->
      if (targetExpanded) {
        InfoPage(this@SharedTransitionLayout, this, clicked)
      } else {
        InfoCard(this@SharedTransitionLayout, this, clicked)
      }
    }
  }
}

@Composable
private fun InfoCard(transitionScope: SharedTransitionScope,
                     visibilityScope: AnimatedVisibilityScope,
                     clicked: () -> Unit) {
  with(transitionScope) {
    Row(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .background(Color.LightGray, RoundedCornerShape(8.dp))
        .sharedBounds(rememberSharedContentState("Container"),
          visibilityScope)
        .clickable { clicked() }
        .padding(8.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.avatar_rengwuxian),
        contentDescription = "Cover",
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .size(80.dp)
          .sharedElement(rememberSharedContentState("Cover"),
            visibilityScope)
          .clip(RoundedCornerShape(8.dp))
      )
      Spacer(Modifier.width(16.dp))
      Text("扔物线 Compose 大师课", fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
  }
}

@Composable
private fun InfoPage(transitionScope: SharedTransitionScope,
                     visibilityScope: AnimatedVisibilityScope,
                     clicked: () -> Unit) {
  with(transitionScope) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(Color.LightGray, RoundedCornerShape(16.dp))
        .sharedBounds(rememberSharedContentState("Container"),
          visibilityScope)
        .clickable { clicked() }
        .padding(16.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.avatar_rengwuxian),
        contentDescription = "Cover",
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .fillMaxWidth()
          .height(300.dp)
          .sharedElement(rememberSharedContentState("Cover"),
            visibilityScope)
          .clip(RoundedCornerShape(8.dp))
      )
      Spacer(Modifier.height(16.dp))
      Text("扔物线 Compose 大师课", fontSize = 24.sp, fontWeight = FontWeight.Bold)
      Spacer(Modifier.height(8.dp))
      Text("这里应该写点广告，但是我懒得编了。", fontSize = 16.sp)
    }
  }
}