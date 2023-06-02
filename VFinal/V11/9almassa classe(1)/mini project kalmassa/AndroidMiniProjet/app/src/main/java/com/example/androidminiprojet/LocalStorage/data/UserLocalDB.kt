package com.example.androidminiprojet.LocalStorage.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO 6 "Change this class to an Entity"
@Entity
data class UserLocalDB(

    @PrimaryKey val id: Int,

    @ColumnInfo(name = "champPic") val champPic: Int,

    @ColumnInfo(name = "champName") val champName: String,

    @ColumnInfo(name = "champRole") val champRole: String

)