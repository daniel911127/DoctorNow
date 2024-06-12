package Agendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object MisCitas : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon= rememberVectorPainter(Icons.Default.Info)
            return remember{
                TabOptions(
                    index=0u,
                    title = "Mis citas",
                    icon=icon
                )
            }
        }

    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize().background(Color.Yellow), contentAlignment = Alignment.Center){
            Text("MisCitasScreen", fontSize = 22.sp,color= Color.Blue)
        }
    }
}