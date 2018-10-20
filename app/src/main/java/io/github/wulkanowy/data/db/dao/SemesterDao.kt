package io.github.wulkanowy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.wulkanowy.data.db.entities.Semester
import io.reactivex.Single

@Dao
interface SemesterDao {

    @Insert
    fun insertAll(semester: List<Semester>)

    @Query("SELECT * FROM Semesters WHERE student_id = :studentId")
    fun getSemester(studentId: Int): Single<List<Semester>>
}
