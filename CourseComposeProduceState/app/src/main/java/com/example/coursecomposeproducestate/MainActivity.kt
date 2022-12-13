package com.example.coursecomposeproducestate

import android.graphics.Point
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.coursecomposeproducestate.ui.theme.CourseComposeProduceStateTheme
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
  val geoManager : GeoManager = TODO()
  val positionData: LiveData<Point> = TODO()
  val positionState: StateFlow<Point> = TODO()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      CourseComposeProduceStateTheme {
        var position by remember { mutableStateOf(Point(0, 0)) }
        DisposableEffect(Unit) {
          val callback = object : ???? { newPos ->
            position = newPos
          }
          geoManager.register(callback)
          onDispose {
            geoManager.unregister(callback)
          }
        }
        DisposableEffect(Unit) {
          val observer = Observer<Point> { newPos ->
            position = newPos
          }
          positionData.observe(this@MainActivity, observer)
          onDispose {
            positionData.removeObserver(observer)
          }
        }
        val positionStateFromLiveData = positionData.observeAsState()
        LaunchedEffect(Unit) {
          positionState.collect { newPos ->
            position = newPos
          }
        }
        val producedState = produceState(Point(0, 0)) {
          positionState.collect { newPos ->
            value = newPos
          }
        }
        val positionStateFromFlow = positionState.collectAsState()
      }
    }
  }
}