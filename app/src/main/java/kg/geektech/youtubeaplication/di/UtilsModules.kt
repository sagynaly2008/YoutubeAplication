package kg.geektech.youtubeaplication.di

import kg.geektech.kotlinapplicationyoutube.utils.NetworkStatusHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val connectionModule = module {
    single { NetworkStatusHelper(androidContext()) }
}