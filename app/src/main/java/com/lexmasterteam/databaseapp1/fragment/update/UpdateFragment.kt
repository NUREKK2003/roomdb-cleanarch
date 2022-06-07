package com.lexmasterteam.databaseapp1.fragment.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lexmasterteam.databaseapp1.R
import com.lexmasterteam.databaseapp1.databinding.FragmentUpdateBinding
import com.lexmasterteam.databaseapp1.model.User
import com.lexmasterteam.databaseapp1.viewmodel.UserViewModel


class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Ustawiamy dane do edycji

        binding.etUpdateFirstName.setText(args.currentUser.firstName)
        binding.etUpdataLastName.setText(args.currentUser.lastName)
        binding.etUpdateAge.setText(args.currentUser.age.toString())

        // podpięcie akcji do przycisku
        binding.btUpdateCommit.setOnClickListener {
            updateItem()
        }

        // add men
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),
                "Successfully removed: ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){ _, _->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }

    private fun updateItem(){
        val firstName = binding.etUpdateFirstName.text.toString()
        val lastName = binding.etUpdataLastName.text.toString()
        val age = Integer.parseInt(binding.etUpdateAge.text.toString())
        if(inputCheck(firstName,lastName,binding.etUpdateAge.text.toString())){
            // create user object
            val updatedUser = User(args.currentUser.id,firstName,lastName,age)
            // Update Current User
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(),"Updated Succesfully!",Toast.LENGTH_SHORT).show()
            // back to menu
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else{
            Toast.makeText(requireContext(),"Please fill out all fields",Toast.LENGTH_SHORT).show()
        }
    }
    // sprawdza czy jakaś linijka nie jest pusta
    private fun inputCheck(firstName:String,lastName:String,age:String):Boolean{
        return !(TextUtils.isEmpty(firstName)&& TextUtils.isEmpty(lastName)&& TextUtils.isEmpty((age)))
    }
}