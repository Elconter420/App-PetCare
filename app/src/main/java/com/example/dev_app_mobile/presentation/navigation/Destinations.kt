package com.example.dev_app_mobile.presentation.navigation

sealed class Screen(val route: String) {


    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object UserRegister : Screen("user_register")
    object VeterinarianRegister : Screen("vet_register")
    object TermsConditions : Screen("terms")
    object ResetPassword : Screen("reset_password")


    object OwnerHome : Screen("owner_home")
    object OwnerMenu : Screen("owner_menu")
    object UserProfile : Screen("user_profile")

    object PetProfile : Screen("pet_profile/{petId}") {
        fun createRoute(petId: String) = "pet_profile/$petId"
        const val PET_ID = "petId"
    }

    object ScheduleAppointment : Screen("schedule_appointment")
    object AppointmentConfirmation : Screen("appointment_confirmation")
    object ClinicalHistory : Screen("clinical_history")
    object MedicalHistory : Screen("medical_history")
    object Plans : Screen("plans")
    object About : Screen("about")
    object Notifications : Screen("notifications")


    object VetHome : Screen("vet_home")
    object VetMenu : Screen("vet_menu")
    object VetProfile : Screen("vet_profile")
    object VetPendingAppointments : Screen("vet_pending_appointments")
    object VetClinicalHistory : Screen("vet_clinical_history")
    object VetMedicalHistory : Screen("vet_medical_history")
    object VetPlans : Screen("vet_plans")
    object PaymentHistory : Screen("payment_history")
    object Help : Screen("help")
    object Language : Screen("language")


    object SuccessfulMessage : Screen("success_message/{type}") {
        fun createRoute(type: String) = "success_message/$type"
        const val MESSAGE_TYPE = "type"
    }
}

enum class SuccessType {
    REGISTRATION, APPOINTMENT, PET_REGISTRATION, PAYMENT
}

enum class UserRole {
    OWNER, VETERINARIAN
}
