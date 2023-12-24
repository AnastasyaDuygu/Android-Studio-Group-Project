import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.habits.model.Habit
import com.ncorti.kotlin.template.app.userClass.HelperClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HabitSys {
    companion object {
        private val _habits = MutableLiveData<MutableList<Habit>>()
        val habits: LiveData<MutableList<Habit>> = _habits

        suspend fun prepareHabits(uid: String): MutableList<Habit> {
            return withContext(Dispatchers.IO) {
                val userNode = HelperClass.getDatabaseInstance().getReference("habits").child(uid)
                val habitSnapshot = userNode.get().await()
                Log.d("DATA1", "UID: $uid, Snapshot: $habitSnapshot")

                val habitList = mutableListOf<Habit>()

                if (habitSnapshot.exists()) {
                    for (habitDataSnapshot in habitSnapshot.children) {
                        val habit = habitDataSnapshot.getValue(Habit::class.java)
                        Log.d("DATA2", "UID: $uid, Snapshot: $habitDataSnapshot, habit: $habit")

                        habit?.let {
                            habitList.add(it)
                        }
                    }
                }

                // Check if the new habitList is different from the current value before posting
                if (_habits.value != habitList) {
                    // Use postValue to update LiveData on the main thread
                    _habits.postValue(habitList)
                    Log.d("_HABITS", habitList.toString())
                }

                // Return the list directly
                return@withContext habitList
            }
        }
    }
}