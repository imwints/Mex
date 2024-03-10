package io.github.mex.ui.navigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SdCard
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.mex.ui.theme.MexTheme

@Composable
fun NavDrawerContent(modifier: Modifier = Modifier) {
    ModalDrawerSheet(modifier) {
        NavDrawerItem(
            label = { Text(text = "Internal storage") },
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.SdCard,
                    contentDescription = "Internal storage"
                )
            }
        )
        NavDrawerSection()
        NavDrawerItem(
            label = { Text(text = "Settings") },
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = "Settings"
                )
            }
        )
    }
}

@Composable
fun NavDrawerSection(modifier: Modifier = Modifier) {
    HorizontalDivider(modifier = modifier.padding(horizontal = 32.dp, vertical = 12.dp))
}

@Composable
fun NavDrawerSectionHeader(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier.padding(start = 32.dp))
}

@Composable
fun NavDrawerItem(
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null
) {
    NavigationDrawerItem(
        label = label,
        selected = selected,
        onClick = onClick,
        modifier = modifier.padding(horizontal = 16.dp),
        icon = icon,
        badge = badge
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DrawerContentPreview() {
    MexTheme {
        NavDrawerContent()
    }
}