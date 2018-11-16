package com.buddha.a3frameslabchallengeapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Word(val word: String, val count: Int) : Parcelable
