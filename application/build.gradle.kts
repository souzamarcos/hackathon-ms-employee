plugins {
    application
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
}

application {
    mainClass.set("com.fiap.hackathon.application.boot.EmployeeApplication")
    applicationDefaultJvmArgs = listOf(
            "-Duser.timezone=America/Sao_Paulo"
    )
}

dependencies {
    implementation(project(":entity"))
    implementation(project(":usecase"))
    implementation(project(":controller"))
    implementation(project(":api"))
    implementation(project(":gateway"))

    implementation(rootProject.libs.spring.boot.starter.web)
}