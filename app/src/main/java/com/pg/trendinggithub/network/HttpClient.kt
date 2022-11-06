
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private var API_BASE_URL = " https://api.github.com"

var httpClient = OkHttpClient.Builder()

var builder = Retrofit.Builder()
    .baseUrl(API_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())

var retrofit: Retrofit = builder
    .client(
        httpClient.build()
    )
    .build()
