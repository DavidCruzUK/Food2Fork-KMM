package com.unitmock.food2forkkmm.android.di

import android.content.Context
import com.unitmock.food2forkkmm.android.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext app: Context): BaseApplication =
        app as BaseApplication

}