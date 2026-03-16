package com.rengwuxian.coursecompose.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Preview(showBackground = true)
@Composable
private fun TextFieldDemo() {
  Box(Modifier
    .safeDrawingPadding()
    .padding(28.dp)) {
    val state = rememberTextFieldState("rengwuxian.com")
    BasicTextField(state, decorator = { innerTextField ->
      Column(Modifier.width(IntrinsicSize.Min)) {
        innerTextField()
        Box(Modifier.padding(top = 4.dp)
          .height(2.dp)
          .fillMaxWidth()
          .background(Color.Black))
      }
    })
  }
}














