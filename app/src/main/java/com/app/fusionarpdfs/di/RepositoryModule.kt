package com.app.fusionarpdfs.di

import com.app.fusionarpdfs.data.repository.MergeHistoryRepositoryImpl
import com.app.fusionarpdfs.data.repository.MergeSessionRepositoryImpl
import com.app.fusionarpdfs.data.repository.PdfFileRepositoryImpl
import com.app.fusionarpdfs.data.repository.PdfMergerRepositoryImpl
import com.app.fusionarpdfs.data.repository.UserPreferencesRepositoryImpl
import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository
import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import com.app.fusionarpdfs.domain.repository.PdfFileRepository
import com.app.fusionarpdfs.domain.repository.PdfMergerRepository
import com.app.fusionarpdfs.domain.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPdfMergerRepository(
        impl: PdfMergerRepositoryImpl,
    ): PdfMergerRepository

    @Binds
    @Singleton
    abstract fun bindMergeHistoryRepository(
        impl: MergeHistoryRepositoryImpl,
    ): MergeHistoryRepository

    @Binds
    @Singleton
    abstract fun bindPdfFileRepository(
        impl: PdfFileRepositoryImpl,
    ): PdfFileRepository

    @Binds
    @Singleton
    abstract fun bindMergeSessionRepository(
        impl: MergeSessionRepositoryImpl,
    ): MergeSessionRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        impl: UserPreferencesRepositoryImpl,
    ): UserPreferencesRepository
}
