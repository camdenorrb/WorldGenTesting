plugins {
    kotlin("jvm") version "2.1.21"
    id("io.github.goooler.shadow") version "8.1.8"
}

group = "dev.twelveoclock"
version = "1.0.0"

repositories {

    //mavenLocal()
    mavenCentral()

    maven("https://hub.spigotmc.org/nexus/content/repositories/public/") {
        name = "SpigotMC"
    }
}

dependencies {

    //compileOnly("org.spigotmc:spigot:1.20.4-R0.1-SNAPSHOT")
    compileOnly("org.spigotmc:spigot-api:1.21.6-R0.1-SNAPSHOT")

    implementation(kotlin("stdlib-jdk8"))
    implementation("com.moandjiezana.toml:toml4j:0.7.2")

    testImplementation(kotlin("test-junit5"))
}


tasks {

    test {
        useJUnitPlatform()
    }

    // TODO: Change the second parameter to your plugin's package + the suffix.
    //       For example, if your main package is "me.example.catplugin",
    //       change the second parameter for the first relocate to:
    //       "me.example.catplugin.libs.org.jetbrains".
    //       Then, follow this pattern to the other relocate calls.
    shadowJar {
        relocate("org.jetbrains", "dev.twelveoclock.worldgentesting.libs.org.jetbrains")
        relocate("org.intellij", "dev.twelveoclock.worldgentesting.libs.org.intellij")
        relocate("com.google", "dev.twelveoclock.worldgentesting.libs.com.google")
        relocate("com.moandjiezana", "dev.twelveoclock.worldgentesting.libs.com.moandjiezana")
        relocate("kotlin", "dev.twelveoclock.worldgentesting.libs.kotlin")
    }

}
