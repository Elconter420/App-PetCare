package com.example.dev_app_mobile.presentation.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

data class AppointmentData(
    val petName: String = "Lyra",
    val veterinarian: String = "Johan Fernando Castillo",
    val date: String = "DD/MM/AAAA",
    val time: String = "HH:MM"
)

@Composable
fun AppointmentSuccessDialog(
    appointmentData: AppointmentData,
    onDismiss: () -> Unit,
    onAccept: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = PetCareWhite
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Icono de éxito
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Cita solicitada exitosamente",
                    tint = PetCarePrimary,
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Título principal
                Text(
                    text = "¡Haz solicitado una cita!*",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Información de la cita
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF8F9FA), RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AppointmentInfoRow(
                        label = "Cita para:",
                        value = appointmentData.petName
                    )
                    AppointmentInfoRow(
                        label = "Veterinario:",
                        value = appointmentData.veterinarian
                    )
                    AppointmentInfoRow(
                        label = "Fecha:",
                        value = appointmentData.date
                    )
                    AppointmentInfoRow(
                        label = "Hora:",
                        value = appointmentData.time
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Mensaje informativo
                Text(
                    text = "¡Todo listo! Solo falta que tu veterinario confirme la cita. Te avisaremos enseguida.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = Color.Black.copy(alpha = 0.7f),
                    lineHeight = 18.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Texto del asterisco
                Text(
                    text = "*La cita queda en estado pendiente hasta confirmación del veterinario",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    lineHeight = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botón Aceptar
                Button(
                    onClick = onAccept,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(25.dp),
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
            }
        }
    }
}

@Composable
private fun AppointmentInfoRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black.copy(alpha = 0.8f)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAppointmentSuccessDialog() {
    val sampleAppointmentData = AppointmentData(
        petName = "Lyra",
        veterinarian = "Johan Fernando Castillo",
        date = "17/08/2025",
        time = "20:00 PM"
    )

    AppointmentSuccessDialog(
        appointmentData = sampleAppointmentData,
        onDismiss = {},
        onAccept = {}
    )
}