package ua.blink.facebookgraff.util

import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import java.nio.charset.StandardCharsets
import java.util.*


class UTF8Control : ResourceBundle.Control() {

    @Throws(IllegalAccessException::class, InstantiationException::class, IOException::class)
    override fun newBundle(
        baseName: String?,
        locale: Locale?,
        format: String?,
        loader: ClassLoader,
        reload: Boolean
    ): ResourceBundle? {
        // The below is a copy of the default implementation.
        val bundleName: String = toBundleName(baseName, locale)
        val resourceName: String = toResourceName(bundleName, "properties")
        var bundle: ResourceBundle? = null
        var stream: InputStream? = null
        if (reload) {
            val url: URL? = loader.getResource(resourceName)
            if (url != null) {
                val connection: URLConnection = url.openConnection()
                connection.useCaches = false
                stream = connection.getInputStream()
            }
        } else {
            stream = loader.getResourceAsStream(resourceName)
        }
        if (stream != null) {
            try {
                // Only this line is changed to make it to read properties files as UTF-8.
                bundle = PropertyResourceBundle(InputStreamReader(stream, StandardCharsets.UTF_8))
            } finally {
                stream.close()
            }
        }
        return bundle
    }
}