package com.example.dev_app_mobile.presentation.components.dialogs
//Dialogo qiue sale cuando se da en cancelar una cita por parte

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun WarningDialog(
    onSendClick: (String) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var reasonText by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .padding(horizontal = 24.dp, vertical = 26.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Título
                Text(
                    text = "¿Está seguro de\nrechazar la\ncita?",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Etiqueta
                Text(
                    text = "Motivo:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 4.dp)
                )

                // Cuadro de texto para escribir el motivo
                OutlinedTextField(
                    value = reasonText,
                    onValueChange = { reasonText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
                    placeholder = { Text("Escribe el motivo aquí") },
                    maxLines = 6
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Botón
                Button(
                    onClick = {
                        onSendClick(reasonText)
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD38A8A), // rosadito tipo alerta
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(24.dp),
                    elevation = ButtonDefaults.buttonElevation(6.dp)
                ) {
                    Text(
                        text = "Enviar y cancelar",
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WarningDialogPreview() {
    WarningDialog()
}
