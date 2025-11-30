package com.example.dev_app_mobile.presentation.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.presentation.ui.owner.AboutScreen

@Composable
fun FaqAnswerDialog(
    title: String,
    emoji: String,
    body: String,
    onDismiss: () -> Unit
) {
    // Fondo oscuro semi-transparente
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA000000)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            shadowElevation = 4.dp,
            modifier = Modifier.padding(24.dp)
        ) {
            Box {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    // Título con emoji
                    Text(
                        text = "$emoji $title",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.size(12.dp))

                    // Texto de la respuesta
                    Text(
                        text = body,
                        fontSize = 14.sp,
                        lineHeight = 18.sp
                    )
                }

                // Botón de cerrar
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(28.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                        .clickable { onDismiss() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Cerrar"
                    )
                }
            }
        }
    }
}
@Composable
@androidx.compose.ui.tooling.preview.Preview(showBackground = true, showSystemUi = true)
fun FaqAnswerDialogPreview() {
    FaqAnswerDialog(
        title = "¿Cómo puedo cambiar mi plan de Free a Premium?",
        emoji = "💳",
        body = """
Puedes cambiar tu plan en cualquier momento desde la sección “Planes y membresías”.
Selecciona el plan Premium 💎, elige tu método de pago y confirma la suscripción.
Tu nueva membresía se activa al instante y tus beneficios estarán disponibles enseguida.

💡 Recuerda: puedes cancelar la renovación automática cuando quieras.
        """.trimIndent(),
        onDismiss = {}
    )
}
