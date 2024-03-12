package io.github.mex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs

@Composable
fun MexApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        modifier = modifier,
        navController = navController
    )
}
// @OptIn(ExperimentalMaterial3Api::class)
// @Composable
// fun MexApp(modifier: Modifier = Modifier) {
//    val snackbarHostState = remember { SnackbarHostState() }
//    val scope = rememberCoroutineScope()
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val navController = rememberNavController()
//    ModalNavigationDrawer(
//        drawerContent = {
//            ModalDrawerSheet(
//                modifier.fillMaxWidth(0.8f)
//            ) {
//                NavigationDrawerItem(
//                    modifier = Modifier.padding(16.dp),
//                    label = {
//                        Text(text = "Internal Storage")
//                    },
//                    selected = true,
//                    onClick = {
//                        scope.launch {
//                            drawerState.close()
//                            snackbarHostState.showSnackbar("Internal Storage")
//                        }
//                    },
//                    icon = {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Outlined.InsertDriveFile,
//                            contentDescription = "Internal Storage"
//                        )
//                    }
//                )
//                HorizontalDivider()
//                NavigationDrawerItem(
//                    modifier = Modifier.padding(16.dp),
//                    label = {
//                        Text(text = stringResource(id = R.string.nav_settings_title))
//                    },
//                    selected = false,
//                    onClick = {
//                        scope.launch {
//                            snackbarHostState.showSnackbar("Internal Storage")
//                        }
//                    },
//                    icon = {
//                        Icon(
//                            imageVector = Icons.Outlined.Settings,
//                            contentDescription = stringResource(id = R.string.nav_settings_title)
//                        )
//                    }
//                )
//                NavigationDrawerItem(
//                    modifier = Modifier.padding(16.dp),
//                    label = {
//                        Text(text = stringResource(id = R.string.nav_about_title))
//                    },
//                    selected = false,
//                    onClick = {
//                        scope.launch {
//                            snackbarHostState.showSnackbar("Internal Storage")
//                        }
//                    },
//                    icon = {
//                        Icon(
//                            imageVector = Icons.Outlined.Info,
//                            contentDescription = stringResource(id = R.string.nav_about_title)
//                        )
//                    }
//                )
//            }
//        },
//        drawerState = drawerState
//    ) {
//        Scaffold(
//            modifier = modifier,
//            topBar = {
//                TopAppBar(
//                    title = {
//                        Column {
//                            Text(text = "Files")
//                            Text(
//                                text = "3 Folders, 4 Files",
//                                style = MaterialTheme.typography.bodySmall,
//                                color = MaterialTheme.colorScheme.onSurfaceVariant
//                            )
//                        }
//                    },
//                    navigationIcon = {
//                        IconButton(
//                            onClick = {
//                                if (drawerState.isClosed) {
//                                    scope.launch {
//                                        drawerState.open()
//                                    }
//                                }
//                            }
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Menu,
//                                contentDescription = "Menu"
//                            )
//                        }
//                    }
//                )
//            },
//            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
//            floatingActionButton = {
//                FloatingActionButton(
//                    onClick = {
//                        scope.launch {
//                            snackbarHostState.showSnackbar(
//                                message = "Click",
//                                duration = SnackbarDuration.Short
//                            )
//                        }
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Outlined.NoteAdd,
//                        contentDescription = null
//                    )
//                }
//            }
//        ) { paddingValues ->
//            FileListScreen(
//                modifier = Modifier.padding(paddingValues),
//                navController = navController
//            )
//        }
//    }
// }
