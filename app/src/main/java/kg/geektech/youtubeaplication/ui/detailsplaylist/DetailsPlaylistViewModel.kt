package kg.geektech.youtubeaplication.ui.detailsplaylist

import androidx.lifecycle.LiveData
import kg.geektech.youtubeaplication.core.network.result.Resource
import kg.geektech.youtubeaplication.core.ui.BaseViewModel
import kg.geektech.youtubeaplication.data.remote.model.PlaylistModel
import kg.geektech.youtubeaplication.repository.Repository

class DetailsPlaylistViewModel(private val repository: Repository) : BaseViewModel() {
    fun getDetailPlayList(id: String): LiveData<Resource<PlaylistModel>> {
        return repository.createDetailCall(id)
    }
}