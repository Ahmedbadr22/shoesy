package com.ab.core.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File

 fun compressBitmap(photoFile: File): Bitmap? {
    val maxFileSizeBytes = 200 * 1024 // 500 KB
    var quality = 100 // Start with maximum quality

    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(photoFile.absolutePath, options)

    // Calculate the initial quality based on the file size
    val fileSizeBytes = photoFile.length()
    if (fileSizeBytes > maxFileSizeBytes) {
        quality = ((maxFileSizeBytes * 100) / fileSizeBytes).toInt()
    }

    var compressedBitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
    val outputStream = ByteArrayOutputStream()

    do {
        outputStream.reset()
        compressedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        quality -= 5 // Reduce quality by 5 each time

        if (quality <= 0) {
            break
        }
    } while (outputStream.toByteArray().size > maxFileSizeBytes)

    return BitmapFactory.decodeStream(ByteArrayInputStream(outputStream.toByteArray()), null, null)
}