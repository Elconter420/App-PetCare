package com.example.dev_app_mobile.presentation.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun PaymentReceiptDialog(
    isVisible: Boolean,
    onDownloadPdf: () -> Unit,
    onClose: () -> Unit
) {
    if (!isVisible) return

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .background(Color(0x66000000))
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    // --- Bloque 1: Detalles del pago ---
                    ReceiptSectionCard(
                        title = "📅 Detalles del pago",
                        content = """
                            Fecha del pago: 25/09/2025
                            Hora: 10:32 a.m.
                            ID de transacción: #PC-8492031
                        """.trimIndent()
                    )

                    // --- Bloque 2: Plan adquirido ---
                    ReceiptSectionCard(
                        title = "💎 Plan adquirido",
                        content = """
                            Plan: Premium Mensual
                            Periodo: Septiembre 2025
                            Duración: 30 días
                        """.trimIndent()
                    )

                    // --- Bloque 3: Monto ---
                    ReceiptSectionCard(
                        title = "💰 Monto",
                        content = """
                            Monto total: $19.900 COP
                            Método de pago: Tarjeta Visa •••• 5432
                            Estado: ✅ Pagado exitosamente
                        """.trimIndent()
                    )

                    // Nota pequeña
                    Text(
                        text = "Factura generada automáticamente por PetCare.\nPuedes descargar este recibo para tus registros.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Botón Descargar PDF
                    Button(
                        onClick = onDownloadPdf,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4579B5),
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.buttonElevation(0.dp)
                    ) {
                        Text(
                            text = "Descargar PDF",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Botón Cerrar recibo
                    Button(
                        onClick = onClose,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE3A1A1),
                            contentColor = Color.Black
                        ),
                        elevation = ButtonDefaults.buttonElevation(0.dp)
                    ) {
                        Text(
                            text = "Cerrar recibo",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ReceiptSectionCard(
    title: String,
    content: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFDFDFD)
        ),
        elevation = CardDefaults.cardElevation(0.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = content,
                fontSize = 14.sp,
                color = Color.DarkGray,
                lineHeight = 18.sp
            )
        }
    }
}
