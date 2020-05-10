package io.github.wulkanowy.data.repositories.grade

import io.github.wulkanowy.data.db.entities.Grade
import io.github.wulkanowy.data.db.entities.GradeSummary
import io.github.wulkanowy.data.db.entities.Semester
import io.github.wulkanowy.data.db.entities.Student
import io.github.wulkanowy.sdk.Sdk
import io.github.wulkanowy.utils.init
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GradeRemote @Inject constructor(private val sdk: Sdk) {

    fun getGrades(student: Student, semester: Semester): Single<Pair<List<Grade>, List<GradeSummary>>> {
        return sdk.init(student).switchDiary(semester.diaryId, semester.schoolYear)
            .getGrades(semester.semesterId)
            .map { (details, summary) ->
                details.map {
                    Grade(
                        studentId = semester.studentId,
                        semesterId = semester.semesterId,
                        subject = it.subject,
                        entry = it.entry,
                        value = it.value,
                        modifier = it.modifier,
                        comment = it.comment,
                        color = it.color,
                        gradeSymbol = it.symbol,
                        description = it.description,
                        weight = it.weight,
                        weightValue = it.weightValue,
                        date = it.date,
                        teacher = it.teacher
                    )
                } to summary.map {
                    GradeSummary(
                        semesterId = semester.semesterId,
                        studentId = semester.studentId,
                        position = 0,
                        subject = it.name,
                        predictedGrade = it.predicted,
                        finalGrade = it.final,
                        pointsSum = it.pointsSum,
                        proposedPoints = it.proposedPoints,
                        finalPoints = it.finalPoints,
                        average = it.average
                    )
                }
            }
    }
}
