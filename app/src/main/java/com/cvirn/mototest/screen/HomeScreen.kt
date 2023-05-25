package com.cvirn.mototest.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.cvirn.mototest.component.ServiceItem
import com.cvirn.mototest.ui.theme.searchGrey
import com.cvirn.mototest.viewModel.ServicesViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = koinViewModel<ServicesViewModel>()
    ConstraintLayout(modifier.fillMaxSize()) {
        val (searchBar, row, column) = createRefs()
        val list by viewModel.mObjectsList.collectAsState()
        val (value, onValueChange) = remember { mutableStateOf("") }

        TextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 20.sp),
            leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color(0xBDFFFFFF)) },
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(searchBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(column.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
            label = { Text(text = "Search") },
            placeholder = { Text(text = "Search") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = searchGrey,
                cursorColor = Color.DarkGray,
            ),
            shape = RoundedCornerShape(8.dp),
            maxLines = 1,
        )

        LazyColumn(
            modifier = Modifier.constrainAs(column) {
                top.linkTo(searchBar.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            contentPadding = PaddingValues(16.dp),
        ) {
            item {
            }
            items(items = list, itemContent = { item ->
                item?.let { ServiceItem(item = it, navController) }
            })
        }
    }
}
