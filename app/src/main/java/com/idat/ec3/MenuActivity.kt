package com.idat.ec3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.idat.ec3.databinding.ActivityMainBinding
import com.idat.ec3.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddCoupon.setOnClickListener{
            val intent= Intent( this, AddPunkActivity::class.java)
            startActivity(intent)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_coupon)  as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMenu.setupWithNavController(navController)

    }
}