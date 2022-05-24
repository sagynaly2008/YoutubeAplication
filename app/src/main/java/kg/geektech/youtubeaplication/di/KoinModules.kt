package kg.geektech.youtubeaplication.di

import kg.geektech.youtubeaplication.core.network.networkModule

val koinModules = listOf(
    repoModules, viewModels, networkModule, connectionModule
)