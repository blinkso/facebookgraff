package ua.blink.facebookgraff.util

enum class ImageType(val type: String, val extension: String) {
    JPEG(type = "image/jpeg", extension = "jpg"),
    PNG(type = "image/png", extension = "png"),
    GIF(type = "image/gif", extension = "gif"),
    UNKNOWN(type = "image/unknown", extension = "jpg");

    companion object {
        fun fromByteArray(photo: ByteArray): ImageType {
            return when {
                photo.size >= 3 && photo[0] == 0xFF.toByte() && photo[1] == 0xD8.toByte() && photo[2] == 0xFF.toByte() -> JPEG
                photo.size >= 8 && photo[0] == 0x89.toByte() && photo[1] == 'P'.code.toByte() && photo[2] == 'N'.code.toByte() && photo[3] == 'G'.code.toByte() -> PNG
                photo.size >= 6 && photo[0] == 'G'.code.toByte() && photo[1] == 'I'.code.toByte() && photo[2] == 'F'.code.toByte() -> GIF
                else -> UNKNOWN
            }
        }
    }
}