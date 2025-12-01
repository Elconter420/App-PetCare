package com.example.dev_app_mobile.presentation.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onVetRegisterClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "PetCare Logo",
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 12.dp)
        )

        //Titulo
        Text(
            text = "Donde las huellitas\nimportan, entra y\ndescubre PetCare",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Botón Iniciar Sesión
        ShadowButton(
            text = "Iniciar sesión",
            onClick = onLoginClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Registrarme
        ShadowButton(
            text = "Registrarme",
            onClick = onRegisterClick
        )

        Spacer(modifier = Modifier.height(22.dp))

        //
        Text(
            text = "¿Eres veterinario, cuidador o estilista?",
            fontSize = 14.sp,
            color = Color.Black.copy(alpha = 0.8f)
        )

        Text(
            text = "Regístrate aquí",
            fontSize = 14.sp,
            color = Color(0xFF1B73E8), // azul tipo hipervínculo
            modifier = Modifier
                .clickable { onVetRegisterClick() }
                .padding(top = 2.dp)
        )
    }
}

@Composable
fun ShadowButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PetCarePrimary,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(24.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 2.dp
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}
