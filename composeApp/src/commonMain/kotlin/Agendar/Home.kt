package Agendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object Home :Tab{
    override val options: TabOptions
        @Composable
        get() {
            val icon= rememberVectorPainter(Icons.Default.DateRange)
          return remember{
              TabOptions(
                  index=0u,
                  title = "Citas",
                  icon=icon
              )
          }
        }

    @Composable
    override fun Content() {
        val doctors = listOf(
            Doctor("Dr. Carlos", "Pediatra", listOf("Lunes 9:00 AM", "Miércoles 3:00 PM")),
            Doctor("Dr. Ana", "Dermatóloga", listOf("Martes 10:00 AM", "Jueves 2:00 PM")),
            Doctor("Dr. Juan", "Cardiólogo", listOf("Lunes 2:00 PM", "Miércoles 5:00 PM"))
        )
        Box(Modifier.fillMaxSize().background(Color(73, 160, 209)).padding(20.dp), contentAlignment = Alignment.Center) {
            LazyColumn(modifier = Modifier.fillMaxSize().background(Color(73, 160, 209))) {
                items(doctors) { doctor ->
                    DoctorCard(doctor = doctor)
                }
            }
        }
    }

    @Composable
    fun DoctorCard(doctor: Doctor, modifier: Modifier = Modifier) {
        var showDialog by remember { mutableStateOf(false) }

        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .clickable {
                    showDialog = true
                }
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(text = "Doctor: ${doctor.name}", fontSize = 18.sp)
                Text(text = "Especialidad: ${doctor.specialty}", fontSize = 14.sp)
                Button(onClick = { showDialog = true }) {
                    Text(text = "Ver Horarios")
                }
                if (showDialog) {
                    ScheduleDialog(doctor = doctor) {
                        showDialog = false
                    }
                }
            }
        }
    }

    @Composable
    fun ScheduleDialog(doctor: Doctor, onClose: () -> Unit) {
        AlertDialog(
            onDismissRequest = onClose,
            title = { Text("Horarios de ${doctor.name}") },
            text = {
                Column {
                    doctor.schedule.forEach { schedule ->
                        Text(text = "- $schedule", fontSize = 14.sp, color = Color.Gray)
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = onClose
                ) {
                    Text("Cerrar")
                }
            }
        )
    }
}

// Clase Doctor
data class Doctor(
    val name: String,
    val specialty: String,
    val schedule: List<String>
)