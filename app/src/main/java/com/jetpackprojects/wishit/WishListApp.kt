package com.jetpackprojects.wishit

import android.app.Application

//we need to write the application in Manifest
class WishListApp:Application() {
  override fun onCreate() {
    super.onCreate()
    Graph.provide(this)
  }
}