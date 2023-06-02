package com.example.androidminiprojet.LocalStorage.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.androidminiprojet.LocalStorage.data.UserLocalDB

//TODO 7 "Create the dao for the entity"
@Dao
interface UserLocalDBDao {




    @Insert(onConflict = REPLACE)
    fun addChampion(champion: UserLocalDB)



    @Insert(entity = UserLocalDB::class)
    fun insertChampion(vararg champion: UserLocalDB)
    //vararg lkitha f google
    //apparament maaneha var + arg   momkon khater arg khater mehabitech tekhdem k nhot val aadeya



    @Delete
    fun delete(vararg champion: UserLocalDB)



    @Query("SELECT * FROM champion")
    fun getAll(): MutableList<UserLocalDB>

    @Query("SELECT * FROM champion WHERE id = (:championId)")
    fun getById(championId: Int): List<UserLocalDB>


    @Query("Delete from champion")
    fun deleteAll()












}