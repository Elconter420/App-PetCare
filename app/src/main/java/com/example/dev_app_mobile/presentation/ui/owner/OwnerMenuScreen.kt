package com.example.dev_app_mobile.presentation.ui.owner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareSecondary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareDark

@Composable
fun OwnerMenuScreen(
    onNavigate: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
    ) {

        //Encabezado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(PetCarePrimary)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "menu",
                tint = PetCareDark,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = "PETCARE",
                color = PetCareDark,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Perfil",
                tint = PetCareDark,
                modifier = Modifier.size(32.dp)
            )
        }

        // Opciones principales
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
        ) {
            MenuItem("Mis mascotas") { onNavigate("pets") }
            MenuItem("Sobre nosotros") { onNavigate("about") }
            MenuItem("Solicitar cita con veterinario") { onNavigate("appointment") }
            MenuItem("Guardería") { onNavigate("daycare") }
            MenuItem("Transporte") { onNavigate("transport") }

            Spacer(modifier = Modifier.height(25.dp))

            MenuItem("Notificaciones") { onNavigate("notifications") }
            MenuItem("Idioma") { onNavigate("language") }
            MenuItem("Cambiar contraseña") { onNavigate("resetPassword") }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Cerrar sesión",
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .clickable { onNavigate("logout") }
            )
        }
    }
}

@Composable
fun MenuItem(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp)
            .clickable { onClick() }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OwnerMenuScreenPreview() {
    OwnerMenuScreen()
}
