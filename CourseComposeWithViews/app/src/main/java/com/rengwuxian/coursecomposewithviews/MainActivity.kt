package com.rengwuxian.coursecomposewithviews

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import com.rengwuxian.coursecomposewithviews.ui.theme.CourseComposeWithViewsTheme

class MainActivity : FragmentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    /*setContentView(R.layout.layout_mix)

    findViewById<ComposeView>(R.id.composeView).setContent { CustomText() }*/

    setContent {
      CourseComposeWithViewsTheme {
        val context = LocalContext.current
        var name by remember { mutableStateOf("扔物线") }
        Column {
          Text("rengwuxian", Modifier.clickable { name += "哈" })
          AndroidView(factory = {
            TextView(context).apply {
              text = "扔物线"
            }
          }) {
            it.text = name
          }
        }
      }
    }
    /*val linearLayout = LinearLayout(this)
    val composeView = ComposeView(this).apply {
      setContent { CustomText() }
    }
    linearLayout.addView(composeView, ViewGroup.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))*/
  }
}

@Composable
fun CustomText() {
  Text("rengwuxian")
}