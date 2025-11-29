package com.example.dev_app_mobile.presentation.ui.veterinarian

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
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

data class MedicalRecord(
    val date: String,
    val consultationType: String,
    val description: String = ""
)

@Composable
fun VeterinarianMedicalHistoryScreen(
    navController: NavController,
    petName: String = "Mateo",
    petImageResId: Int = R.drawable.mateo
) {
    val medicalRecords = listOf(
        MedicalRecord("12/08/2025", "Vacuna", "Vacuna antirrábica anual"),
        MedicalRecord("23/06/2025", "Control operación", "Seguimiento post-operatorio"),
        MedicalRecord("04/02/2025", "Operación", "Esterilización")
    )

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
                    color = PetCarePrimary
                )
            }
        }

        // Medical Records List
        items(medicalRecords) { record ->
            MedicalRecordCard(
                medicalRecord = record,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                onClick = {
                    // Navigate to clinical history detail
                    navController.navigate("veterinarian_clinical_history/${record.date}")
                }
            )
        }

        // Spacer before button
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        // New History Button
        item {
            Button(
                onClick = {
                    // Navigate to new clinical history form
                    navController.navigate("new_clinical_history")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PetCarePrimary
                )
            ) {
                Text(
                    text = "Nueva historia",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PetCareWhite
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalRecordCard(
    medicalRecord: MedicalRecord,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE8F4F3) // Light green background
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
                    text = medicalRecord.date,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = medicalRecord.consultationType,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                if (medicalRecord.description.isNotEmpty()) {
                    Text(
                        text = medicalRecord.description,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black.copy(alpha = 0.7f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // Medical icon
            Icon(
                imageVector = Icons.Default.MedicalServices,
                contentDescription = null,
                tint = PetCarePrimary,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewVeterinarianMedicalHistoryScreen() {
    DevappmobileTheme {
        VeterinarianMedicalHistoryScreen(navController = rememberNavController())
    }
}