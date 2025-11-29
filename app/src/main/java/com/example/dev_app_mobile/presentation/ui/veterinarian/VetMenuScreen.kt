package com.example.dev_app_mobile.presentation.ui.veterinarian

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite
import com.example.dev_app_mobile.presentation.ui.theme.DevappmobileTheme

@Composable
fun VetMenuScreen(
    onNavigate: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)   //alt fija
                    .background(PetCarePrimary)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = { /* abrir/cerrar drawer luego */ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú",
                        tint = Color.Black
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.nombre_logo),
                    contentDescription = "PetCare Logo",
                    modifier = Modifier
                        .height(400.dp) // Ajustar despues se ve muy peque
                )
            }


        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PetCareWhite)
                .padding(innerPadding)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.78f) // igual vibe que el menú de dueño
                    .align(Alignment.CenterStart),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE6E6E6) // gris del menú
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 32.dp),
                    verticalArrangement = Arrangement.Top
                ) {

                    // BLOQUE 1
                    VetMenuItem("Mis pacientes") {
                        onNavigate("patients")
                    }
                    VetMenuItem("Citas agendadas") {
                        onNavigate("appointments")
                    }

                    VetMenuItem("Planes") {
                        onNavigate("plans")
                    }

                    Spacer(modifier = Modifier.height(162.dp))

                    // BLOQUE 2
                    VetMenuItem("Sobre nosotros") {
                        onNavigate("AbourtUs")
                    }
                    VetMenuItem("Notificaciones") {
                        onNavigate("notifications")
                    }
                    VetMenuItem("Idioma") {
                        onNavigate("language")
                    }
                    VetMenuItem("Cambiar contraseña") {
                        onNavigate("change_password")
                    }

                    Spacer(modifier = Modifier.height(100.dp))

                    // Logout en rojo
                    Text(
                        text = "Cerrar sesión",
                        color = Color.Red,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .clickable { onNavigate("logout") }
                    )
                }
            }
        }
    }
}

@Composable
private fun VetMenuItem(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp)
            .clickable { onClick() }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewVetMenuScreen() {
    DevappmobileTheme {
        VetMenuScreen()
    }
}
