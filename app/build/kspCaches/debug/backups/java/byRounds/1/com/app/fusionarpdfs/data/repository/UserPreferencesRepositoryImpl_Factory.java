package com.app.fusionarpdfs.data.repository;

import com.app.fusionarpdfs.data.preferences.UserPreferencesLocalDataSource;
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
public final class UserPreferencesRepositoryImpl_Factory implements Factory<UserPreferencesRepositoryImpl> {
  private final Provider<UserPreferencesLocalDataSource> localDataSourceProvider;

  public UserPreferencesRepositoryImpl_Factory(
      Provider<UserPreferencesLocalDataSource> localDataSourceProvider) {
    this.localDataSourceProvider = localDataSourceProvider;
  }

  @Override
  public UserPreferencesRepositoryImpl get() {
    return newInstance(localDataSourceProvider.get());
  }

  public static UserPreferencesRepositoryImpl_Factory create(
      Provider<UserPreferencesLocalDataSource> localDataSourceProvider) {
    return new UserPreferencesRepositoryImpl_Factory(localDataSourceProvider);
  }

  public static UserPreferencesRepositoryImpl newInstance(
      UserPreferencesLocalDataSource localDataSource) {
    return new UserPreferencesRepositoryImpl(localDataSource);
  }
}
