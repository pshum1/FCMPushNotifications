package com.example.fcmpushnotifs

import com.example.fcmpushnotifs.Constants.BASE_URL
import com.example.fcmpushnotifs.Constants.CONTENT_TYPE
import com.example.fcmpushnotifs.Constants.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationAPI {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ) : Response<ResponseBody>

    companion object {
        operator fun invoke() : NotificationAPI {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NotificationAPI::class.java)
        }
    }
}