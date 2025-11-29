package com.example.dev_app_mobile.presentation.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.domain.model.Appointment
import com.example.dev_app_mobile.presentation.ui.theme.DevappmobileTheme
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

@Composable
fun AppointmentCard(
    userName: String,
    date: String,
    time: String,
    reason: String,
    // foto opcional (por ahora drawable local, luego pueden cambiarlo por Coil, etc.)
    petPhotoResId: Int? = null,
    modifier: Modifier = Modifier,
    onAccept: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFCEE1FF) // azul suave como en el diseño
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            // Header: foto + nombre
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (petPhotoResId != null) {
                    Image(
                        painter = painterResource(id = petPhotoResId),
                        contentDescription = "Foto de la mascota",
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(PetCarePrimary)
                    )
                } else {
                    // Placeholder si aún no hay foto
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(PetCarePrimary)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = userName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Datos de la cita
            Text(
                text = "Fecha: $date",
                fontSize = 16.sp
            )
            Text(
                text = "Hora: $time",
                fontSize = 16.sp
            )
            Text(
                text = "Motivo de la consulta: $reason",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botones Aceptar / Cancelar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onAccept,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PetCarePrimary,
                        contentColor = PetCareWhite
                    )
                ) {
                    Text(
                        text = "Aceptar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = onCancel,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE69A9A), // rojito suave
                        contentColor = PetCareWhite
                    )
                ) {
                    Text(
                        text = "Cancelar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAppointmentCard() {
    DevappmobileTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            AppointmentCard(
                userName = "Nombre usuario",
                date = "12/12/2025",
                time = "3:30 p.m.",
                reason = "Control general",
                petPhotoResId = R.drawable.perro_dos, // o cualquier drawable de prueba
            )
        }
    }
}
