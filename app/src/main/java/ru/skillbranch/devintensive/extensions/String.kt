package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String {
    val startString = this
    var finalString: String = ""
    var i: Int = 0
    var notSpace: Int = 0
    for (symbol in startString) {
        if(i < count) {
            finalString += symbol
        } else if(symbol != ' ') {
            notSpace++
        }
        i++
    }
    if (notSpace != 0 && finalString[finalString.length - 1] == ' ') {
        finalString = finalString.substringBeforeLast(' ') + "..."
    } else if (notSpace != 0){
        finalString += "..."
    }
    return finalString
}

fun String.stripHtml(): String {
    val startString = this
    var clearString: String = ""
    var htmlSymbols: Int = 0
    var space: Boolean = false
    for (symbol in startString) {
        if (symbol == '>' || symbol == '<') {
            htmlSymbols++
        } else if (htmlSymbols%2 == 0 && symbol == ' ') {
            space = true
        } else if (htmlSymbols%2 == 0 && space) {
            clearString +=" $symbol"
            space = false
        } else if (htmlSymbols%2 == 0){
            clearString += symbol
        }
    }
    return clearString
}