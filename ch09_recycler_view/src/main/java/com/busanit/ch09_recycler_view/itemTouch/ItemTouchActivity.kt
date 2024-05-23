package com.busanit.ch09_recycler_view.itemTouch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.R
import com.busanit.ch09_recycler_view.databinding.ActivityItemBinding
import com.busanit.ch09_recycler_view.databinding.ItemBinding

class ItemTouchActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 더미 데이터 생성
        val itemList = mutableListOf<Item>()
        for (i in 1..100) {
            itemList.add(Item("항복 $i"))
        }

        // 7. 액티비티에서 어댑터 및 레이아웃 매니저 설정
        val adapter = ItemAdapter(itemList)
        binding.recyclerView1.adapter = adapter
        binding.recyclerView1.layoutManager =
            LinearLayoutManager(this)

        // ItemTouchHelperCallback 을 실행하는 ItemTouchHelper
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        // recyclerView 에 연결
        itemTouchHelper.attachToRecyclerView(binding.recyclerView1)

    }

}
// 1. 데이터 클래스 생성

// 2. 리사이클러뷰 레이아웃 XML 설정
// 3. 아이템 항목 레이아웃 XML 설정
// 4. 어댑터 클래스 작성하기
// 매개변수로 데이터 리스트를 받음 (기타 매개변수 추가 가능)
// recyclerView.Adapter 를 상속받고, 커스텀 뷰홀더를 제네릭 추가
