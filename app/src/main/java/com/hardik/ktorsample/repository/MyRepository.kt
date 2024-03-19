package com.hardik.ktorsample.repository

import com.hardik.ktorsample.data.network.model.Post
import com.hardik.ktorsample.data.network.KtorClient
import com.hardik.ktorsample.data.network.model.Comment
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse


class MyRepository {

    suspend fun getPosts(): List<Post> =
        KtorClient.httpClient.get("https://jsonplaceholder.typicode.com/posts")

    suspend fun getComments(id: String): List<Comment> = KtorClient.httpClient.get {
        url("https://jsonplaceholder.typicode.com/comments")
        parameter("postId", id)
    }

    suspend fun postPost(post: Post): Post = KtorClient.httpClient.post {
        url("https://jsonplaceholder.typicode.com/posts")
        body = post
    }

    suspend fun putPost(id: Int, post: Post): Post = KtorClient.httpClient.put {
        url("https://jsonplaceholder.typicode.com/posts/$id")
        body = post
    }
    suspend fun patchPost(id: Int, map: MutableMap<String,String>): Post = KtorClient.httpClient.patch {
        url("https://jsonplaceholder.typicode.com/posts/$id")
        body = map
    }
    suspend fun deletePost(id: Int): HttpResponse = KtorClient.httpClient.delete {
        url("https://jsonplaceholder.typicode.com/posts/$id")
    }
}/* try {
            client.get { url(HttpRoutes.POSTS) }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }*/