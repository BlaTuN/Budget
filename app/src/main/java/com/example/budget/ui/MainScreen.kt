package com.example.budget.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.budget.R
import com.example.budget.model.MoneyboxDb
import com.example.budget.service.MainScreenEvent
import com.example.budget.ui.navigation.Screen
import com.example.budget.ui.theme.ColorContainer
import com.example.budget.ui.theme.ColorContainerFAB
import com.example.budget.ui.theme.ColorElement
import com.example.budget.ui.theme.ColorProgressIndication
import com.example.budget.ui.theme.ColorText
import com.example.budget.ui.theme.ColorTintText
import com.example.budget.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = koinViewModel(),
) {
    val mainScreenEvent = viewModel.mainScreenEvent
    val moneyboxList by viewModel.moneyboxSaveList.collectAsState()
    Scaffold(topBar = {
        TopBar(
            stringResource(R.string.main_screen_text),
            navController
        )
    },
        floatingActionButton = {
            SmallFloatingActionButton(onClick = {
                navController.navigate(Screen.SecondScreen.route)
            },
                containerColor = ColorContainerFAB) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    modifier = Modifier.size(31.dp),
                    tint = ColorElement
                )
            }
        }
    ) { paddingValue ->
        LaunchedEffect(key1 = mainScreenEvent, block = {
            if (mainScreenEvent == MainScreenEvent.Loading) {
                viewModel.getMoneyboxList()
            }
        })
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValue)
                .padding(all = dimensionResource(id = R.dimen.main_padding))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = stringResource(R.string.active_button_text),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = stringResource(R.string.archive_button_text),
                    style = MaterialTheme.typography.bodyMedium,
                    color = ColorTintText
                )
            }

            when (mainScreenEvent) {
                is MainScreenEvent.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.error_text, mainScreenEvent.error),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                MainScreenEvent.Loading -> Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }

                is MainScreenEvent.Success -> LazyColumn {
                    items(moneyboxList) { moneybox ->
                        Spacer(modifier = Modifier.height(15.dp))
                        WishItem(moneybox)
                    }
                }
            }

        }
    }
}

@Composable
fun WishItem(moneybox: MoneyboxDb) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(color = ColorContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        ) {
            Text(
                text = moneybox.title,
                style = MaterialTheme.typography.bodyMedium,
                color = ColorText
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = moneybox.dateTo,
                style = MaterialTheme.typography.bodySmall,
                color = ColorText
            )
        }
        LinearProgressIndicator(
            progress = { moneybox.progress },
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.element_padding))
                .fillMaxWidth(),
            color = ColorProgressIndication
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.element_padding))
        ) {
            Text(
                text = moneybox.alreadyHave.toString() + " " + moneybox.unit,
                style = MaterialTheme.typography.bodyMedium,
                color = ColorText
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = moneybox.amount.toString() + " " + moneybox.unit,
                style = MaterialTheme.typography.bodyMedium,
                color = ColorText
            )
        }
    }
}