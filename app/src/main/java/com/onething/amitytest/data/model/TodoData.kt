package com.onething.amitytest.data.model


import com.google.gson.annotations.SerializedName
import com.onething.amitytest.data.db.entity.TodoEntity
import com.onething.amitytest.domain.model.Todo
import com.onething.amitytest.utils.orZero

data class TodoData(
    @SerializedName("completed") val completed: Boolean? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("userId") val userId: Int? = null
)

fun TodoData?.toEntity() : TodoEntity {
    return TodoEntity(
      status = if (this?.completed == true) "Done" else "Have to do",
      id = this?.id.orZero,
      title = this?.title.orEmpty(),
      userId = this?.userId.orZero,
      isDone = this?.completed ?: false
    )
}