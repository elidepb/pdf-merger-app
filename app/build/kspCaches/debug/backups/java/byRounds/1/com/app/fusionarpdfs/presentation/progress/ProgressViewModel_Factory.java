package com.app.fusionarpdfs.presentation.progress;

import com.app.fusionarpdfs.domain.repository.MergeSessionRepository;
import com.app.fusionarpdfs.domain.usecase.ExecuteMergeFromSessionUseCase;
import com.app.fusionarpdfs.domain.usecase.SaveHistoryUseCase;
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

  private final Provider<SaveHistoryUseCase> saveHistoryUseCaseProvider;

  public ProgressViewModel_Factory(Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<ExecuteMergeFromSessionUseCase> executeMergeFromSessionUseCaseProvider,
      Provider<SaveHistoryUseCase> saveHistoryUseCaseProvider) {
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
    this.executeMergeFromSessionUseCaseProvider = executeMergeFromSessionUseCaseProvider;
    this.saveHistoryUseCaseProvider = saveHistoryUseCaseProvider;
  }

  @Override
  public ProgressViewModel get() {
    return newInstance(mergeSessionRepositoryProvider.get(), executeMergeFromSessionUseCaseProvider.get(), saveHistoryUseCaseProvider.get());
  }

  public static ProgressViewModel_Factory create(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<ExecuteMergeFromSessionUseCase> executeMergeFromSessionUseCaseProvider,
      Provider<SaveHistoryUseCase> saveHistoryUseCaseProvider) {
    return new ProgressViewModel_Factory(mergeSessionRepositoryProvider, executeMergeFromSessionUseCaseProvider, saveHistoryUseCaseProvider);
  }

  public static ProgressViewModel newInstance(MergeSessionRepository mergeSessionRepository,
      ExecuteMergeFromSessionUseCase executeMergeFromSessionUseCase,
      SaveHistoryUseCase saveHistoryUseCase) {
    return new ProgressViewModel(mergeSessionRepository, executeMergeFromSessionUseCase, saveHistoryUseCase);
  }
}
