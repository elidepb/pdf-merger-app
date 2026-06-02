package com.app.fusionarpdfs.presentation.settings;

import android.content.Context;
import com.app.fusionarpdfs.domain.repository.UserPreferencesRepository;
import com.app.fusionarpdfs.domain.usecase.ClearHistoryUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<UserPreferencesRepository> userPreferencesRepositoryProvider;

  private final Provider<ClearHistoryUseCase> clearHistoryUseCaseProvider;

  public SettingsViewModel_Factory(Provider<Context> contextProvider,
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<ClearHistoryUseCase> clearHistoryUseCaseProvider) {
    this.contextProvider = contextProvider;
    this.userPreferencesRepositoryProvider = userPreferencesRepositoryProvider;
    this.clearHistoryUseCaseProvider = clearHistoryUseCaseProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(contextProvider.get(), userPreferencesRepositoryProvider.get(), clearHistoryUseCaseProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<Context> contextProvider,
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<ClearHistoryUseCase> clearHistoryUseCaseProvider) {
    return new SettingsViewModel_Factory(contextProvider, userPreferencesRepositoryProvider, clearHistoryUseCaseProvider);
  }

  public static SettingsViewModel newInstance(Context context,
      UserPreferencesRepository userPreferencesRepository,
      ClearHistoryUseCase clearHistoryUseCase) {
    return new SettingsViewModel(context, userPreferencesRepository, clearHistoryUseCase);
  }
}
