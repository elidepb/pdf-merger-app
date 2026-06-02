package com.app.fusionarpdfs.presentation.history;

import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository;
import com.app.fusionarpdfs.domain.usecase.ClearHistoryUseCase;
import com.app.fusionarpdfs.domain.usecase.DeleteHistoryItemUseCase;
import com.app.fusionarpdfs.domain.usecase.OpenMergedPdfUseCase;
import com.app.fusionarpdfs.domain.usecase.ShareMergedPdfUseCase;
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
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  private final Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider;

  private final Provider<DeleteHistoryItemUseCase> deleteHistoryItemUseCaseProvider;

  private final Provider<ClearHistoryUseCase> clearHistoryUseCaseProvider;

  private final Provider<OpenMergedPdfUseCase> openMergedPdfUseCaseProvider;

  private final Provider<ShareMergedPdfUseCase> shareMergedPdfUseCaseProvider;

  public HistoryViewModel_Factory(Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider,
      Provider<DeleteHistoryItemUseCase> deleteHistoryItemUseCaseProvider,
      Provider<ClearHistoryUseCase> clearHistoryUseCaseProvider,
      Provider<OpenMergedPdfUseCase> openMergedPdfUseCaseProvider,
      Provider<ShareMergedPdfUseCase> shareMergedPdfUseCaseProvider) {
    this.mergeHistoryRepositoryProvider = mergeHistoryRepositoryProvider;
    this.deleteHistoryItemUseCaseProvider = deleteHistoryItemUseCaseProvider;
    this.clearHistoryUseCaseProvider = clearHistoryUseCaseProvider;
    this.openMergedPdfUseCaseProvider = openMergedPdfUseCaseProvider;
    this.shareMergedPdfUseCaseProvider = shareMergedPdfUseCaseProvider;
  }

  @Override
  public HistoryViewModel get() {
    return newInstance(mergeHistoryRepositoryProvider.get(), deleteHistoryItemUseCaseProvider.get(), clearHistoryUseCaseProvider.get(), openMergedPdfUseCaseProvider.get(), shareMergedPdfUseCaseProvider.get());
  }

  public static HistoryViewModel_Factory create(
      Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider,
      Provider<DeleteHistoryItemUseCase> deleteHistoryItemUseCaseProvider,
      Provider<ClearHistoryUseCase> clearHistoryUseCaseProvider,
      Provider<OpenMergedPdfUseCase> openMergedPdfUseCaseProvider,
      Provider<ShareMergedPdfUseCase> shareMergedPdfUseCaseProvider) {
    return new HistoryViewModel_Factory(mergeHistoryRepositoryProvider, deleteHistoryItemUseCaseProvider, clearHistoryUseCaseProvider, openMergedPdfUseCaseProvider, shareMergedPdfUseCaseProvider);
  }

  public static HistoryViewModel newInstance(MergeHistoryRepository mergeHistoryRepository,
      DeleteHistoryItemUseCase deleteHistoryItemUseCase, ClearHistoryUseCase clearHistoryUseCase,
      OpenMergedPdfUseCase openMergedPdfUseCase, ShareMergedPdfUseCase shareMergedPdfUseCase) {
    return new HistoryViewModel(mergeHistoryRepository, deleteHistoryItemUseCase, clearHistoryUseCase, openMergedPdfUseCase, shareMergedPdfUseCase);
  }
}
