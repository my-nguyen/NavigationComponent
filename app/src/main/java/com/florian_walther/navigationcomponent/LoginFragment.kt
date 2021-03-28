package com.florian_walther.navigationcomponent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.florian_walther.navigationcomponent.databinding.FragmentLoginBinding

class LoginFragment: Fragment(R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLoginBinding.bind(view)
        binding.apply {
            val username = edUsername.text.toString()
            val password = edPassword.text.toString()

            btConfirm.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username, password)
                findNavController().navigate(action)
            }
        }
    }
}