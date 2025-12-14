package com.example.dev_app_mobile.presentation.navigation

import androidx.compose.foundation.layout.Column
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
    var userRole by remember { mutableStateOf<UserRole?>(null) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {


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
                onForgotPasswordClick = {
                    navController.navigate(Screen.ResetPassword.route)
                },
                onRegisterClick = {
                    navController.navigate(Screen.UserRegister.route)
                }
            )
        }

        composable(Screen.UserRegister.route) {
            UserRegisterScreen(
                onRegisterClick = {
                    navController.navigate(
                        Screen.SuccessfulMessage.createRoute(SuccessType.REGISTRATION.name)
                    )
                },
                onVetRegisterClick = {
                    navController.navigate(Screen.VeterinarianRegister.route)
                },
                onTermsClick = {
                    navController.navigate(Screen.TermsConditions.route)
                }
            )
        }

        composable(Screen.VeterinarianRegister.route) {
            VeterinarianRegisterScreen(
                onBackClick = { navController.popBackStack() },
                onRegisterClick = {
                    userRole = UserRole.VETERINARIAN
                    navController.navigate(
                        Screen.SuccessfulMessage.createRoute(SuccessType.REGISTRATION.name)
                    )
                },
                onTermsClick = {
                    navController.navigate(Screen.TermsConditions.route)
                }
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


        composable(Screen.OwnerHome.route) {
            OwnerMainScreen(
                navController = navController,
                userName = "Usuario"
            )
        }

        composable(Screen.OwnerMenu.route) {
            OwnerMenuScreen(
                onNavigate = { action ->
                    when (action) {
                        "ABOUT" -> navController.navigate(Screen.About.route)
                        "APPOINTMENT" -> navController.navigate(Screen.ScheduleAppointment.route)
                        "NOTIFICATIONS" -> navController.navigate(Screen.Notifications.route)
                        "PROFILE" -> navController.navigate(Screen.UserProfile.route)
                        "PLANS" -> navController.navigate(Screen.Plans.route)
                        "LOGOUT" -> {
                            userRole = null
                            navController.navigate(Screen.Welcome.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }
                }
            )
        }

        composable(Screen.UserProfile.route) {
            UserProfileScreen(
                onBackClick = { navController.popBackStack() },
                onAddPetClick = {
                    navController.navigate(Screen.PetProfile.createRoute("1"))
                },
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
        ) {
            PetProfileScreen(
                onBackClick = { navController.popBackStack() },
                onOpenHistoryClick = {
                    navController.navigate(Screen.MedicalHistory.route)
                },
                onOpenAgendaClick = {
                    navController.navigate(Screen.ScheduleAppointment.route)
                },
                onEditPetClick = {},
                onDeletePetClick = {
                    navController.popBackStack()
                }
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
                    navController.navigate(
                        Screen.SuccessfulMessage.createRoute(SuccessType.APPOINTMENT.name)
                    )
                },
                onCancel = { navController.popBackStack() }
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
                    navController.navigate(
                        Screen.SuccessfulMessage.createRoute(SuccessType.PAYMENT.name)
                    )
                },
                onSelectPremium = {
                    navController.navigate(
                        Screen.SuccessfulMessage.createRoute(SuccessType.PAYMENT.name)
                    )
                }
            )
        }

        /*veterinario*/
        composable(Screen.VetHome.route) {
            VeterinarianMainScreen(
                navController = navController,
                userName = "Dra. Melissa"
            )
        }

        composable(Screen.VetMenu.route) {
            VetMenuScreen(
                onNavigate = { action ->
                    when (action) {
                        "PROFILE" -> navController.navigate(Screen.VetProfile.route)
                        "APPOINTMENTS" -> navController.navigate(Screen.VetPendingAppointments.route)
                        "PLANS" -> navController.navigate(Screen.VetPlans.route)
                        "HELP" -> navController.navigate(Screen.Help.route)
                        "LANGUAGE" -> navController.navigate(Screen.Language.route)
                        "LOGOUT" -> {
                            userRole = null
                            navController.navigate(Screen.Welcome.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }
                }
            )
        }

        composable(Screen.VetProfile.route) {
            VeterinarianProfileScreen(
                navController = navController,
                veterinarianName = "Dra. Melissa",
                email = "melissavet@gmail.com",
                phone = "+57 315 534 55"
            )
        }

        composable(Screen.VetPendingAppointments.route) {
            VetPendingAppointmentsScreen(
                vetName = "Dra. Melissa",
                patientName = "Paciente",
                date = "25/11/2025",
                time = "3:00 p.m.",
                reason = "Consulta general",
                onBackClick = { navController.popBackStack() },
                onAcceptClick = {},
                onCancelClick = { navController.popBackStack() }
            )
        }

        composable(Screen.VetPlans.route) {
            VetPlansScreen(
                onBack = { navController.popBackStack() },
                onSelectFree = {
                    navController.navigate(
                        Screen.SuccessfulMessage.createRoute(SuccessType.PAYMENT.name)
                    )
                },
                onSelectPremium = {
                    navController.navigate(
                        Screen.SuccessfulMessage.createRoute(SuccessType.PAYMENT.name)
                    )
                }
            )
        }

        composable(Screen.Help.route) {
            HelpScreen(onBackClick = { navController.popBackStack() })
        }

        composable(Screen.Language.route) {
            LanguageScreen(
                navController = navController,
                onAcceptClick = { navController.popBackStack() }
            )
        }


        composable(
            route = Screen.SuccessfulMessage.route,
            arguments = listOf(navArgument(Screen.SuccessfulMessage.MESSAGE_TYPE) {
                type = NavType.StringType
            })
        ) {
            SuccessfulMessage(
                onConfirm = {
                    when (userRole) {
                        UserRole.OWNER -> navController.navigate(Screen.OwnerHome.route)
                        UserRole.VETERINARIAN -> navController.navigate(Screen.VetHome.route)
                        else -> navController.navigate(Screen.Welcome.route)
                    }
                },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}
