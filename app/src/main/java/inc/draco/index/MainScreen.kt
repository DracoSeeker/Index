package inc.draco.index

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotMutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import inc.draco.index.ui.theme.ColorScheme
import inc.draco.index.ui.theme.IndexTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainScreen()
{
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun display() {
        val db = Database()
        val mainScreenState = MainScreenState(db, rememberLazyListState())
        val scope = rememberCoroutineScope()
        IndexTheme {
            Surface(
                color = ColorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TopAppBar(
                        title = {
                            Text(text = "Index")
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = ColorScheme.primary)
                    )
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        userScrollEnabled = true,
                        modifier = Modifier
                            .fillMaxSize()
//                                .height(IntrinsicSize.Max)
                            .padding(10.dp)
                            .weight(1f),
                        state = mainScreenState.listState
                    ) {
                        items(mainScreenState.resultList) {
                            Surface(
                                shadowElevation = 5.dp,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    Text(text = it.name)
                                    Text(text = it.birthday)
                                    Text(text = "${it.height} ft")
                                }
                            }
                        }
                    }
                    Row(
                    ) {
                        Button( // Add
                            onClick = {
                                db.add{
                                    mainScreenState.openAdd = true
                                    mainScreenState.resultList = db.results().toList()
                                    scope.launch {
                                        mainScreenState.listState.scrollToItem(mainScreenState.resultList.size - 1)
                                    }
                                }
                            },
                            modifier = Modifier
                        ) {
                            Text(text = "Add")
                        }
                    }
                }
                if (mainScreenState.openAdd) {
                    addPerson(mainScreenState)
                }
            }
        }
    }
    @Composable
    fun addPerson(mainScreenState: MainScreenState) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Popup(
                onDismissRequest = {mainScreenState.openAdd = false},
                alignment = Alignment.Center
            ) {
                Text("Adding")
            }
        }
    }
}