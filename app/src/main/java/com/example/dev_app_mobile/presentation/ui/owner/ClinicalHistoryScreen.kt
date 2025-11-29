package com.example.dev_app_mobile.presentation.ui.owner

import com.example.dev_app_mobile.presentation.ui.theme.PetCareSecondary
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.dev_app_mobile.presentation.ui.theme.PetCareDark
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

data class ClinicalRecord(
    val date: String,
    val veterinarian: String,
    val diagnosis: String,
    val treatment: String,
    val recommendations: String
)

@Composable
fun ClinicalHistoryScreen(
    navController: NavController,
    petName: String = "Lyra",
    petImageResId: Int = R.drawable.lyra, // Cambia por tu imagen
    clinicalRecord: ClinicalRecord = ClinicalRecord(
        date = "23/06/2025",
        veterinarian = "Jhoan Fernando Castillo",
        diagnosis = "Control de rutina - Estado saludable",
        treatment = "Vacuna múltiple, desparasitación",
        recommendations = "Continuar con alimentación balanceada. Próximo control en 6 meses."
    )
) {
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
            text = "Historia clínica",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = androidx.compose.ui.text.font.FontFamily.Serif
        )

        // Pet Profile with Photo
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

        // Clinical Record Form
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Date
                ClinicalHistoryField(
                    label = "Fecha:",
                    value = clinicalRecord.date,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Veterinarian
                ClinicalHistoryField(
                    label = "Veterinario:",
                    value = clinicalRecord.veterinarian,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Diagnosis
                ClinicalHistoryField(
                    label = "Diagnóstico:",
                    value = clinicalRecord.diagnosis,
                    modifier = Modifier.padding(bottom = 20.dp),
                    isMultiline = true
                )

                // Treatment
                ClinicalHistoryField(
                    label = "Tratamiento:",
                    value = clinicalRecord.treatment,
                    modifier = Modifier.padding(bottom = 20.dp),
                    isMultiline = true
                )

                // Recommendations
                ClinicalHistoryField(
                    label = "Recomendaciones:",
                    value = clinicalRecord.recommendations,
                    modifier = Modifier.padding(bottom = 16.dp),
                    isMultiline = true
                )
            }
        }

        /* Action Buttons tal vez se puedan implementar mas adelante
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    // Edit clinical record
                    navController.navigate("edit_clinical_history")
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PetCareSecondary
                )
            ) {
                Text(
                    text = "Editar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PetCareWhite
                )
            }

            Button(
                onClick = {
                    // Share clinical record
                    // TODO: Implement share functionality
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .padding(start = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PetCarePrimary
                )
            ) {
                Text(
                    text = "Compartir",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PetCareWhite
                )
            }
        } */

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ClinicalHistoryField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    isMultiline: Boolean = false
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (isMultiline) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = value,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    lineHeight = 20.sp
                )
            }
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = value,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewClinicalHistoryScreen() {
    DevappmobileTheme {
        ClinicalHistoryScreen(navController = rememberNavController())
    }
}