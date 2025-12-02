package com.example.dev_app_mobile.presentation.components.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dev_app_mobile.presentation.components.common.AppointmentConfirmationCard
import com.example.dev_app_mobile.presentation.ui.theme.PetCareDark

@Composable
fun AppointmentConfirmationScreen(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Confirmar Cita",
            style = MaterialTheme.typography.headlineMedium,
            color = PetCareDark,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        AppointmentConfirmationCard(
            patientName = "Nombre de la Mascota", // Estos datos vendrían de ViewModel
            date = "Fecha de la cita",
            time = "Hora de la cita",
            reason = "Motivo de la consulta",
            onAcceptClick = onConfirm,
            onCancelClick = onCancel,
            modifier = Modifier.fillMaxWidth(),
            showPatientAvatar = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Revisa los detalles antes de confirmar",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}