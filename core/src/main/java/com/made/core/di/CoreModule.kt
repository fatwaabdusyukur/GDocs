package com.made.core.di

import androidx.room.Room
import com.made.core.BuildConfig
import com.made.core.data.GDocsRepository
import com.made.core.data.source.local.LocalDataSource
import com.made.core.data.source.local.room.GDocsDatabase
import com.made.core.data.source.remote.RemoteDataSource
import com.made.core.data.source.remote.network.ApiServices
import com.made.core.domain.repository.IGDocsRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataBaseModule = module {
    factory { get<GDocsDatabase>().getGDocsDao() }
    single {
        val encryptKey : ByteArray = SQLiteDatabase.getBytes(BuildConfig.PASSPHRASE.toCharArray())
        val factory = SupportFactory(encryptKey)
        Room.databaseBuilder(
            androidContext(),
            GDocsDatabase::class.java, BuildConfig.DATABASE
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {

        val certificatePinner = CertificatePinner.Builder()
            .add(BuildConfig.HOSTNAME,
                BuildConfig.PIN_A,
                BuildConfig.PIN_B
                ).build()

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(ApiServices::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IGDocsRepository> {
        GDocsRepository(get(), get())
    }
}