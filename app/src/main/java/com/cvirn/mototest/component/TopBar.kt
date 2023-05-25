package com.cvirn.mototest.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.cvirn.mototest.R

@Composable
fun TopBar(
    onBack: () -> Unit,
    title: String,
    navControler: NavController,
) {
    val context = LocalContext.current
    ConstraintLayout(
        Modifier.fillMaxWidth().height(TabHeight),
    ) {
        val (icBack, lblTitle, icAdd, icBookmark, icSettings) = createRefs()

        IconButton(
            onClick = { navControler.popBackStack() },
            Modifier.constrainAs(icBack) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }.padding(start = 8.dp).clickable { onBack },
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.ic_arrow_back,
                ),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.clickable { navControler.popBackStack() },
            )
        }

        Text(
            text = title,
            Modifier.constrainAs(lblTitle) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(icBack.end)
                end.linkTo(icAdd.start)
                width = Dimension.fillToConstraints
            }.padding(start = 24.dp),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            color = Color.White,
        )

        IconButton(
            onClick = { showToast(context) },
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
            onClick = { showToast(context) },
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
            onClick = { showToast(context) },
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

private fun showToast(context: Context) {
    Toast.makeText(
        context,
        "Under construction. Will be available in the next release",
        Toast.LENGTH_SHORT,
    ).show()
}

private val TabHeight = 56.dp
