package oystudy.clonecoding

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class ClonecodingApplication

fun main(args: Array<String>) {
	runApplication<ClonecodingApplication>(*args)
}
