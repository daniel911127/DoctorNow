import Agendar.BottomScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.SlideTransition
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(screen=MainScreen()){navigator ->
            FadeTransition(navigator)
        }
    }
}

class MainScreen: Screen {

    private val settings:Settings = Settings()

    companion object{
        const val KEY_EMAIL = "EMAIL"
        const val KEY_PASS = "PASSWORD"
    }
    @Composable
    override fun Content() {

        var username by remember { mutableStateOf(TextFieldValue()) }
        var password by remember { mutableStateOf(TextFieldValue()) }
        var isValidEmail by remember { mutableStateOf(true) }


        val navigator= LocalNavigator.current

        Box(
            Modifier
                .fillMaxSize()
                .background(Color(73, 160, 209))
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.wrapContentSize(align = Alignment.Center)
            ) {
                Text(
                        "Inicio de Sesión",
                        fontSize = 24.sp,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        isValidEmail = isValidEmail(it.text)
                                    },
                    label = { Text("Correo") },
                    singleLine = true,
                    isError = !isValidEmail && username.text.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                Button(
                    onClick = {
                        settings.putString(KEY_EMAIL, username.text)
                        settings.putString(KEY_PASS, password.text)
                         navigator?.push(BottomScreen())
                    },
                    enabled = username.text.isNotEmpty() && password.text.isNotEmpty(),
                    modifier = Modifier
                        .width(200.dp)
                ) {
                    Text("Iniciar Sesión")
                }
                Button(
                    onClick = {
                        navigator?.push(Registro())
                    },
                    modifier = Modifier
                        .width(200.dp)
                ) {
                    Text("Registrate")
                }
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }
}


