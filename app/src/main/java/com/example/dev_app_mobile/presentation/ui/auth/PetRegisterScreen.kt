package com.example.dev_app_mobile.presentation.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetRegisterScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    // Estados visuales
    var petName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    var speciesExpanded by remember { mutableStateOf(false) }
    val speciesOptions = listOf("Perro", "Gato", "Otro")
    var selectedSpecies by remember { mutableStateOf("") }

    var breed by remember { mutableStateOf("") }
    var sex by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start
        ) {


            Text(
                text = "Agregar mascota",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "Cuéntanos sobre tu mejor amigo\npara ofrecerle el mejor cuidado.",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 16.dp)
            )


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(96.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.perro_3),
                            contentDescription = "Foto de perfil",
                            modifier = Modifier.fillMaxSize().clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                    }

                    IconButton(
                        onClick = { /* TODO: abrir galería */ },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(32.dp)
                            .background(Color.White, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhotoCamera,
                            contentDescription = "Subir foto"
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Sube la foto de tu mascota",
                    fontSize = 14.sp
                )
            }

            LabeledTextFieldPet(
                label = "Nombre de la mascota:*",
                value = petName,
                onValueChange = { petName = it }
            )

            LabeledTextFieldPet(
                label = "Edad:",
                value = age,
                onValueChange = { age = it }
            )

            Text(
                text = "Especie:*",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            ExposedDropdownMenuBox(
                expanded = speciesExpanded,
                onExpandedChange = { speciesExpanded = !speciesExpanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 12.dp)
            ) {
                OutlinedTextField(
                    value = selectedSpecies,
                    onValueChange = { },
                    readOnly = true,
                    placeholder = { Text("Selecciona la especie") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = speciesExpanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .height(48.dp)
                )

                ExposedDropdownMenu(
                    expanded = speciesExpanded,
                    onDismissRequest = { speciesExpanded = false }
                ) {
                    speciesOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedSpecies = option
                                speciesExpanded = false
                            }
                        )
                    }
                }
            }

            LabeledTextFieldPet(
                label = "Raza:",
                value = breed,
                onValueChange = { breed = it }
            )

            LabeledTextFieldPet(
                label = "Sexo:",
                value = sex,
                onValueChange = { sex = it }
            )

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PetCarePrimary,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(24.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    text = "Agregar ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun LabeledTextFieldPet(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Text(
        text = label,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 12.dp),
        singleLine = true
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PetRegisterScreenPreview() {
    PetRegisterScreen()
}
