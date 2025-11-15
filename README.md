
# DKon Auth SDK for Android

This SDK provides a simple way to authenticate users via the DKon API in your Android applications. It streamlines the process of logging in users with their credentials.

## Features

- Easy user authentication with DKon API.
- Asynchronous login requests using Retrofit.
- Clear handling of authentication responses.

## Installation

1. **Add the SDK**:
   - Clone or download this repository.
   - Include the library in your Android project by adding it to your `build.gradle` file.

   ```groovy
   dependencies {
       implementation 'com.squareup.retrofit2:retrofit:2.9.0'
       implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
   }
   ```

2. **Sync your project** with Gradle.

## Usage

### Initialization

To use the SDK, create an instance of the `DKonAuth` class with your client ID:

```kotlin
val auth = DKonAuth("1302") // Your client ID
```

### User Login

You can authenticate a user by providing the username and password:

```kotlin
auth.login(username, password) { response ->
    if (response != null && response.error_code == 0) {
        // Login successful
        // Handle successful login (e.g., transition to another screen)
    } else {
        // Login failed
        // Handle error (show a message to the user)
    }
}
```

### Example Code

Here’s a simple implementation in an activity:

```kotlin
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
                    Toast.makeText(this, "Login successful! Access Token: ${response.accessToken}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Login failed: ${response?.message ?: "Unknown error"}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
```

## Requirements

- **Android API Level 21 or higher**
- **Kotlin**



## Contributions

Feel free to fork the repository and submit a pull request if you have improvements or new features to add!

## Support

https://DKon.app/dev

