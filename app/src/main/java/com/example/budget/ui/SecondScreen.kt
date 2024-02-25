package com.example.budget.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.budget.R
import com.example.budget.ui.theme.ColorButtonElement
import com.example.budget.ui.theme.ColorTintText
import com.example.budget.utilit.Constant
import com.example.budget.utilit.toTimeFormat
import com.example.budget.viewmodel.SecondViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar


@Composable
fun SecondScreen(
    navController: NavController,
    viewModel: SecondViewModel = koinViewModel(),
) {
    val dataText by viewModel.dataText.collectAsState()
    val titleText by viewModel.titleText.collectAsState()
    val amountText by viewModel.amountText.collectAsState()
    val amountSaveText by viewModel.amountSaveText.collectAsState()
    val focusManager = LocalFocusManager.current
    val isChecked by viewModel.isChecked.collectAsState()
    val openDialog by viewModel.isOpenDialog.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                stringResource(R.string.second_screen_text),
                navController
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValue)
                .padding(all = dimensionResource(id = R.dimen.main_padding))
        ) {
            Text(
                text = stringResource(R.string.name_text),
                style = MaterialTheme.typography.bodyMedium
            )
            OutlinedTextField(
                value = titleText,
                onValueChange = { title ->
                    viewModel.onTitleChangeText(title)
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.example_title_text),
                        style = MaterialTheme.typography.labelLarge,
                        color = ColorTintText
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.second_padding)),
                supportingText = {
                    Text(
                        text = stringResource(R.string.support_title_text),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
            Text(
                text = stringResource(R.string.amount_text),
                style = MaterialTheme.typography.bodyMedium
            )
            OutlinedTextField(
                value = amountText,
                onValueChange = { amount ->
                    viewModel.onAmountChangeText(amount)
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.example_amount_text),
                        style = MaterialTheme.typography.labelLarge,
                        color = ColorTintText
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.second_padding)),
                supportingText = {
                    Text(
                        text = stringResource(R.string.support_amount_text),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { checked -> viewModel.onIsCheckedChange(checked) },
                    colors = CheckboxDefaults.colors(
                        checkedColor = ColorButtonElement,
                    ),
                )
                Text(
                    text = stringResource(R.string.checkbox_text),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if (isChecked) {
                Text(
                    text = stringResource(R.string.amount_save_text),
                    style = MaterialTheme.typography.bodyMedium
                )
                OutlinedTextField(
                    value = amountSaveText,
                    onValueChange = { amountSave ->
                        viewModel.onAmountSaveChangeText(amountSave)
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.example_amount_text),
                            style = MaterialTheme.typography.labelLarge,
                            color = ColorTintText
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.second_padding)),
                    supportingText = {
                        Text(
                            text = stringResource(R.string.support_amount_save_text),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )
            }
            Text(
                text = stringResource(R.string.data_text),
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.second_padding))
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    tint = ColorButtonElement
                )
                Text(
                    text = dataText.toTimeFormat(Constant.DATA_FORMAT_DATA_PICKER),
                    style = MaterialTheme.typography.labelMedium,
                    color = ColorButtonElement,
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.element_padding))
                        .clickable { viewModel.onIsDialogOpenChange(true) }
                )
            }
            Text(
                text = stringResource(R.string.support_data_text),
                style = MaterialTheme.typography.labelSmall
            )
        }

    }
    TimePickerDialog(
        openDialog = openDialog,
        onOpenDialog = { isOpen -> viewModel.onIsDialogOpenChange(isOpen) },
        onSelectedDate = { selectDate ->
            viewModel.onDataTextChange(selectDate)
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    openDialog: Boolean,
    onOpenDialog: (isOpen: Boolean) -> Unit,
    onSelectedDate: (selectDate: Long) -> Unit,
) {
    if (openDialog) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = {
                onOpenDialog(false)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val date = datePickerState.selectedDateMillis
                        if (date != null) {
                            if (date >= Calendar.getInstance().timeInMillis) {
                                onOpenDialog(false)
                                onSelectedDate(date)
                            } else {
                                onOpenDialog(false)
                                onSelectedDate(Calendar.getInstance().timeInMillis)
                            }
                        } else {
                            onOpenDialog(false)
                            onSelectedDate(Calendar.getInstance().timeInMillis)
                        }
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text(stringResource(R.string.success_button_text))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onOpenDialog(false)
                    }
                ) {
                    Text(stringResource(R.string.cancel_button_text))
                }
            }
        ) {
            DatePicker(state = datePickerState, showModeToggle = false)
        }
    }
}