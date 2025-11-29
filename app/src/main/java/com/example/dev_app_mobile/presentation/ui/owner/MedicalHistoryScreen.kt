package com.example.dev_app_mobile.presentation.ui.owner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.ui.theme.DevappmobileTheme
import com.example.dev_app_mobile.presentation.ui.theme.PetCareBlack
import com.example.dev_app_mobile.presentation.ui.theme.PetCareDark
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareSecondary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

data class MedicalHistoryItem(
    val date: String,
    val service: String,
    val professional: String,
    val type: ServiceType
)

enum class ServiceType {
    VETERINARIAN, GROOMING
}

@Composable
fun MedicalHistoryScreenWithPhoto(
    navController: NavController,
    petName: String = "Lyra",
    petImageResId: Int = R.drawable.lyra
) {
    val historyItems = listOf(
        MedicalHistoryItem("12/08/2025", "Peluqueria", "Spa Pattas", ServiceType.GROOMING),
        MedicalHistoryItem("23/06/2025", "Veterinario", "Jhoan Fernando Castillo", ServiceType.VETERINARIAN),
        MedicalHistoryItem("04/02/2025", "Veterinario", "Jhoan Fernando Castillo", ServiceType.VETERINARIAN)
    )

    // Usamos LazyColumn como contenedor principal en lugar de Column con scroll
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(PetCareWhite),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Header with back button
        item {
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
        }

        // Title
        item {
            Text(
                text = "Historial",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Serif
            )
        }

        // Pet Profile with Photo
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Photo
                Image(
                    painter = painterResource(id = petImageResId),
                    contentDescription = "Foto de $petName",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(PetCarePrimary),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Pet Name
                Text(
                    text = petName,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PetCareDark
                )
            }
        }

        // History List
        items(historyItems) { item ->
            HistoryCard(
                historyItem = item,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                onClick = {
                    // Navigate to clinical history detail
                    navController.navigate("clinical_history/${item.date}")
                }
            )
        }

        // Spacer before buttons
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Action Buttons
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        // Navigate to new appointment
                        navController.navigate("new_appointment")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PetCarePrimary
                    )
                ) {
                    Text(
                        text = "Nueva Cita",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PetCareBlack
                    )
                }

                Button(
                    onClick = {
                        // Navigate to new service
                        navController.navigate("new_service")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PetCareSecondary
                    )
                ) {
                    Text(
                        text = "Nuevo servicio",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PetCareBlack
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryCard(
    historyItem: MedicalHistoryItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (historyItem.type) {
                ServiceType.VETERINARIAN -> Color(0xFFE8F4F3) // Light green for vet
                ServiceType.GROOMING -> Color(0xFFF0E8FF) // Light purple for grooming
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = historyItem.date,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = historyItem.service,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = historyItem.professional,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(alpha = 0.7f)
                )
            }

            // Service type icon
            Icon(
                imageVector = when (historyItem.type) {
                    ServiceType.VETERINARIAN -> Icons.Default.MedicalServices
                    ServiceType.GROOMING -> Icons.Default.Spa
                },
                contentDescription = null,
                tint = when (historyItem.type) {
                    ServiceType.VETERINARIAN -> PetCarePrimary
                    ServiceType.GROOMING -> Color(0xFF7E57C2)
                },
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMedicalHistoryScreen() {
    DevappmobileTheme {
        MedicalHistoryScreenWithPhoto(navController = rememberNavController())
    }
}