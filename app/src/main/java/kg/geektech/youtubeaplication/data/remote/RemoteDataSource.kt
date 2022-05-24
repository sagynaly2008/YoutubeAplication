package kg.geektech.youtubeaplication.data.remote

import kg.geektech.kotlinapplicationyoutube.utils.Constant.channelId
import kg.geektech.kotlinapplicationyoutube.utils.Constant.maxResult
import kg.geektech.kotlinapplicationyoutube.utils.Constant.part
import kg.geektech.youtubeaplication.BuildConfig.API_KEY
import kg.geektech.youtubeaplication.core.network.BaseDataSource

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {
    suspend fun getPlaylists() = getResult {
        apiService.getPlaylists(part, channelId, API_KEY, maxResult)
    }

    suspend fun getDetailPlaylist(id: String) = getResult {
        apiService.getDetailPLayList(part, id, API_KEY)
    }
}