import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.OneTimeWorkRequestBuilder
import com.example.habits.model.Habit
import androidx.work.Data
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.ncorti.kotlin.template.app.userClass.HelperClass
import java.util.concurrent.Executors
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
class HabitSys {
    companion object {
        private val _habits = MutableLiveData<MutableList<Habit>>()
        val habits: LiveData<MutableList<Habit>> = _habits
        var habitList = mutableListOf<Habit>()

        suspend fun prepareHabits(context: Context, uid: String): MutableList<Habit> {
            val inputData = Data.Builder().putString("uid", uid).build()
            val workRequestForDB = OneTimeWorkRequestBuilder<DBWorker>().setInputData(inputData).build()

            WorkManager.getInstance(context).enqueue(workRequestForDB)
            return habitList!!
        }

        internal suspend fun updateHabitLiveData(habitList: MutableList<Habit>) {
            withContext(Dispatchers.Main){
                if (_habits.value != habitList) {
                    // Use postValue to update LiveData on the main thread
                    _habits.postValue(habitList)
                    Log.d("_HABITS", habitList.toString())
                }
            }
        }
    }
}