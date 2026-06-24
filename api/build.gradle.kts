plugins {
    id("java-library")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
}

tasks.register("prepareKotlinBuildScriptModel") {}
