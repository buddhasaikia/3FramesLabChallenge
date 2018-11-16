package com.buddha.a3frameslabchallengeapp.home.view


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.buddha.a3frameslabchallengeapp.R
import com.buddha.a3frameslabchallengeapp.data.Word
import com.buddha.a3frameslabchallengeapp.home.viewmodel.HomeViewModel
import com.buddha.a3frameslabchallengeapp.utils.ProgressInterface
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class HomeFragment : Fragment(), ProgressInterface {
    private var progressBar: ContentLoadingProgressBar? = null

    override fun showProgress() {
        progressBar?.show()
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.hide()
        progressBar?.visibility = View.GONE
    }

    private var viewModel: HomeViewModel? = null
    private var sourceString: String =
        "These are forested lands where logging, hunting, grazing and other activities may be permitted on a sustainable basis to members of certain communities. In reserved forests, explicit permission is required for such activities. In protected forests, such activities are allowed unless explicitly prohibited. Thus, in general reserved forests enjoy a higher degree of protection with respect to protected forests."

    interface ActionListener {
        fun onActionComplete(list: ArrayList<Word>?)
    }

    private var actionListener: ActionListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ActionListener) {
            actionListener = context
        } else {
            throw RuntimeException("Must implement ActionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val etParagraph: EditText = view.findViewById(R.id.etParagraph)
        etParagraph.setText(sourceString)

        val btnProcess: Button = view.findViewById(R.id.btnProcess)
        btnProcess.setOnClickListener {
            showProgress()
            viewModel?.findMostPopular(etParagraph.text.toString())
                ?.subscribe(object : Observer<ArrayList<Word>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        viewModel?.addSubscription(d)
                    }

                    override fun onNext(list: ArrayList<Word>) {
                        hideProgress()
                        actionListener?.onActionComplete(list)
                    }

                    override fun onError(e: Throwable) {
                        hideProgress()
                        e.printStackTrace()
                        val builder = AlertDialog.Builder(activity ?: return)
                            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                            .setMessage("Something snapped!")
                        val alertDialog = builder.create()
                        alertDialog.show()
                    }
                })
        }
    }
}
