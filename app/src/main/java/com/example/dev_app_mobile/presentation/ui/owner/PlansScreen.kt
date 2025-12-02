package com.example.dev_app_mobile.presentation.ui.owner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.components.common.PlanCard
import com.example.dev_app_mobile.presentation.ui.theme.PetCareDark
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

@Composable
fun PlansScreen(
    onBackClick: () -> Unit = {},
    onSelectFree: () -> Unit = {},
    onSelectPremium: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
            }

            Text(
                text = "Elige el plan ideal\npara ti y tus mascotas",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )

            Image(
                painter = painterResource(id = R.drawable.perro_plan),
                contentDescription = "Perrito feliz",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 20.dp, y = 140.dp)
            )
        }

        Box(
            modifier = Modifier
                .offset(x = (-20).dp)
                .width(230.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(PetCareDark)
        ) {
            Text(
                text = "PetCare se adapta a tus necesidades,\n" +
                        "conoce las diferencias entre Free y Premium",
                textAlign = TextAlign.Center,
                fontSize = 13.sp,
                color = PetCareWhite,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        PlanCard(
            title = "Free",
            price = "Precio: Gratis 🎁",
            features = listOf(
                "1 mascota por usuario 🐾.",
                "2 servicios por tipo al mes (ej: 2 peluquerías, 2 guarderías, etc.).",
                "Acceso básico a historial médico.",
                "Soporte general."
            ),
            buttonText = "Seguir siendo Free",
            modifier = Modifier.fillMaxWidth(),
            isPrimary = false,
            onClick = onSelectFree
        )

        Spacer(modifier = Modifier.height(20.dp))

        PlanCard(
            title = "Premium",
            price = "Precio: \$XX.XXX/mes 👑",
            features = listOf(
                "Mascotas ilimitadas 🐶🐱🐭.",
                "Servicios ilimitados por mes.",
                "Acceso completo al historial médico.",
                "Recordatorios automáticos de citas y vacunas.",
                "Soporte prioritario."
            ),
            buttonText = "Hazte PREMIUM",
            modifier = Modifier.fillMaxWidth(),
            isPrimary = true,
            onClick = onSelectPremium
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlansScreenPreview() {
    PlansScreen()
}