package io.github.mex

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.Fort
import androidx.compose.material.icons.twotone.FolderOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.mex.ui.theme.MexTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileList(modifier: Modifier = Modifier, files: Array<String>) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        stickyHeader {
            ListItem(modifier = Modifier.wrapContentHeight(),
                headlineContent = {
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
                })
        }
        items(
            items = files,
            itemContent = { file ->
                ListItem(
                    headlineContent = {
                        Text(text = file)
                    },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.TwoTone.FolderOpen,
                            contentDescription = null
                        )
                    },
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null
                        )
                    },

                    )
            }
        )
    }
}

@Preview
@Composable
private fun FileListPreview() {
    MexTheme {
        FileList(files = arrayOf("Android", "Documents", "Music"))
    }
}