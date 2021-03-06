package com.example.coursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.coursework.databinding.ActivityAdminBinding
import com.example.coursework.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        // check user is logged in or not

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else {
            val email = firebaseUser.email
            binding.emailTv.text = email
        }
    }
}