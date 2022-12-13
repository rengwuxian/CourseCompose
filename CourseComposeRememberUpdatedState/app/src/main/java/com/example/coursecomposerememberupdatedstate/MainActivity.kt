package com.example.coursecomposerememberupdatedstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import com.example.coursecomposerememberupdatedstate.ui.theme.CourseComposeRememberUpdatedStateTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeRememberUpdatedStateTheme {
        /*var text by remember { mutableStateOf("扔物线") }
        Text(text)

        var showText by remember { mutableStateOf(false) }
        Button(onClick = { showText = !showText }) {
          Text("点击")
          if (showText) {
            Text("rengwuxian")
          }
          SideEffect {
            println("@@@ SideEffect()")
          }
          DisposableEffect(showText) {
            println("@@@ DisposableEffect()")
            onDispose {
              println("@@@ onDispose()")
            }
          }
        }*/
        var user = ????
        CustomDisposableEffet(user)
        var welcome by remember { mutableStateOf("欢迎光临！") }
        CustomLaunchedEffect(welcome)
        Button(onClick = { welcome = "不欢迎" }) {
          Text(welcome)
        }
      }
    }
  }
}

@Composable
private fun CustomDisposableEffet(user: User) {
  val updatedUser by rememberUpdatedState(user)
  DisposableEffect(Unit) {
    suscriber.subscribe(updatedUser)
    onDispose {
      xxxx.unsubscribe()
    }
  }
}

@Composable
private fun CustomLaunchedEffect(welcome: String) {
  val rememberedWelcome by rememberUpdatedState(welcome)
  LaunchedEffect(Unit) {
    delay(3000)
    println("@@= $rememberedWelcome")
  }
}