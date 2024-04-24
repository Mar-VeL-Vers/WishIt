package com.jetpackprojects.wishit


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jetpackprojects.wishit.data.Wish
import com.jetpackprojects.wishit.ui.theme.WishItTheme
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
  id: Long,
  viewModel: WishViewModel,
  navController: NavController
) {

  val snackMessage = remember{ mutableStateOf("") }
  val scope= rememberCoroutineScope()
  val scaffoldState= rememberScaffoldState()

  if(id!=0L){
    val wish=viewModel.getAWishById(id).collectAsState(initial = Wish(0L,"",""))
    viewModel.wishTitleState=wish.value.title
    viewModel.wishDescriptionState=wish.value.description
  }else{
    viewModel.wishTitleState=""
    viewModel.wishDescriptionState=""
  }


  Scaffold(

    topBar = {
    AppBarView(
      title = if (id != 0L) stringResource(id = R.string.update_wish)
      else stringResource(id = R.string.add_wish))
    //default behavior for arrows in the upper left corner that takes the user where it came from
    {navController.navigateUp()}
  },
    scaffoldState = scaffoldState

  ) {
    Column(
      modifier = Modifier
        .padding(it)
        .fillMaxSize()
        .background(Color.White),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Top
    ) {
      Spacer(modifier = Modifier.height(10.dp))
      WishTextField(
        label = "Title",
        value = viewModel.wishTitleState,
        onValueChanged = { viewModel.onWishTitleChange(it) }
      )
      Spacer(modifier = Modifier.height(10.dp))
      WishTextField(
        label = "Description",
        value = viewModel.wishDescriptionState,
        onValueChanged = { viewModel.onWishDescriptionChange(it) }
      )
      Spacer(modifier = Modifier.height(10.dp))
      Button(onClick = {
        if (viewModel.wishTitleState.isNotEmpty() &&
            viewModel.wishDescriptionState.isNotEmpty()) {
          if(id!=0L){
            // update wish
            viewModel.updateWish(
              Wish(id=id,
                title = viewModel.wishTitleState.trim(),
                description = viewModel.wishDescriptionState.trim()
                )
            )
          }else{
            // add wish
            viewModel.addWish(
              Wish(
                title = viewModel.wishTitleState.trim(),
                description = viewModel.wishDescriptionState.trim()
              )
            )
            snackMessage.value="wish has been created"
          }

        }else{
          //TODO enter fields for wish to be created
          snackMessage.value="Enter fields to create a wish"
        }
       scope.launch {
         scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
         navController.navigateUp()
       }

      }) {
        Text(
          text = if (id != 0L) stringResource(id = R.string.update_wish)
          else stringResource(id = R.string.add_wish),
          style = TextStyle(
            fontSize = 18.sp
          )
        )
      }
    }
  }

}


@Composable
fun WishTextField(
  label: String,
  value: String,
  onValueChanged: (String) -> Unit
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChanged,
    label = { Text(text = label, color = Color.Black) },
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 5.dp, end = 5.dp),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    colors = OutlinedTextFieldDefaults.colors(
      focusedTextColor = colorResource(id = R.color.black),
      unfocusedTextColor = colorResource(id = R.color.black),
      focusedBorderColor = colorResource(id = R.color.black),
      unfocusedBorderColor = colorResource(id = R.color.black),
      cursorColor = colorResource(id = R.color.black),
      focusedLabelColor = colorResource(id = R.color.black),
      unfocusedLabelColor = colorResource(id = R.color.black)
    )

  )


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextFieldPreview() {
  WishItTheme {
    WishTextField(label = "label", value = "object", onValueChanged = {})
  }
}
