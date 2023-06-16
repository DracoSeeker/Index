package inc.draco.index

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class Person : RealmObject {
    @PrimaryKey
    var id: BsonObjectId = BsonObjectId()
    var name: String = ""
    var birthday: String = ""
    var height: Float = 0F
}