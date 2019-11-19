/*
 * Copyright 2019 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package im.vector.matrix.android.internal.session.user.accountdata

import com.zhuinden.monarchy.Monarchy
import im.vector.matrix.android.internal.database.model.IgnoredUserEntity
import im.vector.matrix.android.internal.task.Task
import im.vector.matrix.android.internal.util.awaitTransaction
import javax.inject.Inject

/**
 * Save the ignored users list in DB
 */
internal interface SaveIgnoredUsersTask : Task<SaveIgnoredUsersTask.Params, Unit> {
    data class Params(
            val userIds: List<String>
    )
}

internal class DefaultSaveIgnoredUsersTask @Inject constructor(private val monarchy: Monarchy) : SaveIgnoredUsersTask {

    override suspend fun execute(params: SaveIgnoredUsersTask.Params) {
        monarchy.awaitTransaction { realm ->
            // clear current ignored users
            realm.where(IgnoredUserEntity::class.java)
                    .findAll()
                    .deleteAllFromRealm()

            // And save the new received list
            params.userIds.forEach { realm.createObject(IgnoredUserEntity::class.java).apply { userId = it } }
        }
    }
}
