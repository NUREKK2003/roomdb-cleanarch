package com.lexmasterteam.databaseapp1.fragment.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lexmasterteam.databaseapp1.R
import com.lexmasterteam.databaseapp1.model.User
import com.lexmasterteam.databaseapp1.viewmodel.UserViewModel
import com.lexmasterteam.databaseapp1.databinding.FragmentAddBinding


class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btCommit.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text.toString()
        if(inputCheck(firstName,lastName,age)){
            // Create user object
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
            // Add Data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Succesfully added!",Toast.LENGTH_LONG).show()
            // naviagte back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else{
            Toast.makeText(requireContext(),"Please fill out all fields!",Toast.LENGTH_LONG).show()
        }
    }

    // sprawdza czy jakaś linijka nie jest pusta
    private fun inputCheck(firstName:String,lastName:String,age:String):Boolean{
        return !(TextUtils.isEmpty(firstName)&&TextUtils.isEmpty(lastName)&&TextUtils.isEmpty((age)))
    }
}