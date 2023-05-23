package com.cvirn.mototest.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cvirn.mototest.R

@Composable
fun TopBar(
    onBack: () -> Unit,
    title: String,
) {
    ConstraintLayout(
        Modifier.fillMaxWidth().height(TabHeight),
    ) {
        val (icBack, lblTitle, icAdd, icBookmark, icSettings) = createRefs()

        IconButton(
            onClick = { },
            Modifier.constrainAs(icBack) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }.padding(start = 16.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.ic_arrow_back,
                ),
                contentDescription = "",
                tint = Color.Unspecified,
            )
        }

        Text(
            text = title,
            Modifier.constrainAs(lblTitle) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(icBack.end)
            }.padding(start = 37.dp),
            fontSize = 24.sp,
            color = Color.White,
        )

        IconButton(
            onClick = { },
            Modifier.constrainAs(icSettings) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }.padding(end = 16.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.ic_settings,
                ),
                contentDescription = "",
                tint = Color.Unspecified,
            )
        }

        IconButton(
            onClick = { },
            Modifier.constrainAs(icBookmark) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(icSettings.start)
            }.padding(end = 2.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.ic_bookmark,
                ),
                contentDescription = "",
                tint = Color.Unspecified,
            )
        }

        IconButton(
            onClick = { },
            Modifier.constrainAs(icAdd) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(icBookmark.start)
            }.padding(end = 2.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.ic_add,
                ),
                contentDescription = "",
                tint = Color.Unspecified,
            )
        }
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    TopBar(onBack = { }, title = "Services")
}

private val TabHeight = 56.dp
