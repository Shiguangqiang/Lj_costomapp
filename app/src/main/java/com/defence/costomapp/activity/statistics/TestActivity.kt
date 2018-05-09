package com.defence.costomapp.activity.statistics

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by author Sgq
 * on 2018/5/4.
 */
class TestActivity : AppCompatActivity() {

    var flag: Boolean? = false


    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            return obj.length
        }
        return null
    }

    fun main(args: Array<String>) {
        fun printLength(obj: Any) {
            println("'$obj' string length is ${getStringLength(obj) ?: "...err,not a string"}")

        }
    }


}