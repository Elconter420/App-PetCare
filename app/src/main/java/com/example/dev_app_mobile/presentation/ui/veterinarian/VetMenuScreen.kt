package com.example.dev_app_mobile.presentation.ui.veterinarian

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VetMenuScreen(
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Menú Veterinario",
            style = MaterialTheme.typography.headlineMedium
        )

        MenuItem(
            icon = Icons.Default.Person,
            text = "Mi perfil",
            onClick = { onNavigate("PROFILE") }
        )

        MenuItem(
            icon = Icons.Default.Schedule,
            text = "Citas pendientes",
            onClick = { onNavigate("APPOINTMENTS") }
        )

        MenuItem(
            icon = Icons.Default.Star,
            text = "Planes",
            onClick = { onNavigate("PLANS") }
        )

        MenuItem(
            icon = Icons.Default.Help,
            text = "Ayuda",
            onClick = { onNavigate("HELP") }
        )

        MenuItem(
            icon = Icons.Default.Language,
            text = "Idioma",
            onClick = { onNavigate("LANGUAGE") }
        )

        Spacer(modifier = Modifier.weight(1f))

        MenuItem(
            icon = Icons.Default.ExitToApp,
            text = "Cerrar sesión",
            onClick = { onNavigate("LOGOUT") }
        )
    }
}

@Composable
private fun MenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
