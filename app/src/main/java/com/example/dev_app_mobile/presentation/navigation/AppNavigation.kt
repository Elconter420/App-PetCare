package com.example.dev_app_mobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.components.common.AppointmentConfirmationScreen
import com.example.dev_app_mobile.presentation.ui.auth.LoginScreen
import com.example.dev_app_mobile.presentation.ui.auth.ResetPasswordScreen
import com.example.dev_app_mobile.presentation.ui.auth.TermsConditionsScreen
import com.example.dev_app_mobile.presentation.ui.auth.UserRegisterScreen
import com.example.dev_app_mobile.presentation.ui.auth.VeterinarianRegisterScreen
import com.example.dev_app_mobile.presentation.ui.auth.WelcomeScreen
import com.example.dev_app_mobile.presentation.ui.owner.AboutScreen
import com.example.dev_app_mobile.presentation.ui.owner.ClinicalHistoryScreen
import com.example.dev_app_mobile.presentation.ui.owner.MedicalHistoryScreenWithPhoto
import com.example.dev_app_mobile.presentation.ui.owner.NotificationsScreen
import com.example.dev_app_mobile.presentation.ui.owner.OwnerMainScreen
import com.example.dev_app_mobile.presentation.ui.owner.OwnerMenuScreen
import com.example.dev_app_mobile.presentation.ui.owner.PetProfileScreen
import com.example.dev_app_mobile.presentation.ui.owner.PlansScreen
import com.example.dev_app_mobile.presentation.ui.owner.ScheduleAppointmentScreen
import com.example.dev_app_mobile.presentation.ui.owner.SuccessfulMessage
import com.example.dev_app_mobile.presentation.ui.owner.UserProfileScreen
import com.example.dev_app_mobile.presentation.ui.veterinarian.HelpScreen
import com.example.dev_app_mobile.presentation.ui.veterinarian.LanguageScreen
import com.example.dev_app_mobile.presentation.ui.veterinarian.PaymentHistoryScreen
import com.example.dev_app_mobile.presentation.ui.veterinarian.VeterinarianClinicalHistoryScreen
import com.example.dev_app_mobile.presentation.ui.veterinarian.VeterinarianMainScreen
import com.example.dev_app_mobile.presentation.ui.veterinarian.VeterinarianMedicalHistoryScreen
import com.example.dev_app_mobile.presentation.ui.veterinarian.VetMenuScreen
import com.example.dev_app_mobile.presentation.ui.veterinarian.VetPendingAppointmentsScreen
import com.example.dev_app_mobile.presentation.ui.veterinarian.VetPlansScreen
import com.example.dev_app_mobile.presentation.ui.veterinarian.VeterinarianProfileScreen

