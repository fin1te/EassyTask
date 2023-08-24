package com.finite.eassytask.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.finite.eassytask.R
import com.finite.eassytask.databinding.FragmentNewPasswordBinding
import com.finite.eassytask.ui.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class NewPasswordFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentNewPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            FragmentNewPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clearErrorsOnFocusChange()

        binding.savePasswordButton.setOnClickListener {
            viewModel.validatePassword(
                binding.passwordTextInput.editText?.text.toString().trim(),
                binding.confirmPasswordTextInput.editText?.text.toString().trim()
            )
        }

        viewModel.passwordValidationResult.observe(viewLifecycleOwner) { validationResult ->
            if (validationResult.isValid) {
                binding.passwordTextInput.clearFocus()
                binding.confirmPasswordTextInput.clearFocus()
                viewModel.clearErrorsPassword(binding)
                viewModel.hideKeyboard(requireContext(), requireView())

                findNavController().navigate(R.id.action_newPasswordFragment_to_onboardingFragment)

                viewModel.clearOtpValidationResult()
                Snackbar.make(
                    requireView(),
                    "Password successfully changed!",
                    Snackbar.LENGTH_SHORT
                ).show()

            } else if (validationResult.errors.isEmpty()) {
                viewModel.clearErrorsPassword(binding)
            } else {
                validationResult.errors.forEach {
                    when (it.first) {
                        "password" -> binding.passwordTextInput.error = it.second
                        "confirmPassword" -> binding.confirmPasswordTextInput.error = it.second
                    }
                }
            }
        }
    }

    private fun clearErrorsOnFocusChange() {
        binding.passwordTextInput.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.passwordTextInput.error = null
                binding.passwordTextInput.isErrorEnabled = false
            }
        }

        binding.confirmPasswordTextInput.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.confirmPasswordTextInput.error = null
                binding.confirmPasswordTextInput.isErrorEnabled = false
            }
        }
    }

}