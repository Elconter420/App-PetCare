package com.example.dev_app_mobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dev_app_mobile.domain.model.Pet
import com.example.dev_app_mobile.domain.usecase.pets.GetPetsUseCase
import com.example.dev_app_mobile.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase
) : ViewModel() {

    private val _petsState = MutableStateFlow<Result<List<Pet>>?>(null)
    val petsState: StateFlow<Result<List<Pet>>?> = _petsState.asStateFlow()

    private val _selectedPet = MutableStateFlow<Pet?>(null)
    val selectedPet: StateFlow<Pet?> = _selectedPet.asStateFlow()

    fun getPets(ownerId: String) {
        viewModelScope.launch {
            _petsState.value = Result.Loading
            _petsState.value = getPetsUseCase(ownerId)
        }
    }

    fun selectPet(pet: Pet) {
        _selectedPet.value = pet
    }

    fun clearPetsState() {
        _petsState.value = null
    }
}