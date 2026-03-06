plugins {
  alias(libs.plugins.android)
  alias(libs.plugins.compose)
  alias(libs.plugins.serialization)
}

kotlin {
  jvmToolchain(21)
}

android {
  namespace = "com.rengwuxian.coursecompose"
  compileSdk {
    version = release(36) {
      minorApiLevel = 1
    }
  }

  defaultConfig {
    applicationId = "com.rengwuxian.coursecompose"
    minSdk = 25
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  val composeBom = platform(libs.compose.bom)
  implementation(composeBom)
  debugImplementation(composeBom)
  androidTestImplementation(composeBom)

  implementation(libs.bundles.kotlinx)
  implementation(libs.bundles.androidx)
  implementation(libs.bundles.compose)
  implementation(libs.bundles.nav3)
  implementation(libs.bundles.coil)
  implementation(libs.recyclerview)
  implementation(libs.customview)

  debugImplementation(libs.compose.tooling)

  testImplementation(libs.junit)
  androidTestImplementation(libs.android.junit)
  androidTestImplementation(libs.compose.test.manifest)
  androidTestImplementation(libs.compose.test.ui)
}