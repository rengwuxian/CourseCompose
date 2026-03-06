package com.rengwuxian.coursecompose.viewmodel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

// 模拟数据模型
data class Song(val id: Int, val title: String, val artist: String, val genre: String, val color: Color)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerHome(viewModel: MusicViewModel = viewModel()) {
  // 1. 状态管理
  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()
  val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()

  // TopBar 的滚动行为 (ExitUntilCollapsed: 向上滚动时收起，只留标题)
  val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

  // 模拟歌单数据
  val songs by viewModel.songs.collectAsStateWithLifecycle()

  // 2. 核心骨架 Scaffold
  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .nestedScroll(scrollBehavior.nestedScrollConnection), // 绑定滚动行为

    // Slot A: 顶部栏
    topBar = {
      LargeTopAppBar(
        title = { Text("早上好，扔物线") },
        navigationIcon = {
          IconButton(onClick = { /* 打开侧滑菜单 */ }) {
            Icon(Icons.Default.Menu, contentDescription = "Menu")
          }
        },
        actions = {
          IconButton(onClick = { /* 搜索 */ }) {
            Icon(Icons.Default.Search, contentDescription = "Search")
          }
          IconButton(onClick = { /* 个人中心 */ }) {
            Box(
              modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.tertiary)
            ) {
              Text(
                "扔",
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onTertiary
              )
            }
          }
        },
        scrollBehavior = scrollBehavior
      )
    },

    // Slot B: 底部导航栏
    bottomBar = {
      NavigationBar {
        NavigationBarItem(
          icon = { Icon(Icons.Default.Home, null) },
          label = { Text("主页") },
          selected = selectedTab == 0,
          onClick = { viewModel.selectTab(0) }
        )
        NavigationBarItem(
          icon = { Icon(Icons.Default.Explore, null) },
          label = { Text("发现") },
          selected = selectedTab == 1,
          onClick = { viewModel.selectTab(1) }
        )
        NavigationBarItem(
          icon = { Icon(Icons.Default.LibraryMusic, null) },
          label = { Text("我的") },
          selected = selectedTab == 2,
          onClick = { viewModel.selectTab(2) }
        )
      }
    },

    // Slot C: 悬浮按钮 (这里做成了一个播放按钮)
    floatingActionButton = {
      ExtendedFloatingActionButton(
        text = { Text("随便播播") },
        icon = { Icon(Icons.Default.PlayArrow, null) },
        onClick = {
          scope.launch {
            snackbarHostState.showSnackbar("开始随机播放歌曲")
          }
        },
        // 当列表滚动时收缩 FAB (可选的高级交互)
        expanded = true
      )
    },

    // Slot D: 消息提示宿主 (必须放这里，否则会被 BottomBar 遮挡)
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

  ) { innerPadding ->
    // Slot E: 主内容区
    // 关键点：innerPadding 必须传递给 LazyColumn

    LazyColumn(
      contentPadding = innerPadding, // ✅ 关键
      modifier = Modifier.fillMaxSize()
    ) {
      // 列表头部：最近播放卡片
      item {
        FeaturedCard()
      }

      // 列表标题
      item {
        Text(
          text = "最近添加",
          style = MaterialTheme.typography.titleMedium,
          modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
        )
      }

      // 歌曲列表
      items(songs) { song ->
        SongListItem(song)
      }
    }
  }
}

// ---- 子组件封装 ----

@Composable
fun FeaturedCard() {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
      .height(180.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer
    ),
    shape = RoundedCornerShape(24.dp)
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      // 模拟复杂的背景渐变
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.linearGradient(
              colors = listOf(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.secondary
              )
            )
          )
      )

      Column(
        modifier = Modifier
          .padding(24.dp)
          .align(Alignment.BottomStart)
      ) {
        Text(
          "本周推荐",
          style = MaterialTheme.typography.headlineSmall,
          color = Color.White
        )
        Text(
          "为你专属定制的歌单",
          style = MaterialTheme.typography.bodyMedium,
          color = Color.White.copy(alpha = 0.8f)
        )
      }

      Icon(
        Icons.Default.PlayCircle,
        contentDescription = null,
        modifier = Modifier
          .align(Alignment.BottomEnd)
          .padding(24.dp)
          .size(48.dp),
        tint = Color.White
      )
    }
  }
}

@Composable
fun SongListItem(song: Song) {
  ListItem(
    headlineContent = {
      Text(song.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
    },
    overlineContent = {
      Text(song.genre, maxLines = 1, overflow = TextOverflow.Ellipsis)
    },
    supportingContent = {
      Text(song.artist, maxLines = 1)
    },
    leadingContent = {
      // 模拟专辑封面
      Box(
        modifier = Modifier
          .size(56.dp)
          .clip(RoundedCornerShape(8.dp))
          .background(song.color.copy(alpha = 0.4f)), // 降低透明度看着更和谐
        contentAlignment = Alignment.Center
      ) {
        Icon(Icons.Default.MusicNote, null, tint = MaterialTheme.colorScheme.onSurface)
      }
    },
    trailingContent = {
      IconButton(onClick = { /* Like */ }) {
        Icon(Icons.Outlined.FavoriteBorder, null)
      }
    },
    modifier = Modifier.fillMaxWidth()
  )
}

@Preview(showBackground = true)
@Composable
fun PreviewMusicPlayer() {
  MaterialTheme {
    MusicPlayerHome()
  }
}