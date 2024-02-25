package com.example.budget.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.budget.R
import com.example.budget.ui.navigation.Screen
import com.example.budget.ui.theme.ColorContainer
import com.example.budget.ui.theme.ColorElement
import com.example.budget.ui.theme.ColorText
import com.example.budget.ui.theme.Purple40
import com.example.budget.viewmodel.SecondViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    screen: String,
    navController: NavController,
    viewModel: SecondViewModel = koinViewModel(),
) {
    val titleText by viewModel.titleText.collectAsState()
    val amountText by viewModel.amountText.collectAsState()
    val amountSaveText by viewModel.amountSaveText.collectAsState()
    val isCheck by viewModel.isChecked.collectAsState()
    val context = LocalContext.current
    val secondScreen = stringResource(id = R.string.second_screen_text)
    CenterAlignedTopAppBar(title = {
        Text(text = stringResource(R.string.moneybox_text),
            style = MaterialTheme.typography.bodyLarge)
    }, navigationIcon = {
        IconButton(onClick = {
            if (screen == secondScreen) {
                navController.navigate(Screen.MainScreen.route)
            }
            Log.e("Back button", "Click back")
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = null
            )
        }
    }, actions = {
        IconButton(onClick = {
            if (screen == secondScreen) {
                if (titleText.isBlank()) {
                    Toast.makeText(context, "Укажите название цели", Toast.LENGTH_SHORT).show()
                } else {
                    if (amountText.isBlank()) {
                        Toast.makeText(context, "Укажите планируемую сумму", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        if (isCheck) {
                            if (amountSaveText.isBlank()) {
                                Toast.makeText(
                                    context,
                                    "Укажите сколько накопили",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                viewModel.saveMoneybox()
                                navController.navigate(Screen.MainScreen.route)
                            }
                        } else {
                            viewModel.saveMoneybox()
                            navController.navigate(Screen.MainScreen.route)
                        }
                    }
                }

            } else {
                Log.e("Back button", "Click menu")
            }

        }) {
            if (screen == secondScreen) Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null
            ) else Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = null
            )
        }
    },
        colors = TopAppBarColors(
            containerColor = ColorContainer,
            navigationIconContentColor = ColorElement,
            titleContentColor = ColorElement,
            scrolledContainerColor = ColorElement,
            actionIconContentColor = ColorElement
        )
    )
}