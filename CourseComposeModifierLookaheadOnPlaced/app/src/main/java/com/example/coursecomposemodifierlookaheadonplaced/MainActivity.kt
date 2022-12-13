package com.example.coursecomposemodifierlookaheadonplaced

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LookaheadLayout
import androidx.compose.ui.layout.OnPlacedModifier
import androidx.compose.ui.layout.OnRemeasuredModifier
import androidx.compose.ui.layout.onPlaced
import com.example.coursecomposemodifierlookaheadonplaced.ui.theme.CourseComposeModifierLookaheadOnPlacedTheme

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalComposeUiApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierLookaheadOnPlacedTheme {
        OnPlacedModifier
        OnRemeasuredModifier
        LookaheadLayout(content = {
          Row(Modifier.onPlaced { lookaheadCoordinates, coordinates ->

          }) {

          }
        }, measurePolicy =)
      }
    }
  }
}

for est