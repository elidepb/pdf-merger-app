package com.app.fusionarpdfs.presentation.preview;

import com.app.fusionarpdfs.domain.repository.MergeSessionRepository;
import com.app.fusionarpdfs.domain.usecase.PersistOutputUriPermissionUseCase;
import com.app.fusionarpdfs.domain.usecase.SaveMergeConfigurationUseCase;
import com.app.fusionarpdfs.domain.usecase.ValidateMergeConfigurationUseCase;
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
public final class PreviewViewModel_Factory implements Factory<PreviewViewModel> {
  private final Provider<MergeSessionRepository> mergeSessionRepositoryProvider;

  private final Provider<ValidateMergeConfigurationUseCase> validateMergeConfigurationUseCaseProvider;

  private final Provider<SaveMergeConfigurationUseCase> saveMergeConfigurationUseCaseProvider;

  private final Provider<PersistOutputUriPermissionUseCase> persistOutputUriPermissionUseCaseProvider;

  public PreviewViewModel_Factory(Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<ValidateMergeConfigurationUseCase> validateMergeConfigurationUseCaseProvider,
      Provider<SaveMergeConfigurationUseCase> saveMergeConfigurationUseCaseProvider,
      Provider<PersistOutputUriPermissionUseCase> persistOutputUriPermissionUseCaseProvider) {
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
    this.validateMergeConfigurationUseCaseProvider = validateMergeConfigurationUseCaseProvider;
    this.saveMergeConfigurationUseCaseProvider = saveMergeConfigurationUseCaseProvider;
    this.persistOutputUriPermissionUseCaseProvider = persistOutputUriPermissionUseCaseProvider;
  }

  @Override
  public PreviewViewModel get() {
    return newInstance(mergeSessionRepositoryProvider.get(), validateMergeConfigurationUseCaseProvider.get(), saveMergeConfigurationUseCaseProvider.get(), persistOutputUriPermissionUseCaseProvider.get());
  }

  public static PreviewViewModel_Factory create(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<ValidateMergeConfigurationUseCase> validateMergeConfigurationUseCaseProvider,
      Provider<SaveMergeConfigurationUseCase> saveMergeConfigurationUseCaseProvider,
      Provider<PersistOutputUriPermissionUseCase> persistOutputUriPermissionUseCaseProvider) {
    return new PreviewViewModel_Factory(mergeSessionRepositoryProvider, validateMergeConfigurationUseCaseProvider, saveMergeConfigurationUseCaseProvider, persistOutputUriPermissionUseCaseProvider);
  }

  public static PreviewViewModel newInstance(MergeSessionRepository mergeSessionRepository,
      ValidateMergeConfigurationUseCase validateMergeConfigurationUseCase,
      SaveMergeConfigurationUseCase saveMergeConfigurationUseCase,
      PersistOutputUriPermissionUseCase persistOutputUriPermissionUseCase) {
    return new PreviewViewModel(mergeSessionRepository, validateMergeConfigurationUseCase, saveMergeConfigurationUseCase, persistOutputUriPermissionUseCase);
  }
}
