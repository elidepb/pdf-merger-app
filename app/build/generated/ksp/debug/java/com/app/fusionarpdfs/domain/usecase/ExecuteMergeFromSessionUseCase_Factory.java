package com.app.fusionarpdfs.domain.usecase;

import com.app.fusionarpdfs.domain.repository.MergeSessionRepository;
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
public final class ExecuteMergeFromSessionUseCase_Factory implements Factory<ExecuteMergeFromSessionUseCase> {
  private final Provider<MergeSessionRepository> mergeSessionRepositoryProvider;

  private final Provider<ValidateMergeConfigurationUseCase> validateMergeConfigurationUseCaseProvider;

  private final Provider<MergePdfUseCase> mergePdfUseCaseProvider;

  public ExecuteMergeFromSessionUseCase_Factory(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<ValidateMergeConfigurationUseCase> validateMergeConfigurationUseCaseProvider,
      Provider<MergePdfUseCase> mergePdfUseCaseProvider) {
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
    this.validateMergeConfigurationUseCaseProvider = validateMergeConfigurationUseCaseProvider;
    this.mergePdfUseCaseProvider = mergePdfUseCaseProvider;
  }

  @Override
  public ExecuteMergeFromSessionUseCase get() {
    return newInstance(mergeSessionRepositoryProvider.get(), validateMergeConfigurationUseCaseProvider.get(), mergePdfUseCaseProvider.get());
  }

  public static ExecuteMergeFromSessionUseCase_Factory create(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<ValidateMergeConfigurationUseCase> validateMergeConfigurationUseCaseProvider,
      Provider<MergePdfUseCase> mergePdfUseCaseProvider) {
    return new ExecuteMergeFromSessionUseCase_Factory(mergeSessionRepositoryProvider, validateMergeConfigurationUseCaseProvider, mergePdfUseCaseProvider);
  }

  public static ExecuteMergeFromSessionUseCase newInstance(
      MergeSessionRepository mergeSessionRepository,
      ValidateMergeConfigurationUseCase validateMergeConfigurationUseCase,
      MergePdfUseCase mergePdfUseCase) {
    return new ExecuteMergeFromSessionUseCase(mergeSessionRepository, validateMergeConfigurationUseCase, mergePdfUseCase);
  }
}
