import java.io.BufferedReader
import java.io.File

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.lizongying.mytv0"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.fcurrk.mytv0"
        minSdk = 21
        targetSdk = 35
        versionCode = getVersionCode()
        versionName = getVersionName()
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        // Flag to enable support for the new language APIs
        // For AGP 4.1+
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

//fun getTag(): String {
//    return try {
//        val process = Runtime.getRuntime().exec("git describe --tags --always")
//        process.waitFor()
//        process.inputStream.bufferedReader().use(BufferedReader::readText).trim().removePrefix("v")
//    } catch (_: Exception) {
//        ""
//    }
//}

//fun getVersionCode(): Int {
//    return try {
//        val arr = (getTag().replace(".", " ").replace("-", " ") + " 0").split(" ")
//        arr[0].toInt() * 16777216 + arr[1].toInt() * 65536 + arr[2].toInt() * 256 + arr[3].toInt()
//    } catch (_: Exception) {
//        1
//    }
//}

fun getVersionCode(): Int {
    return try {
        val json = File("./version.json").readText()
        val versionCode = json.substringAfter("\"version_code\":").substringBefore(",").trim()
        versionCode.toInt()
    } catch (e: Exception) {
        1
    }
}

fun getVersionName(): String {
    return try {
        val json = File("./version.json").readText()
        val versionName = json.substringAfter("\"version_name\":").substringBefore(",").trim().replace("\"", "")
        versionName.replace("v", "") 
    } catch (e: Exception) {
        "0.0.0-1"
    }
}

dependencies {
    // For AGP 7.4+
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(libs.media3.ui)
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.exoplayer.hls)
    implementation(libs.media3.exoplayer.dash)
    implementation(libs.media3.exoplayer.rtsp)
    implementation(libs.media3.datasource.okhttp)
    implementation(libs.media3.datasource.rtmp)

    implementation(libs.nanohttpd)
    implementation(libs.gua64)
    implementation(libs.zxing)
    implementation(libs.glide)

    implementation(libs.gson)
    implementation(libs.okhttp)

    implementation(libs.core.ktx)
    implementation(libs.coroutines)

    implementation(libs.constraintlayout)
    implementation(libs.appcompat)
    implementation(libs.recyclerview)
    implementation(libs.lifecycle.viewmodel)

    implementation(files("libs/lib-decoder-ffmpeg-release.aar"))
}