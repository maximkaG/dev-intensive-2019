package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when(question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION ->Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String): Pair<String, Triple<Int, Int, Int>> {
        return if(!question.checkAnswer(answer)) {
            when(question) {
                Question.NAME -> " Имя должно начинаться с заглавной буквы\n" +
                        question.question to status.color
                Question.PROFESSION -> "Профессия должна начинаться со строчной буквы\n" +
                        question.question to status.color
                Question.MATERIAL -> "Материал не должен содержать цифр\n" +
                        question.question to status.color
                Question.BDAY -> "Год моего рождения должен содержать только цифры\n" +
                        question.question to status.color
                Question.SERIAL -> "Серийный номер содержит только цифры, и их 7\n" +
                        question.question to status.color
                Question.IDLE -> "На этом все, вопросов больше нет\n" +
                        question.question to status.color
            }
        } else if (question == Question.IDLE) {
            "На этом все, вопросов больше нет" to status.color
        }
        else if(question.answers.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            "Отлично - это правильный ответ!\n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            "Это не правильный ответ!\n${question.question}" to status.color
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));


        fun nextStatus():Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("Бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION

            override fun checkAnswer (answer: String): Boolean = answer[0].isUpperCase()
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL

            override fun checkAnswer (answer: String): Boolean = answer[0].isLowerCase()
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY

            override fun checkAnswer (answer: String): Boolean {
               var check: Boolean = true
               for(char in answer) {
                    if (char >= '0' && char <= '9')
                        check = false
                    }
                return check
            }
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL

            override fun checkAnswer (answer: String): Boolean {
                var check: Boolean = true
                for(char in answer) {
                    if ( !(char >= '0' && char <= '9') )
                        check = false
                }
                return check
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE

            override fun checkAnswer (answer: String): Boolean {
                var check: Boolean = true
                var countChar: Int = 0
                for(char in answer) {
                    if( !(char == ' ') )
                        countChar++
                    if ( !(char >= '0' && char <= '9'))
                        check = false
                }
                if(countChar != 7) {
                    check = false
                }
                return check
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE

            override fun checkAnswer (answer: String): Boolean {
                return true
            }
        };
        abstract fun nextQuestion (): Question

        abstract fun checkAnswer (answer: String): Boolean

    }
}