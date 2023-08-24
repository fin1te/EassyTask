package com.finite.eassytask.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.finite.eassytask.R
import com.finite.eassytask.databinding.FragmentResetPasswordBinding
import com.finite.eassytask.ui.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class ResetPasswordFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentResetPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            forgotPasswordDescription.text =
                "We sent a 6 digits OTP on this number : +91-${viewModel.currentNumber.value}"

            resetButton.setOnClickListener {
                viewModel.validateOtp(
                    binding.otpTextInput.editText?.text.toString().trim()
                )
            }

            resendButton.setOnClickListener {
                Snackbar.make(requireView(), "New OTP Sent!", Snackbar.LENGTH_SHORT).show()
            }
        }


        viewModel.otpValidationResult.observe(viewLifecycleOwner) { validationResult ->
            if (validationResult.isValid) {
                binding.otpTextInput.clearFocus()
                viewModel.clearErrorsOtp(binding)
                viewModel.hideKeyboard(requireContext(), requireView())

                findNavController().navigate(R.id.action_resetPasswordFragment_to_newPasswordFragment)

                viewModel.clearOtpValidationResult()
                Snackbar.make(requireView(), "OTP Verified!", Snackbar.LENGTH_SHORT).show()

            } else if (validationResult.errors.isEmpty()) {
                viewModel.clearErrorsOtp(binding)
            } else {
                validationResult.errors.forEach {
                    when (it.first) {
                        "otp" -> binding.otpTextInput.error = it.second
                    }
                }
            }
        }
    }

}