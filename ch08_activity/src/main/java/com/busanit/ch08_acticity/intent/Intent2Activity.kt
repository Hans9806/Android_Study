package com.busanit.ch08_acticity.intent

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch08_acticity.R
import com.busanit.ch08_acticity.databinding.ActivityIntent2Binding

class Intent2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntent2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.run {
            // 호출한 인텐트로부터 데이터를 받음
            val extra = intent.getStringExtra("extra_msg")
            textView1.text = extra

            // 버튼을 클릭하면 반환할 인텐트를 생성하고 Activity 를 종료
            button1.setOnClickListener {
                val resultIntent = Intent()     // 인텐트 객체 생성
                resultIntent.putExtra("result_msg",
                    "$extra, Result from Other Activity")   //

                setResult(RESULT_OK, resultIntent)  // 결과 설정
                finish()        // Activity를 종료
            }
        }
    }
}