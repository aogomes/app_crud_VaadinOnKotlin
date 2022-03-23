import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    id("org.gretty") version "3.0.6"
    id("com.vaadin") version "0.14.7.3"
    war
}

val karibudsl_version = "1.1.1"
val vaadin_version = "14.7.4"

defaultTasks("clean", "build")

repositories {
    mavenCentral()
    maven { setUrl("https://maven.vaadin.com/vaadin-addons") }
}

gretty {
    contextPath = "/"
    servletContainer = "jetty9.4"
    // managedClassReload = true // temporarily disabled because of https://github.com/gretty-gradle-plugin/gretty/issues/166
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
    }
}

val staging by configurations.creating

dependencies {
    // Karibu-DSL dependency
    implementation("com.github.mvysny.karibudsl:karibu-dsl:$karibudsl_version")
    implementation("com.github.mvysny.karibu-tools:karibu-tools:0.10")

    // Vaadin 14
    implementation("com.vaadin:vaadin-core:${vaadin_version}") {
        // Webjars are only needed when running in Vaadin 13 compatibility mode
        listOf("com.vaadin.webjar", "org.webjars.bowergithub.insites",
                "org.webjars.bowergithub.polymer", "org.webjars.bowergithub.polymerelements",
                "org.webjars.bowergithub.vaadin", "org.webjars.bowergithub.webcomponents")
                .forEach { exclude(group = it) }
    }
    providedCompile("javax.servlet:javax.servlet-api:3.1.0")

    // logging
    // currently we are logging through the SLF4J API to SLF4J-Simple. See src/main/resources/simplelogger.properties file for the logger configuration
    implementation("org.slf4j:slf4j-simple:1.7.32")

    implementation(kotlin("stdlib-jdk8"))

    // test support
    testImplementation("com.github.mvysny.kaributesting:karibu-testing-v10:1.3.11")
    testImplementation("com.github.mvysny.dynatest:dynatest:0.24")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

vaadin {
    if (gradle.startParameter.taskNames.contains("stage")) {
        productionMode = true
    }
    pnpmEnable = false
}
