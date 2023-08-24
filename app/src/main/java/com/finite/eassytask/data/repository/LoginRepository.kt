package com.finite.eassytask.data.repository

data class ValidationResult(
    val isValid: Boolean,
    val errors: List<Pair<String, String>>
)

class LoginRepository {

    private val errors = mutableListOf<Pair<String, String>>()

    fun validateNumber(number: String): ValidationResult {
        if(errors.isNotEmpty()) {
            errors.clear()
        }
        validPhoneNumber(number)

        return ValidationResult(errors.isEmpty(), errors)
    }

    fun validateOTP(otp: String): ValidationResult {
        if(errors.isNotEmpty()) {
            errors.clear()
        }
        validOTP(otp)

        return ValidationResult(errors.isEmpty(), errors)
    }

    // checks if the phone number is valid and meets the all requirements
    private fun validPhoneNumber(phoneNumber: String): Boolean {
        val phoneNumberPattern = Regex("^\\d{10}$")

        if (phoneNumber.isEmpty()) {
            errors.add(Pair("phoneNumber", "Phone number is required"))
            return false
        }
        if (!phoneNumber.matches(phoneNumberPattern)) {
            errors.add(Pair("phoneNumber", "Invalid phone number"))
            return false
        }
        return true
    }

    // Checks if the OTP is valid and meets the all requirements
    private fun validOTP(otp: String): Boolean {
        val otpPattern = Regex("^\\d{6}$")

        if (otp.isEmpty()) {
            errors.add(Pair("otp", "OTP is required"))
            return false
        }
        if (!otp.matches(otpPattern)) {
            errors.add(Pair("otp", "Enter 6 digit OTP"))
            return false
        }
        return true
    }

    // Checks if the password is valid and meets the all requirements
    private fun validPassword(password: String): Boolean {
        val passwordPattern =
            Regex("^(?=.*[0-9])(?=.*[!@#\$%&()\\[\\]])(?=.*[a-z])(?=.*[A-Z]).{8,}$")

        if (password.isEmpty()) {
            errors.add(Pair("password", "Password is required"))
            return false
        }

        if (!password.matches(passwordPattern)) {
            if (!password.matches(Regex(".*[0-9].*"))) {
                errors.add(Pair("password", "Password must contain at least one digit"))
            } else if (!password.matches(Regex(".*[!@#\$%&()].*"))) {
                errors.add(Pair("password", "Password must contain at least one special character"))
            } else if (!password.matches(Regex(".*[a-z].*"))) {
                errors.add(Pair("password", "Password must contain at least one lowercase letter"))
            } else if (!password.matches(Regex(".*[A-Z].*"))) {
                errors.add(Pair("password", "Password must contain at least one uppercase letter"))
            } else {
                errors.add(Pair("password", "Password must have at least 8 characters"))
            }
            return false
        }

        return true
    }

    // Checks if the confirm password is valid and matches the password
    private fun validConfirmPassword(password: String, confirmPassword: String): Boolean {
        return if (confirmPassword.isEmpty()) {
            errors.add(Pair("confirmPassword", "Confirm password is required"))
            false
        } else if (confirmPassword != password) {
            errors.add(Pair("confirmPassword", "Passwords do not match"))
            false
        } else {
            true
        }
    }

}