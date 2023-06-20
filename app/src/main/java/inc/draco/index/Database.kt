package inc.draco.index

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

class Database {
    var realm: Realm
    private var config: RealmConfiguration
    init {
        config = RealmConfiguration.Builder(
            schema = setOf(Person::class))
            .inMemory()
            .build()
        realm = Realm.open(config)
        realm.writeBlocking {
            this.copyToRealm(
                Person().apply{
                    name = "Trevor GT"
                    birthday = "01/06/2003"
                    height = (5+11/12).toFloat()
                }
            )
            this.copyToRealm(
                Person().apply {
                    name = "Catherine"
                    birthday = "uhh...idk"
                    height = 4F
                }
            )
            this.copyToRealm(
                Person().apply {
                    name = "Melisa"
                    birthday = "Feb 7?"
                    height = 3.1F
                }
            )
            for (i in 1..10) {
                this.copyToRealm(
                    Person().apply {
                        name = "$i"
                    }
                )
            }
        }
    }

    fun results() = realm.query<Person>().find()

    fun add(update: () -> Unit) {
        realm.writeBlocking {
            this.copyToRealm(
                Person().apply {
                    name = "Kayso"
                }
            )
        }
        update()
    }

    fun close() {
        realm.close()
        Realm.deleteRealm(config)
    }
}