package com.attendify_admin.common.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object FileUtil {

    fun getFileNameFromUri(context: Context, uri: Uri): String? {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val displayNameColumnIndex = it.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)
                if (displayNameColumnIndex != -1) {
                    return it.getString(displayNameColumnIndex)
                }
            }
        }
        return null
    }

    fun getFileFromUri(context: Context, uri: Uri): File? {
        val inputStream: InputStream = context.contentResolver.openInputStream(uri) ?: return null

        val mimeType = context.contentResolver.getType(uri)

        val extension = when (mimeType) {
            "image/jpeg" -> ".jpg"
            "image/png" -> ".png"
            "image/gif" -> ".gif"
            "image/webp" -> ".webp"
            else -> ".jpg"
        }

        val fileName = "student_image_${System.currentTimeMillis()}"

        val tempFile = File.createTempFile(fileName, extension, context.cacheDir)

        try {
            val outputStream = FileOutputStream(tempFile)
            val buffer = ByteArray(4096) //4kb
            var bytesRead: Int

            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }

            outputStream.close()
            inputStream.close()

            return tempFile
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}