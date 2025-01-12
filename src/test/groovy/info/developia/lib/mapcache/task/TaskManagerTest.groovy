package info.developia.lib.mapcache.task

import spock.lang.Ignore
import spock.lang.Specification

import java.time.Duration

@Ignore
class TaskManagerTest extends Specification {
    def "Should task"() {
        given:
        TaskManager taskManager = TaskManager.getInstance()
        when:
        taskManager.schedule(this::print, Duration.ofSeconds(1))
        then:
        def result = true
        while (result) sleep 1000
    }

    private static print() {
        println "Hello World"
    }
}
