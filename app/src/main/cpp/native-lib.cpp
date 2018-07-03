#include <jni.h>
#include <string>
#include <android/log.h>
//#include <cepri_dev.h>

#define TAG    "myhello-jni-test" // 这个是自定义的LOG的标识
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__) // 定义LOGD类型
extern "C" JNIEXPORT jstring

JNICALL
Java_com_easygold_cmake_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
//    std::int32_t result=Java_cepri_device_utils_SecurityUnit_Init();
    LOGD("the name from java is %s", hello.c_str());
    return env->NewStringUTF(hello.c_str());
}
