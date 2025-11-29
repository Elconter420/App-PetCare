package com.example.dev_app_mobile.presentation.ui.owner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun ScheduleAppointmentScreen(
    onBackClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {}
) {
    var selectedPet by remember { mutableStateOf("Lyra") }
    var motivo by remember { mutableStateOf("") }
    var selectedVet by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("08/17/2025") }
    var selectedTime by remember { mutableStateOf("20:00 PM") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        // Fecha y Titulo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
            }

            Text(
                text = "Agendar cita\nveterinaria",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }


        Text(
            text = "¿Para quién es la cita?",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            PetOptionItem(
                name = "Lyra",
                avatarRes = R.drawable.lyra, // cámbialo si usas otro recurso
                isSelected = selectedPet == "Lyra",
                onClick = { selectedPet = "Lyra" }
            )
            PetOptionItem(
                name = "Ryuk",
                avatarRes = R.drawable.ryuk, // cámbialo si usas otro recurso
                isSelected = selectedPet == "Ryuk",
                onClick = { selectedPet = "Ryuk" }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Motivo de la consulta:",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = motivo,
            onValueChange = { motivo = it },
            placeholder = {
                Text(
                    text = "Escribe todos los detalles e información necesaria",
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Seleccione el veterinario:",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = selectedVet,
            onValueChange = { selectedVet = it },
            placeholder = { Text("Nombre del veterinario") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Seleccione la fecha y hora de la cita:",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // calendario
        Surface(
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* aquí iría el dia real */ }
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Date",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = "Calendario"
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = selectedDate, fontSize = 14.sp)
                Text(text = "MM/DD/YYYY", fontSize = 11.sp, color = MaterialTheme.colorScheme.outline)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // selector de hora
        Surface(
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* aquí iría el tiempo real */ }
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Enter time",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Filled.AccessTime,
                        contentDescription = "Horario"
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = selectedTime, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de solicitar cita
        Button(
            onClick = onConfirmClick,
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
                text = "Solicitar cita",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun PetOptionItem(
    name: String,
    avatarRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val background = if (isSelected)
        PetCarePrimary.copy(alpha = 0.2f)
    else
        MaterialTheme.colorScheme.surface

    Surface(
        shape = RoundedCornerShape(12.dp),
        tonalElevation = if (isSelected) 2.dp else 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .background(background)
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = avatarRes),
                contentDescription = name,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = name,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScheduleAppointmentScreenPreview() {
    ScheduleAppointmentScreen()
}
