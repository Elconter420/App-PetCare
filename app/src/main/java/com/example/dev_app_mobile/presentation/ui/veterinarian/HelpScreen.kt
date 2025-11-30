package com.example.dev_app_mobile.presentation.ui.veterinarian

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary

@Composable
fun HelpScreen(
    onBackClick: () -> Unit = {},
    onCategoryClick: (String) -> Unit = {},
    onContactClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        // Backcito
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver"
                )
            }
        }

        // Titulo
        Text(
            text = "Centro de\nayuda  ❓",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp)
        )

        // Subtitle
        Text(
            text = "Encuentra respuestas rápidas o " +
                    "contáctanos si necesitas asistencia personalizada.",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // Categorías
        HelpCategoryCard(
            text = "Pagos y planes",
            emoji = "🧾",
            onClick = { onCategoryClick("pagos") }
        )
        HelpCategoryCard(
            text = "Citas y servicios",
            emoji = "📅",
            onClick = { onCategoryClick("citas") }
        )
        HelpCategoryCard(
            text = "Perfil y mascotas",
            emoji = "🐾",
            onClick = { onCategoryClick("perfil") }
        )
        HelpCategoryCard(
            text = "Historial y reportes",
            emoji = "📃",
            onClick = { onCategoryClick("historial") }
        )
        HelpCategoryCard(
            text = "Cuenta y configuración",
            emoji = "⚙️",
            onClick = { onCategoryClick("cuenta") }
        )
        HelpCategoryCard(
            text = "Problemas técnicos",
            emoji = "🛠️",
            onClick = { onCategoryClick("tecnico") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Separador
        HelpSectionHeader(title = "Preguntas frecuentes:")

        FaqItem(question = "¿Cómo cambio mi plan?")
        FaqItem(question = "¿Qué pasa si olvido mi contraseña?")
        FaqItem(question = "¿Puedo registrar más de una mascota?")

        Spacer(modifier = Modifier.height(18.dp))

        //
        Text(
            text = "¿Aún necesitas ayuda?",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )

        Text(
            text = "Contactar soporte aquí",
            fontSize = 14.sp,
            color = PetCarePrimary,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clickable { onContactClick() }
        )

        // Perrito con audífonos
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.ayuda),
                contentDescription = "Perro centro de ayuda",
                modifier = Modifier
                    .height(180.dp)
                    .wrapContentWidth()
            )
        }
    }
}

@Composable
private fun HelpCategoryCard(
    text: String,
    emoji: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = emoji,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
private fun HelpSectionHeader(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(32.dp)
    ) {
        Surface(
            color = PetCarePrimary,
            modifier = Modifier
                .fillMaxSize()
        ) {}
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 12.dp)
        )
    }
}

@Composable
private fun FaqItem(question: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "▸", fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
            Text(text = question, fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HelpScreenPreview() {
    HelpScreen()
}
