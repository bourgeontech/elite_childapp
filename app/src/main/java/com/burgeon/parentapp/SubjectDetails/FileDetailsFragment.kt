package com.burgeon.parentapp.SubjectDetails

import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.burgeon.parentapp.R
import kotlinx.android.synthetic.main.fragment_file_details.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [SubjectDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FileDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }

        Toast.makeText(activity,"ffff"+param1,Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_file_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        wbFiles.settings.javaScriptEnabled = true
       // val pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf"
        //wbFiles.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$param1")
        //wbFiles.loadUrl(param1)

        val src="http://docs.google.com/gview?embedded=true&url="+param1
        wbFiles.loadUrl( src);
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            FileDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
