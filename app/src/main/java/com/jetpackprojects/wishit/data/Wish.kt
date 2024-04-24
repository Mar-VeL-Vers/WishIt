package com.jetpackprojects.wishit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish-table")


data class Wish(
@PrimaryKey(autoGenerate = true)
  val id:Long=0L,
@ColumnInfo(name = "wish-title")
  val title:String="",
@ColumnInfo(name="wish-desc")
  val description:String=""
)

object DummyWish{
  val wishList= listOf(
    Wish(title="Google watch 2", description = "An Android Watch"),
    Wish(title="Maldive Hollyday", description = "Go to Maldive for anniversary"),
    Wish(title="Lottery win", description = "To win the lottery"),
    Wish(title="LA penthouse", description = "To buy a penthouse in Los Angeles"),
    Wish(title="Mercedes ", description = "To buy a new Mercedes"),
    Wish(title="Africa Safary", description = "Go one month in Africa")
  )
}
