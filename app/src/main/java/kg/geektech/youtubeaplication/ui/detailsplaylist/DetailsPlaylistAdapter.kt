package kg.geektech.youtubeaplication.ui.detailsplaylist

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.youtubeaplication.R
import kg.geektech.youtubeaplication.core.extentions.load
import kg.geektech.youtubeaplication.data.remote.model.PlayListItem
import kg.geektech.youtubeaplication.data.remote.model.PlaylistModel
import kg.geektech.youtubeaplication.databinding.ItemDetailPlaylistBinding
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class DetailsPlaylistAdapter(private val playList: PlaylistModel) :
    RecyclerView.Adapter<DetailsPlaylistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail_playlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playList.items[position])
    }

    override fun getItemCount() = playList.items.size

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemDetailPlaylistBinding.bind(item)
        fun onBind(playList: PlayListItem) = with(binding) {
            tvDetailItemTitle.text = playList.snippet.title
            ivItemVideo.load(playList.snippet.thumbnails.default.url)
            val date = playList.snippet.publishedAt
            val offsetDateTime = OffsetDateTime.parse(date)
            val localOffsetDateTime = offsetDateTime.withOffsetSameInstant(ZoneOffset.ofHours(-2))
            tvDuration.text =
                String.format(localOffsetDateTime.format(DateTimeFormatter.ofPattern("dd-MM-uuuu ")))
        }
    }
}