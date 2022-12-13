package com.rengwuxian.coursecomposemodifierdraw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposemodifierdraw.ui.theme.CourseComposeModifierDrawTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierDrawTheme {
        /*Modifier.requiredSize(80.dp).background(Color.Blue).requiredSize(40.dp)
        Modifier.then(object : DrawModifier {
          override fun ContentDrawScope.draw() {
            drawContent()
          }
        })
        Modifier.drawWithContent {
          drawContent()
        }*/
//        Box(Modifier.background(Color.Red).background(Color.Blue))
        Box(Modifier.background(Color.Red).requiredSize(80.dp).background(Color.Blue).requiredSize(40.dp))
        Box(Modifier.size(40.dp).padding(8.dp).background(Color.Blue))
        /*Box(Modifier.background(Color.Blue).background(Color.Blue).requiredSize(40.dp)
          .background(Color.Blue).background(Color.Blue).requiredSize(40.dp)
          .background(Color.Blue).background(Color.Blue))
        Box(Modifier.size(40.dp).drawWithContent {
          drawCircle(Color.Red)
          drawContent()
          drawCircle(Color.Red)
        }.background(Color.Blue))*/
      }
    }
  }
}

// onDraw() , canvas.drawXxx() canvas.drawText()