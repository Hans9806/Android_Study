package com.busanit.ch11_persistence.b_internal

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch11_persistence.R
import com.busanit.ch11_persistence.databinding.ActivityInternalBinding
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader

class internalActivity : AppCompatActivity() {
    lateinit var binding: ActivityInternalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 내부 저장소(Internal Storage)
        // 앱의 내부 저장소에 파일 형태로 데이터 저장
        // 앱 전용 데이터 저장, 다른 앱에서는 접근이 불가
        // 사용자의 데이터 파일, 캐시 등

        // 내부 저장소에 사용된 파일은 사용자가 앱 정보에서 `저장소 지우기`로 삭제 가능

        val fileName = "internal_storage.txt"   // 저장할 파일 이름

        binding.run {
            // 1. 파일 저장
            buttonSave.setOnClickListener {
                val inputData = editTextName.text.toString()    // 입력된 데이터

                // 출력스트림을 열고, 바이트배열로 쓰고, 스트림 종료
                val fileOutputStream = openFileOutput(fileName, MODE_PRIVATE)
                fileOutputStream.write(inputData.toByteArray())
                fileOutputStream.close()
            }
            // 2. 파일 읽기
            buttonLoad.setOnClickListener {
                try{
                    // 입력 스트림 열기
                    val fileInputStream = openFileInput(fileName)
                    val isr = InputStreamReader(fileInputStream)
                    val br = BufferedReader(isr)
                    val sb = StringBuilder()
                    var text: String?
                    while (br.readLine().also {
                            text = it
                        } != null) {
                        sb.append(text)
                    }
                    br.close()
                    isr.close()
                    fileInputStream.close()

                    // 빌더에 담은 문자열 반환
                    val readData = sb.toString()

                    // 뷰에 표시
                    textView.text = readData
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            buttonClear.setOnClickListener {
                // 1. 안드로이드 Context 메서드 사용
                deleteFile(fileName)     // context 에서 파일 삭제

                // 2. File 클래스와 경로 사용
                try {

                    // 파일 경로 가져오기
                    val fileStreamPath = getFileStreamPath(fileName)
                    // 경로 확인
                    Log.d("mylog", "onCreate: ${fileStreamPath.toString()}")

                    // 파일 경로가 존재하면 삭제
                    if (fileStreamPath != null) {
                        fileStreamPath.delete()
                    }
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }

            }
        }
    }
}