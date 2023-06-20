package inc.draco.index

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.realm.kotlin.Realm

class MainScreenState(
    db: Database, _listState: LazyListState
) {
    var resultList by mutableStateOf(db.results().toList())
    var listState: LazyListState = _listState
    var openAdd by mutableStateOf(false)
}