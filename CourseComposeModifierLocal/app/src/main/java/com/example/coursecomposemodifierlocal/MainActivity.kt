package com.example.coursecomposemodifierlocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.modifier.ModifierLocalConsumer
import androidx.compose.ui.modifier.ModifierLocalProvider
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.modifier.ProvidableModifierLocal
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.coursecomposemodifierlocal.ui.theme.CourseComposeModifierLocalTheme

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalComposeUiApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierLocalTheme {
        val sharedKey = modifierLocalOf { "空" }
        Modifier
          .modifierLocalProvider(sharedKey) {
            "rengwuxian"
          }
          .modifierLocalConsumer { println(sharedKey.current) }

        val sharedWidthKey = modifierLocalOf { "0" }
        Modifier
          .then(object : LayoutModifier, ModifierLocalProvider<String> {
            lateinit var widthString: String
            override fun MeasureScope.measure(
              measurable: Measurable,
              constraints: Constraints
            ): MeasureResult {
              val placeable = measurable.measure(constraints)
//              widthString = placeable.width.toString()
              return layout(placeable.width, placeable.height) {
                placeable.placeRelative(0, 0)
              }
            }

            override val key: ProvidableModifierLocal<String>
              get() = sharedWidthKey
            override val value: String
              get() = widthString
          })
          .then(object : LayoutModifier, ModifierLocalConsumer {
            lateinit var sharedString: String
            override fun MeasureScope.measure(
              measurable: Measurable,
              constraints: Constraints
            ): MeasureResult {
              sharedString
              val placeable = measurable.measure(constraints)
              return layout(placeable.width, placeable.height) {
                placeable.placeRelative(0, 0)
              }
            }

            override fun onModifierLocalsUpdated(scope: ModifierLocalReadScope) = with(scope) {
              sharedString = sharedWidthKey.current
            }
          })
          .layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            val widthString = placeable.width.toString()
            layout(placeable.width, placeable.height) {
              placeable.placeRelative(0, 0)
            }
          }
          .layout { measurable, constraints ->
            widthString
            val placeable = measurable.measure(constraints)
            layout(placeable.width, placeable.height) {
              placeable.placeRelative(0, 0)
            }
          }

        Modifier.windowInsetsPadding(WindowInsets(4.dp, 4.dp, 4.dp, 4.dp))
          .windowInsetsPadding(WindowInsets(4.dp, 6.dp, 4.dp, 6.dp))
          .windowInsetsPadding(WindowInsets(4.dp, 2.dp, 4.dp, 2.dp))
      }
    }
  }
}