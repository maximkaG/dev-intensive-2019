package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName :String?): Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")
        val firstName: String?
        val lastName: String?
        if((parts?.getOrNull(0) == "" && parts.getOrNull(1) == "")|| parts?.getOrNull(0) == null) {
            firstName = null
            lastName = null
        }
        else if (parts.getOrNull(0) == "" && parts.getOrNull(1) != "") {
            if (parts.getOrNull(2) == "") {
                firstName = parts.getOrNull(1)
                lastName = null
            } else {
                firstName = parts.getOrNull(1)
                lastName = parts.getOrNull(2)
            }

        }
        else if (parts.getOrNull(1) == "") {
            firstName = parts.getOrNull(0)
            lastName = null
        }
        else {
            firstName = parts.getOrNull(0)
            lastName = parts.getOrNull(1)
        }
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " ") =
        payload.replace(Regex("[абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЭЮЯ ]")) {
        when (it.value) {
            "а" -> "a"
            "А" -> "A"
            "б" -> "b"
            "Б" -> "B"
            "в" -> "v"
            "В" -> "V"
            "г" -> "g"
            "Г" -> "G"
            "д" -> "d"
            "Д" -> "D"
            "е", "ё", "э" -> "e"
            "Е", "Ё", "Э" -> "E"
            "ж" -> "zh"
            "Ж" -> "Zh"
            "з" -> "z"
            "З" -> "Z"
            "и", "й", "ы" -> "i"
            "И", "Й" -> "I"
            "к" -> "k"
            "К" -> "K"
            "л" -> "l"
            "Л" -> "L"
            "м" -> "m"
            "М" -> "M"
            "н" -> "n"
            "Н" -> "N"
            "о" -> "o"
            "О" -> "O"
            "п" -> "p"
            "П" -> "P"
            "р" ->"r"
            "Р" -> "R"
            "с" -> "s"
            "С" -> "S"
            "т" -> "t"
            "Т" -> "T"
            "у" -> "u"
            "У" -> "U"
            "ф" -> "f"
            "Ф" -> "F"
            "х" -> "h"
            "Х" ->"X"
            "ц" -> "c"
            "Ц" -> "C"
            "ч" -> "ch"
            "Ч" -> "Ch"
            "ш", "щ" -> "sh"
            "Ш", "Щ" -> "Sh"
            "ъ", "ь" -> ""
            "ю" -> "yu"
            "Ю" -> "Yu"
            "я" -> "ya"
            "Я" -> "Ya"
            " " -> divider
            else -> it.value
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val initials = when (firstName) {
            "", " ", null -> null
            else -> "${isTitle(firstName.get(0))}${if (lastName?.getOrNull(0) != null) isTitle(lastName.get(0)) else "" }"
        }

        return initials
    }

    fun isTitle(char: Char) = if (char.isLowerCase()) char.toUpperCase() else  char
}