package com.example.dev_app_mobile.presentation.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegisterScreen(
    onRegisterClick: () -> Unit = {},
    onVetRegisterClick: () -> Unit = {},
    onTermsClick: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var termsChecked by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.Start
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        // Título
        Text(
            text = "¡Únete a PetCare!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Subtítulo
        Text(
            text = "Regístrate y disfruta de servicios\n" +
                    "diseñados para tu mascota.",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        // Campos de formulario
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

        Spacer(modifier = Modifier.height(8.dp))

        // Texto de link a registro de vet
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¿Eres veterinario, cuidador o estilista?",
                fontSize = 13.sp
            )
            Text(
                text = "Regístrate aquí",
                fontSize = 13.sp,
                color = Color(0xFF1E88E5),
                modifier = Modifier.clickable { onVetRegisterClick() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Checkbox de términos
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = termsChecked,
                onCheckedChange = {
                    termsChecked = it
                    if (it) onTermsClick()
                }
            )

            Column(
                modifier = Modifier
                    .clickable {
                        // Abrir pantalla de términos al tocar el texto
                        onTermsClick()
                    }
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

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Registrarme
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

        Spacer(modifier = Modifier.height(32.dp))
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
fun UserRegisterScreenPreview() {
    UserRegisterScreen()
}
