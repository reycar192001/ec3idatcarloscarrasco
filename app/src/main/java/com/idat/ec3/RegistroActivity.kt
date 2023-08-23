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
import com.idat.ec3.databinding.ActivityRegistroBinding
import java.lang.Exception

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var googleLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistroBinding.inflate(layoutInflater)
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
                        putString(LoginActivity.EMAIL,user.email)
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
            binding.btnRegistrarView.isEnabled = validateInputs(text.toString(),binding.tilPassword.editText?.text.toString())
        }
        binding.tilPassword.editText?.addTextChangedListener { text ->
            binding.btnRegistrarView.isEnabled = validateInputs(binding.tilEmail.editText?.text.toString(),text.toString())
        }

        binding.btnRegistrarView.setOnClickListener {
            signInWithEmailPassword()
        }
    }

    private fun signInWithEmailPassword() {
        val email= binding.tilEmail.editText?.text.toString()
        val password= binding.tilPassword.editText?.text.toString()
        if(validateInputs(email, password)){
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this,"Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this,"El usuario no puedo ser creado", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(this,"Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
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