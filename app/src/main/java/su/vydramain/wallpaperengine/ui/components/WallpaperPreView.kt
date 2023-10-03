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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.compositeOver
import coil.compose.rememberAsyncImagePainter

import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.data.Wallpaper
import su.vydramain.wallpaperengine.ui.theme.WallpaperEngineAppTheme
import java.util.UUID

private val STANDARD_PADDING = 16.dp

@Composable
fun WallpaperPreView(
    wallpaper: Wallpaper,
    onChooseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = STANDARD_PADDING, top = STANDARD_PADDING, end = STANDARD_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = STANDARD_PADDING)
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
                    value = wallpaper.path,
                    onValueChange = { wallpaper.path = it },
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
                    value = wallpaper.duration.toString(),
                    onValueChange = { /* update duration */ },
                    onClickIncreaseButton = { /* increase duration */ },
                    onClickDecreaseButton = { /* decrease duration */ }
                )

                RowWithParameterAndControls(
                    name = stringResource(R.string.wallpaper_settings_transition_title),
                    value = wallpaper.transition.toString(),
                    onValueChange = { /* update transition */ },
                    onClickIncreaseButton = { /* increase transition */ },
                    onClickDecreaseButton = { /* decrease transition */ }
                )
            }

            WallpaperImagePreView(
                wallpaperImageUri = wallpaper.imageUri,
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
            )
        }

        Button(
            onClick = onChooseClick,
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
                .padding(top = STANDARD_PADDING),
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
            wallpaper = Wallpaper(
                UUID.randomUUID(),
                "empty_path",
                Uri.EMPTY,
                0,
                0
            ),
            onChooseClick = {}
        )
    }
}

@Composable
fun RowWithParameterAndControls(
    name: String,
    value: String,
    onValueChange: (String) -> Unit,
    onClickIncreaseButton: () -> Unit,
    onClickDecreaseButton: () -> Unit,
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
            Button(
                onClick = onClickIncreaseButton,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.onTertiary,
                    contentColor = MaterialTheme.colorScheme.tertiary,
                    disabledBackgroundColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.12f)
                        .compositeOver(androidx.compose.material.MaterialTheme.colors.surface),
                    disabledContentColor = MaterialTheme.colorScheme.tertiary
                        .copy(alpha = ContentAlpha.disabled)
                ),
                modifier = modifier
                    .width(50.dp)
                    .padding(all = 2.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = stringResource(R.string.wallpaper_settings_transition_button_add_description),
                    tint = MaterialTheme.colorScheme.tertiary,
                )
            }

            Button(
                onClick = onClickDecreaseButton,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.onTertiary,
                    contentColor = MaterialTheme.colorScheme.tertiary,
                    disabledBackgroundColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.12f)
                        .compositeOver(androidx.compose.material.MaterialTheme.colors.surface),
                    disabledContentColor = MaterialTheme.colorScheme.tertiary
                        .copy(alpha = ContentAlpha.disabled)
                ),
                modifier = modifier
                    .width(50.dp)
                    .padding(all = 2.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.remove),
                    contentDescription = stringResource(R.string.wallpaper_settings_transition_button_reduce_description),
                    tint = MaterialTheme.colorScheme.tertiary,
                )
            }
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
            onClickIncreaseButton = {},
            onClickDecreaseButton = {})
    }
}

@Composable
fun WallpaperImagePreView(
    wallpaperImageUri: Uri,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    when (wallpaperImageUri.path?.isEmpty()) {
        false -> Image(
            painter = rememberAsyncImagePainter(model = wallpaperImageUri),
            contentDescription = contentDescription,
            modifier = modifier
        )

        else -> Icon(
            imageVector = Icons.Outlined.Image,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = MaterialTheme.colorScheme.onSurface,
        )
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
fun WallpaperImagePreViewPreview() {
    WallpaperEngineAppTheme {
        WallpaperImagePreView(
            wallpaperImageUri = Uri.EMPTY,
            contentDescription = "Description",
        )
    }
}