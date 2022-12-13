package com.rengwuxian.coursecomposemodifiercomposed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposemodifiercomposed.ui.theme.CourseComposeModifierComposedTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierComposedTheme {
        val modifier = Modifier.composed {
          var padding by remember { mutableStateOf(8.dp) }
          Modifier
            .padding(padding)
            .clickable { padding = 0.dp }
        }
        var padding by remember { mutableStateOf(8.dp) }
        val modifier1 = Modifier
          .padding(padding)
          .clickable { padding = 0.dp }
        Column {
          var padding1 by remember { mutableStateOf(8.dp) }
          val paddingModifier = Modifier
            .padding(padding1)
            .clickable { padding1 = 0.dp }
          var padding2 by remember { mutableStateOf(8.dp) }
          val padding2Modifier = Modifier
            .padding(padding2)
            .clickable { padding2 = 0.dp }
          Box(Modifier.background(Color.Blue) then paddingModifier)
          Text("rengwuxian", Modifier.background(Color.Green) then padding2Modifier)
        }
      }
    }
  }
}

fun Modifier.paddingJumpModifier() = composed {
  var padding by remember { mutableStateOf(8.dp) }
  Modifier
    .padding(padding)
    .clickable { padding = 0.dp }
}

fun Modifier.coroutineModifier() = composed {
  LaunchedEffect(key1 =, block =)
    ...Modifier
}

// CompositionLocal.current get()

fun Modifier.localModifier() = composed {
  LocalContext.current
  Modifier
}