package com.finite.eassytask.ui.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finite.eassytask.data.repository.LoginRepository
import com.finite.eassytask.data.repository.ValidationResult
import com.finite.eassytask.databinding.FragmentForgotPasswordBinding
import com.finite.eassytask.databinding.FragmentLoginBinding

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val loginRepository = LoginRepository()

    // LiveData to hold the validation result of registration
    private val _numberValidationResult = MutableLiveData<ValidationResult>()
    val numberValidationResult: LiveData<ValidationResult> = _numberValidationResult

    // current number live data
    private val _currentNumber = MutableLiveData<String>("")
    val currentNumber: LiveData<String> = _currentNumber

    fun clearNumberValidationResult() {
        _numberValidationResult.value = ValidationResult(false, emptyList())
    }

    fun validatePhoneNumber(phoneNumber: String) {
        val validationResult = loginRepository.validateNumber(phoneNumber)
        _numberValidationResult.value = validationResult
    }

    fun clearErrorsForNumber(binding: FragmentForgotPasswordBinding, vararg fields: String) {
        fields.forEach {
            when (it) {
                "phoneNumber" -> {
                    binding.numberTextInput.error = null
                    binding.numberTextInput.isErrorEnabled = false
                }
            }
        }
    }

    fun setCurrentNumber(phoneNumber: String) {
        _currentNumber.value = phoneNumber
    }

    fun clearErrorsNumber(binding: FragmentForgotPasswordBinding) {
        binding.numberTextInput.error = null
        binding.numberTextInput.isErrorEnabled = false
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}