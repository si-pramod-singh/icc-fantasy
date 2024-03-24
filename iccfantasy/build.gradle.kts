plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    `maven-publish`
}

android {

    group = GameConfig.group

    namespace = GameConfig.nameSpace
    compileSdk = GameConfig.compileSdk

    defaultConfig {
        minSdk = GameConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("pre") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = GameConfig.sourceCompatibility
        targetCompatibility = GameConfig.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = GameConfig.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    publishing {
        multipleVariants {
            allVariants()
        }
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/si-pramod-singh/icc-fantasy")
            credentials {
                username = System.getProperty("gpr.uefa.user") ?: System.getenv("GPR_UEFA_USER")
                password = System.getProperty("gpr.uefa.key") ?: System.getenv("GPR_UEFA_KEY")
            }
        }
    }
    publications {

        create<MavenPublication>("allVariants") {
            afterEvaluate {
                from(components["default"])
            }
            version =
                if (project.hasProperty("snapshot")) "${GameConfig.snapshotVersion}-SNAPSHOT"
                else GameConfig.version
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
}