package de.jgroeneveld.koma

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KomaApplication

fun main(args: Array<String>) {
    runApplication<KomaApplication>(*args)
}

