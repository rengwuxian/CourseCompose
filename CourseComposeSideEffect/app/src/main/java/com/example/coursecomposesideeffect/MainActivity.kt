package com.example.coursecomposesideeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import com.example.coursecomposesideeffect.ui.theme.CourseComposeSideEffectTheme

class MainActivity : ComponentActivity() {
  fun a() {
    println("rengwuxian")
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeSideEffectTheme {
        var seasonsCount = 0
        Column {
          val names = arrayOf("春天欢快跳舞", "夏天暴跳如雷", "秋天收起躁动", "冬天冷静回顾")
          seasonsCount = names.size
          for (name in names) {
            Text(name)
//            SideEffect {
//              seasonsCount++
//            }
            // side effect 「附带效应」「副作用」
          }
          Text("一共有 $seasonsCount 个季节")
        }
      }
    }
  }
}