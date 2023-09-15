package top.pptptbbbd.playground.utils

import android.content.ContentValues
import android.content.Context
import android.content.res.AssetManager
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

object AssetsUtils {
    fun getAssetsList(context: Context, path: String): MutableList<String> {
        val manager: AssetManager = context.assets
        val fileNames: MutableList<String> = ArrayList<String>()
        try {
            fileNames.addAll(manager.list(path) as Array<out String>)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return fileNames
    }

    fun saveAssetsFile(context: Context, file: String,path:String) {
        val outFile = File(Environment.getExternalStorageDirectory(), path)
        val ipsteam: InputStream = context.assets.open(file)
        val fos = FileOutputStream(outFile)
        val buffer = ByteArray(1024)
        var byteCount: Int

        while (ipsteam.read(buffer).also { byteCount = it } != -1) {
            fos.write(buffer, 0, byteCount)
        }
        fos.flush()
        ipsteam.close()
        fos.close()
    }

    fun saveAssetsFile(context: Context, file: String,name:String,uri: Uri) {
        val ipsteam: InputStream = context.assets.open(file)
        val buffer = ByteArray(1024)
        var byteCount: Int
        val values = ContentValues()
        values.put(MediaStore.Downloads.DISPLAY_NAME,name)
        val mResolver = context.getContentResolver();
        val insertUri = mResolver.insert(uri,values)
        insertUri?.let {
            mResolver.openOutputStream(it).use {outputStream->
                while (ipsteam.read(buffer).also { byteCount = it } != -1) {
                    outputStream?.write(buffer, 0, byteCount)
                }
            }
        }
        ipsteam.close()
    }
}