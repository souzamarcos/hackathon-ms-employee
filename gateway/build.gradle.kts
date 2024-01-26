dependencies {
    implementation(project(":entity"))
    implementation(project(":usecase"))

    implementation(rootProject.libs.spring.boot.starter.data.jpa)


    implementation(rootProject.libs.aws.dynamodb.enhanced)
}