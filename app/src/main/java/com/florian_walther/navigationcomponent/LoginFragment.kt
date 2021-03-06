package com.florian_walther.navigationcomponent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.florian_walther.navigationcomponent.databinding.FragmentLoginBinding

class LoginFragment: Fragment(R.layout.fragment_login) {

    private val args: LoginFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLoginBinding.bind(view)
        val usernameDeepLink = args.username
        binding.apply {
            edUsername.setText(usernameDeepLink)
            btConfirm.setOnClickListener {
                val username = edUsername.text.toString()
                val password = edPassword.text.toString()
                val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username, password)
                findNavController().navigate(action)
            }
        }
    }
}