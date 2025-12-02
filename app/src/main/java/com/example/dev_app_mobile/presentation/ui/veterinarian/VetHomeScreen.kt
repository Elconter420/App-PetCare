package com.example.dev_app_mobile.presentation.ui.veterinarian

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.ui.theme.DevappmobileTheme
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

data class Patient(
    val id: Int,
    val name: String,
    val petName: String,
    val imageResId: Int,
    val nextAppointment: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeterinarianMainScreen(
    navController: NavController,
    userName: String = "Dra. Melissa"
) {
    val patients = listOf(
        Patient(1, "Karen", "Lyra", R.drawable.lyra, "Hoy 3:30 PM"),
        Patient(2, "Carlos", "Ryuk", R.drawable.ryuk, "Mañana 10:00 AM"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PetCareWhite)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Hola",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
                Text(
                    text = userName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            IconButton(
                onClick = { navController.navigate("notifications") }
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notificaciones",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        // Quick Actions Cards
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Mis pacientes
            QuickActionCard(
                title = "Mis pacientes",
                icon = Icons.Default.People,
                color = Color(0xFF4CAF50), // Verde
                onClick = { navController.navigate("my_patients") },
                modifier = Modifier.weight(1f)
            )

            // Quiero ser premium
            QuickActionCard(
                title = "Quiero ser premium",
                icon = Icons.Default.Star,
                color = Color(0xFFFF9800), // Naranja
                onClick = { navController.navigate("veterinarian_premium") },
                modifier = Modifier.weight(1f)
            )
        }

        // Gestionar mis citas
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .height(120.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3F2FD) // Azul claro
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            onClick = { navController.navigate("manage_appointments") }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Citas",
                    tint = Color(0xFF2196F3), // Azul
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Gestionar mis citas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1976D2) // Azul oscuro
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Revisa, confirma o reprograma tus citas pendientes",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF1976D2).copy(alpha = 0.8f),
                        lineHeight = 18.sp
                    )
                }

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Ver citas",
                    tint = Color(0xFF1976D2),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Sección: Mis pacientes recientes
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mis pacientes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                TextButton(
                    onClick = { navController.navigate("all_patients") }
                ) {
                    Text(
                        text = "Ver todos",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = PetCarePrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Lista horizontal de pacientes
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(patients) { patient ->
                    PatientCard(
                        patient = patient,
                        onClick = { navController.navigate("patient_detail/${patient.id}") }
                    )
                }
            }
        }

        // Sección: Artículo/blog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF3E5F5) // Púrpura muy claro
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            onClick = { navController.navigate("article_detail") }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "¿Sabes cuidar la piel de tu peludo?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7B1FA2), // Púrpura oscuro
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Descubre consejos esenciales para mantener la piel de tu mascota saludable durante todo el año.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF7B1FA2).copy(alpha = 0.8f),
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Etiqueta de categoría
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFE1BEE7), // Púrpura claro
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = "CUIDADO",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF7B1FA2),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    TextButton(
                        onClick = { navController.navigate("article_detail") }
                    ) {
                        Text(
                            text = "Leer más",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF7B1FA2)
                        )
                    }
                }
            }
        }

        // Sección: Citas del día
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF5F5F5)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Citas de hoy",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                    Badge(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White
                    ) {
                        Text(
                            text = "3",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Lista de citas del día
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TodayAppointmentItem(
                        time = "10:00 AM",
                        patientName = "Karen - Lyra",
                        reason = "Control anual"
                    )

                    TodayAppointmentItem(
                        time = "2:30 PM",
                        patientName = "Carlos - Ryuk",
                        reason = "Vacunación"
                    )

                    TodayAppointmentItem(
                        time = "4:00 PM",
                        patientName = "Ana - Max",
                        reason = "Consulta general"
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = { navController.navigate("today_appointments") },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "Ver agenda completa",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = PetCarePrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickActionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = color,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientCard(
    patient: Patient,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(180.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = patient.imageResId),
                contentDescription = "Foto de ${patient.petName}",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(PetCarePrimary),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = patient.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Text(
                text = patient.petName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Badge para próxima cita
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = PetCarePrimary.copy(alpha = 0.2f)
            ) {
                Text(
                    text = patient.nextAppointment,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PetCarePrimary,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun TodayAppointmentItem(
    time: String,
    patientName: String,
    reason: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Hora
        Text(
            text = time,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.width(80.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Información de la cita
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = patientName,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Text(
                text = reason,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }

        IconButton(
            onClick = { /* Ver detalles de la cita */ },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Opciones",
                tint = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewVeterinarianMainScreen() {
    DevappmobileTheme {
        VeterinarianMainScreen(navController = rememberNavController())
    }
}