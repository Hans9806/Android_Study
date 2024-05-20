package com.busanit.ch_ex.Ex_08.ex1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch_ex.R
import com.busanit.ch_ex.databinding.ActivityMain2Binding
import com.busanit.ch_ex.databinding.ActivityMainBinding
import com.busanit.ex01.SecondActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    val REQUEST_CODE = 20
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            // 문제1 데이터 전달
            button.setOnClickListener {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    .apply {
                        val msg = editText.text.toString()
                        putExtra("msg", msg)
                    }
                startActivity(intent)
            }

            // 문제2. 데이터 전달
            button2.setOnClickListener {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    .apply {
                        val msg = editText.text.toString()
                        putExtra("msg", msg)
                    }
                startActivityForResult(intent, REQUEST_CODE)
            }

            val contract = ActivityResultContracts.StartActivityForResult()
            val launcher = registerForActivityResult(contract) {
                if (it.resultCode == RESULT_OK) {
                    val returnMsg = it.data?.getStringExtra("return_msg")
                    binding.textView.text = returnMsg
                }
            }

            // 문제3. Result API
            button3.setOnClickListener {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    .apply {
                        val msg = editText.text.toString()
                        putExtra("msg", msg)
                    }

                launcher.launch(intent)
            }
        }
    }

    // 문제2
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE -> {
                    binding.textView.text = data?.getStringExtra("return_msg")
                }
            }
        }
    }
}