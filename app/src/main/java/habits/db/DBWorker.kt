import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.habits.model.Habit
import com.ncorti.kotlin.template.app.userClass.HelperClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DBWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        try {
            // Perform background operations here
            val uid = inputData.getString("uid") ?: ""
            val habitList = fetchDataInBackground(uid)
            Log.d("DBWorker:", "$uid")
            // Update LiveData using a separate mechanism
            HabitSys.updateHabitLiveData(habitList)
            HabitSys.habitList=habitList
            Log.d("DBListFromWorker:", "$habitList")

            return Result.success()
        } catch (e: Exception) {
            // Handle errors or exceptions here
            return Result.failure()
        }
    }

    private suspend fun fetchDataInBackground(uid: String): MutableList<Habit> {
        return withContext(Dispatchers.Default) {
            val userNode = HelperClass.getDatabaseInstance().getReference("habits").child(uid)
            val habitSnapshot = userNode.get().await()

            val habitList = mutableListOf<Habit>()

            if (habitSnapshot.exists()) {
                for (habitDataSnapshot in habitSnapshot.children) {
                    val habit = habitDataSnapshot.getValue(Habit::class.java)

                    habit?.let {
                        habitList.add(it)
                    }
                }
            }

            return@withContext habitList
        }
    }
}
