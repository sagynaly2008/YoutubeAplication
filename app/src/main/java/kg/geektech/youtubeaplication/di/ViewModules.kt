package kg.geektech.youtubeaplication.di

import kg.geektech.youtubeaplication.ui.detailsplaylist.DetailsPlaylistViewModel
import kg.geektech.youtubeaplication.ui.playlist.PlaylistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModels: Module = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { DetailsPlaylistViewModel(get()) }
}