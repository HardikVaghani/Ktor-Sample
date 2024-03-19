package com.hardik.ktorsample.data.remote

import com.hardik.ktorsample.data.remote.dto.PostRequest
import com.hardik.ktorsample.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface PostsService {

    suspend fun getPosts(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?

    companion object {
        /// this is specific for JsonFeature if you want
        val json = kotlinx.serialization.json.Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            isLenient = true
        }
        fun create(): PostsService {
            return PostsServiceImpl(
                client = HttpClient(Android) {
                    install(HttpTimeout){
                        connectTimeoutMillis = 30000
                        socketTimeoutMillis = 30000
                        requestTimeoutMillis = 30000
                    }

                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                        /// OR
//                        serializer = KotlinxSerializer(json)
                    }

                    defaultRequest {
                        contentType(ContentType.Application.Json)
                        accept(ContentType.Application.Json)
                    }
                }
            )
        }
    }
}