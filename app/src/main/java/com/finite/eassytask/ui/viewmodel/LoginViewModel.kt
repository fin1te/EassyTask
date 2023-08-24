package com.finite.eassytask.ui.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.finite.eassytask.data.repository.LoginRepository
import com.finite.eassytask.data.repository.ValidationResult
import com.finite.eassytask.databinding.FragmentForgotPasswordBinding
import com.finite.eassytask.databinding.FragmentNewPasswordBinding
import com.finite.eassytask.databinding.FragmentResetPasswordBinding

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val loginRepository = LoginRepository()

    // current number live data
    private val _currentNumber = MutableLiveData<String>("")
    val currentNumber: LiveData<String> = _currentNumber

    // LiveData to hold the validation result of registration
    private val _numberValidationResult = MutableLiveData<ValidationResult>()
    val numberValidationResult: LiveData<ValidationResult> = _numberValidationResult

    private val _otpValidationResult = MutableLiveData<ValidationResult>()
    val otpValidationResult: LiveData<ValidationResult> = _otpValidationResult

    private val _passwordValidationResult = MutableLiveData<ValidationResult>()
    val passwordValidationResult: LiveData<ValidationResult> = _passwordValidationResult

    fun clearNumberValidationResult() {
        _numberValidationResult.value = ValidationResult(false, emptyList())
    }

    fun clearOtpValidationResult() {
        _otpValidationResult.value = ValidationResult(false, emptyList())
    }

    fun clearPasswordValidationResult() {
        _passwordValidationResult.value = ValidationResult(false, emptyList())
    }

    fun validatePhoneNumber(phoneNumber: String) {
        val validationResult = loginRepository.validateNumber(phoneNumber)
        _numberValidationResult.value = validationResult
    }

    fun validateOtp(otp: String) {
        val validationResult = loginRepository.validateOTP(otp)
        _otpValidationResult.value = validationResult
    }

    fun validatePassword(password: String, confirmPassword: String) {
        val validationResult = loginRepository.validatePasswords(password, confirmPassword)
        _passwordValidationResult.value = validationResult
    }

    fun setCurrentNumber(phoneNumber: String) {
        _currentNumber.value = phoneNumber
    }

    fun clearErrorsNumber(binding: FragmentForgotPasswordBinding) {
        binding.numberTextInput.error = null
        binding.numberTextInput.isErrorEnabled = false
    }

    fun clearErrorsOtp(binding: FragmentResetPasswordBinding) {
        binding.otpTextInput.error = null
        binding.otpTextInput.isErrorEnabled = false
    }

    fun clearErrorsPassword(binding: FragmentNewPasswordBinding) {
        binding.passwordTextInput.error = null
        binding.passwordTextInput.isErrorEnabled = false
        binding.confirmPasswordTextInput.error = null
        binding.confirmPasswordTextInput.isErrorEnabled = false
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}