package ua.blink.facebookgraff.util

enum class FileType(val type: String, val extension: String) {
    PDF(type = "application/pdf", extension = "pdf"),
    DOC(type = "application/msword", extension = "doc"),
    DOCX(type = "application/vnd.openxmlformats-officedocument.wordprocessingml.document", extension = "docx"),
    XLS(type = "application/vnd.ms-excel", extension = "xls"),
    XLSX(type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", extension = "xlsx"),
    TXT(type = "text/plain", extension = "txt"),
    UNKNOWN(type = "application/unknown", extension = "txt");

    companion object {
        fun fromByteArray(file: ByteArray): FileType {
            return when {
                file.startsWith("%PDF".toByteArray()) -> PDF
                file.size >= 8 && file.startsWith("504B0304".toHexByteArray()) && file.containsSequence("word/".toByteArray()) -> DOCX
                file.size >= 8 && file.startsWith("504B0304".toHexByteArray()) && file.containsSequence("xl/".toByteArray()) -> XLSX
                file.size >= 8 && file.startsWith("D0CF11E0A1B11AE1".toHexByteArray()) && file.containsSequence("WordDocument".toByteArray()) -> DOC
                file.size >= 8 && file.startsWith("D0CF11E0A1B11AE1".toHexByteArray()) && file.containsSequence("Workbook".toByteArray()) -> XLS
                file.isPlainText() -> TXT
                else -> UNKNOWN
            }
        }

        private fun ByteArray.startsWith(signature: ByteArray): Boolean {
            return this.size >= signature.size && this.sliceArray(signature.indices).contentEquals(signature)
        }

        private fun String.toHexByteArray(): ByteArray {
            return this.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
        }

        private fun ByteArray.isPlainText(): Boolean {
            return this.all { it in 32..126 || it in byteArrayOf(0x0A, 0x0D) }
        }

        private fun ByteArray.containsSequence(sequence: ByteArray): Boolean {
            for (i in 0..this.size - sequence.size) {
                if (this.sliceArray(i until i + sequence.size).contentEquals(sequence)) {
                    return true
                }
            }
            return false
        }
    }
}