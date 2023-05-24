package com.cvirn.mototest.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cvirn.mototest.R
import com.cvirn.mototest.ui.theme.grey
import com.cvirn.mototest.ui.theme.orange
import com.example.rocketreserver.MobjectsQuery

@Composable
fun ServiceItem(item: MobjectsQuery.Mobject) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(top = 16.dp, start = 0.dp, end = 0.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
    ) {
        ConstraintLayout(Modifier.fillMaxSize()) {
            val (photo, title, description, arrow, address, rate, listing) = createRefs()

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.photo.downloadUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "default crossfade example",
                modifier = Modifier.constrainAs(photo) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }.width(48.dp).height(48.dp).clip(CircleShape),
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
                    }.width(96.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 14.sp,
                color = grey,
            )
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.angles_right_solid),
                contentDescription = "",
                modifier = Modifier.constrainAs(arrow) {
                    top.linkTo(description.top)
                    start.linkTo(description.end)
                    bottom.linkTo(description.bottom)
                    end.linkTo(address.start)
                }.width(12.dp).height(6.dp).padding(start = 0.dp, end = 0.dp, top = 2.dp),
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
                    }.padding(end = 4.dp),
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
                    }.padding(end = 2.dp),
                fontSize = 14.sp,
                color = grey,
                fontWeight = FontWeight(700),
            )
        }
    }
}

@Preview
@Composable
fun PrevieServiceItem() {
    ServiceItem(
        item = MobjectsQuery.Mobject(
            id = "2",
            type = 2,
            rating = 4.7,
            title = "Jacomoto Racing Team",
            description = "Repair shop",
            address = "adress",
            city = "Madrid",
            listing = 2,
            phone = "89898989",
            publicEmail = "jacomoto@gmail.com",
            website = "www.jacomoto.com",
            workingHours = "Monday 12-24",
            createdAt = "31.12.2023",
            lat = 40.45,
            lng = -3.7,
            updatedAt = "31.12.2023",
            photo = MobjectsQuery.Photo(
                id = "32270ad5-778f-4e54-b213-6d86b0ec623c",
                downloadUrl = "https://cryptomoto-images.s3.eu-west-3.amazonaws.com/b4344e89-8e65-46ba-8d2b-e1daf81b71ae.svg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIA2TY2HEXFNWVGTCXZ%2F20230524%2Feu-west-3%2Fs3%2Faws4_request&X-Amz-Date=20230524T102320Z&X-Amz-Expires=3600&X-Amz-Signature=5585b55766a3c8aecc4c2072d3f0fe2b552e308a9f40e5f4b0631828e3703d3c&X-Amz-SignedHeaders=host&x-id=GetObject",
            ),
            country = MobjectsQuery.Country(
                id = "199",
                nicename = "Spain",
            ),
            author = MobjectsQuery.Author(
                id = "2",
                first_name = "Yulia",
                last_name = "Aleynikova",
                avatar = MobjectsQuery.Avatar(
                    id = "b4344e89-8e65-46ba-8d2b-e1daf81b71ae",
                    downloadUrl = "https://cryptomoto-images.s3.eu-west-3.amazonaws.com/b4344e89-8e65-46ba-8d2b-e1daf81b71ae.svg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIA2TY2HEXFNWVGTCXZ%2F20230523%2Feu-west-3%2Fs3%2Faws4_request&X-Amz-Date=20230523T142320Z&X-Amz-Expires=3600&X-Amz-Signature=04ea4e750025da6be4725e07dd1b37e0748cc0aea51ffe77f839548695546907&X-Amz-SignedHeaders=host&x-id=GetObject",
                ),
            ),
        ),
    )
}
