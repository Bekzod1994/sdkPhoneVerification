package uz.digid.myverdi.utils.extensions

import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Base64
import java.io.ByteArrayOutputStream

fun Bitmap.toBase64(): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}
fun Bitmap.rotateImage(angle: Float): Bitmap? {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return when (angle) {
        0.0f -> {
            Bitmap.createBitmap(
                this,
                this.width.times(0.05).toInt(),
                this.height.times(0.1).toInt(),
                this.width.times(0.9).toInt(),
                this.height.times(0.7).toInt(),
                matrix,
                true
            )
        }
        180.0f -> {
            Bitmap.createBitmap(
                this,
                this.width.times(0.05).toInt(),
                this.height.times(0.2).toInt(),
                this.width.times(0.9).toInt(),
                this.height.times(0.7).toInt(),
                matrix,
                true
            )
        }
        90.0f -> {
            Bitmap.createBitmap(
                this,
                this.width.times(0.1).toInt(),
                this.height.times(0.05).toInt(),
                this.width.times(0.7).toInt(),
                this.height.times(0.9).toInt(),
                matrix,
                true
            )
        }

        else -> {
            Bitmap.createBitmap(
                this,
                this.width.times(0.2).toInt(),
                this.height.times(0.05).toInt(),
                this.width.times(0.7).toInt(),
                this.height.times(0.9).toInt(),
                matrix,
                true
            )
        }
    }
}