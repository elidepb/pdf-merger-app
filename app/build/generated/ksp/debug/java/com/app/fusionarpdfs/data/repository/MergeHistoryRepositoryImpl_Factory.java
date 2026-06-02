package com.app.fusionarpdfs.data.repository;

import com.app.fusionarpdfs.data.preferences.MergeHistoryLocalDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class MergeHistoryRepositoryImpl_Factory implements Factory<MergeHistoryRepositoryImpl> {
  private final Provider<MergeHistoryLocalDataSource> mergeHistoryLocalDataSourceProvider;

  public MergeHistoryRepositoryImpl_Factory(
      Provider<MergeHistoryLocalDataSource> mergeHistoryLocalDataSourceProvider) {
    this.mergeHistoryLocalDataSourceProvider = mergeHistoryLocalDataSourceProvider;
  }

  @Override
  public MergeHistoryRepositoryImpl get() {
    return newInstance(mergeHistoryLocalDataSourceProvider.get());
  }

  public static MergeHistoryRepositoryImpl_Factory create(
      Provider<MergeHistoryLocalDataSource> mergeHistoryLocalDataSourceProvider) {
    return new MergeHistoryRepositoryImpl_Factory(mergeHistoryLocalDataSourceProvider);
  }

  public static MergeHistoryRepositoryImpl newInstance(
      MergeHistoryLocalDataSource mergeHistoryLocalDataSource) {
    return new MergeHistoryRepositoryImpl(mergeHistoryLocalDataSource);
  }
}
