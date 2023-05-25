package com.cvirn.mototest.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cvirn.mototest.R
import com.cvirn.mototest.component.ShowToast
import com.cvirn.mototest.viewModel.ServicesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(navController: NavHostController, id: String?) {
    val viewModel = koinViewModel<ServicesViewModel>()
    viewModel.findService(id ?: "1")
    val list by viewModel.mObject.collectAsState()
    val context = LocalContext.current

    if (list.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxHeight(fraction = 0.95f)
                .verticalScroll(rememberScrollState()).padding(bottom = 60.dp),
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize().padding(16.dp),
            ) {
                val (image, title, description, address, hours, contact) = createRefs()
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(list.first()?.photo?.downloadUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier.constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(title.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }.height(196.dp).clip(RectangleShape),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = "${list.first()?.title} - ${list.first()?.rating}",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(description.top)
                        width = Dimension.fillToConstraints
                        height = Dimension.preferredWrapContent
                    }.padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )
                Text(
                    text = list.first()?.description ?: "",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.constrainAs(description) {
                        top.linkTo(title.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(address.top)
                        width = Dimension.fillToConstraints
                        height = Dimension.preferredWrapContent
                    }.padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )
                Text(
                    text = "${list.first()?.address}, ${list.first()?.city} , ${list.first()?.country?.nicename}",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.constrainAs(address) {
                        top.linkTo(description.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(hours.top)
                        width = Dimension.fillToConstraints
                        height = Dimension.preferredWrapContent
                    }.padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )
                Text(
                    text = "${list.first()?.workingHours}",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.constrainAs(hours) {
                        top.linkTo(address.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(contact.top)
                        width = Dimension.fillToConstraints
                        height = Dimension.preferredWrapContent
                    }.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )

                Text(
                    text = "${list.first()?.phone} - ${list.first()?.publicEmail} - ${list.first()?.website}",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.constrainAs(contact) {
                        top.linkTo(hours.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.preferredWrapContent
                    }.padding(top = 16.dp).clickable {
                        ShowToast(context)
                    },
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )
            }
        }
    }
}
