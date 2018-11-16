package com.buddha.a3frameslabchallengeapp.home.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val mCompositeDisposable = CompositeDisposable()

    fun addSubscription(d: Disposable) {
        mCompositeDisposable.add(d)
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }
}
