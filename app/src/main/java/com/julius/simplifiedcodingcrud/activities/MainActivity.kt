package com.julius.simplifiedcodingcrud.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.julius.simplifiedcodingcrud.R
import com.julius.simplifiedcodingcrud.api.RetrofitClient
import com.julius.simplifiedcodingcrud.model.DefaultResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSignUp.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val name = editTextName.text.toString().trim()
            val school = editTextSchool.text.toString().trim()

            if (email.isEmpty()){
                editTextEmail.setError("Email Required")
//                stop further execution
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                editTextPassword.setError("Password Required")
                editTextPassword.requestFocus()
                return@setOnClickListener
            }
            if (name.isEmpty()){
                editTextName.setError("Name Required")
                editTextName.requestFocus()
                return@setOnClickListener
            }
            if (school.isEmpty()){
                editTextSchool.setError("School Required")
                editTextSchool.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.createUser(email, name, password, school)
                .enqueue(object : Callback<DefaultResponse> {
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT ).show()

                    }

                    override fun onResponse(
                        call: Call<DefaultResponse>,
                        response: Response<DefaultResponse>
                    ) {
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()

                    }
                })
        }
    }
}
