package com.app.quizapp.composites

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.quizapp.R
import com.app.quizapp.response.LoginResponse
import com.app.quizapp.utils.AllEvents
import com.app.quizapp.utils.SharedPreferenceUtil
import com.app.quizapp.utils.WidgetIds
import com.app.quizapp.utils.circularProgressBar
import com.app.quizapp.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    context: Context,
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {

    val coroutine = rememberCoroutineScope()
    val model = remember { loginViewModel }
    val userNameValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var isDialogVisible = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TextField(
            value = userNameValue.value,
            label = { Text(text = stringResource(id = R.string.username)) },
            onValueChange = {
                userNameValue.value = it
                model.userName.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(WidgetIds.TF_LOGIN_USERNAME_ID)
                .padding(16.dp),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            ),
            maxLines = 1,
        )

        TextField(
            value = passwordValue.value,
            label = { Text(text = stringResource(id = R.string.password)) },
            onValueChange = { passwordValue.value = it
                            model.password.value = it},
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(WidgetIds.TF_LOGIN_PASSWORD_ID)
                .padding(16.dp),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus(true)
            })
        )

        ClickableText(
            text = AnnotatedString("Forgot Password!"),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Start),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.primary
            )
        )

        Button(
            onClick = {
                model.login()
            },
            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp / 2)
        ) {
            Text(text = AnnotatedString("Login"))
        }

        Box(modifier = Modifier.fillMaxSize()) {
            ClickableText(
                text = AnnotatedString("Sign up here"),
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.BottomCenter),
                onClick = { },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
        if (isDialogVisible.value) {
            circularProgressBar(showDialog = true)
        } else {
            circularProgressBar(showDialog = false)
        }
    }

    coroutine.launch {
        loginViewModel.allEventsFlow.collect { event ->
            when (event) {
                is AllEvents.SuccessBool -> {
                    when (event.code) {
                        1 -> {

                        }
                    }
                }
                is AllEvents.Success<*> -> {
                    val loginresponse = event.data as LoginResponse
                    Log.e("TAG", "onCreate: ${loginresponse.token}")
                    SharedPreferenceUtil().setToken(context, loginresponse.token)
                    /*startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()*/
                }
                is AllEvents.Loading -> {
                    Log.e("TAG", "LoginScreen: ${event.load}")
                    isDialogVisible.value = event.load
                }
                else -> {
                    val asString = event.asString(context as Activity)
                    if (asString !is Unit && asString.toString().isNotBlank()) {
                        Toast.makeText(
                            context,
                            asString.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

}