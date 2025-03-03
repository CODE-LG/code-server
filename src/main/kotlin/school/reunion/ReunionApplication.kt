package school.reunion

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReunionApplication

fun main(args: Array<String>) {
	runApplication<ReunionApplication>(*args)
}
