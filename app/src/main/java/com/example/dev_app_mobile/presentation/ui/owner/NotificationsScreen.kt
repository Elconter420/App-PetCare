package com.example.dev_app_mobile.presentation.ui.owner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.ui.theme.DevappmobileTheme
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareSecondary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

@Composable
fun NotificationsScreen(
    navController: NavController
) {
    var pushNotificationsEnabled by remember { mutableStateOf(true) }
    var emailNotificationsEnabled by remember { mutableStateOf(true) }
    var appointmentRemindersEnabled by remember { mutableStateOf(true) }
    var confirmationsChangesEnabled by remember { mutableStateOf(true) }
    var cancellationsEnabled by remember { mutableStateOf(true) }
    var reviewsEnabled by remember { mutableStateOf(true) }
    var offersNewsEnabled by remember { mutableStateOf(true) }

    var selectedNotificationMethod by remember { mutableStateOf(0) }
    var selectedHour by remember { mutableStateOf(9) }
    var selectedMinute by remember { mutableStateOf(30) }
    var isAM by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PetCareWhite)
            .verticalScroll(rememberScrollState())
    ) {
        // Header with back button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 39.dp, start = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(33.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }

        // Title
        Text(
            text = "Notificaciones y preferencias 🔔",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 46.dp, bottom = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = androidx.compose.ui.text.font.FontFamily.Serif
        )

        // Tipos de notificación section
        NotificationSectionTitle("Tipos de notificación")

        // Citas y servicios subsection
        Text(
            text = "📅 Citas y servicios",
            modifier = Modifier.padding(start = 28.dp, top = 51.dp, bottom = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )

        // Toggle cards for Citas y servicios
        NotificationToggleCard(
            title = "Recordatorios de citas",
            description = "Recibe alertas antes de cada cita.",
            isChecked = appointmentRemindersEnabled,
            onCheckedChange = { appointmentRemindersEnabled = it },
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 8.dp)
        )

        NotificationToggleCard(
            title = "Confirmaciones y cambios de horario",
            description = "Te avisamos cuando un veterinario o cliente modifique una cita.",
            isChecked = confirmationsChangesEnabled,
            onCheckedChange = { confirmationsChangesEnabled = it },
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 8.dp)
        )

        NotificationToggleCard(
            title = "Cancelaciones",
            description = "Recibe notificaciones cuando se cancele un servicio.",
            isChecked = cancellationsEnabled,
            onCheckedChange = { cancellationsEnabled = it },
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 8.dp)
        )

        // Comunicación subsection
        Text(
            text = "💬 Comunicación",
            modifier = Modifier.padding(start = 28.dp, top = 32.dp, bottom = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black.copy(alpha = 0.56f)
        )

        // Toggle cards for Comunicación
        NotificationToggleCard(
            title = "Comentarios o reseñas",
            description = "Recibe alertas cuando un cliente deje una reseña.",
            isChecked = reviewsEnabled,
            onCheckedChange = { reviewsEnabled = it },
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 8.dp)
        )

        NotificationToggleCard(
            title = "Ofertas y novedades de PetCare",
            description = "Noticias, promociones y actualizaciones.",
            isChecked = offersNewsEnabled,
            onCheckedChange = { offersNewsEnabled = it },
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 8.dp)
        )

        // Método de notificación section
        NotificationSectionTitle("Método de notificación")

        // Radio buttons for notification method
        Column(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 16.dp)
        ) {
            NotificationRadioButton(
                text = "Notificaciones dentro de la app (push)",
                selected = selectedNotificationMethod == 0,
                onSelect = { selectedNotificationMethod = 0 }
            )

            NotificationRadioButton(
                text = "Correo electrónico",
                selected = selectedNotificationMethod == 1,
                onSelect = { selectedNotificationMethod = 1 }
            )

            NotificationRadioButton(
                text = "Ambos",
                selected = selectedNotificationMethod == 2,
                onSelect = { selectedNotificationMethod = 2 }
            )
        }

        // Tipo de notificación section
        NotificationSectionTitle("Tipo de notificación")

        // Time picker section
        Card(
            modifier = Modifier
                .padding(horizontal = 42.dp, vertical = 24.dp)
                .fillMaxWidth()
                .height(461.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = PetCareWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            border = CardDefaults.outlinedCardBorder(false)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hasta",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Time picker
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Hour
                    TimePickerSection(
                        value = selectedHour,
                        onValueChange = { selectedHour = it },
                        label = "Hour"
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = ":",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Minute
                    TimePickerSection(
                        value = selectedMinute,
                        onValueChange = { selectedMinute = it },
                        label = "Minute"
                    )

                    Spacer(modifier = Modifier.width(32.dp))

                    // AM/PM
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TimePickerAMPMButton(
                            text = "AM",
                            isSelected = isAM,
                            onSelect = { isAM = true }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TimePickerAMPMButton(
                            text = "PM",
                            isSelected = !isAM,
                            onSelect = { isAM = false }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Accept button
                Button(
                    onClick = { /* TODO: Save preferences */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PetCarePrimary
                    )
                ) {
                    Text(
                        text = "Aceptar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PetCareWhite
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun NotificationSectionTitle(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp)
            .background(PetCareSecondary)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}

@Composable
private fun NotificationToggleCard(
    title: String,
    description: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(69.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = PetCareWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = CardDefaults.outlinedCardBorder(false)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black.copy(alpha = 0.7f)
                )
            }

            Switch(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = PetCareWhite,
                    checkedTrackColor = Color(0xFF34C759),
                    uncheckedThumbColor = PetCareWhite,
                    uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
                )
            )
        }
    }
}

@Composable
private fun NotificationRadioButton(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onSelect
            )
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                selectedColor = PetCarePrimary
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
private fun TimePickerSection(
    value: Int,
    onValueChange: (Int) -> Unit,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier
                .width(80.dp)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = value.toString().padStart(2, '0'),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )

                // Simple increment/decrement buttons (could be enhanced with proper number picker)
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(24.dp)
                        .align(Alignment.CenterEnd),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    TimePickerIncrementButton(
                        onClick = { onValueChange(value + 1) }
                    )
                    TimePickerDecrementButton(
                        onClick = { onValueChange(value - 1) }
                    )
                }
            }
        }
    }
}

@Composable
private fun TimePickerIncrementButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(RoundedCornerShape(topEnd = 12.dp))
            .background(Color.LightGray.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "+",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun TimePickerDecrementButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(RoundedCornerShape(bottomEnd = 12.dp))
            .background(Color.LightGray.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "-",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun TimePickerAMPMButton(
    text: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isSelected) PetCarePrimary else Color.LightGray.copy(alpha = 0.3f)
            )
            .selectable(
                selected = isSelected,
                onClick = onSelect
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) PetCareWhite else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNotificationsScreen() {
    DevappmobileTheme {
        NotificationsScreen(navController = rememberNavController())
    }
}