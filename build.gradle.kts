plugins {
	java
	id("org.sonarqube") version "3.5.0.2730"
	jacoco
}

repositories {
	mavenCentral()
}

subprojects {

	apply {
		plugin("java")
		plugin("jacoco")
	}

	group = "com.fiap"
	version = "0.0.1-SNAPSHOT"
	java.sourceCompatibility = JavaVersion.VERSION_19

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation("org.jacoco:org.jacoco.ant:0.8.10")

		//Test
		testImplementation(rootProject.libs.spring.boot.starter.test)
	}

	tasks.withType<Test> {
		useJUnitPlatform()
		testLogging {
			events("passed", "skipped", "failed")
		}
		finalizedBy(tasks.jacocoTestReport)
	}

	tasks.jacocoTestReport {
		dependsOn(tasks.test) // tests are required to run before generating the report
		reports{
			xml.required.set(true)
		}
	}
}

tasks.register<JacocoReport>("codeCoverageReport") {
	// If a subproject applies the 'jacoco' plugin, add the result it to the report
	subprojects {
		val subproject = this
		subproject.plugins.withType<JacocoPlugin>().configureEach {
			subproject.tasks.matching { it.extensions.findByType<JacocoTaskExtension>() != null }.configureEach {
				val testTask = this
				sourceSets(subproject.sourceSets.main.get())
				executionData(testTask)
			}

			// To automatically run `test` every time `./gradlew codeCoverageReport` is called,
			// you may want to set up a task dependency between them as shown below.
			// Note that this requires the `test` tasks to be resolved eagerly (see `forEach`) which
			// may have a negative effect on the configuration time of your build.
			subproject.tasks.matching { it.extensions.findByType<JacocoTaskExtension>() != null }.forEach {
				rootProject.tasks["codeCoverageReport"].dependsOn(it)
			}
		}
	}

	// enable the different report types (html, xml, csv)
	reports {
		// xml is usually used to integrate code coverage with
		// other tools like SonarQube, Coveralls or Codecov
		xml.required.set(true)

		// HTML reports can be used to see code coverage
		// without any external tools
		html.required.set(true)
	}
}

tasks.test {
	dependsOn(tasks.named("codeCoverageReport"))
}

sonarqube {
	properties {
		property("sonar.exclusions", "**/entity/**,**/secret/**,**/*Configuration.java,**/*JPA.java,**/*Dto.java,**/ExceptionHandlerApi.java,**/HealthApi.java,**/ExceptionHttpResponse.java,**/BurgerApplication.java,**/exception/**,**/InMemory*")
	}
}
