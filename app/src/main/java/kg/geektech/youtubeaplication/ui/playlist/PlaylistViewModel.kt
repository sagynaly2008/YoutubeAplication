package kg.geektech.youtubeaplication.ui.playlist

import androidx.lifecycle.LiveData
import kg.geektech.youtubeaplication.core.network.result.Resource
import kg.geektech.youtubeaplication.core.ui.BaseViewModel
import kg.geektech.youtubeaplication.data.remote.model.PlaylistModel
import kg.geektech.youtubeaplication.repository.Repository


class PlaylistViewModel(private val repository: Repository) : BaseViewModel() {
    fun getPlayList(): LiveData<Resource<PlaylistModel>> {
        return repository.createCall()
    }
}