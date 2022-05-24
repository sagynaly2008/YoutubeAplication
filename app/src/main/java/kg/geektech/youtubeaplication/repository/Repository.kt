package kg.geektech.youtubeaplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geektech.youtubeaplication.core.network.result.Resource
import kg.geektech.youtubeaplication.data.remote.RemoteDataSource
import kg.geektech.youtubeaplication.data.remote.model.PlaylistModel
import kotlinx.coroutines.Dispatchers

class Repository(private val dataSource: RemoteDataSource) {

    fun createCall(): LiveData<Resource<PlaylistModel>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getPlaylists()
        emit(response)
    }

    fun createDetailCall(id: String): LiveData<Resource<PlaylistModel>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getDetailPlaylist(id)
            emit(response)
        }
}