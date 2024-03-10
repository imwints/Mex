package io.github.mex.data.filesystem

import android.os.Environment
import java.nio.file.Path
import kotlin.io.path.listDirectoryEntries

object FilesystemRepository {

    init {
        System.loadLibrary("filesystem")
    }

    fun listFiles(path: Path = Environment.getExternalStorageDirectory().toPath()): List<Path> =
        path.listDirectoryEntries()
}