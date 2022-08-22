package com.example.retrofitandmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.catfact.util.CatRetrofit
import com.example.retrofitandmodule.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "로그"
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.buttonGetCatFact.setOnClickListener {
            binding.buttonGetCatFact.isEnabled = false
            binding.progressbarLoading.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {
                val fact = getCatFact()

                withContext(Dispatchers.Main) {
                    binding.textviewCatFact.text = fact
                    binding.progressbarLoading.visibility = View.GONE
                    binding.buttonGetCatFact.isEnabled = true
                }
            }
        }
    }

    private suspend fun getCatFact(): String {
        return CatRetrofit.api.getCatInfo().await().fact
    }
}