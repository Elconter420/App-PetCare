package com.example.dev_app_mobile.presentation.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary

@Composable
fun TermsConditionsScreen(
    onCloseClick: () -> Unit = {},
    onAcceptClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {

        // Header: X en la esquina
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            IconButton(
                onClick = onCloseClick,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar"
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Términos y\ncondiciones 📜",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "En PetCare valoramos tu confianza. " +
                        "A continuación, te presentamos nuestras políticas de uso " +
                        "y protección de datos para garantizar tu seguridad y la de tus mascotas.",
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TermsSection(
                title = "1. Uso de la aplicación",
                lines = listOf(
                    "Los usuarios se comprometen a utilizar la plataforma de manera responsable.",
                    "PetCare ofrece servicios de conexión entre dueños y profesionales del cuidado animal."
                )
            )

            TermsSection(
                title = "2. Protección de datos personales",
                lines = listOf(
                    "Tu información es confidencial y solo será usada para ofrecer los servicios de la app.",
                    "No compartimos datos con terceros sin tu consentimiento."
                )
            )

            TermsSection(
                title = "3. Pagos y suscripciones",
                lines = listOf(
                    "Los servicios Premium implican un pago mensual o anual según la elección del usuario.",
                    "Los pagos se procesan mediante plataformas seguras."
                )
            )

            TermsSection(
                title = "4. Cancelación y reprogramación de citas",
                lines = listOf(
                    "Los usuarios pueden cancelar o reprogramar citas según las políticas de cada profesional.",
                    "Algunas citas pueden requerir aviso previo."
                )
            )

            TermsSection(
                title = "5. Responsabilidad de los profesionales",
                lines = listOf(
                    "Los veterinarios y cuidadores registrados son responsables de la atención brindada.",
                    "PetCare actúa como intermediario tecnológico, no como prestador directo del servicio."
                )
            )

            TermsSection(
                title = "6. Contacto y soporte",
                lines = listOf(
                    "Si tienes dudas, puedes escribirnos a: soporte@petcare.com."
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = onAcceptClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PetCarePrimary,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text(text = "Aceptar", fontSize = 15.sp)
        }
    }
}

@Composable
private fun TermsSection(
    title: String,
    lines: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(2.dp))
        lines.forEach { line ->
            Text(
                text = "• $line",
                fontSize = 13.sp,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TermsConditionsScreenPreview() {
    TermsConditionsScreen()
}