@Composable
fun AppNavigation(
    startDestination: String = Screen.Welcome.route
) {
    val navController = rememberNavController()

    // Estado para manejar el rol del usuario (simulado por ahora)
    var userRole by remember { mutableStateOf<UserRole?>(null) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // ========== AUTH FLOW ==========
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onLoginClick = { navController.navigate(Screen.Login.route) },
                onRegisterClick = { navController.navigate(Screen.UserRegister.route) }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onBackClick = { navController.popBackStack() },
                onLoginClick = {
                    userRole = UserRole.OWNER
                    navController.navigate(Screen.OwnerHome.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                },
                onForgotPasswordClick = { navController.navigate(Screen.ResetPassword.route) },
                onRegisterClick = { navController.navigate(Screen.UserRegister.route) }
            )
        }

        composable(Screen.UserRegister.route) {
            UserRegisterScreen(
                onRegisterClick = {
                    navController.navigate(Screen.SuccessfulMessage.createRoute(SuccessType.REGISTRATION.name)) {
                        popUpTo(Screen.Welcome.route) { inclusive = false }
                    }
                },
                onVetRegisterClick = { navController.navigate(Screen.VeterinarianRegister.route) },
                onTermsClick = { navController.navigate(Screen.TermsConditions.route) }
            )
        }

        composable(Screen.VeterinarianRegister.route) {
            VeterinarianRegisterScreen(
                onBackClick = { navController.popBackStack() },
                onRegisterClick = {
                    navController.navigate(Screen.SuccessfulMessage.createRoute(SuccessType.REGISTRATION.name))
                },
                onTermsClick = { navController.navigate(Screen.TermsConditions.route) }
            )
        }

        composable(Screen.ResetPassword.route) {
            ResetPasswordScreen(
                onBackClick = { navController.popBackStack() },
                onSendClick = { navController.popBackStack() }
            )
        }

        composable(Screen.TermsConditions.route) {
            TermsConditionsScreen(
                onCloseClick = { navController.popBackStack() },
                onAcceptClick = { navController.popBackStack() }
            )
        }

        // ========== OWNER FLOW ==========
        composable(Screen.OwnerHome.route) {
            OwnerMainScreen(
                navController = navController,
                userName = "Usuario"
            )
        }

        composable(Screen.OwnerMenu.route) {
            OwnerMenuScreen(
                onNavigate = { destination ->
                    when (destination) {
                        "about" -> navController.navigate(Screen.About.route)
                        "appointment" -> navController.navigate(Screen.ScheduleAppointment.route)
                        "daycare" -> navController.navigate("daycare")
                        "transport" -> navController.navigate("transport")
                        "notifications" -> navController.navigate(Screen.Notifications.route)
                        "language" -> navController.navigate(Screen.Language.route)
                        "resetPassword" -> navController.navigate(Screen.ResetPassword.route)
                        "logout" -> {
                            userRole = null
                            navController.navigate(Screen.Welcome.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                        "profile" -> navController.navigate(Screen.UserProfile.route)
                    }
                }
            )
        }

        composable(Screen.UserProfile.route) {
            UserProfileScreen(
                onBackClick = { navController.popBackStack() },
                onAddPetClick = { /* Se maneja internamente */ },
                onLogoutClick = {
                    userRole = null
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Screen.PetProfile.route,
            arguments = listOf(navArgument(Screen.PetProfile.PET_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val petId = backStackEntry.arguments?.getString(Screen.PetProfile.PET_ID) ?: ""
            PetProfileScreen(
                onBackClick = { navController.popBackStack() },
                onOpenHistoryClick = { navController.navigate(Screen.MedicalHistory.route) },
                onOpenAgendaClick = { /* Navegar a agenda */ },
                onEditPetClick = { /* Navegar a editar mascota */ },
                onDeletePetClick = { navController.popBackStack() }
            )
        }

        composable(Screen.ScheduleAppointment.route) {
            ScheduleAppointmentScreen(
                onBackClick = { navController.popBackStack() },
                onConfirmClick = {
                    navController.navigate(Screen.AppointmentConfirmation.route)
                }
            )
        }

        composable(Screen.AppointmentConfirmation.route) {
            AppointmentConfirmationScreen(
                onConfirm = {
                    navController.navigate(Screen.SuccessfulMessage.createRoute(SuccessType.APPOINTMENT.name)) {
                        popUpTo(Screen.OwnerHome.route) { inclusive = false }
                    }
                },
                onCancel = { navController.popBackStack() }
            )
        }

        composable(Screen.ClinicalHistory.route) {
            ClinicalHistoryScreen(
                navController = navController,
                petName = "Lyra",
                petImageResId = R.drawable.lyra
            )
        }

        composable(Screen.MedicalHistory.route) {
            MedicalHistoryScreenWithPhoto(
                navController = navController,
                petName = "Lyra",
                petImageResId = R.drawable.lyra
            )
        }

        composable(Screen.Plans.route) {
            PlansScreen(
                onBackClick = { navController.popBackStack() },
                onSelectFree = {
                    navController.navigate(Screen.SuccessfulMessage.createRoute(SuccessType.PAYMENT.name))
                },
                onSelectPremium = {
                    navController.navigate(Screen.SuccessfulMessage.createRoute(SuccessType.PAYMENT.name))
                }
            )
        }

        composable(Screen.About.route) {
            AboutScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Notifications.route) {
            NotificationsScreen(
                navController = navController
            )
        }

        // ========== VETERINARIAN FLOW ==========
        composable(Screen.VetHome.route) {
            VeterinarianMainScreen(
                navController = navController,
                userName = "Dra. Melissa"
            )
        }

        composable(Screen.VetMenu.route) {
            // Asumiendo que VetMenuScreen tiene onNavigate o necesita navController
            // Si no tiene, necesitamos crear una versión que acepte parámetros
            VetMenuScreen(
                onNavigate = { destination ->
                    when (destination) {
                        "profile" -> navController.navigate(Screen.VetProfile.route)
                        "appointments" -> navController.navigate(Screen.VetPendingAppointments.route)
                        "medical_history" -> navController.navigate(Screen.VetMedicalHistory.route)
                        "clinical_history" -> navController.navigate(Screen.VetClinicalHistory.route)
                        "payment_history" -> navController.navigate(Screen.PaymentHistory.route)
                        "plans" -> navController.navigate(Screen.VetPlans.route)
                        "help" -> navController.navigate(Screen.Help.route)
                        "language" -> navController.navigate(Screen.Language.route)
                        "logout" -> {
                            userRole = null
                            navController.navigate(Screen.Welcome.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }
                }
            )
        }

        // CORRECCIÓN: VeterinarianProfileScreen necesita navController
        composable(Screen.VetProfile.route) {
            VeterinarianProfileScreen(
                navController = navController,
                veterinarianName = "Dra. Melissa",
                email = "melissavet@gmail.com",
                phone = "+57 315 534 55"
            )
        }

        // CORRECCIÓN: VetPendingAppointmentsScreen necesita estos parámetros
        composable(Screen.VetPendingAppointments.route) {
            VetPendingAppointmentsScreen(
                vetName = "Dra. Melissa",
                patientName = "Paciente", // Esto debería venir de datos reales
                date = "25/11/2025", // Esto debería venir de datos reales
                time = "3:00 p.m.", // Esto debería venir de datos reales
                reason = "Consulta general", // Esto debería venir de datos reales
                onBackClick = { navController.popBackStack() },
                onAcceptClick = {
                    // Lógica para aceptar cita
                    // Después de aceptar, podrías navegar a otra pantalla o actualizar
                },
                onCancelClick = {
                    // Lógica para cancelar cita
                    navController.popBackStack()
                }
            )
        }

        // CORRECCIÓN: VeterinarianMedicalHistoryScreen necesita navController
        composable(Screen.VetMedicalHistory.route) {
            VeterinarianMedicalHistoryScreen(
                navController = navController,
                petName = "Mateo",
                petImageResId = R.drawable.mateo
            )
        }

        // CORRECCIÓN: VeterinarianClinicalHistoryScreen necesita navController
        composable(Screen.VetClinicalHistory.route) {
            VeterinarianClinicalHistoryScreen(
                navController = navController,
                petName = "Mateo",
                petImageResId = R.drawable.mateo
            )
        }

        // CORRECCIÓN: PaymentHistoryScreen necesita navController
        composable(Screen.PaymentHistory.route) {
            PaymentHistoryScreen(
                navController = navController
            )
        }

        // CORRECCIÓN: VetPlansScreen tiene estos parámetros
        composable(Screen.VetPlans.route) {
            VetPlansScreen(
                onBack = { navController.popBackStack() },
                onSelectFree = {
                    // Lógica para plan free
                    navController.navigate(Screen.SuccessfulMessage.createRoute(SuccessType.PAYMENT.name))
                },
                onSelectPremium = {
                    // Lógica para plan premium
                    navController.navigate(Screen.SuccessfulMessage.createRoute(SuccessType.PAYMENT.name))
                }
            )
        }

        // CORRECCIÓN: HelpScreen - asumiendo que necesita navController o onBackClick
        composable(Screen.Help.route) {
            HelpScreen(
                onBackClick = { navController.popBackStack() }
                // O si HelpScreen necesita navController:
                // navController = navController
            )
        }

        // CORRECCIÓN: LanguageScreen necesita ambos parámetros
        composable(Screen.Language.route) {
            LanguageScreen(
                navController = navController,
                onAcceptClick = {
                    // Lógica al aceptar idioma
                    navController.popBackStack()
                }
            )
        }

        // ========== COMMON SCREENS ==========
        composable(
            route = Screen.SuccessfulMessage.route,
            arguments = listOf(navArgument(Screen.SuccessfulMessage.MESSAGE_TYPE) { type = NavType.StringType })
        ) { backStackEntry ->
            val messageType = backStackEntry.arguments?.getString(Screen.SuccessfulMessage.MESSAGE_TYPE)

            // Crear un wrapper que convierta los parámetros
            Column {
                SuccessfulMessage(
                    title = when (messageType) {
                        "REGISTRATION" -> "¡Registro Exitoso!"
                        "APPOINTMENT" -> "¡Cita Agendada!"
                        "PET_REGISTRATION" -> "¡Mascota Registrada!"
                        "PAYMENT" -> "¡Pago Procesado!"
                        else -> "¿Está seguro de\neliminar a su\nmascota?"
                    },
                    confirmText = when (messageType) {
                        "REGISTRATION" -> "Continuar"
                        "APPOINTMENT" -> "Ver mis citas"
                        "PET_REGISTRATION" -> "Ver mis mascotas"
                        "PAYMENT" -> "Ver mis suscripciones"
                        else -> "Sí, estoy seguro/a"
                    },
                    cancelText = when (messageType) {
                        "REGISTRATION", "APPOINTMENT", "PET_REGISTRATION", "PAYMENT" -> "Volver"
                        else -> "No, no estoy seguro/a"
                    },
                    onConfirm = {
                        when (userRole) {
                            UserRole.OWNER -> navController.navigate(Screen.OwnerHome.route) {
                                popUpTo(0) { inclusive = true }
                            }
                            UserRole.VETERINARIAN -> navController.navigate(Screen.VetHome.route) {
                                popUpTo(0) { inclusive = true }
                            }
                            else -> navController.navigate(Screen.Welcome.route)
                        }
                    },
                    onCancel = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
fun Column(content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}

// Enum para roles de usuario
enum class UserRole {
    OWNER, VETERINARIAN
}