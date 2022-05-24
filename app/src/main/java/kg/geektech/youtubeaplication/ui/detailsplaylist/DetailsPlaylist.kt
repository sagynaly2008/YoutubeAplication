package kg.geektech.youtubeaplication.ui.detailsplaylist

import android.os.Build
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kg.geektech.kotlinapplicationyoutube.utils.NetworkStatus
import kg.geektech.kotlinapplicationyoutube.utils.NetworkStatusHelper
import kg.geektech.youtubeaplication.R
import kg.geektech.youtubeaplication.core.extentions.showToast
import kg.geektech.youtubeaplication.core.network.result.Status
import kg.geektech.youtubeaplication.core.ui.BaseActivity
import kg.geektech.youtubeaplication.data.remote.model.PlaylistModel
import kg.geektech.youtubeaplication.databinding.ActivityDetailsPlaylistBinding
import kg.geektech.youtubeaplication.ui.playlist.PlaylistActivity.Companion.PLAYLIST
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.O)
class DetailsPlaylist : BaseActivity<DetailsPlaylistViewModel, ActivityDetailsPlaylistBinding>() {
    override val viewModel: DetailsPlaylistViewModel by viewModel()
    private lateinit var detailList: PlaylistModel
    private val networkStatusHelper: NetworkStatusHelper by inject()
    private val detailAdapter: DetailsPlaylistAdapter by lazy {
        DetailsPlaylistAdapter(detailList)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getDetailPlayList(intent.getStringExtra(PLAYLIST).toString())
            .observe(this) { it ->
                viewModel.loading.observe(this) { binding.progress.isVisible = it }
                when (it.status) {
                    Status.ERROR -> it.msg?.let { it1 -> showToast(it1) }
                    Status.SUCCESS -> {
                        if (it.data != null) with(binding) {
                            detailList = it.data
                            tvDetailPlaylistTitle.text = detailList.items[0].snippet.title
                            tvDetailPlaylistSubtitle.text = detailList.items[0].snippet.description
                            tvDetailPlaylistSubtitle.movementMethod = ScrollingMovementMethod()
                            (detailList.items.size.toString() + getString(R.string.video)).also { it1 ->
                                binding.tvDetailSeries.text = it1
                            }
                            setupRecycler()
                        }
                    }
                    Status.LOADING -> viewModel.loading.postValue(true)
                }
            }
    }

    override fun checkInternet() {
        super.checkInternet()
        checkInternetMethod()
    }


    override fun initListener() {
        super.initListener()
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecycler() {
        binding.recyclerDetail.apply {
            layoutManager = LinearLayoutManager(this@DetailsPlaylist)
            adapter = this@DetailsPlaylist.detailAdapter
        }
    }

    private fun checkInternetMethod() {
        var status: Boolean
        networkStatusHelper.observe(this) {
            status = false
            if (it == NetworkStatus.Available && !status) binding.connection.parentConnection.isVisible =
                false
            else {
                status = true
                binding.connection.parentConnection.isVisible = true
                binding.connection.button.setOnClickListener {
                    if (NetworkStatus.Available == NetworkStatus.Available) {
                        binding.connection.parentConnection.isVisible = false
                    }
                }
            }
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityDetailsPlaylistBinding {
        return ActivityDetailsPlaylistBinding.inflate(inflater)
    }
}