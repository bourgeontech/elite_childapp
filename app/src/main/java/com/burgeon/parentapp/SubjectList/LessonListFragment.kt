package com.burgeon.parentapp.SubjectList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Adapter.ChapterContentAdapter
import com.burgeon.parentapp.Datamodels.Chapter
import com.burgeon.parentapp.Datamodels.SubjectData
import com.burgeon.parentapp.Datamodels.contentDetails
import com.burgeon.parentapp.Dilaog.ChapterDialog
import com.burgeon.parentapp.Dilaog.SubjectDialog
import com.burgeon.parentapp.R
import com.burgeon.parentapp.SubjectDetails.SubjectDetailsFragment
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_subject_list.*
import java.util.regex.Pattern


class LessonListFragment : Fragment(), LessonListView {

    private lateinit var selectedChapterContent: List<contentDetails>
    private lateinit var chapterContentObj: ChapterContentAdapter.OnChaptercontenetdetails
    private lateinit var chapterData: List<Chapter>
    private lateinit var selectedChapterId: String
    private lateinit var ChapterDataobj: ChapterDialog.ChapterDialogListener
    private lateinit var selectedSubjectId: String
    private lateinit var subjectData: List<SubjectData>
    private lateinit var subjectDataObj: SubjectDialog.DialogListener

    private val presenter = LessonListPresenter(this, LessonListInteractor())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_subject_list, container, false)
        return view
    }


    override fun onResume() {
        super.onResume()

        if (this::selectedChapterContent.isInitialized && selectedChapterContent != null) {
            rvLesson.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            val adapter =
                fragmentManager?.let { ChapterContentAdapter(selectedChapterContent, activity, it) }
            adapter?.setChapterInterface(chapterContentObj)
            rvLesson.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var preferenceObj = PreferenceRequestHelper(activity)
        var classId = preferenceObj.getStringValue(Constants.PRES_STUDENT_CLASS_ID, "")


        subjectDataObj = object : SubjectDialog.DialogListener {
            override fun onDialogclicked(id: String, title: String) {
                selectedSubjectId = id;
                tvSubjectdialog.text = title

                presenter.getChapter(selectedSubjectId)
            }
        }

        ChapterDataobj = object : ChapterDialog.ChapterDialogListener {
            override fun onChapterDialogclicked(id: String, title: String) {
                selectedChapterId = id;
                tvChapterdialog.text = title;

                if (selectedChapterId != null && selectedChapterId != "")
                    presenter.getChapterContent(selectedChapterId)
            }
        }

        chapterContentObj = object : ChapterContentAdapter.OnChaptercontenetdetails {
            override fun onChaptercontenet(
                video: String,
                audio: String,
                file: String,
                name: String,
                contentId:String
            ) {

                var vId: String = ""
                val pattern =
                    "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
                val compiledPattern =
                    Pattern.compile(pattern)
                val matcher = compiledPattern.matcher(video)
                if (matcher.find()) {
                    vId = matcher.group()
                }
                val SubjectDetailsFragment =
                    SubjectDetailsFragment.newInstance(vId, audio, file, name,contentId)

                fragmentManager?.beginTransaction()?.replace(
                    R.id.fl_container,
                    SubjectDetailsFragment
                )?.addToBackStack("")?.commit()
            }
        }

        tvSubjectdialog.setOnClickListener {
            presenter.getSubject(classId)
        }

        tvChapterdialog.setOnClickListener {
            if (selectedSubjectId != null && selectedSubjectId != "")
                presenter.getChapter(selectedSubjectId)
        }

        ivBack.setOnClickListener {
            try {
                fragmentManager?.popBackStackImmediate()
            } catch (e: Exception) {

            }
        }

        tvTitle.setOnClickListener {
            try {
                fragmentManager?.popBackStackImmediate()
            } catch (e: Exception) {

            }
        }
    }

    override fun showProgress() {
        pgProgresslesson?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgresslesson?.visibility = View.GONE
    }

    override fun setSubjectData(body: List<SubjectData>?) {

        if (body != null) {
            subjectData = body

            try {
                val SubjectDialogObj = SubjectDialog(body)
                SubjectDialogObj.setDialoglistener(subjectDataObj)
                val fm = activity?.supportFragmentManager
                SubjectDialogObj.show(fm!!, "name")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun setChapterData(body: List<Chapter>?) {

        if (body != null) {
            chapterData = body

            try {
                val ChapterDialogObj = ChapterDialog(body)
                ChapterDialogObj.setDialoglistener(ChapterDataobj)
                val fm = activity?.supportFragmentManager
                ChapterDialogObj.show(fm!!, "name")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    override fun setChapterDataError(msg: String?) {

    }

    override fun showChapterContent(data: List<contentDetails>?) {

        tvHeadtitle.visibility = View.VISIBLE

        selectedChapterContent = data!!

        rvLesson.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val adapter = fragmentManager?.let { ChapterContentAdapter(data, activity, it) }
        adapter?.setChapterInterface(chapterContentObj)
        rvLesson.adapter = adapter

    }

    override fun showChapterContentError(msg: String) {
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
        }
    }

    override fun apiError() {
    }
}
