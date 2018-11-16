package com.buddha.a3frameslabchallengeapp.home.viewmodel

import android.util.Log
import com.buddha.a3frameslabchallengeapp.data.Word
import com.buddha.a3frameslabchallengeapp.utils.WordComparator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class HomeViewModel : BaseViewModel() {

    fun findMostPopular(paragraph: String): Observable<ArrayList<Word>>? {
        return Observable.just(processParagraph(paragraph))
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread());
    }

    private fun processParagraph(str: String?): ArrayList<Word> {
        val map: HashMap<String, Int> = hashMapOf()
        str?.replace(",", " ")
            ?.replace(".", " ")
            ?.toLowerCase()
            ?.trim()
            ?.split("\\s+".toRegex())
            ?.forEach {
                val count = map[it.trim()]?.plus(1) ?: 1
                map[it.trim()] = count
            }

        val list: ArrayList<Word> = arrayListOf()
        map.entries.forEach {
            val word = Word(it.key, it.value)
            list.add(word)
        }
        Collections.sort(list, WordComparator())
        Log.d("list:",list.toString())
        return list
    }
}
