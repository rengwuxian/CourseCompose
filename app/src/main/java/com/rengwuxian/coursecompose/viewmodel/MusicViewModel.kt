package com.rengwuxian.coursecompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MusicViewModel : ViewModel() {
  private val _songs = MutableStateFlow(List(20) { index ->
    Song(
      id = index,
      title = "歌单歌曲：${index + 1} 号",
      artist = if (index % 2 == 0) "贝多芬" else "莫扎特",
      genre = if (index % 2 == 0) "流行" else "摇滚",
      color = Color(
        (0xFF000000..0xFFFFFFFF).random() or 0xFF000000
      ) // 随机颜色模拟专辑封面
    )
  })
  private val _selectedTab = MutableStateFlow(0)

  val songs: StateFlow<List<Song>> = _songs.asStateFlow()
  val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

  fun selectTab(index: Int) {
    _selectedTab.value = index
  }

}