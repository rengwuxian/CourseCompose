package com.rengwuxian.coursecompose

import android.app.Application
import androidx.emoji2.text.EmojiCompat

class App : Application() {
  override fun onCreate() {
    super.onCreate()
    EmojiCompat.init(this)
  }
}