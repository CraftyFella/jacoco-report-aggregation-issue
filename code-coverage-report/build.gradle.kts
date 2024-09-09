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
            testType = TestSuiteType.UNIT_TEST
        }
    }
}

tasks.check {
    dependsOn(tasks.named<JacocoReport>("testCodeCoverageReport")) // <.>
}
