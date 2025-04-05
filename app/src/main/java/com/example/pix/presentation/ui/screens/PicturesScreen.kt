package com.example.pix.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pix.R
import com.example.pix.presentation.ui.screens.composefun.AlertDialogScreen
import com.example.pix.presentation.ui.screens.composefun.SearchPane
import java.net.UnknownHostException

@Composable
fun PicturesScreen(vm: PicturesViewModel = viewModel()) {
    //  val searchString = vm.searchString.collectAsState()

    //val clickStartRequestButton: () -> Unit = { vm.startRequest() }// запуск запроса
    val isNotCancelErrorMessage = remember { mutableStateOf(true) }

    val clickCancelErrorMessage: () -> Unit =
        {
            isNotCancelErrorMessage.value = false
        }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val minSize = ((if (screenHeight < screenWidth) {
        screenHeight
    } else {
        screenWidth
    }) - 64.dp) / 2

    val picturesData =
        vm.pagesPictures.collectAsLazyPagingItems()// постраничная отображение и загрузка фото

    val clickStartRequestButton: (String) -> Unit =
        { newString: String ->
            vm.startRequest(newString)
            picturesData.refresh()
            isNotCancelErrorMessage.value = true
        }// установка строки поиска

    val listState = rememberLazyListState()

    val isLoading: Boolean = (picturesData.loadState.refresh == LoadState.Loading)

    Box(contentAlignment = Alignment.Center) {

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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalArrangement = Arrangement.SpaceAround,
                contentPadding = PaddingValues(top = 18.dp, bottom = 18.dp)
            ) {
                items(
                    count = picturesData.itemCount,
                )
                { index ->
                    if (picturesData[index] != null) {
                        ItemPicturesScreen(
                            picturesData[index]!!, minSize
                        )
                    }
                }
            }
        }
        when {// обработка состояний
            picturesData.loadState.append is LoadState.Loading -> { // загрузка-добавление данных в существующий список
                Box(
                    modifier = Modifier
                        .padding(top = 26.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            ((picturesData.loadState.append is LoadState.Error) && (isNotCancelErrorMessage.value)) -> {// ошибка при добавлении данных
                val e = picturesData.loadState.append as LoadState.Error
                var message = ""
                if (e.error is UnknownHostException) {
                    message = stringResource(R.string.alert_dialog_unknown_host_exception_message)
                } else
                    message = e.error.message.toString()
                AlertDialogScreen(
                    text = message,
                    textType = stringResource(R.string.alert_dialog_screen_label),
                    resetError = { picturesData.retry() },
                    dismissError = { clickCancelErrorMessage() }
                )
            }

            picturesData.loadState.refresh is LoadState.Loading -> {// загрузка списка с нуля
                Box {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            ((picturesData.loadState.refresh is LoadState.Error) && (isNotCancelErrorMessage.value)) -> {// ошибка при загрузке списка с нуля
                val e = picturesData.loadState.refresh as LoadState.Error
                var message = ""
                if (e.error is UnknownHostException) {
                    message = stringResource(R.string.alert_dialog_unknown_host_exception_message)
                } else
                    message = e.error.message.toString()
                AlertDialogScreen(
                    text = message,
                    textType = stringResource(R.string.alert_dialog_screen_label),
                    resetError = { picturesData.retry() },
                    dismissError = { clickCancelErrorMessage() }
                )
            }
        }

    }
}


class PicturesScreenCompanion() {
    companion object {
        const val LIST_OF_PICTURES_TEST_TAG = "picsList"
    }
}
