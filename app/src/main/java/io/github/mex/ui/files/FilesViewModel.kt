package io.github.mex.ui.files

import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.nio.file.Path
import kotlin.io.path.listDirectoryEntries

class FilesViewModel : ViewModel() {
    private val _dir = MutableStateFlow(Environment.getExternalStorageDirectory().toPath())
    val dir: StateFlow<Path> = _dir
    private val _entries = MutableStateFlow(dir.value.listDirectoryEntries())
    val entries: StateFlow<List<Path>> = _entries

    fun navigateTo(path: Path) {
        viewModelScope.launch {
            _dir.value = path
            _entries.value = path.listDirectoryEntries()
        }
    }
}
