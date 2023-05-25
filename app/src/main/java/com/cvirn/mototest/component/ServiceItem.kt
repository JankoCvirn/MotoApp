package com.cvirn.mototest.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cvirn.mototest.R
import com.cvirn.mototest.ui.theme.grey
import com.cvirn.mototest.ui.theme.orange
import com.cvirn.mototest.ui.theme.startGradient
import com.example.rocketreserver.MobjectsQuery

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ServiceItem(item: MobjectsQuery.Mobject, navController: NavController) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(top = 16.dp, start = 0.dp, end = 0.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
    ) {
        ConstraintLayout(Modifier.fillMaxSize()) {
            val (photo, title, description, arrow, address, rate, listing, ratings) = createRefs()

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.photo.downloadUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(photo) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .width(48.dp)
                    .height(48.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate("details/${item.id}")
                    },
                placeholder = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = item.title,
                Modifier
                    .padding(start = 8.dp, end = 4.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(photo.end)
                        bottom.linkTo(description.top)
                        end.linkTo(rate.start)
                        width = Dimension.fillToConstraints
                    }
                    .clickable {
                        navController.navigate("details/${item.id}")
                    },
                color = Color.White,
                fontWeight = FontWeight(700),
                fontSize = 17.sp,
                maxLines = 1,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = item.description,
                Modifier
                    .padding(start = 8.dp)
                    .constrainAs(description) {
                        top.linkTo(title.bottom)
                        start.linkTo(title.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(arrow.start)
                    }
                    .width(96.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 14.sp,
                color = grey,
            )
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.angles_right_solid),
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(arrow) {
                        top.linkTo(description.top)
                        start.linkTo(description.end)
                        bottom.linkTo(description.bottom)
                        end.linkTo(address.start)
                    }
                    .width(12.dp)
                    .height(6.dp)
                    .padding(start = 0.dp, end = 0.dp, top = 2.dp),
            )
            Text(
                text = "${item.city}, ${item.country.nicename}",
                Modifier
                    .constrainAs(address) {
                        top.linkTo(title.bottom)
                        start.linkTo(arrow.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    },
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 14.sp,
                color = grey,
            )

            Text(
                text = item.rating.toString(),
                Modifier
                    .constrainAs(rate) {
                        top.linkTo(title.top)
                        end.linkTo(listing.start)
                    }
                    .padding(end = 4.dp, top = 4.dp),
                fontSize = 14.sp,
                color = orange,
                fontWeight = FontWeight(700),
            )
            Text(
                text = "(${item.listing})",
                Modifier
                    .constrainAs(listing) {
                        top.linkTo(rate.top)
                        end.linkTo(parent.end)
                    }
                    .padding(end = 2.dp, top = 4.dp, start = 4.dp),
                fontSize = 14.sp,
                color = grey,
                fontWeight = FontWeight(700),
            )
            Canvas(
                modifier = Modifier
                    .constrainAs(ratings) {
                        end.linkTo(parent.end)
                        start.linkTo(address.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(rate.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
                    .padding(top = 8.dp, start = 32.dp, end = 2.dp),
            ) {
                drawRoundRect(
                    brush = createBrush(1, item.rating ?: 0.0),
                    size = Size(width = 10.dp.toPx(), height = 10.dp.toPx()),
                    cornerRadius = CornerRadius(x = 2.dp.toPx(), 2.dp.toPx()),
                )
                drawRoundRect(
                    brush = createBrush(2, item.rating ?: 0.0),
                    size = Size(width = 10.dp.toPx(), height = 10.dp.toPx()),
                    cornerRadius = CornerRadius(x = 2.dp.toPx(), 2.dp.toPx()),
                    topLeft = Offset(x = 14.dp.toPx(), y = 0.dp.toPx()),
                )
                drawRoundRect(
                    brush = createBrush(3, item.rating ?: 0.0),
                    size = Size(width = 10.dp.toPx(), height = 10.dp.toPx()),
                    cornerRadius = CornerRadius(x = 2.dp.toPx(), 2.dp.toPx()),
                    topLeft = Offset(x = 28.dp.toPx(), y = 0.dp.toPx()),
                )
                drawRoundRect(
                    brush = createBrush(4, item.rating ?: 0.0),
                    size = Size(width = 10.dp.toPx(), height = 10.dp.toPx()),
                    cornerRadius = CornerRadius(x = 2.dp.toPx(), 2.dp.toPx()),
                    topLeft = Offset(x = 42.dp.toPx(), y = 0.dp.toPx()),
                )
                drawRoundRect(
                    brush = createBrush(5, item.rating ?: 0.0),
                    size = Size(width = 10.dp.toPx(), height = 10.dp.toPx()),
                    cornerRadius = CornerRadius(x = 2.dp.toPx(), 2.dp.toPx()),
                    topLeft = Offset(x = 56.dp.toPx(), y = 0.dp.toPx()),
                )
            }
        }
    }
}

private fun createBrush(value: Int, rate: Double): Brush {
    return if (rate > value) {
        Brush.horizontalGradient(colors = listOf(orange, orange))
    } else {
        Brush.horizontalGradient(
            0.0f to orange,
            (value - rate).toFloat() + 0.25f to orange,
            1.0f to startGradient,
            startX = 0f,
            endX = Float.POSITIVE_INFINITY,
        )
    }
}
