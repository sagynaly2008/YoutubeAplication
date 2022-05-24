package kg.geektech.youtubeaplication

import android.app.Application
import kg.geektech.youtubeaplication.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }
    }
}