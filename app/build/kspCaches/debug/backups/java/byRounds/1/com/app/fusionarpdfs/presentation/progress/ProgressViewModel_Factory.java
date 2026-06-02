package com.app.fusionarpdfs.presentation.progress;

import com.app.fusionarpdfs.domain.repository.MergeSessionRepository;
import com.app.fusionarpdfs.domain.usecase.ExecuteMergeFromSessionUseCase;
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
public final class ProgressViewModel_Factory implements Factory<ProgressViewModel> {
  private final Provider<MergeSessionRepository> mergeSessionRepositoryProvider;

  private final Provider<ExecuteMergeFromSessionUseCase> executeMergeFromSessionUseCaseProvider;

  public ProgressViewModel_Factory(Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<ExecuteMergeFromSessionUseCase> executeMergeFromSessionUseCaseProvider) {
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
    this.executeMergeFromSessionUseCaseProvider = executeMergeFromSessionUseCaseProvider;
  }

  @Override
  public ProgressViewModel get() {
    return newInstance(mergeSessionRepositoryProvider.get(), executeMergeFromSessionUseCaseProvider.get());
  }

  public static ProgressViewModel_Factory create(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<ExecuteMergeFromSessionUseCase> executeMergeFromSessionUseCaseProvider) {
    return new ProgressViewModel_Factory(mergeSessionRepositoryProvider, executeMergeFromSessionUseCaseProvider);
  }

  public static ProgressViewModel newInstance(MergeSessionRepository mergeSessionRepository,
      ExecuteMergeFromSessionUseCase executeMergeFromSessionUseCase) {
    return new ProgressViewModel(mergeSessionRepository, executeMergeFromSessionUseCase);
  }
}
