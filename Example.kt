import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var auth: DKonAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        auth = DKonAuth("1302") // Your client ID

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            auth.login(username, password) { response ->
                if (response != null && response.error_code == 0) {
                    // Login successful
                    Toast.makeText(this, "Login successful! Access Token: ${response.accessToken}", Toast.LENGTH_LONG).show()
                } else {
                    // Login failed
                    Toast.makeText(this, "Login failed: ${response?.message ?: "Unknown error"}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
