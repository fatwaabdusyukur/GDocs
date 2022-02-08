package com.made.core.di

import androidx.room.Room
import com.made.core.BuildConfig
import com.made.core.data.GDocsRepository
import com.made.core.data.source.local.LocalDataSource
import com.made.core.data.source.local.room.GDocsDatabase
import com.made.core.data.source.remote.RemoteDataSource
import com.made.core.data.source.remote.network.ApiServices
import com.made.core.domain.repository.IGDocsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataBaseModule = module {
    factory { get<GDocsDatabase>().getGDocsDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GDocsDatabase::class.java, "GDocs.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
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