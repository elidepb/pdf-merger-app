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
public final class StartNewMergeUseCase_Factory implements Factory<StartNewMergeUseCase> {
  private final Provider<ClearPdfSelectionUseCase> clearPdfSelectionUseCaseProvider;

  private final Provider<MergeSessionRepository> mergeSessionRepositoryProvider;

  public StartNewMergeUseCase_Factory(
      Provider<ClearPdfSelectionUseCase> clearPdfSelectionUseCaseProvider,
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider) {
    this.clearPdfSelectionUseCaseProvider = clearPdfSelectionUseCaseProvider;
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
  }

  @Override
  public StartNewMergeUseCase get() {
    return newInstance(clearPdfSelectionUseCaseProvider.get(), mergeSessionRepositoryProvider.get());
  }

  public static StartNewMergeUseCase_Factory create(
      Provider<ClearPdfSelectionUseCase> clearPdfSelectionUseCaseProvider,
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider) {
    return new StartNewMergeUseCase_Factory(clearPdfSelectionUseCaseProvider, mergeSessionRepositoryProvider);
  }

  public static StartNewMergeUseCase newInstance(ClearPdfSelectionUseCase clearPdfSelectionUseCase,
      MergeSessionRepository mergeSessionRepository) {
    return new StartNewMergeUseCase(clearPdfSelectionUseCase, mergeSessionRepository);
  }
}
