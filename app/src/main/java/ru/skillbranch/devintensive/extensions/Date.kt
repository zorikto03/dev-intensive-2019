package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECONDS = 1000L
const val MINUTE = 60 * SECONDS
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECONDS
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time

    return this
}

fun Date.humanizeDiff(date: Date = Date()): String{
    date.time = date.time.minus(this.time)
    var result = ""

    if (date.time < -HOUR * 26 && date.time > -DAY * 360){
        val nDays = Math.abs(Math.round((date.time.toDouble()/DAY)).toInt())
        result = "через ${inclination(nDays, TimeUnits.DAY)}"
    }
    else if (date.time < -DAY * 360){
        result = "более чем через год"
    }

    else if (date.time > HOUR * 26 && date.time < DAY * 360){
        val nDays = Math.abs(Math.round((date.time.toDouble()/DAY)).toInt())
        result = "${inclination(nDays, TimeUnits.DAY)} назад"
    }
    else if (date.time > DAY * 360){
        result = "более года назад"
    }

    else if (date.time > HOUR * 22 && date.time < HOUR * 26){
        result = "день назад"
    }
    else if (date.time < -HOUR * 22 && date.time > -HOUR * 26){
        result = "через день"
    }

    else if (date.time < HOUR * 22 && date.time > MINUTE * 75){
        val nHours = (date.time/HOUR).toInt()
        result = "${inclination(nHours, TimeUnits.HOUR)} назад"
    }
    else if (date.time > -HOUR * 22 && date.time < -MINUTE * 75){
        val nHours = Math.abs(Math.round(date.time.toDouble()/HOUR).toInt())
        result = "через ${inclination(nHours, TimeUnits.HOUR)}"
    }

    else if (date.time < MINUTE * 75 && date.time > MINUTE * 45){
        result = "час назад"
    }
    else if (date.time < -MINUTE * 75 && date.time < -MINUTE * 45){
        result = "через час"
    }

    else if (date.time < MINUTE * 45 && date.time > SECONDS * 75){
        val nMinuts = (date.time / MINUTE).toInt()
        result = "${inclination(nMinuts, TimeUnits.MINUTE)} назад"
    }
    else if (date.time > -MINUTE * 45 && date.time < -SECONDS * 75){
        val nMinuts = Math.abs(Math.round(date.time.toDouble() / MINUTE).toInt())
        result = "через ${inclination(nMinuts, TimeUnits.MINUTE)}"
    }
    else if (date.time > SECONDS * 45 && date.time < SECONDS * 75){
        result = "минуту назад"
    }
    else if (date.time < -SECONDS * 45 && date.time > -SECONDS * 75){
        result = "через минуту"
    }
    else {
        val seconds = Math.round((date.time.toDouble() / SECONDS)).toInt()
        if (seconds > 0){
            if (date.time < SECONDS * 45 && seconds > 1){
                result = "несколько секунд назад"
            }
            else {
                result = "только что"
            }
        }
        else{
            if (date.time > -SECONDS * 45 && seconds < -1){
                result = "через несколько секунд"
            }
            else {
                result = "только что"
            }
        }

    }

    return result
}

fun inclination(number: Int, Times: TimeUnits): String{
    val lastNumber = number % 10

    when (Times){
        TimeUnits.DAY -> {
            when (lastNumber){
                1 -> return "${number} день"
                in 2..4 -> return "${number} дня"
                in 5..9, 0 -> return "${number} дней"
            }
        }
        TimeUnits.HOUR ->{
            when (lastNumber){
                1 -> return "${number} час"
                in 2..4 -> return "${number} часа"
                in 5..9, 0 -> return "${number} часов"
            }
        }
        TimeUnits.MINUTE ->{
            when (lastNumber){
                1 -> return "${number} минуту"
                in 2..4 -> return "${number} минуты"
                in 5..9, 0 -> return "${number} минут"
            }
        }
        TimeUnits.SECOND ->{
            when (lastNumber){
                1 -> return "${number} секунду"
                in 2..4 -> return "${number} секунды"
                in 5..9, 0 -> return "${number} секунд"
            }
        }
    }
    return ""
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
