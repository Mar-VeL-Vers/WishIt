package com.jetpackprojects.wishit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackprojects.wishit.data.Wish
import com.jetpackprojects.wishit.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//take wish repositori, store data,load data edit data
class WishViewModel(
  private val wishRepository:WishRepository = Graph.wishRepository):ViewModel() {

  var wishTitleState by mutableStateOf("")
  var wishDescriptionState by mutableStateOf("")


  fun onWishTitleChange(newString: String) {
    wishTitleState = newString
  }

  fun onWishDescriptionChange(newString: String) {
    wishDescriptionState = newString
  }

  lateinit var getAllWishes: Flow<List<Wish>>

  init {
    viewModelScope.launch {
      getAllWishes = wishRepository.getWishes()
    }
  }

  fun addWish(wish: Wish) {
    viewModelScope.launch(Dispatchers.IO) {
      wishRepository.addAWish(wish = wish)
    }
  }
  fun getAWishById(id:Long):Flow<Wish>{
    return wishRepository.getAWishById(id)
  }

  fun updateWish(wish: Wish) {
    viewModelScope.launch(Dispatchers.IO) {
      wishRepository.updateAWish(wish = wish)
    }
  }
  fun deleteWish(wish:Wish){
    viewModelScope.launch(Dispatchers.IO) {
      wishRepository.deleteAWish(wish=wish)
    }
  }



}























