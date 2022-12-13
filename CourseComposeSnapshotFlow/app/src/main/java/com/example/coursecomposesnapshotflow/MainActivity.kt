package com.example.coursecomposesnapshotflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import com.example.coursecomposesnapshotflow.ui.theme.CourseComposeSnapshotFlowTheme
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeSnapshotFlowTheme {
        var name by remember { mutableStateOf("rengwuxian") }
        var age by remember { mutableStateOf(18) }
        val flow = snapshotFlow { "$name $age" }
        LaunchedEffect(Unit) {
          flow.collect { info ->
            println(info)
          }
        }
      }
    }
  }
}