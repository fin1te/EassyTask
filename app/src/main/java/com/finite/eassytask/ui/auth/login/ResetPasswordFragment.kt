package com.finite.eassytask.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.finite.eassytask.R
import com.finite.eassytask.databinding.FragmentResetPasswordBinding
import com.finite.eassytask.ui.viewmodel.LoginViewModel

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

        binding.forgotPasswordDescription.text = "We sent a 6 digits OTP on this number : +91-${viewModel.currentNumber.value}"

        binding.resetButton.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_newPasswordFragment)
        }
    }

}