package com.rengwuxian.coursecompose.navigation3

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

// ==========================================
// 0. 数据模型与模拟数据
// ==========================================
@Serializable
data class Song(
  val id: Int,
  val title: String,
  val artist: String,
  val genre: String,
  val colorValue: Long,
) {
  val color: Color
    get() = Color(colorValue)
}

val sampleSongs =
  listOf(
    Song(1, "Bohemian Rhapsody", "Queen", "Rock", 0xFFE91E63),
    Song(2, "Imagine", "John Lennon", "Pop", 0xFF9C27B0),
    Song(3, "Hotel California", "Eagles", "Rock", 0xFF673AB7),
    Song(4, "Billie Jean", "Michael Jackson", "Pop", 0xFF3F51B5),
    Song(5, "Smells Like Teen Spirit", "Nirvana", "Grunge", 0xFF2196F3),
    Song(6, "Hey Jude", "The Beatles", "Rock", 0xFF03A9F4),
    Song(7, "Like a Rolling Stone", "Bob Dylan", "Rock", 0xFF00BCD4),
    Song(8, "I Will Always Love You", "Whitney Houston", "R&B", 0xFF009688)
  )

// ==========================================
// 1. 路由定义层
// ==========================================
sealed interface AppRoute : NavKey {
  @Serializable
  data object Home : AppRoute

  @Serializable
  data class SongDetail(val songId: Int) : AppRoute
}

// ==========================================
// 2. 导航中枢
// ==========================================
@Composable
fun Nav3Demo() {
  val backStack = rememberNavBackStack(AppRoute.Home)

  NavDisplay(
    backStack = backStack,
    /*entryProvider = { key ->
      when(val route = key as AppRoute) {
        is AppRoute.Home -> NavEntry(route) {
          HomeScreen(
            onSongClick = { songId -> backStack.add(AppRoute.SongDetail(songId)) }
          )
        }
        is AppRoute.SongDetail -> NavEntry(route) {
          val song = sampleSongs.find { song -> song.id == (it as AppRoute.SongDetail).songId }
          if (song != null) {
            SongDetailScreen(
              song = song,
              onBack = { if (backStack.size > 1) backStack.removeLastOrNull() }
            )
          } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
              Text("Song not found")
            }
          }
        }
      }
    },*/
    entryProvider = entryProvider {
      entry<AppRoute.Home> {
        HomeScreen(
          onSongClick = { songId -> backStack.add(AppRoute.SongDetail(songId)) }
        )
      }
      entry<AppRoute.SongDetail> {
        val song = sampleSongs.find { song -> song.id == it.songId }
        if (song != null) {
          SongDetailScreen(
            song = song,
            onBack = { backStack.removeLastOrNull() }
          )
        } else {
          Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Song not found")
          }
        }
      }
    }
  )
}

// ==========================================
// 3. UI 层
// ==========================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onSongClick: (Int) -> Unit) {
  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()
  var selectedTab by remember { mutableIntStateOf(0) }
  val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .nestedScroll(scrollBehavior.nestedScrollConnection),
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
              modifier =
                Modifier
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
    bottomBar = {
      NavigationBar {
        NavigationBarItem(
          icon = { Icon(Icons.Default.Home, null) },
          label = { Text("主页") },
          selected = selectedTab == 0,
          onClick = { selectedTab = 0 }
        )
        NavigationBarItem(
          icon = { Icon(Icons.Default.Explore, null) },
          label = { Text("发现") },
          selected = selectedTab == 1,
          onClick = { selectedTab = 1 }
        )
        NavigationBarItem(
          icon = { Icon(Icons.Default.LibraryMusic, null) },
          label = { Text("我的") },
          selected = selectedTab == 2,
          onClick = { selectedTab = 2 }
        )
      }
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        text = { Text("随便播播") },
        icon = { Icon(Icons.Default.PlayArrow, null) },
        onClick = { scope.launch { snackbarHostState.showSnackbar("开始随机播放歌曲") } },
        expanded = true
      )
    },
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
  ) { innerPadding ->
    LazyColumn(contentPadding = innerPadding, modifier = Modifier.fillMaxSize()) {
      item { FeaturedCard() }
      item {
        Text(
          text = "最近添加",
          style = MaterialTheme.typography.titleMedium,
          modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
        )
      }
      items(sampleSongs) { song -> SongListItem(song, onClick = { onSongClick(song.id) }) }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongDetailScreen(song: Song, onBack: () -> Unit) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(song.title) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = song.color.copy(alpha = 0.2f))
      )
    }
  ) { innerPadding ->
    Surface(color = song.color.copy(alpha = 0.1f)) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
          .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // 大封面
        Box(
          modifier =
            Modifier
              .size(240.dp)
              .clip(RoundedCornerShape(24.dp))
              .background(song.color)
              .padding(bottom = 32.dp),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            Icons.Default.MusicNote,
            contentDescription = null,
            modifier = Modifier.size(120.dp),
            tint = Color.White
          )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
          text = song.title,
          style = MaterialTheme.typography.headlineMedium,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = song.artist,
          style = MaterialTheme.typography.titleMedium,
          color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = "Genre: ${song.genre}",
          style = MaterialTheme.typography.bodyMedium,
          modifier =
            Modifier
              .background(
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(8.dp)
              )
              .padding(horizontal = 12.dp, vertical = 6.dp)
        )
      }
    }
  }
}

@Composable
fun FeaturedCard() {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
      .height(180.dp),
    colors =
      CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
      ),
    shape = RoundedCornerShape(24.dp)
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      Box(
        modifier =
          Modifier
            .fillMaxSize()
            .background(
              Brush.linearGradient(
                colors =
                  listOf(
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
        Text("本周推荐", style = MaterialTheme.typography.headlineSmall, color = Color.White)
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
fun SongListItem(song: Song, onClick: () -> Unit) {
  ListItem(
    headlineContent = { Text(song.title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
    overlineContent = { Text(song.genre, maxLines = 1, overflow = TextOverflow.Ellipsis) },
    supportingContent = { Text(song.artist, maxLines = 1) },
    leadingContent = {
      Box(
        modifier =
          Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(song.color.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
      ) { Icon(Icons.Default.MusicNote, null, tint = MaterialTheme.colorScheme.onSurface) }
    },
    trailingContent = {
      IconButton(onClick = { /* Like */ }) { Icon(Icons.Outlined.FavoriteBorder, null) }
    },
    modifier = Modifier
      .fillMaxWidth()
      .clickable(onClick = onClick)
  )
}