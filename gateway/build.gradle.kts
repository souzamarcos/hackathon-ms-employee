dependencies {
    implementation(project(":entity"))
    implementation(project(":usecase"))

    implementation(rootProject.libs.spring.boot.starter.data.jpa)


    implementation(rootProject.libs.aws.dynamodb.enhanced)
//    implementation("com.github.derjust:spring-data-dynamodb:5.1.0")

}