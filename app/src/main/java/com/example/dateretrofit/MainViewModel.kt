package com.example.dateretrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dateretrofit.date.model.Autor
import com.example.dateretrofit.date.repository.ApiAutorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class MainViewModel(
    private val apiAutorRepository: ApiAutorRepository
) : ViewModel() {

    private val _responseAutors = MutableStateFlow(listOf<Autor>())
    val responseAutors = _responseAutors.asStateFlow()

    init {
        getAutor()
    }

    private fun getAutor(){
        viewModelScope.launch {
            val response = apiAutorRepository.getAutor()
            _responseAutors.value = response
        }
    }
}