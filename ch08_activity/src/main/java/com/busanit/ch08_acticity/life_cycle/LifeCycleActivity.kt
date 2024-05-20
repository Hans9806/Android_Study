
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch08_acticity.R

private const val TAG = "mylog"

class LifeCycleActivity : AppCompatActivity() {

    // Activity 가 실행될 때 : onCreate -> onStart -> onResume
    // Activity 가 보이지 않을 때 : onPause -> onStop
    // Activity 가 다시 보일 때 : onRestart -> onStart -> onResume
    // Activity 가 종료될 때 : onPause -> onStop -> onDestroy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate 호출: 액티비티가 처음 실행되 때 호출")
        // 화면 회전이 발생했을 떄도 호출됨.

        setContentView(R.layout.activity_life_cycle)
        // 액티비티가 관리할 View를 생성하고 View에 대한 기초작업을 수행
    }

    override fun onStart() {
        // onCreate 다음으로 호출
        // 보이지 않는 상태가 되었다가 보이게 될 때 호출
        super.onStart()
        Log.d(TAG, "onResume 호출: ")
    }

    override fun onResume() {
        // onStart 다음으로 호출
        // 다른 액티비티 보여지다가 다시 보여질 때 호출
        super.onResume()
        Log.d(TAG, "onResume 호출: ")
    }

    override fun onPause() {
        // 사용자가 Actitvity를 떠나는 것에 대한 첫 신호
        // 다른 액티비티가 눈에 보여질 때 호출 -> 현 액티비티 일시정지
        super.onPause()
        Log.d(TAG, "onPause 호출")
    }
    override fun onStop() {
        // 더 이상 사용자에게 표시되지 않는 상태
        // 리소스 해제, 데이터 저장 등
        super.onStop()
        Log.d(TAG, "onStop 호출: ")
    }

    override fun onDestroy() {
        // 완전 종료될 때 호출
        super.onDestroy()
        Log.d(TAG, "onDestroy 호출: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart 호출: ")
    }
}

