package io.github.mex.filesystem

object FilesystemRepository {

    init {
        System.loadLibrary("filesystem")
    }

    private external fun nativeListFiles(path: String): Array<String>?
}