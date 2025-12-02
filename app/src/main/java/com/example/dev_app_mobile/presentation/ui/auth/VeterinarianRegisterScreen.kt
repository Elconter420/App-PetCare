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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
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
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(6.dp))

            Column(
                modifier = Modifier
                    .width(300.dp)   // Igual al ancho visual del ejemplo
                    .padding(horizontal = 12.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "¡Únete a PetCare!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = "Haz crecer tu negocio ofreciendo\ntus servicios a muchas familias.",
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )

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
                        onValueChange = {},
                        readOnly = true,
                        placeholder = { Text("Selecciona tu rol") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = roleExpanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .height(48.dp),
                        singleLine = true
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

                LabeledTextField("Nombre:", name, { name = it })
                LabeledTextField("Correo:", email, { email = it })
                LabeledTextField("Teléfono:", phone, { phone = it })
                LabeledTextField("Contraseña:", password, { password = it }, true)
                LabeledTextField("Confirmar contraseña:", confirmPassword, { confirmPassword = it }, true)
                LabeledTextField("N° Tarjeta profesional:", proCard, { proCard = it })

                Spacer(modifier = Modifier.height(6.dp))

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
                            fontSize = 11.sp
                        )
                        Text(
                            text = "de PetCare",
                            fontSize = 11.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onRegisterClick,
                    enabled = termsChecked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (termsChecked) PetCarePrimary else Color(0xFFB0BEC5),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(22.dp)
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