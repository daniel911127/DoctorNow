package Agendar

import MainScreen.Companion.KEY_EMAIL
import MainScreen.Companion.KEY_PASS
import Registro.Companion.KEY_LASTNAME
import Registro.Companion.KEY_NAME
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.russhwolf.settings.Settings

object Perfil : Tab {

    private val settings: Settings = Settings()
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Person)
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Perfil",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val user = settings.getString(KEY_EMAIL, "")
        val name = settings.getString(KEY_NAME, "")
        val lastname = settings.getString(KEY_LASTNAME, "")

        Box(
            Modifier
                .fillMaxSize()
                .background(Color(73, 160, 209))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Correo: $user", fontSize = 22.sp, color = Color.White, modifier = Modifier.padding(bottom = 8.dp))
                Text("Nombre: $name", fontSize = 22.sp, color = Color.White, modifier = Modifier.padding(bottom = 8.dp))
                Text("Apellido: $lastname", fontSize = 22.sp, color = Color.White, modifier = Modifier.padding(bottom = 8.dp))
            }
        }
    }
}
