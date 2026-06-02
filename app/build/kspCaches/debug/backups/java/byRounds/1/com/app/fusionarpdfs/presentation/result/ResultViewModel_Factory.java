package com.app.fusionarpdfs.presentation.result;

import androidx.lifecycle.SavedStateHandle;
import com.app.fusionarpdfs.domain.repository.PdfFileRepository;
import com.app.fusionarpdfs.domain.usecase.GetMergeResultUseCase;
import com.app.fusionarpdfs.domain.usecase.OpenMergedPdfUseCase;
import com.app.fusionarpdfs.domain.usecase.ShareMergedPdfUseCase;
import com.app.fusionarpdfs.domain.usecase.StartNewMergeUseCase;
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
public final class ResultViewModel_Factory implements Factory<ResultViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetMergeResultUseCase> getMergeResultUseCaseProvider;

  private final Provider<PdfFileRepository> pdfFileRepositoryProvider;

  private final Provider<OpenMergedPdfUseCase> openMergedPdfUseCaseProvider;

  private final Provider<ShareMergedPdfUseCase> shareMergedPdfUseCaseProvider;

  private final Provider<StartNewMergeUseCase> startNewMergeUseCaseProvider;

  public ResultViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetMergeResultUseCase> getMergeResultUseCaseProvider,
      Provider<PdfFileRepository> pdfFileRepositoryProvider,
      Provider<OpenMergedPdfUseCase> openMergedPdfUseCaseProvider,
      Provider<ShareMergedPdfUseCase> shareMergedPdfUseCaseProvider,
      Provider<StartNewMergeUseCase> startNewMergeUseCaseProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getMergeResultUseCaseProvider = getMergeResultUseCaseProvider;
    this.pdfFileRepositoryProvider = pdfFileRepositoryProvider;
    this.openMergedPdfUseCaseProvider = openMergedPdfUseCaseProvider;
    this.shareMergedPdfUseCaseProvider = shareMergedPdfUseCaseProvider;
    this.startNewMergeUseCaseProvider = startNewMergeUseCaseProvider;
  }

  @Override
  public ResultViewModel get() {
    return newInstance(savedStateHandleProvider.get(), getMergeResultUseCaseProvider.get(), pdfFileRepositoryProvider.get(), openMergedPdfUseCaseProvider.get(), shareMergedPdfUseCaseProvider.get(), startNewMergeUseCaseProvider.get());
  }

  public static ResultViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetMergeResultUseCase> getMergeResultUseCaseProvider,
      Provider<PdfFileRepository> pdfFileRepositoryProvider,
      Provider<OpenMergedPdfUseCase> openMergedPdfUseCaseProvider,
      Provider<ShareMergedPdfUseCase> shareMergedPdfUseCaseProvider,
      Provider<StartNewMergeUseCase> startNewMergeUseCaseProvider) {
    return new ResultViewModel_Factory(savedStateHandleProvider, getMergeResultUseCaseProvider, pdfFileRepositoryProvider, openMergedPdfUseCaseProvider, shareMergedPdfUseCaseProvider, startNewMergeUseCaseProvider);
  }

  public static ResultViewModel newInstance(SavedStateHandle savedStateHandle,
      GetMergeResultUseCase getMergeResultUseCase, PdfFileRepository pdfFileRepository,
      OpenMergedPdfUseCase openMergedPdfUseCase, ShareMergedPdfUseCase shareMergedPdfUseCase,
      StartNewMergeUseCase startNewMergeUseCase) {
    return new ResultViewModel(savedStateHandle, getMergeResultUseCase, pdfFileRepository, openMergedPdfUseCase, shareMergedPdfUseCase, startNewMergeUseCase);
  }
}
