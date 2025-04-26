package com.watsidev.retrofitapi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.watsidev.retrofitapi.R
import com.watsidev.retrofitapi.data.model.Region
import com.watsidev.retrofitapi.navigation.Regions

@Composable
fun RegionsScreen(
    listRegions: List<Region>,
    onNavigateClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(listRegions) { region ->
            CardRegion(
                region = region,
                model = region.imageUrl,
                onNavigateClick = { onNavigateClick(region.name) }
            )
        }
    }
}

@Composable
fun CardRegion(
    region: Region,
    model: String,
    onNavigateClick: (String) -> Unit
){
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(8.dp)
            .height(78.dp)
            .fillMaxWidth()
            .clickable{ onNavigateClick(region.name) }
    ) {
        Text(region.name, fontSize = 30.sp)
        Box(
            modifier = Modifier
                .height(178.dp)
        ){
            AsyncImage(
                model = model,
                contentDescription = region.name,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
