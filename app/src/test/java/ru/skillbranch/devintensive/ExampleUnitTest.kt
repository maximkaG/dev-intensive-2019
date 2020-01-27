package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

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
    fun test_instance() {
        val user = User("1")
        val user2 = User("2", "Jhon", "Sean")
    }

    @Test
    fun test_factory() {
        val user = User.makeUser(" Jhon Sean")
        //val user2 = user.copy(id = "2", lastName = "Sena", lastVisit = Date())
        //print("$user \n$user2")
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("Jhon Wick")
        val user2 = user.copy(lastName = "Sena", lastVisit = Date().add(-2, TimeUnits.HOUR))

        println("""
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format("HH:mm")}
        """.trimIndent())
        //fun getUserInfo() = user

        //val (id, firstName, lastname) = getUserInfo()

        //println ("$id, $firstName, $lastname")
    }

    @Test
    fun test_data_maping() {
        val user = User.makeUser("Jhon Wick")
        val newUser = user.copy(lastVisit = Date().add(-7,TimeUnits.SECOND))
        println(user)

        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Jhon Wick")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", date = Date().add(-10,TimeUnits.HOUR), type = "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image")

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())
    }

    @Test
    fun test_initials() {
        println("${ Utils.toInitials("jhon", "wick")}")
    }

    @Test
    fun test_transliteration() {
        println(Utils.transliteration("Максим Гавриленко", "_"))
    }

    @Test
    fun test_plural() {
        println(TimeUnits.DAY.plural(222))
    }

    @Test
    fun test_humanizeDiff() {
        println(Date().add(400, TimeUnits.DAY).humanizeDiff())
    }

    @Test
    fun test_truncate() {
        println("А".truncate(3))
    }

    @Test
    fun test_stripHtml() {
        println("<p class=\"title\">Образовательное        IT-сообщество    Skill   Branch   </p>".stripHtml())
    }

    @Test
    fun test_builder() {
        val user = User.Builder("1", "2 ", "3", "4")

    }
}
