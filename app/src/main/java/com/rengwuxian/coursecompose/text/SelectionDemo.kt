package com.rengwuxian.coursecompose.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, widthDp = 240)
@Composable
private fun SelectionDemo() {
  Box(Modifier.safeDrawingPadding()) {
    val text1 = "如需启动新的 Compose 项目，请打开 Android Studio。"
    val text2 =
      "如果您位于 Welcome to Android Studio 窗口中，请点击 Start a new Android Studio project。"
    val text3 = "对于新项目，请从可用模板中选择 Empty Activity。"
    CompositionLocalProvider(
      LocalTextSelectionColors provides TextSelectionColors(
        Color.Red,
        Color.Red.copy(alpha = 0.4f)
      )
    ) {
      SelectionContainer {
        Column() {
          Text(text1)
          DisableSelection {
            Text(text2)
          }
          BasicTextField(rememberTextFieldState("rengwuxian.com"))
          Text(text3)
        }
      }
    }
  }
}

















