package top.takuron.basemodel.utils.interfaces

import top.takuron.basemodel.utils.SharedPreferencesUtils

interface KeyValueUtilsI {
    fun saveString(key:String,value:String)
    fun loadString(key:String,default:String):String
    fun saveInt(key:String,value:Int)
    fun loadInt(key:String,default:Int):Int
    fun saveLong(key:String,value:Long)
    fun loadLong(key:String,default:Long):Long
}

object KeyValueUtils : KeyValueUtilsI by SharedPreferencesUtils()