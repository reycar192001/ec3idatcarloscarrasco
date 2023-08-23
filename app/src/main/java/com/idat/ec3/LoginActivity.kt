package com.idat.ec3

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.idat.ec3.databinding.ActivityLoginBinding
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleLauncher: ActivityResultLauncher<Intent>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()

        sharedPreferences=getSharedPreferences(SESSION_PREFERENCE, MODE_PRIVATE)
        firebaseAuth= Firebase.auth
        googleLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account= task.getResult(ApiException::class.java)
                    signInFirebaseWithGoogle(account.idToken)
                }catch (e: Exception){

                }
            }
        }
    }

    private fun signInFirebaseWithGoogle(idToken: String?) {
        val authCredential = GoogleAuthProvider.getCredential(idToken,null)
        firebaseAuth.signInWithCredential(authCredential)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    val user : FirebaseUser = firebaseAuth.currentUser!!
                    //navegar a la siguiente pantalla
                    //guardar en shared preferences
                    sharedPreferences.edit().apply{
                        putString(EMAIL,user.email)
                            .apply()
                    }
                    val intent = Intent(this,MenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Ocurrio un error", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun setupViews() {
        binding.tilEmail.editText?.addTextChangedListener { text ->
            binding.btnLogin.isEnabled = validateInputs(text.toString(),binding.tilPassword.editText?.text.toString())
        }
        binding.tilPassword.editText?.addTextChangedListener { text ->
            binding.btnLogin.isEnabled = validateInputs(binding.tilEmail.editText?.text.toString(),text.toString())
        }

        /*binding.btnGoogle.setOnClickListener{
            signInWithGoogle()
        }*/
        binding.btnRegistrar.setOnClickListener {
            accesRegistrar()
        }
        binding.btnLogin.setOnClickListener {
            signUpWithEmailPassword()
        }


    }

    private fun accesRegistrar() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }

    private fun signUpWithEmailPassword() {
        val email = binding.tilEmail.editText?.text.toString()
        val password = binding.tilPassword.editText?.text.toString()
        signInFirebaseWithEmail(email,password)
    }

    private fun signInFirebaseWithEmail(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "El usuario no se encontro", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validateInputs(email:String,password: String):Boolean{
        val isEmailOK = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordOk = password.length >=6
        return  isPasswordOk && isEmailOK
    }

    companion object{
        const val SESSION_PREFERENCE: String="SESSION_PREFERENCES"
        const val EMAIL:String="EMAIL"
    }
}