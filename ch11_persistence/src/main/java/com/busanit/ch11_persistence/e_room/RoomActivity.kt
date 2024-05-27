package com.busanit.ch11_persistence.e_room

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.busanit.ch11_persistence.R
import com.busanit.ch11_persistence.databinding.ActivityRoomBinding
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoomBinding
    lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "myroomdb.db"
        ).build()

        binding.run {
            buttonSave.setOnClickListener {
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toIntOrNull()

                if (age == null) {
                    Toast.makeText(this@RoomActivity, "나이를 잘못 입력하셨습니다.",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // User 객체 생성
                    val user = User(name = name, age = age) // User 객체 생성

                    // 비동기 작업을 위한 코틀린 코루틴 스코프 (DB작업)
                    lifecycleScope.launch {
                        db.userDao().insert(user)               // DB에 삽입
                    }
                }
            }

            buttonLoad.setOnClickListener {
                lifecycleScope.launch {
                    val userList = db.userDao().getAll()    // 쿼리 실행 -> List에 담김
                    // 데이터를 표시 (리스트를 구분자(줄바꿈) 문자로 조인)
                    textView.text = userList.joinToString("\n")
                }
            }
        }

    }
}