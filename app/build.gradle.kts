plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.freshtime"
    compileSdk = 34  // 최신 버전으로 업데이트

    defaultConfig {
        applicationId = "com.example.freshtime"
        minSdk = 26  // 일반적으로 지원되는 최소 버전으로 설정
        targetSdk = 34  // 최신 버전으로 업데이트
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")  // 최신 버전 유지
    implementation("com.google.android.material:material:1.12.0")  // 최신 버전 유지
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")  // 최신 버전 유지
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    implementation("androidx.navigation:navigation-fragment:2.5.3")     // 하단바
    implementation("androidx.navigation:navigation-ui:2.5.3")       // 하단바
}
