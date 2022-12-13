package com.example.coursecomposeremembercoroutinescope

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.coursecomposeremembercoroutinescope.ui.theme.CourseComposeRememberCoroutineScopeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeRememberCoroutineScopeTheme {
        lifecycleScope.launch {  }
        val scope = rememberCoroutineScope()
        Box(Modifier.clickable {
          scope.launch {  }
        })
        val coroutine = remember { scope.launch {  } }
        LaunchedEffect(key1 = , block = )
      }
    }
  }
}