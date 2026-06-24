import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.gradleup.shadow")
    java
}

repositories {
    mavenLocal()
    maven {
        name = "spigotmc-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    "compileOnly"("io.papermc.paper:paper-api:26.1.2.build.+")
    "implementation"(project(":api"))
}

tasks.processResources {
    val props = mapOf("version" to project.version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.named<ShadowJar>("shadowJar") {
    // We want to shade the api module
    // By default implementation(project(":api")) will include it in shadowJar
    
    // You might want to relocate it if you expect conflicts
    // relocate("me.illusion.abilityabstraction.api", "me.illusion.abilityabstraction.example.api")
}

tasks.named("build") {
    dependsOn(tasks.named("shadowJar"))
}

tasks.register("prepareKotlinBuildScriptModel") {} // gradle quirk