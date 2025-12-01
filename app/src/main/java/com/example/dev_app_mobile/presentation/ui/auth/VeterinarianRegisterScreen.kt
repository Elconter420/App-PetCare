package com.example.dev_app_mobile.presentation.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeterinarianRegisterScreen(
    onRegisterClick: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    // ------- Estados UI (solo visual) -------
    var roleExpanded by remember { mutableStateOf(false) }
    val roles = listOf("Veterinario", "Cuidador", "Transportador", "Estilista")
    var selectedRole by remember { mutableStateOf("") }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var proCard by remember { mutableStateOf("") }

    var termsChecked by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
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
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "¡Únete a PetCare!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "Haz crecer tu negocio ofreciendo\n" +
                        "tus servicios a muchas familias.",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )

            // ---- Rol (dropdown) ----
            Text(
                text = "Rol",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            ExposedDropdownMenuBox(
                expanded = roleExpanded,
                onExpandedChange = { roleExpanded = !roleExpanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 12.dp)
            ) {
                OutlinedTextField(
                    value = selectedRole,
                    onValueChange = { },
                    readOnly = true,
                    placeholder = { Text("Selecciona tu rol") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = roleExpanded
                        )
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .height(48.dp)
                )

                ExposedDropdownMenu(
                    expanded = roleExpanded,
                    onDismissRequest = { roleExpanded = false }
                ) {
                    roles.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedRole = option
                                roleExpanded = false
                            }
                        )
                    }
                }
            }

            // ---- Campos de texto ----
            LabeledTextField(
                label = "Nombre:",
                value = name,
                onValueChange = { name = it }
            )
            LabeledTextField(
                label = "Correo:",
                value = email,
                onValueChange = { email = it }
            )
            LabeledTextField(
                label = "Teléfono:",
                value = phone,
                onValueChange = { phone = it }
            )
            LabeledTextField(
                label = "Contraseña:",
                value = password,
                onValueChange = { password = it },
                isPassword = true
            )
            LabeledTextField(
                label = "Confirmar contraseña:",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                isPassword = true
            )
            LabeledTextField(
                label = "N° Tarjeta profesional:",
                value = proCard,
                onValueChange = { proCard = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ---- Checkbox términos ----
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = termsChecked,
                    onCheckedChange = { termsChecked = it }
                )

                Column(
                    modifier = Modifier.clickable { onTermsClick() }
                ) {
                    Text(
                        text = "He leído y acepto los términos y condiciones",
                        fontSize = 12.sp
                    )
                    Text(
                        text = "de PetCare",
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ---- Botón Registrarme ----
            Button(
                onClick = onRegisterClick,
                enabled = termsChecked,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (termsChecked) PetCarePrimary else Color(0xFFB0BEC5),
                    contentColor = Color.Black,
                    disabledContainerColor = Color(0xFFCFD8DC),
                    disabledContentColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(24.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = if (termsChecked) 6.dp else 0.dp
                )
            ) {
                Text(
                    text = "Registrarme",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
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
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VeterinarianRegisterPreview() {
    VeterinarianRegisterScreen()
}