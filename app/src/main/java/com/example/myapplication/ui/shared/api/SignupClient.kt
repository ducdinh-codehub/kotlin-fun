package com.example.myapplication.ui.shared.api

import com.example.myapplication.ui.shared.dataModel.Account
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object SignupClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                coerceInputValues = true // Helps with optional fields
            })
        }
    }

    suspend fun createAccount(request : Account?): String {
        var response: HttpResponse? = null;
        var responseText: String = "";
        print("request123"+request)
        try {
            /*
            response = client.post(signup_create_entrypoint) {
                header("Content-Type", "application/json")
                header("accept", "application/json") // Replace with your actual API key securely
                setBody(request)
            }*/

            print("responseFromAPI"+response)
            responseText = response!!.body<GenerateContentResponse>().candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "No response"
        } catch (e: Exception) {
            responseText = "Error: ${e.message}"
            print("responseFromAPIError"+responseText)
        }
        return responseText
    }
}