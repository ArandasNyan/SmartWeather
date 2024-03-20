package br.com.fiap.smartweather


import android.app.Application
import br.com.fiap.smartweather.modules.appModule
import br.com.fiap.smartweather.modules.managerModule
import br.com.fiap.smartweather.modules.repositoryModule
import br.com.fiap.smartweather.modules.screenModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Weather : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Weather)
            modules(
                appModule, repositoryModule, screenModelModule, managerModule
            )
        }
    }
}
