package com.rengwuxian.coursecomposenestedscroll

import android.os.Bundle
import android.widget.ListView
import android.widget.ScrollView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rengwuxian.coursecomposenestedscroll.ui.theme.CourseComposeNestedScrollTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeNestedScrollTheme {
        NestedScrollSample()
      }
    }
  }
}

@Composable
fun NestedScrollSample() {
  var offsetY by remember { mutableStateOf(0f) }
  val dispatcher = remember { NestedScrollDispatcher() }
  val connection = remember {
    object : NestedScrollConnection {
      override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
      ): Offset {
        offsetY += available.y
        return available
      }
    }
  }
  Column(
    Modifier
      .offset { IntOffset(0, offsetY.roundToInt()) }
      .draggable(rememberDraggableState {
        val consumed = dispatcher.dispatchPreScroll(Offset(0f, it), NestedScrollSource.Drag)
        offsetY += it - consumed.y
        dispatcher.dispatchPostScroll(Offset(0f, it), Offset(0f, 0f), NestedScrollSource.Drag)
      }, Orientation.Vertical)
      .nestedScroll(connection, dispatcher)
  ) {
    for (i in 1..10) {
      Text("第 $i 项")
    }
    LazyColumn(Modifier.height(50.dp)) {
      items(5) {
        Text("内部 List - 第 $it 项")
      }
    }
  }
}

@Composable
private fun NestedScrollDemo() {
  LazyColumn(Modifier.fillMaxSize()) {
    item {
      LazyColumn(
        Modifier
          .fillMaxWidth()
          .height(250.dp)
          .background(Color.Red)
      ) {
        items(8) {
          Text(
            "第一部分：${it + 1}",
            Modifier
              .fillMaxWidth()
              .padding(8.dp), fontSize = 24.sp, textAlign = TextAlign.Center
          )
        }
      }
    }
    items(20) {
      Text(
        "第二部分：${it + 1}",
        Modifier
          .fillMaxWidth()
          .padding(8.dp), fontSize = 24.sp, textAlign = TextAlign.Center
      )
    }
  }
}