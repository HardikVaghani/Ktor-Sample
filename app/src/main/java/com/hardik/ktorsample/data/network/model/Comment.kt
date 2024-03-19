package com.hardik.ktorsample.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val body: String = "",
    val email: String = "",
    val id: Int = 0,
    val name: String = "",
    val postId: Int = 0
)