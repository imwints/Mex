package io.github.mex

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.twotone.FolderOpen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.LifecycleResumeEffect
import io.github.mex.ui.theme.MexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Draw behind system bars
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MexTheme {
                // Make sure the app has access to external storage
                val hasStoragePermission = remember {
                    mutableStateOf(Environment.isExternalStorageManager())
                }
                if (!hasStoragePermission.value) {
                    LifecycleResumeEffect {
                        // This effect runs on the onResume event. Update the permission state since
                        // it should've changed by now from within the settings app.
                        hasStoragePermission.value = Environment.isExternalStorageManager()
                        onPauseOrDispose { }
                    }
                    AlertDialog(
                        onDismissRequest = {
                            // TODO: Find a better way to also be able to use tha app without full 
                            //  permission
                            finish()
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.TwoTone.FolderOpen,
                                contentDescription = null
                            )
                        },
                        title = { Text(text = "Storage access") },
                        text = {
                            Text(
                                text = "This app requires access to the devices' storage. " +
                                        "You can grant access in the system settings.",
                                textAlign = TextAlign.Center
                            )
                        },
                        confirmButton = {
                            FilledTonalButton(
                                onClick = {
                                    startActivity(
                                        // TODO: This only jumps to the overview page. It is
                                        //  possible to skip this and jump to this apps special page
                                        //  directly (with [Uri.fromParts])
                                        Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                                    )
                                }
                            ) {
                                Text(text = stringResource(id = R.string.dialog_button_allow))
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.OpenInNew,
                                    contentDescription = null
                                )
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { finish() }) {
                                Text(text = stringResource(id = R.string.dialog_button_dismiss))
                            }
                        }
                    )
                }
                MexApp()
            }
        }
    }
}

// TODO: Decouple the dialog and preview it here
