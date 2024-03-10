package io.github.mex

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.InsertDriveFile
import androidx.compose.material.icons.automirrored.outlined.NoteAdd
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import io.github.mex.ui.theme.MexTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MexTheme {
                RequestStorageAccess(onClick = {
                    startActivity(Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION))
                })
                MexApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MexApp(modifier: Modifier = Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier.fillMaxWidth(0.8f)
            ) {
                NavigationDrawerItem(
                    modifier = Modifier.padding(16.dp),
                    label = {
                        Text(text = "Internal Storage")
                    },
                    selected = true,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            snackbarHostState.showSnackbar("Internal Storage")
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.InsertDriveFile,
                            contentDescription = "Internal Storage"
                        )
                    }
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    modifier = Modifier.padding(16.dp),
                    label = {
                        Text(text = stringResource(id = R.string.nav_settings_title))
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Internal Storage")
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = stringResource(id = R.string.nav_settings_title)
                        )
                    }
                )
                NavigationDrawerItem(
                    modifier = Modifier.padding(16.dp),
                    label = {
                        Text(text = stringResource(id = R.string.nav_about_title))
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Internal Storage")
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(id = R.string.nav_about_title)
                        )
                    }
                )
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(text = "Files")
                            Text(
                                text = "3 Folders, 4 Files",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                if (drawerState.isClosed) {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Click",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.NoteAdd,
                        contentDescription = null
                    )
                }
            }
        ) { paddingValues ->
            FileListScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }
    }
}
