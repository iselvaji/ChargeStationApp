package com.task.chargestationapp.util

object AppUtils {

    fun validateData(input: String?) : String {
        input?.let {
            if(it.isBlank() || it.isEmpty() || it == "null") {
                return ""
            }
        }
        return input?: ""
    }
}