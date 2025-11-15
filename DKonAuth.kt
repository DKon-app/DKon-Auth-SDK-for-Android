import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class AuthResponse(
    val accessToken: String?,
    val accountId: String?,
    val error_code: Int?,
    val message: String?
)

interface DKonAPI {
    @POST("api/v3/method/account.signIn")
    fun login(@Body request: AuthRequest): Call<AuthResponse>
}

data class AuthRequest(
    val clientId: String,
    val username: String,
    val password: String
)

class DKonAuth(private val clientId: String) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.dkon.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: DKonAPI = retrofit.create(DKonAPI::class.java)

    fun login(username: String, password: String, callback: (AuthResponse?) -> Unit) {
        val request = AuthRequest(clientId, username, password)
        api.login(request).enqueue(object : retrofit2.Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: retrofit2.Response<AuthResponse>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}
