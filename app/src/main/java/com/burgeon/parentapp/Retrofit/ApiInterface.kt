package com.study.firebasecrash.Retrofit


import com.app.yashqualitytesting.Datamodels.LoginDatamodel
import com.burgeon.parentapp.Datamodels.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("parentlogin")
    fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginDatamodel>

    @FormUrlEncoded
    @POST("getStudentByParent")
    fun getStudentByParent(@Field("parent_id") parent_id: String): Call<ChildDatamodel>

    @FormUrlEncoded
    @POST("staffforgetPassword")
    fun userForgotpasswd(@Field("contact_no") username: String): Call<LoginDatamodel>


    @FormUrlEncoded
    @POST("getStudentHomework")
    fun getHomework(
        @Field("index") index: String,
        @Field("count") count: String,
        @Field("class_id") class_id: String,
        @Field("section_id") section_id: String
    ): Call<HomeworkDatamodel>

    @FormUrlEncoded
    @POST("getStudentExam")
    fun getStudentExam(
        @Field("index") index: String,
        @Field("count") count: String,
        @Field("class_id") class_id: String,
        @Field("section_id") section_id: String
    ): Call<HomeworkDatamodel>


    @Streaming
    @GET
    fun downloadFileWithDynamicUrlSync(@Url fileUrl: String): Call<ResponseBody>


    @FormUrlEncoded
    @POST("gettimetable")
    fun gettimetable(@Field("student_id") parent_id: String): Call<TimetableDatamodel>

    @FormUrlEncoded
    @POST("getExamResult")
    fun getExamResult(@Field("student_id") parent_id: String): Call<ExamresultDatamodel>

    @FormUrlEncoded
    @POST("getExamination")
    fun getExamination(@Field("student_id") student_id: String): Call<GetExaminationModel>


    @FormUrlEncoded
    @POST("getExamSchedule")
    fun getExamSchedule(
        @Field("student_id") student_id: String,
        @Field("exam_id") exam_id: String
    ): Call<ExamDetails>

    @FormUrlEncoded
    @POST("getParentCircular")
    fun getCircular(
        @Field("index") index: String,
        @Field("count") count: String,
        @Field("student_id") student_id: String
    ): Call<Circular>


    @FormUrlEncoded
    @POST("parentViewAttandence")
    fun parentViewAttandence(
        @Field("student_id") student_id: String,
        @Field("year") year: String,
        @Field("month") month: String
    ): Call<AttendanceDatamodel>


    @FormUrlEncoded
    @POST("parentViewRemark")
    fun parentViewRemark(@Field("student_id") student_id: String): Call<RemarkModel>

    @FormUrlEncoded
    @POST("deleteremark")
    fun deleteRemark(@Field("remarkid") remarkid: String): Call<RemarkModel>


    @FormUrlEncoded
    @POST("studentProfile")
    fun studentProfile(@Field("student_id") student_id: String): Call<Studentprofile>

    @FormUrlEncoded
    @POST("changepass")
    fun changepass(
        @Field("current_pass") current_pass: String,
        @Field("new_pass") new_pass: String,
        @Field("parent_id") parent_id: String
    ): Call<LoginDatamodel>

    @FormUrlEncoded
    @POST("getSubjectsByClassId")
    fun getSubjectsByClassId(@Field("class_id") class_id: String): Call<SubjectDatamodel>


    @FormUrlEncoded
    @POST("getChaptersBySubject")
    fun getChaptersBySubject(@Field("subject_id") class_id: String): Call<ChapterDatamodel>

    @FormUrlEncoded
    @POST("getChapterContent")
    fun getChapterContent(@Field("chapter_id") chapter_id: String): Call<ChapterContentDatamodel>

    @FormUrlEncoded
    @POST("signIn")
    fun signIn(@Field("student_id") chapter_id: String): Call<LoginDatamodel>

    @FormUrlEncoded
    @POST("checkIsSignIn")
    fun checkIsSignIn(@Field("student_id") chapter_id: String): Call<LoginDatamodel>

    @FormUrlEncoded
    @POST("sendComments")
    fun Question(
        @Field("student_id") student_id: String,
        @Field("content_id") teacher_id: String,
        @Field("comment") lesson_id: String
    ): Call<LoginDatamodel>

    @FormUrlEncoded
    @POST("viewStudentComments")
    fun viewStudentComments(
        @Field("index") index: String,
        @Field("count") count: String,
        @Field("content_id") teacher_id: String
    ): Call<QuestionmainPOjo>

    @FormUrlEncoded
    @POST("viewTeacherReply")
    fun viewTeacherReply(
        @Field("question_id") question_id: String
    ): Call<Replaymain>

    @POST("studentSubmitHomework")
    fun studentSubmitHomework(@Body requestBody: RequestBody): Call<LoginDatamodel>

    @POST("sendCommentAudio")
    fun sendCommentAudio(@Body requestBody: RequestBody): Call<LoginDatamodel>

    @FormUrlEncoded
    @POST("StudentViewUploads")
    fun StudentViewUploads(
        @Field("student_id") student_id: String,
        @Field("homework_id") homework_id: String,
        @Field("type") type: String
    ): Call<Uploadedmain>

    @FormUrlEncoded
    @POST("deleteUpload")
    fun deleteUpload(
        @Field("student_id") student_id: String,
        @Field("homework_id") homework_id: String,
        @Field("upload_id") upload_id: String
    ): Call<LoginDatamodel>

    @FormUrlEncoded
    @POST("communicate")
    fun userCommunicate(
        @Field("student_id") student_id: String,
        @Field("teacher_id") teacher_id: String,
        @Field("complaint_type") complaint_type: String,
        @Field("comment") comment: String
    ): Call<LoginDatamodel>

    @FormUrlEncoded
    @POST("loadgmeet")
    fun loadgmeet(
        @Field("student_id") student_id: String
    ): Call<OnlineClassPojo>

}