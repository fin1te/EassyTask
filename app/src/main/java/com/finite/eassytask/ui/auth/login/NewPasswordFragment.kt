package com.finite.eassytask.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.finite.eassytask.R
import com.finite.eassytask.databinding.FragmentNewPasswordBinding
import com.finite.eassytask.ui.viewmodel.LoginViewModel

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

}