package top.takuron.basemodel.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import top.takuron.basemodel.utils.interfaces.KeyValueUtilsI

class SharedPreferencesUtils :KeyValueUtilsI {
    companion object {
        const val SD_NAME = ""
        lateinit var application:Application

        val dataObj: SharedPreferences by lazy {
            application.getSharedPreferences(
                SD_NAME,
                Context.MODE_PRIVATE
            )
        }
    }

    override fun saveString(key: String, value: String) {
        dataObj.edit().putString(key, value).apply()
    }

    override fun loadString(key: String, default: String): String =
        dataObj.getString(key, default) ?: default

    override fun saveInt(key: String, value: Int) {
        dataObj.edit().putInt(key, value).apply()
    }

    override fun loadInt(key: String, default: Int): Int =
        dataObj.getInt(key, default) ?: default


    override fun saveLong(key: String, value: Long) {
        dataObj.edit().putLong(key, value).apply()
    }

    override fun loadLong(key: String, default: Long): Long =
        dataObj.getLong(key, default) ?: default
}