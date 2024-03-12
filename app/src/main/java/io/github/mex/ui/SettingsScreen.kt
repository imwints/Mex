package io.github.mex.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import io.github.mex.R
import io.github.mex.ui.navigation.SettingsTransitions

@OptIn(ExperimentalMaterial3Api::class)
@Destination<RootGraph>(style = SettingsTransitions::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.nav_settings_title)) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            Column {
                val ctx = LocalContext.current
                ListItem(
                    modifier =
                        Modifier
                            .clickable {
                                ctx.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://github.com/imwints/Mex.git"),
                                    ),
                                )
                            }
                            .clip(RoundedCornerShape(16.dp)),
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Outlined.Code,
                            contentDescription = null,
                        )
                    },
                    headlineContent = { Text(text = "Code is open source") },
                    supportingContent = { Text(text = "View on GitHub") },
                    trailingContent = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.OpenInNew,
                            contentDescription = null,
                        )
                    },
                    shadowElevation = 160.dp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun SettingsPreview() {
    SettingsScreen(navController = rememberNavController())
}
