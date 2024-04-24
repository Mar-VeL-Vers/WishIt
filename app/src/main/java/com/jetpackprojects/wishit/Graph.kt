package com.jetpackprojects.wishit

import android.content.Context
import androidx.room.Room
import com.jetpackprojects.wishit.data.WishDataBase
import com.jetpackprojects.wishit.data.WishRepository

//simple example of dependency injection in kotlin
//Graph is a Singleton, class that has a single instance, in out case, the object Graph thar will remain the same in the entire app
//Graph is a service locator that will provide the dependencies to the rest of the app
object Graph {
  lateinit var database: WishDataBase

//by lazy makes shure that the variable will be initialized only when it is needed

  val wishRepository by lazy {
    WishRepository(wishDao = database.wishDao())
  }

//we need the context of the database and to build it, that is what peovide function does
  fun provide(context:Context){
    database= Room.databaseBuilder(context, WishDataBase::class.java, "wishlist.db").build()
  }


}