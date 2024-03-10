package io.github.mex

import android.os.Environment
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.mex.ui.theme.MexTheme
import java.io.File
import java.nio.file.Path

@Composable
fun FileListScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val viewModel: FileListViewModel = viewModel()
}

class FileListViewModel : ViewModel() {
    private var _fileList = MutableLiveData<List<File>>()
    val fileList: LiveData<List<File>> = _fileList

    fun updateFileList(path: Path) {
        _fileList.value = listOf(Environment.getExternalStorageDirectory())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FileListPreview() {
    MexTheme {
        FileListScreen(navController = rememberNavController())
    }
}