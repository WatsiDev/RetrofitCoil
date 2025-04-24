package com.watsidev.retrofitapi.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.watsidev.retrofitapi.data.model.Region

@Composable
fun RegionsScreen(
    listRegions: List<Region>,
    onNavigateClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(listRegions) {
            Card(
                // TODO: Add parameter idStart and idEnd to send to next page
                onClick = { onNavigateClick(it.name) },
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .padding(8.dp)
                    .height(78.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    Text(text = it.name, fontSize = 30.sp)
                    Text(text = " ${it.idStart} | ", fontSize = 30.sp)
                    Text(text = it.idEnd, fontSize = 30.sp)
                }
            }
        }
    }
}