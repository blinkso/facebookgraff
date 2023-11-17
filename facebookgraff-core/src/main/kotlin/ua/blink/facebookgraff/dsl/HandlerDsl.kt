package ua.blink.facebookgraff.dsl

import org.springframework.context.support.GenericApplicationContext
import ua.blink.facebookgraff.dto.Photo
import ua.blink.facebookgraff.dto.request.SendRequest
import ua.blink.facebookgraff.exception.HandlerException

fun handler(vararg commands: String, init: HandlerDsl.() -> Unit): HandlerDslWrapper {
    return { context ->
        val dsl = HandlerDsl(commands.asList(), context)
        init(dsl) // handler.init()

        dsl.build()
    }
}

class HandlerDsl(private val commands: List<String>, val context: GenericApplicationContext) {

    private val stepDsls: MutableList<StepDsl<*>> = arrayListOf()
    private var process: ProcessBlock? = null


    fun <T : Any> step(key: String, init: StepDsl<T>.() -> Unit): StepDsl<T> {
        val dsl = StepDsl<T>(key)
        init(dsl) // step.init()

        stepDsls.add(dsl)

        return dsl
    }

    fun process(processor: ProcessBlock) {
        this.process = processor
    }

    internal fun build(): Handler {
        val steps = arrayListOf<Step<*>>()

        for (i in 0 until stepDsls.size) {
            val builder = stepDsls[i]
            val step = builder.build {
                if (i + 1 < stepDsls.size) {
                    stepDsls[i + 1].key
                } else {
                    null
                }
            }
            steps.add(step)
        }


        return Handler(
            commands,
            steps.associateBy { it.key },
            stepDsls.firstOrNull()?.key,
            process ?: throw HandlerException("Process block must not be null!")
        )
    }

    inline fun <reified T> getBean(): T {
        return context.getBean(T::class.java)
    }

    fun readClasspathResource(path: String): ByteArray {
        return javaClass.classLoader.getResourceAsStream(path).use {
            it.readBytes()
        }
    }
}

class StepDsl<T : Any>(val key: String) {

    private var question: QuestionBlock? = null

    private var validation: ValidationBlock<T>? = null
    private var next: NextStepBlock? = null


    fun question(question: QuestionBlock) {
        this.question = question
    }

    fun validation(validation: ValidationBlock<T>) {
        this.validation = validation
    }

    fun next(next: NextStepBlock) {
        this.next = next
    }

    internal fun build(defaultNext: NextStepBlock): Step<T> {
        return Step(
            key,
            question ?: throw HandlerException("Step question must not be null!"),
            validation ?: { _, answer, _ ->
                @Suppress("UNCHECKED_CAST")
                answer as T
            },
            next ?: defaultNext
        )
    }

}

typealias ProcessBlock = suspend (state: HandlerState, answers: Map<String, Any>) -> SendRequest?
typealias QuestionBlock = suspend (HandlerState) -> SendRequest
typealias ValidationBlock<T> = suspend (state: HandlerState, answer: String, photo: List<Photo>?) -> T
typealias NextStepBlock = suspend (HandlerState) -> String?
typealias HandlerDslWrapper = suspend (GenericApplicationContext) -> Handler