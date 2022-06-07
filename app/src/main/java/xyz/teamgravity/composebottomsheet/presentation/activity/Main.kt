package xyz.teamgravity.composebottomsheet.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import xyz.teamgravity.composebottomsheet.R
import xyz.teamgravity.composebottomsheet.presentation.theme.ComposeBottomSheetTheme

class Main : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBottomSheetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val bottomSheet = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
                    val scaffold = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheet)
                    val scope = rememberCoroutineScope()

                    BottomSheetScaffold(
                        scaffoldState = scaffold,
                        sheetContent = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(128.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.swipe_up_to_expand),
                                    color = Color.White
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(64.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.sheet_content),
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Button(
                                    onClick = {
                                        scope.launch { bottomSheet.collapse() }
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.click_collapse_sheet))
                                }
                            }
                        },
                        sheetBackgroundColor = Color.Gray,
                        sheetPeekHeight = 128.dp,
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = { },
                                backgroundColor = MaterialTheme.colors.primary
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = stringResource(id = R.string.cd_add_button)
                                )
                            }
                        },
                        floatingActionButtonPosition = FabPosition.End
                    ) { padding ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(padding)
                        ) {
                            Button(
                                onClick = {
                                    scope.launch { bottomSheet.expand() }
                                },
                            ) {
                                Text(text = stringResource(id = R.string.click_expand_sheet) + " ${bottomSheet.progress.fraction}")
                            }
                        }
                    }
                }
            }
        }
    }
}