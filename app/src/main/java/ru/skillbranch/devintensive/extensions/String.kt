package ru.skillbranch.devintensive.extensions


fun String.truncate(length: Int = 16): String {
    val len = this.length
    when (len > length){
        true -> return this.substring(0, length) + "..."
        else -> return this
    }
}

fun String.stripHtml():String{
    var tempResult = this + " "
    while (true){
        val arrayChar = tempResult.toCharArray()
        var start = -1
        var stop = -1

        for (it in 0..arrayChar.size-1)
        {
            if (arrayChar[it] == '<')
                start = it
            else if (arrayChar[it] == '>' && start != -1){
                stop = it
                //temp = tempResult.substring(start, stop+1)
                break
            }
        }
        if (stop!=-1)
            tempResult = tempResult.removeRange(start, stop+1)
        else break
    }
    val regex = "\\s{1,}".toRegex()
    val text = regex.replace(tempResult, " ")
    return text.trim()
}