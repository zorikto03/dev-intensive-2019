package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECONDS = 1000L
const val MINUTE = 60 * SECONDS
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String = "HH:mm:ss, dd.MM.yy"): String{
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

    if (date.time > DAY){
        if (date.time > HOUR * 26 && date.time < DAY * 360){
            var nDays = (date.time/DAY).toInt()
            result = "${nDays} дней назад"
        }
        else {
            result = "более года назад"
        }
    }
    else if(Math.abs(date.time) > DAY){
        if (date.time < -HOUR * 26 && date.time > -DAY * 360){
            var nDays = Math.abs(Math.round((date.time.toDouble()/DAY)).toInt())
            result = "через ${nDays} дней"
        }
        else {
            result = "более чем через год"
        }
    }
    else if (date.time > HOUR * 22 && date.time < HOUR * 26){
        result = "день назад"
    }
    else if (date.time < -HOUR * 22 && date.time > -HOUR * 26){
        result = "через день"
    }
    else if (date.time < HOUR * 22 && date.time > MINUTE * 75){
        var nHours = (date.time/HOUR).toInt()
        result = "${nHours} часов назад"
    }
    else if (date.time > -HOUR * 22 && date.time < -MINUTE * 75){
        var nHours = Math.abs(Math.round(date.time.toDouble()/HOUR).toInt())
        result = "через ${nHours} часов"
    }
    else if (date.time < MINUTE * 75 && date.time > MINUTE * 45){
        result = "час назад"
    }
    else if (date.time < -MINUTE * 75 && date.time < -MINUTE * 45){
        result = "через час"
    }
    else if (date.time < MINUTE * 45 && date.time > SECONDS * 75){
        var nMinuts = (date.time / MINUTE).toInt()
        result = "${nMinuts} минут назад"
    }
    else if (date.time > -MINUTE * 45 && date.time < -SECONDS * 75){
        var nMinuts = Math.abs(Math.round(date.time.toDouble() / MINUTE).toInt())
        result = "через ${nMinuts} минут"
    }
    else if (date.time > SECONDS * 45 && date.time < SECONDS * 75){
        result = "минуту назад"
    }
    else if (date.time < -SECONDS * 45 && date.time > -SECONDS * 75){
        result = "через минуту"
    }
    else {
        var seconds = Math.round((date.time.toDouble() / SECONDS)).toInt()
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

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
