package com.jetpackprojects.wishit.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

  /** suspend is for functions that run in paralel
   * we do not need to use suspend in functions that return a flow, flow is already asyncronous**/

  //insert in database
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  abstract suspend fun addWish(wishEntity: Wish)


  //define what to load from database
  //load wishes from wish-table
  //Flow is asyncronous using coroutines
  @Query("SELECT * from `wish-table`")
  abstract fun getAllWishes(): Flow<List<Wish>>


//update entity in database
  @Update
  abstract suspend fun updateWish(wishEntity: Wish)

  //delete entity from database
  @Delete
  abstract suspend fun deleteWish(wishEntity: Wish)


  //get a single entity by id
  @Query("SELECT * from `wish-table` where id=:id")
  abstract fun getAWishById(id:Long): Flow<Wish>
}