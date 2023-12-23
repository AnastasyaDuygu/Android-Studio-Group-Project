import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.habits.model.Habit
import com.ncorti.kotlin.template.app.userClass.HelperClass
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.tasks.await

class HabitSys {
    companion object {
        private val _habits = MutableLiveData<List<List<Habit>>>()
        val habits: LiveData<List<List<Habit>>> = _habits
        val habitsMutex = Mutex()

        suspend fun prepareHabits(uid: String): List<List<Habit>> {
            val userNode = HelperClass.getDatabaseInstance().getReference("habits").child(uid)
            val habitSnapshot = userNode.get().await()
            Log.d("DATA", "UID: $uid, Snapshot: $habitSnapshot")

            if (habitSnapshot.exists()) {
                val habitList = mutableListOf<Habit>()

                for (habitDataSnapshot in habitSnapshot.children) {
                    val habit = habitDataSnapshot.getValue(Habit::class.java)
                    Log.d("DATA", "UID: $uid, Snapshot: $habitDataSnapshot, habit: $habit")

                    habit?.let {
                        habitList.add(it)
                    }
                }

                // Now habitList is an ArrayList<Habit> containing all habits for the UID
                habitsMutex.withLock {
                    _habits.postValue(listOf(habitList))
                    Log.d("_HABITS", _habits.value.toString())
                }

                // Return the list directly
                return _habits.value.orEmpty()
            }

            // Return empty list if no data
            return emptyList()
        }
    }
}
