package inc.draco.index

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import inc.draco.index.ui.theme.ColorScheme
import inc.draco.index.ui.theme.IndexTheme
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Database()
        val peeps = db.results().toList()

        setContent {
            IndexTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TopAppBar(
                        title = {
                            Text(text = "Index")
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = ColorScheme.primary)
                    )
                    Surface(
                        color = ColorScheme.background,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            userScrollEnabled = true,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            items(peeps) {
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
                    }
                }
            }
        }
    }
}