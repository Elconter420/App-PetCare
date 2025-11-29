package com.example.dev_app_mobile.presentation.ui.veterinarian

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
import androidx.compose.runtime.*
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

@Composable
fun VeterinarianClinicalHistoryScreen(
    navController: NavController,
    petName: String = "Mateo",
    petImageResId: Int = R.drawable.mateo
) {
    var fecha by remember { mutableStateOf("DD/MM/AAAA") }
    var diagnostico by remember { mutableStateOf("") }
    var tratamiento by remember { mutableStateOf("") }
    var recomendaciones by remember { mutableStateOf("") }

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
            text = "Historia Clinica",
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
                color = PetCarePrimary
            )
        }

        // Clinical History Form
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
                // Fecha
                ClinicalHistoryField(
                    label = "Fecha:",
                    value = fecha,
                    onValueChange = { fecha = it },
                    modifier = Modifier.padding(bottom = 20.dp),
                    isEditable = true,
                    placeholder = "DD/MM/AAAA"
                )

                // Diagnóstico
                ClinicalHistoryField(
                    label = "Diagnóstico:",
                    value = diagnostico,
                    onValueChange = { diagnostico = it },
                    modifier = Modifier.padding(bottom = 20.dp),
                    isMultiline = true,
                    isEditable = true,
                    placeholder = "Describe el diagnóstico del paciente..."
                )

                // Tratamiento
                ClinicalHistoryField(
                    label = "Tratamiento:",
                    value = tratamiento,
                    onValueChange = { tratamiento = it },
                    modifier = Modifier.padding(bottom = 20.dp),
                    isMultiline = true,
                    isEditable = true,
                    placeholder = "Describe el tratamiento indicado..."
                )

                // Recomendaciones
                ClinicalHistoryField(
                    label = "Recomendaciones:",
                    value = recomendaciones,
                    onValueChange = { recomendaciones = it },
                    modifier = Modifier.padding(bottom = 16.dp),
                    isMultiline = true,
                    isEditable = true,
                    placeholder = "Indica las recomendaciones para el dueño..."
                )
            }
        }

        // Save Button
        Button(
            onClick = {
                // Save clinical history logic
                // You can add validation here
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PetCarePrimary
            )
        ) {
            Text(
                text = "Guardar",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = PetCareWhite
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ClinicalHistoryField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    isMultiline: Boolean = false,
    isEditable: Boolean = false,
    placeholder: String = ""
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (isEditable) {
            if (isMultiline) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    placeholder = {
                        Text(
                            text = placeholder,
                            color = Color.Gray
                        )
                    },
                    shape = RoundedCornerShape(12.dp)
                )
            } else {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = placeholder,
                            color = Color.Gray
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }
        } else {
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
                        text = value.ifEmpty { "No especificado" },
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
                        text = value.ifEmpty { "No especificado" },
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
}

@Preview(showBackground = true)
@Composable
private fun PreviewVeterinarianClinicalHistoryScreen() {
    DevappmobileTheme {
        VeterinarianClinicalHistoryScreen(navController = rememberNavController())
    }
}