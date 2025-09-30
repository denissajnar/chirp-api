package dev.denissajnar.chirp

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<ChirpApplication>().with(TestcontainersConfiguration::class).run(*args)
}
