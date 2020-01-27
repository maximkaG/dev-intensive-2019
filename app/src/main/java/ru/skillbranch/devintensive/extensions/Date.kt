package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits{
    SECOND {
        override fun plural(value:Int): String = "$value ${if( value%10 == 1 && value != 11) "секунду" 
        else if((value%10 >= 2 && value%10 <= 4) && !(value%100 >= 11 && value%100 <=14) ) "секунды" else "секунд"}"
    },
    MINUTE {
        override fun plural(value:Int): String = "$value ${if( value%10 == 1 && value != 11) "минуту"
        else if((value%10 >= 2 && value%10 <= 4) && !(value%100 >= 11 && value%100 <=14) ) "минуты" else "минут"}"
    },
    HOUR {
        override fun plural(value:Int): String = "$value ${if( value%10 == 1 && value != 11) "час"
        else if((value%10 >= 2 && value%10 <= 4) && !(value%100 >= 11 && value%100 <=14) ) "часа" else "часов"}"
    },
    DAY {
        override fun plural(value:Int): String = "$value ${if( value%10 == 1 && value != 11) "день"
        else if((value%10 >= 2 && value%10 <= 4) && !(value%100 >= 11 && value%100 <= 14) ) "дня" else "дней"}"
    };

    abstract fun plural(value:Int): String
}

    fun Date.humanizeDiff(date: Date = Date()) : String {
        val positive: Boolean = if(date.time - this.time > 0) true else false
        val time = if(positive) (date.time - this.time) else (this.time - date.time + 20)
        val text: String
        if(time < 1) {
            text = "только что"
        } else if (time >= 1 *SECOND && time < 45 * SECOND) {
            text = if(positive) "несколько секунд назад" else "через несколько секунд"
        } else if (time >= 45 * SECOND && time < 75 * SECOND) {
            text = if(positive) "минуту назад" else "через минуту"
        } else if (time >= 75 * SECOND && time < 45 * MINUTE) {
            text = if(positive) "${TimeUnits.MINUTE.plural((time/ MINUTE).toInt())} назад"
            else "через ${TimeUnits.MINUTE.plural((time/ MINUTE).toInt())}"
        } else if (time >= 45 * MINUTE && time < 75 * MINUTE) {
            text = if(positive) "час назад" else "через час"
        } else if (time >= 75 * MINUTE && time < 22 * HOUR) {
            text = if(positive) "${TimeUnits.HOUR.plural((time/HOUR).toInt())} назад"
            else "через ${TimeUnits.HOUR.plural((time/HOUR).toInt())} "
        } else if (time >= 22 * HOUR && time < 26 * HOUR) {
            text = if(positive) "день назад" else "через день"
        } else if (time >= 26 * HOUR && time < 360 * DAY) {
            text = if(positive) "${TimeUnits.DAY.plural((time/ DAY).toInt())} назад"
            else "через ${TimeUnits.DAY.plural((time/ DAY).toInt())}"
        } else {
            text = if(positive) "более года назад" else "более чем через год"
        }

        return text
    }