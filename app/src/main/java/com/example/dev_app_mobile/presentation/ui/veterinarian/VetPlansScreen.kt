package com.example.dev_app_mobile.presentation.ui.veterinarian

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.components.common.PlanCard
import com.example.dev_app_mobile.presentation.ui.theme.DevappmobileTheme
import com.example.dev_app_mobile.presentation.ui.theme.PetCareDark
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

@Composable
fun VetPlansScreen(
    onBack: () -> Unit = {},
    onSelectFree: () -> Unit = {},
    onSelectPremium: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Botón regresar
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Título
        Text(
            text = "Elige tu plan como profesional",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))
        @Composable
        fun VetPlansHeaderImage() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                // Óvalo verde
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .width(230.dp)        //  ancho
                        .height(140.dp)       //  bajito
                        .clip(RoundedCornerShape(100.dp))
                        .background(PetCareDark),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Haz crecer tu práctica veterinaria con PetCare: " +
                                "más visibilidad, más pacientes y todas las " +
                                "herramientas para brindar un mejor cuidado.",
                        color = PetCareWhite,
                        fontSize = 13.sp,
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 12.dp)
                    )
                }

                // Perro
                Image(
                    painter = painterResource(id = R.drawable.perro_dos),
                    contentDescription = "Perro plan veterinario",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(190.dp)          // más grande
                        .offset(x = (-8).dp),  // se mete un poquito en el óvalo
                    contentScale = ContentScale.Crop
                )
            }


        }
        VetPlansHeaderImage()


        Spacer(modifier = Modifier.height(24.dp))


        //Card Free
        PlanCard(
            title = "Free",
            price = "Precio: Gratis 🎁",
            features = listOf(
                "Hasta 2 pacientes atendidos al mes.",
                "Perfil básico visible en la app.",
                "Puede recibir solicitudes de citas (máximo 2 confirmadas/mes).",
                "No aparece en destacados ni búsquedas preferenciales."
            ),
            buttonText = "Seguir siendo Free",
            isPrimary = false,
            onClick = onSelectFree,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        //Card Premium
        PlanCard(
            title = "Premium",
            price = "Precio: \$XX.XXX/mes 👑",
            features = listOf(
                "Pacientes ilimitados.",
                "Citas ilimitadas y prioridad en el agendamiento.",
                "Acceso completo al historial médico.",
                "Perfil profesional destacado en búsquedas.",
                "Herramientas avanzadas para gestión médica.",
                "Posibilidad de ofrecer servicios adicionales (peluquería, guardería, transporte).",
                "Soporte prioritario."
            ),
            buttonText = "Hazte PREMIUM",
            isPrimary = true,
            onClick = onSelectPremium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewVetPlansScreen() {
    DevappmobileTheme {
        VetPlansScreen()
    }
}
