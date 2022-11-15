package com.eric.mvvm_compose_circular_demo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressText(
    modifier: Modifier = Modifier,
    text: String,
    progress: Float,
    testTag: String,
) {
    Column(modifier = modifier) {
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.then(Modifier.size(64.dp))
        )
        Text(text = text, modifier = Modifier.testTag(testTag))
    }

}