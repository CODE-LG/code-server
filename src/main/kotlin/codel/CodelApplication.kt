package codel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CodelApplication

fun main(args: Array<String>) {
    runApplication<CodelApplication>(*args)
}
