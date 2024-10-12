package com.asifiqbal.todotracking.foundation.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asifiqbal.todotracking.foundation.theme.AlphaDisabled

@Composable
fun PgModalBackButton(
    onClick: () -> Unit,
    imageVector: ImageVector = Icons.Rounded.ChevronLeft
) {
    PgIconButton(
        onClick = onClick,
        modifier = Modifier.size(28.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        PgIcon(
            imageVector = imageVector,
        )
    }
}

@Composable
fun PgIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = MaterialTheme.colorScheme.secondary,
    content: @Composable () -> Unit
) {
    val shape = CircleShape
    IconButton(
        onClick = onClick,
        modifier = modifier.background(
            color = color,
            shape = shape
        ).clip(shape),
        enabled = enabled
    ) {
        content()
    }
}

@Composable
fun PgButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier.height(56.dp),
        enabled = enabled,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        content = content,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = AlphaDisabled)
        ),
    )
}

@Composable
fun PgSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        modifier = modifier.height(56.dp),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun PgModalBackButtonPreview() {
    PgModalBackButton(
        onClick = { /* Handle back button click */ },
        imageVector = Icons.Rounded.ChevronLeft
    )
}

@Preview(showBackground = true)
@Composable
fun PgIconButtonPreview() {
    PgIconButton(
        onClick = { /* Handle icon button click */ },
        color = Color.Blue
    ) {
        Icon(
            imageVector = Icons.Rounded.Check,
            contentDescription = "Check Icon",
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PgButtonPreview() {
    PgButton(
        onClick = { /* Handle button click */ },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Primary Button", color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun PgSecondaryButtonPreview() {
    PgSecondaryButton(
        onClick = { /* Handle secondary button click */ },
        modifier = Modifier.padding(16.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Rounded.Clear,
                contentDescription = "Clear Icon",
                tint = Color.Gray
            )
            Text(text = "Secondary Button", modifier = Modifier.padding(start = 8.dp))
        }
    }
}
