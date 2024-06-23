package Agendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object Home : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.DateRange)
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Citas",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        var doctors by remember { mutableStateOf(
            listOf(
                Doctor(
                    "Dr. Carlos", "Pediatra", mutableStateListOf(
                        Schedule("Lunes 9:00 AM", true),
                        Schedule("Miércoles 3:00 PM", false)
                    )
                ),
                Doctor(
                    "Dr. Ana", "Dermatóloga", mutableStateListOf(
                        Schedule("Martes 10:00 AM", true),
                        Schedule("Jueves 2:00 PM", true)
                    )
                ),
                Doctor(
                    "Dr. Juan", "Cardiólogo", mutableStateListOf(
                        Schedule("Lunes 2:00 PM", false),
                        Schedule("Miércoles 5:00 PM", true)
                    )
                )
            )
        )}

        Box(
            Modifier
                .fillMaxSize()
                .background(Color(73, 160, 209))
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize().background(Color(73, 160, 209))) {
                items(doctors) { doctor ->
                    DoctorCard(doctor = doctor) { updatedDoctor ->
                        doctors = doctors.map {
                            if (it.name == updatedDoctor.name) updatedDoctor else it
                        }
                    }
                    Spacer(Modifier.height(18.dp))
                }
            }
        }
    }

    @Composable
    fun DoctorCard(doctor: Doctor, modifier: Modifier = Modifier, onDoctorUpdate: (Doctor) -> Unit) {
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
                    ScheduleDialog(doctor = doctor, onClose = { showDialog = false }, onScheduleUpdate = { updatedSchedules ->
                        onDoctorUpdate(doctor.copy(schedule = updatedSchedules))
                    })
                }
            }
        }
    }

    @Composable
    fun ScheduleDialog(doctor: Doctor, onClose: () -> Unit, onScheduleUpdate: (List<Schedule>) -> Unit) {
        AlertDialog(
            onDismissRequest = onClose,
            title = { Text("Horarios de ${doctor.name}") },
            text = {
                Column {
                    doctor.schedule.forEach { schedule ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "- ${schedule.time} ",
                                fontSize = 14.sp,
                                color = if (schedule.isAvailable) Color.Black else Color.Red
                            )
                            Spacer(modifier = Modifier.weight(1.5f))
                            if (schedule.isAvailable) {
                                Button(onClick = {
                                    val updatedSchedules = doctor.schedule.map {
                                        if (it.time == schedule.time) it.copy(isAvailable = false) else it
                                    }
                                    onScheduleUpdate(updatedSchedules)
                                    onClose()
                                }, modifier=Modifier.size(width = 100.dp, height = 30.dp)) {
                                    Text(text = "Seleccionar", fontSize = 8.sp)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
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

// Clases Doctor y Schedule
data class Doctor(
    val name: String,
    val specialty: String,
    val schedule: List<Schedule>
)

data class Schedule(
    val time: String,
    val isAvailable: Boolean
)
