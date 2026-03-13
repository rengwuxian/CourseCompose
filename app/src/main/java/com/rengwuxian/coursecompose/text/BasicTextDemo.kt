package com.rengwuxian.coursecompose.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.rengwuxian.coursecompose.R

@Preview(showBackground = true)
@Composable
private fun BasicTextDemo() {
  Box(Modifier.size(210.dp).padding(20.dp)) {
    val text1 = """
    如需启动新的 Compose 项目，请打开 Android Studio。
    如果您
    """.trimIndent()
    val text2 = """
    位于 Welcome to Android Studio 窗口中，请点击 Start a new Android Studio project。
    对于新项目，请从可用模板中选择 Empty Activity。
    """.trimIndent()
    val avatarId = "avatar"
    val annotatedText = buildAnnotatedString {
      append(text1)
      appendInlineContent(avatarId, " [头像] ")
      append(text2)
    }
    BasicText(annotatedText, inlineContent = mapOf(
      avatarId to InlineTextContent(
        Placeholder(1.em, 1.em, PlaceholderVerticalAlign.TextCenter)
      ) {
        Image(painterResource(R.drawable.avatar_rengwuxian), "Avatar",
          Modifier.border(1.dp, Color.Black, CircleShape).padding(1.dp).clip(CircleShape))
      }
    ))
  }
}

















