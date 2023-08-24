package com.finite.eassytask.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.finite.eassytask.R
import com.finite.eassytask.databinding.FragmentForgotPasswordBinding
import com.finite.eassytask.ui.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class ForgotPasswordFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentForgotPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val otpButton = view.findViewById<View>(R.id.otpButton)

        otpButton.setOnClickListener {
            viewModel.validatePhoneNumber(
                binding.numberTextInput.editText?.text.toString().trim()
            )
            //findNavController().navigate(R.id.action_forgotPasswordFragment_to_resetPasswordFragment)
        }


        viewModel.numberValidationResult.observe(viewLifecycleOwner) { validationResult ->
            if (validationResult.isValid) {
                binding.numberTextInput.clearFocus()
                viewModel.clearErrorsNumber(binding)
                viewModel.hideKeyboard(requireContext(), requireView())

                findNavController().navigate(R.id.action_forgotPasswordFragment_to_resetPasswordFragment)
                viewModel.setCurrentNumber(binding.numberTextInput.editText?.text.toString().trim())
                viewModel.clearNumberValidationResult()
                Snackbar.make(requireView(), "OTP Sent Successfully!", Snackbar.LENGTH_SHORT).show()

            } else if (validationResult.errors.isEmpty()) {
                viewModel.clearErrorsNumber(binding)
            } else {
                validationResult.errors.forEach {
                    when (it.first) {
                        "phoneNumber" -> binding.numberTextInput.error = it.second
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        binding.numberTextInput.editText?.setText(viewModel.currentNumber.value)
    }


}