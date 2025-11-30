package com.example.dev_app_mobile.presentation.ui.veterinarian

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import androidx.compose.foundation.shape.RoundedCornerShape


@Composable
fun LanguageScreen(
    navController: NavController,
    onAcceptClick: () -> Unit = {}
) {
    // Idiomas disponibles
    val languages = listOf(
        "es" to "Español 🇨🇴",
        "en" to "Inglés 🇺🇸",
        "pt" to "Portugués 🇧🇷",
        "fr" to "Francés 🇫🇷"
    )


    var selectedLanguage by remember { mutableStateOf("es") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {

        // Header backcito
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
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

        // Título
        Text(
            text = "Idioma 🌐",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp)
        )

        // Barra separadora #1
        SectionHeader(text = "Selecciona el idioma")

        Spacer(modifier = Modifier.height(12.dp))

        // Lista de idiomas con RadioButton
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            languages.forEach { (code, label) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedLanguage == code,
                            onClick = { selectedLanguage = code }
                        )
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedLanguage == code,
                        onClick = { selectedLanguage = code }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = label,
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Barra Separadora
        SectionHeader(text = "Ten en cuenta:")

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de aviso
        Text(
            text = "📋 Los cambios de idioma afectarán todo el contenido de la aplicación.",
            fontSize = 15.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // Boton
        Button(
            onClick = onAcceptClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PetCarePrimary,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text(
                text = "Aceptar",
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(PetCarePrimary),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LanguageScreenPreview() {
    val navController = rememberNavController()
    LanguageScreen(navController = navController)
}
