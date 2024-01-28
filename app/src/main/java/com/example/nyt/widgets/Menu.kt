package com.example.nyt.widgets

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nyt.model.ArticleType
import com.example.nyt.viewModel.NYTViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDropDown( onChanged: (ArticleType) -> Unit, initialValue: ArticleType) {
    val viewModel = NYTViewModel
    var expanded by remember { mutableStateOf(false) }
    val selectedCategory by viewModel.articlesType.observeAsState()
    var category by remember {
        mutableStateOf(initialValue)
    }
    ExposedDropdownMenuBox(
        modifier = Modifier
            .fillMaxWidth()
            .padding(19.dp),
        expanded = expanded,
        onExpandedChange = { newValue ->
            expanded = newValue
        }
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = category.value,
            onValueChange = {
                viewModel.setCategory(category)
                onChanged(category)
            },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            placeholder = {
                Text(text = if(initialValue == ArticleType.All) "All" else initialValue.value)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            modifier = Modifier.padding(5.dp),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            DropdownMenuItem(
                text = { Text("All") },
                onClick = {
                    category = ArticleType.All
                    viewModel.setCategory(category)
                    Log.d("menu", "changed to All")
                    onChanged(category)
                    expanded = false
                },
            )
            DropdownMenuItem(
                text = { Text("Arts") },
                onClick = {
                    category = ArticleType.Arts
                    viewModel.setCategory(category)
                    Log.d("menu", "changed to Arts")
                    onChanged(category)
                    expanded = false
                },
            )
            DropdownMenuItem(
                text = { Text("Sports") },
                onClick = {
                    category = ArticleType.Sports
                    viewModel.setCategory(category)
                    Log.d("menu", "changed to Sports")
                    onChanged(category)
                    expanded = false
                },
            )
            DropdownMenuItem(
                text = { Text("Business") },
                onClick = {
                    category = ArticleType.Business
                    viewModel.setCategory(category)
                    Log.d("menu", "changed to Business")
                    onChanged(category)
                    expanded = false
                },
            )
            DropdownMenuItem(
                text = { Text("Cars") },
                onClick = {
                    category = ArticleType.Cars
                    viewModel.setCategory(category)
                    Log.d("menu", "changed to Cars")
                    onChanged(category)
                    expanded = false
                },
            )
            DropdownMenuItem(
                text = { Text("Food") },
                onClick = {
                    category = ArticleType.Food
                    viewModel.setCategory(category)
                    Log.d("menu", "changed to Food")
                    onChanged(category)
                    expanded = false
                },
            )

        }
    }
}
