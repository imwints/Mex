#include <jni.h>

#include <filesystem>
#include <string>
#include <vector>

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_io_github_mex_filesystem_FilesystemRepository_nativeListFiles(JNIEnv *env, jobject thiz,
                                                                  jstring path) {
    // TODO: implement nativeListFile()
}