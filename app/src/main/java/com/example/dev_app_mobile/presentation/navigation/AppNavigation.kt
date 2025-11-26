package com.example.dev_app_mobile.presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppNavigation() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🐾 PetCare App",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text("App funcionando correctamente!")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Estructura MVVM lista")

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Acción simple */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Botón de prueba")
        }
    }
}