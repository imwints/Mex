package io.github.mex

import android.os.Environment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.twotone.FolderOpen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LifecycleResumeEffect
import io.github.mex.ui.theme.MexTheme

@Composable
fun RequestStorageAccess(onClick: () -> Unit) {
    val isExternalStorageManager = remember {
        mutableStateOf(Environment.isExternalStorageManager())
    }
    if (!isExternalStorageManager.value) {
        LifecycleResumeEffect {
            isExternalStorageManager.value = Environment.isExternalStorageManager()
            onPauseOrDispose { }
        }
        StorageAccessDialog(onClick = onClick)
    }
}

@Composable
fun StorageAccessDialog(onClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        icon = {
            Icon(
                imageVector = Icons.TwoTone.FolderOpen,
                contentDescription = null
            )
        },
        title = {
            Text(text = "Storage access")
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "This app requires access to the devices' storage",
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            FilledTonalButton(onClick = onClick) {
                Text(text = "Allow")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.AutoMirrored.Default.OpenInNew, contentDescription = null)
            }
        }
    )
}

@Preview
@Composable
private fun DialogPreview() {
    MexTheme {
        StorageAccessDialog(onClick = {})
    }
}