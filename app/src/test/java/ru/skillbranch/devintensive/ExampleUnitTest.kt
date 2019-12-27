package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun TestInstance() {
        val user = User("1")
        val user1 = User("2", "John", "Cena")
        val user2 = User("2","John", "SilverHand", null, lastVisit = Date(), IsOnline = true)

        //user.printMe()
        //user1.printMe()
        //user2.printMe()

        println("$user")
    }

    @Test
    fun TestFactory(){
        //val user = User.Factory.makeUser("John Cena")
        //val user1 = User.Factory.makeUser("John Wick")
        val user1 = User.Factory.makeUser("John SilverHand")
        val user2 = user1.copy(id = "2", lastName = "Cena")
        print("$user2 \n$user1")
    }
    @Test
    fun testDecomposition(){
        val user = User.makeUser("John Cena")

        fun getUserInfo() = user

        val (id, firstName, lastName) = getUserInfo()

        println("$id , $firstName , $lastName")
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")
    }

    @Test
    fun testCopy(){
        val user = User.makeUser("John Cena")
        val user1 = user.copy(lastVisit = Date().add(-1, Timeunits.SECOND))
        val user2 = user.copy(lastName= "Doe" , lastVisit = Date().add(2, Timeunits.HOUR))

        println("""
            ${user.lastVisit?.format()}
            ${user1.lastVisit?.format()}
            ${user1.lastVisit?.humanizeDiff()}
            ${user2.lastVisit?.format("dd.MM.yy")}
        """.trimIndent())
    }

    @Test
    fun testUtils(){
        println("""
            ${Utils.parrseFullName("Зорикто Содномович")}
            ${Utils.toInitials("зорикто", null)}
            ${Utils.toInitials(null,"Содномович")}
            ${Utils.toInitials(null, null)}
            ${Utils.toInitials(" ", "")}
            ${Utils.transliteration("Зорикто Содномович", "_")}
        """.trimIndent())
    }

    @Test
    fun testTruncate(){
        println("""
            ${"Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate()}
            ${"Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(10)}
            ${"Bender Bending".truncate()}
        """.trimIndent())
    }

    @Test
    fun testStripHtml(){
        println("""
            ${"<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml()}
            ${"<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml()}
        """.trimIndent())
    }

    @Test
    fun testDataMaping(){
        val user = User.Factory.makeUser("Зорикто Базаров")
        val user1 = user.copy(lastVisit = Date().add(-7, Timeunits.SECOND))
        println(user)
        println(user1)
        val userView = user1.toUserView()
        userView.printMe()
    }
    @Test
    fun testAbstractFactory(){
        val user = User.Factory.makeUser("Зорикто Базаров")

        val txtMessage = BaseMessage.makeMessage(user, Chat("0"),date = Date().add(-15, Timeunits.SECOND), payload = "any text Message", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image")

        println("""
            ${txtMessage.FormatMessage()}
            ${imageMessage.FormatMessage()}
        """.trimIndent())
    }
}
