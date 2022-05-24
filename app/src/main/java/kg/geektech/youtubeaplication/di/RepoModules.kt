package kg.geektech.youtubeaplication.di

import kg.geektech.youtubeaplication.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single { Repository(get()) }
}