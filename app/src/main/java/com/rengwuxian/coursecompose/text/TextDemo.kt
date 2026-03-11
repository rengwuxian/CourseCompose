package com.rengwuxian.coursecompose.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.Bullet
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
private fun TextDemo() {
  val text = remember {
    buildAnnotatedString {
      withStyle(ParagraphStyle(lineHeight = 4.em)) {
        append("你好！")
      }
      append("我是")
      withStyle(SpanStyle(color = Color.Red, fontSize = 24.sp)) {
        append("扔物线")
        withStyle(
          SpanStyle(
            fontSize = 12.sp, color = Color.Blue,
            baselineShift = BaselineShift.Superscript
          )
        ) {
          append("朱凯")
        }
      }
      append("。")
      withLink(LinkAnnotation.Url("https://rengwuxian.com",
        TextLinkStyles(
          SpanStyle(textDecoration = TextDecoration.Underline,
            color = Color.Magenta)),
        {
          println("点了链接：rengwuxian.com")
        }
      )) {
        append("rengwuxian.com")
      }
      append("\n")
      withLink(LinkAnnotation.Clickable("chat",
        linkInteractionListener = {
          // chatWithUser()
        })) {
        append("@rengwuxian")
      }
      withBulletList {
        withBulletListItem {
          append("先把冰箱门打开")
        }
        withBulletListItem {
          append("把大象放进去")
        }
        withBulletList(bullet = Bullet.Default.copy(
          shape = RectangleShape,
          drawStyle = Stroke(2.dp.value)
        )) {
          withBulletListItem {
            append("不要切开大象！")
          }
        }
        withBulletListItem {
          append("把冰箱门关上")
        }
      }
    }
  }
  Text(text)
}

@Preview(widthDp = 640)
@Composable
fun CodeSnippetDemo() {
  // 1. 原始文本
  val rawCode = """
        @Preview(showBackground = true)
        @Composable
        fun Demo() {
          Button(onClick = { /*TODO*/ }) { Text("rengwuxian.com") }
        }
    """.trimIndent()

  val codeString = buildAnnotatedString {
    append(rawCode)

    // 2. 样式定义库
    val annotationStyle = SpanStyle(color = Color(0xFFBBB529))
    val keywordStyle = SpanStyle(color = Color(0xFFCC7832))
    val funcDeclStyle = SpanStyle(color = Color(0xFF56A8F5))
    val funcCallStyle = SpanStyle(color = Color(0xFF00B59C))
    val paramStyle = SpanStyle(color = Color(0xFF56A8F5))
    val innerFuncCallStyle = SpanStyle(color = Color(0xFF5FB259))
    val stringStyle = SpanStyle(color = Color(0xFF6A8759))
    val commentStyle = SpanStyle(color = Color(0xFF808080), fontStyle = FontStyle.Italic)
    val todoTokenStyle = SpanStyle(
      color = Color(0xFFA8C023),
      fontStyle = FontStyle.Italic,
      fontWeight = FontWeight.Bold
    )

    // 3. 高亮辅助函数（模拟词法分析器的 Token 映射）
    // 实际开发中，这一步通常由 Regex.findAll() 替代，返回所有匹配的区间
    fun highlight(target: String, style: SpanStyle, startIndex: Int = 0) {
      val start = rawCode.indexOf(target, startIndex)
      if (start >= 0) {
        addStyle(style, start, start + target.length)
      }
    }

    // 4. 统一应用样式
    highlight("@Preview", annotationStyle)
    highlight("true", keywordStyle)
    highlight("@Composable", annotationStyle)
    highlight("fun", keywordStyle)
    highlight("Demo", funcDeclStyle)
    highlight("Button", funcCallStyle)
    highlight("onClick", paramStyle)
    highlight("/*", commentStyle)
    highlight("TODO", todoTokenStyle)
    highlight("*/", commentStyle)
    highlight("Text", innerFuncCallStyle)
    highlight("\"rengwuxian.com\"", stringStyle)
  }

  Text(
    text = codeString,
    color = Color(0xFFA9B7C6), // 基础符号的浅灰色 (括号、等号、大括号等)
    fontFamily = FontFamily.Monospace,
    fontSize = 15.sp,
    modifier = Modifier
      .background(Color(0xFF2B2B2B))
      .padding(16.dp)
  )
}