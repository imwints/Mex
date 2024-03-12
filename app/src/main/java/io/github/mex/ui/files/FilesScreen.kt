package io.github.mex.ui.files

import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.InsertDriveFile
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.FilesScreenDestination
import com.ramcosta.composedestinations.generated.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigate
import io.github.mex.R
import kotlinx.coroutines.launch
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.isDirectory
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name

@OptIn(ExperimentalMaterial3Api::class)
@Destination<RootGraph>(start = true)
@Composable
fun FilesScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    pathName: String = Environment.getExternalStorageDirectory().toString()
) {
    val path = Path(pathName)
    Log.d("PATH", pathName)
    val entries = path.listDirectoryEntries().sorted()
    Log.d("PATH", entries.toString())
    val dirCount = entries.count() { it.isDirectory() }
    Log.d("PATH", "$dirCount")
    val fileCount = entries.count { !it.isDirectory() }
    Log.d("PATH", "$fileCount")
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { TitleWithEntryInfo(dirCount = dirCount, fileCount = fileCount) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Menu, contentDescription = null)
                    }
                }
            )
        }
    ) {
        val context = LocalContext.current
        LazyColumn(modifier = modifier.padding(it)) {
            items(
                items = entries
            ) { path ->
                ListItem(
                    modifier = Modifier.clickable {
                        if (path.isDirectory()) {
                            navigator.navigate(FilesScreenDestination(path.toString()))
                        } else {
                            Toast.makeText(context, "Not implemented", Toast.LENGTH_LONG).show()
                        }
                    },
                    leadingContent = {
                        if (path.isDirectory()) {
                            Icon(Icons.Outlined.Folder, contentDescription = null)
                        }
                    },
                    headlineContent = { Text(text = path.fileName.name) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test(modifier: Modifier = Modifier) {
    MediumTopAppBar(title = { Text(text = "Title") })
}

@Preview
@Composable
private fun TestPreview() {
    Test()
}

@Composable
fun TitleWithEntryInfo(modifier: Modifier = Modifier, dirCount: Int = 0, fileCount: Int = 0) {
    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.files))
        val text = String.format(
            "%s%s%s",
            if (dirCount > 0) pluralStringResource(R.plurals.directory, dirCount, dirCount) else "",
            if (dirCount > 0 && fileCount > 0) ", " else "",
            if (fileCount > 0) pluralStringResource(R.plurals.file, fileCount, fileCount) else ""
        )
        if (text.isNotBlank()) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 12.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileScreenImpl1(modifier: Modifier = Modifier, navController: NavController) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text(text = "Settings") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(SettingsScreenDestination)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Settings",
                        )
                    },
                )
            }
        },
        modifier = modifier,
        drawerState = drawerState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Files") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                )
            },
            floatingActionButton = {
                var clicked by remember { mutableStateOf(false) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AnimatedVisibility(visible = clicked) {
                        Column {
                            SmallFloatingActionButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Outlined.InsertDriveFile,
                                    contentDescription = "New File",
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            SmallFloatingActionButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Outlined.Folder,
                                    contentDescription = "New Folder",
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    FloatingActionButton(onClick = { clicked = !clicked }) {
                        Icon(imageVector = Icons.Outlined.Add, contentDescription = "New")
                    }
                }
            },
        ) {
            LazyColumn(modifier = Modifier.padding(it)) {
            }
        }
    }
}

@Composable
fun EntryItem(
    path: Path,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = {
            Text(text = path.toString())
        },
        modifier = modifier,
        leadingContent = {
            Icon(
                imageVector = if (path.isDirectory()) Icons.Outlined.Folder else Icons.AutoMirrored.Outlined.InsertDriveFile,
                contentDescription = null,
            )
        },
        trailingContent = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
            }
        },
    )
}

@Preview
@Composable
private fun ListItemPreview() {
    EntryItem(path = Path("Document.pdf"))
}
