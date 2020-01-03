package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.models.User

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return Pair(firstName, lastName)
        //return firstName to lastName
    }

    fun transliteration(payload: String, devider: String = " "): String {
        val array = payload.toCharArray()
        val iterator = (1..array.size).iterator()
        var result = ""
        iterator.forEach {
            when (array[it-1].toLowerCase()) {
                'а' ->{result += if (array[it-1].isUpperCase()){"A"} else {"a"}}
                'б' ->{result += if (array[it-1].isUpperCase()){"B"} else {"b"}}
                'в' ->{result += if (array[it-1].isUpperCase()){"V"} else {"v"}}
                'г' ->{result += if (array[it-1].isUpperCase()){"G"} else {"g"}}
                'д' ->{result += if (array[it-1].isUpperCase()){"D"} else {"d"}}
                'е' ->{result += if (array[it-1].isUpperCase()){"E"} else {"e"}}
                'ё' ->{result += if (array[it-1].isUpperCase()){"E"} else {"e"}}
                'ж' ->{result += if (array[it-1].isUpperCase()){"Zh"} else {"zh"}}
                'з' ->{result += if (array[it-1].isUpperCase()){"Z"} else {"z"}}
                'и' ->{result += if (array[it-1].isUpperCase()){"I"} else {"i"}}
                'й' ->{result += if (array[it-1].isUpperCase()){"I"} else {"i"}}
                'к' ->{result += if (array[it-1].isUpperCase()){"K"} else {"k"}}
                'л' ->{result += if (array[it-1].isUpperCase()){"L"} else {"l"}}
                'м' ->{result += if (array[it-1].isUpperCase()){"M"} else {"m"}}
                'н' ->{result += if (array[it-1].isUpperCase()){"N"} else {"n"}}
                'о' ->{result += if (array[it-1].isUpperCase()){"O"} else {"o"}}
                'п' ->{result += if (array[it-1].isUpperCase()){"P"} else {"p"}}
                'р' ->{result += if (array[it-1].isUpperCase()){"R"} else {"r"}}
                'с' ->{result += if (array[it-1].isUpperCase()){"S"} else {"s"}}
                'т' ->{result += if (array[it-1].isUpperCase()){"T"} else {"t"}}
                'у' ->{result += if (array[it-1].isUpperCase()){"U"} else {"u"}}
                'ф' ->{result += if (array[it-1].isUpperCase()){"F"} else {"f"}}
                'х' ->{result += if (array[it-1].isUpperCase()){"H"} else {"h"}}
                'ц' ->{result += if (array[it-1].isUpperCase()){"C"} else {"c"}}
                'ч' ->{result += if (array[it-1].isUpperCase()){"Ch"} else {"ch"}}
                'ш' ->{result += if (array[it-1].isUpperCase()){"Sh"} else {"sh"}}
                'щ' ->{result += if (array[it-1].isUpperCase()){"Sh'"} else {"sh'"}}
                'ъ' ->{result += if (array[it-1].isUpperCase()){""} else {""}}
                'ы' ->{result += if (array[it-1].isUpperCase()){"I"} else {"i"}}
                'ь' ->{result += if (array[it-1].isUpperCase()){""} else {""}}
                'э' ->{result += if (array[it-1].isUpperCase()){"E"} else {"e"}}
                'ю' ->{result += if (array[it-1].isUpperCase()){"Yu"} else {"yu"}}
                'я' ->{result += if (array[it-1].isUpperCase()){"Ya"} else {"ya"}}
                else -> result += devider
            }
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if ((firstName == "" || firstName == " ") && (lastName == "" || lastName == " "))
            return null
        val firstNameSymbol = if (firstName?.toCharArray()?.get(0) == null) "" else firstName?.toCharArray()?.get(0).toUpperCase()
        val lastNameSymbol = if (lastName?.toCharArray()?.get(0) == null) "" else lastName?.toCharArray()?.get(0).toUpperCase()

        if (firstName == lastName && (firstName == null || firstName == ""))
            return null
        return "${firstNameSymbol}${lastNameSymbol}"
    }

}