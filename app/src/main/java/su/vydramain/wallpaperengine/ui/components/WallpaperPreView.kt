package su.vydramain.wallpaperengine.ui.components

import android.net.Uri

import android.content.res.Configuration

import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.TextField

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.compositeOver

import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.data.Wallpaper
import su.vydramain.wallpaperengine.ui.theme.WallpaperEngineAppTheme

@Composable
fun WallpaperPreView(
    wallpaper: Wallpaper
) {
    val standardPadding = 16.dp

    val durationValue = 0
    val transitionValue = 0

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.wallpaper_settings_title),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    textStyle = MaterialTheme.typography.bodySmall,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    )
                )

                RowWithParameterAndControls(
                    name = stringResource(R.string.wallpaper_settings_duration_title),
                    value = durationValue.toString(),
                    onValueChange = {},
                    onClickAddButton = {},
                    onClickReduceButton = {}
                )

                RowWithParameterAndControls(
                    name = stringResource(R.string.wallpaper_settings_transition_title),
                    value = transitionValue.toString(),
                    onValueChange = {},
                    onClickAddButton = {},
                    onClickReduceButton = {},
                )
            }

            Icon(
                Icons.Outlined.Image,
                contentDescription = stringResource(R.string.wallpaper_preview_description),
                modifier = Modifier
                    .padding(5.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = MaterialTheme.shapes.small
                    )
                    .align(Alignment.CenterVertically)
                    .width(72.dp)
                    .fillMaxHeight(),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }

        Button(
            onClick = { /* Handle button click */ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
                disabledBackgroundColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f)
                    .compositeOver(androidx.compose.material.MaterialTheme.colors.surface),
                disabledContentColor = MaterialTheme.colorScheme.onTertiary
                    .copy(alpha = ContentAlpha.disabled)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            Text(
                text = stringResource(R.string.wallpaper_choose_button_text),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surface,
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable()
fun WallpaperPreViewPreview() {

    WallpaperEngineAppTheme {
        WallpaperPreView(
            Wallpaper(
                1,
                "empty_path",
                Uri.EMPTY,
                0,
                0
            )
        )
    }
}

@Composable
fun RowWithParameterAndControls(
    name: String,
    value: String,
    onValueChange: (String) -> Unit,
    onClickAddButton: () -> Unit,
    onClickReduceButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier
                .width(70.dp)
                .align(Alignment.CenterVertically),
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodySmall,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = modifier
                .weight(1f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        Row(
            modifier = modifier
                .align(Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = stringResource(R.string.wallpaper_settings_transition_button_add_description),
                contentScale = ContentScale.Fit,
            )

            Image(
                painter = painterResource(id = R.drawable.remove),
                contentDescription = stringResource(R.string.wallpaper_settings_transition_button_reduce_description),
                contentScale = ContentScale.Fit,
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun RowWithParameterAndControlsPreview() {
    val temporaryValue = 0

    WallpaperEngineAppTheme {
        RowWithParameterAndControls(
            name = "temporary_name",
            value = temporaryValue.toString(),
            onValueChange = {},
            onClickAddButton = {},
            onClickReduceButton = {})
    }
}
