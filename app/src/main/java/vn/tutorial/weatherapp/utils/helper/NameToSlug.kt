package vn.tutorial.weatherapp.utils.helper

import java.text.Normalizer
import java.util.regex.Pattern

fun toSlug(name: String): String {
    val normalized = Normalizer.normalize(name, Normalizer.Form.NFD)
    val noAccents = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        .matcher(normalized)
        .replaceAll("")
        .replace("Đ", "D")
        .replace("đ", "d")
    return noAccents.lowercase()
}