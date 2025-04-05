package com.example.pix.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pix.presentation.ui.screens.PicturesScreenCompanion.Companion.LIST_OF_PICTURES_TEST_TAG
import com.example.pix.presentation.ui.screens.composefun.SearchPane

@Composable
fun PicturesScreen(vm: PicturesViewModel = viewModel()) {
    //  val searchString = vm.searchString.collectAsState()

    //val clickStartRequestButton: () -> Unit = { vm.startRequest() }// запуск запроса

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val minSize=((if (screenHeight<screenWidth) {screenHeight}else {screenWidth})-64.dp)/2

    val picturesData =
        vm.pagesPictures.collectAsLazyPagingItems()// постраничная отображение и загрузка фото

    val clickStartRequestButton: (String) -> Unit =
        { newString: String ->
            vm.startRequest(newString)
            picturesData.refresh()
        }// установка строки поиска

    val listState = rememberLazyListState()

    val isLoading: Boolean = (picturesData.loadState.refresh == LoadState.Loading)

    var size = remember { mutableStateOf(0) }

    //{picturesData.loadState.append == LoadState.Loading}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.Top
    )
    {
        SearchPane(clickStartRequestButton, isLoading)

        LazyVerticalGrid(
            columns = GridCells.FixedSize(minSize),
            modifier = Modifier.fillMaxSize().padding(top=12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.SpaceAround,
            contentPadding = PaddingValues(top=18.dp, bottom = 18.dp)
        ) {

//        LazyColumn(
//            state = listState,
//            verticalArrangement = Arrangement.spacedBy(10.dp),
//            modifier = Modifier
//                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 16.dp)
//              //  .background(color = Color.White)
//                .testTag(LIST_OF_PICTURES_TEST_TAG)
//        ) {

            items(
                count = picturesData.itemCount,
                //    key = picturesData.itemKey { it.url }
            )
            { index ->
                if (picturesData[index] != null) {
                    ItemPicturesScreen(
                        picturesData[index]!!,minSize
                    )
                }
            }
            when {// обработка состояний
                picturesData.loadState.append is LoadState.Loading -> { // загрузка-добавление данных в существующий список
                    item {
                        Box(
                            modifier = Modifier
                                //           .fillParentMaxWidth()
                                .padding(top = 26.dp)
                        ) {
                            CircularProgressIndicator(
                                //     color = Color.Magenta,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                    }
                }

                picturesData.loadState.append is LoadState.Error -> {// ошибка при добавлении данных
                    item {
                        Row(
                            modifier = Modifier
                                //              .fillParentMaxWidth()
                                .padding(16.dp)
                        ) {
                            Button(onClick = { picturesData.retry() }) { Text(text = "Повторить загрузку") }
                            Text(
                                //         color = Color.Red,
                                text = "Ошибка загрузки",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }

                picturesData.loadState.refresh is LoadState.Loading -> {// загрузка списка с нуля
                    item {
                        Box(
                        //    modifier = Modifier.fillParentMaxSize()
                        ) {
                            CircularProgressIndicator(
                                //              color = Color.Magenta,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }

                picturesData.loadState.refresh is LoadState.Error -> {// ошибка при загрузке списка с нуля
                    item {
                        Row(
                            modifier = Modifier
                //                .fillParentMaxWidth()
                                .padding(16.dp)
                        ) {
                            Button(onClick = { picturesData.retry() }) { Text(text = "Повторить загрузку") }
                            Text(
                                //                    color = Color.Red,
                                text = "Ошибка загрузки",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun Service(){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
}


class PicturesScreenCompanion() {
    companion object {
        const val LIST_OF_PICTURES_TEST_TAG = "picsList"
    }
}
