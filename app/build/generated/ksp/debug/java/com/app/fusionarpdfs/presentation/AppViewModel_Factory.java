package com.app.fusionarpdfs.presentation;

import com.app.fusionarpdfs.domain.repository.UserPreferencesRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AppViewModel_Factory implements Factory<AppViewModel> {
  private final Provider<UserPreferencesRepository> userPreferencesRepositoryProvider;

  public AppViewModel_Factory(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider) {
    this.userPreferencesRepositoryProvider = userPreferencesRepositoryProvider;
  }

  @Override
  public AppViewModel get() {
    return newInstance(userPreferencesRepositoryProvider.get());
  }

  public static AppViewModel_Factory create(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider) {
    return new AppViewModel_Factory(userPreferencesRepositoryProvider);
  }

  public static AppViewModel newInstance(UserPreferencesRepository userPreferencesRepository) {
    return new AppViewModel(userPreferencesRepository);
  }
}
