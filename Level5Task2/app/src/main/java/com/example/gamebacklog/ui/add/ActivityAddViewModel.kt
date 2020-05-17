package com.example.gamebacklog.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.gamebacklog.database.GameRepo
import com.example.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityAddViewModel(application: Application): AndroidViewModel(application) {
    private val gameRepo = GameRepo(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val game = MutableLiveData<Game?>()
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean?>()

    fun updateGame(){
        if(isGameValid()){
            mainScope.launch {
                withContext(Dispatchers.IO){
                    gameRepo.updateGame(game.value!!)
                }
            }
        }
    }
    private fun isGameValid(): Boolean{
        return when {

            game.value!!.title.isBlank()->{
                error.value = "Title must not be empty"
                false
            }
            game.value!!.platforms.isBlank()->{
                error.value = "Platform must must not be empty"
                false
            }
            game.value!!.releaseDate.toString().isBlank()->{
                error.value = "Date must not be empty"
                false
            }
            else->true
        }
    }
}