plugins {
    base
    id("jacoco-report-aggregation")
}

repositories {
    mavenCentral()
}

dependencies {
    jacocoAggregation(project(":application")) // <.>
}

reporting {
    reports {
        val testCodeCoverageReport by creating(JacocoCoverageReport::class) { // <.>
            testType.set(TestSuiteType.UNIT_TEST)
            reportTask {
                executionData.setFrom(project.fileTree("dir" to rootDir, "include" to "**/build/jacoco/*.exec")) // <.>
            }
        }
    }
}

tasks.check {
    dependsOn(tasks.named<JacocoReport>("testCodeCoverageReport")) // <.>
}
