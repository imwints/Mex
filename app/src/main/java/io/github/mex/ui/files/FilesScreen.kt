package io.github.mex.ui.files

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.InsertDriveFile
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.isDirectory

@Composable
fun FilesScreen(modifier: Modifier = Modifier) {
    val viewModel: FilesViewModel = viewModel()
    Scaffold(modifier = modifier) { paddingValues ->
        val entries by viewModel.entries.collectAsState()
        if (entries.size > 1) {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(
                    items = entries,
                    itemContent = { path ->

                    }
                )
            }
        } else {
            // TODO: Show empty screen
        }
    }
}

@Composable
fun EntryItem(
    path: Path,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = {
            Text(text = path.toString())
        },
        modifier = modifier,
        leadingContent = {
            Icon(
                imageVector = if (path.isDirectory()) Icons.Outlined.Folder else Icons.AutoMirrored.Outlined.InsertDriveFile,
                contentDescription = null
            )
        },
        trailingContent = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
            }
        }
    )
}

@Preview
@Composable
private fun ListItemPreview() {
    EntryItem(path = Path("/"))
    EntryItem(path = Path("Document.pdf"))
}
