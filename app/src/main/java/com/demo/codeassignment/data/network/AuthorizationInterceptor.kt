package com.demo.codeassignment.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationInterceptor @Inject constructor() :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token = "5255fca41c471d23d6a1c523c58fc0f7"
            //"sessionManager.getString(Constants.AUTHORIZATION_TOKEN)"
        token?.let {
            request = request.newBuilder()
                .addHeader("REMENBER-TOKEN", it)
              //  .addHeader("Authorization", "Bearer $it")
                .build()
        }
        return chain.proceed(request)
    }
}