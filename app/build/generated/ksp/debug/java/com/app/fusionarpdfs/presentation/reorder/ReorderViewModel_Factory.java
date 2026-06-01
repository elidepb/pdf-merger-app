package com.app.fusionarpdfs.presentation.reorder;

import com.app.fusionarpdfs.domain.repository.MergeSessionRepository;
import com.app.fusionarpdfs.domain.usecase.RemoveSelectedPdfUseCase;
import com.app.fusionarpdfs.domain.usecase.UpdatePdfOrderUseCase;
import com.app.fusionarpdfs.domain.usecase.ValidatePdfSelectionUseCase;
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
public final class ReorderViewModel_Factory implements Factory<ReorderViewModel> {
  private final Provider<MergeSessionRepository> mergeSessionRepositoryProvider;

  private final Provider<UpdatePdfOrderUseCase> updatePdfOrderUseCaseProvider;

  private final Provider<RemoveSelectedPdfUseCase> removeSelectedPdfUseCaseProvider;

  private final Provider<ValidatePdfSelectionUseCase> validatePdfSelectionUseCaseProvider;

  public ReorderViewModel_Factory(Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<UpdatePdfOrderUseCase> updatePdfOrderUseCaseProvider,
      Provider<RemoveSelectedPdfUseCase> removeSelectedPdfUseCaseProvider,
      Provider<ValidatePdfSelectionUseCase> validatePdfSelectionUseCaseProvider) {
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
    this.updatePdfOrderUseCaseProvider = updatePdfOrderUseCaseProvider;
    this.removeSelectedPdfUseCaseProvider = removeSelectedPdfUseCaseProvider;
    this.validatePdfSelectionUseCaseProvider = validatePdfSelectionUseCaseProvider;
  }

  @Override
  public ReorderViewModel get() {
    return newInstance(mergeSessionRepositoryProvider.get(), updatePdfOrderUseCaseProvider.get(), removeSelectedPdfUseCaseProvider.get(), validatePdfSelectionUseCaseProvider.get());
  }

  public static ReorderViewModel_Factory create(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<UpdatePdfOrderUseCase> updatePdfOrderUseCaseProvider,
      Provider<RemoveSelectedPdfUseCase> removeSelectedPdfUseCaseProvider,
      Provider<ValidatePdfSelectionUseCase> validatePdfSelectionUseCaseProvider) {
    return new ReorderViewModel_Factory(mergeSessionRepositoryProvider, updatePdfOrderUseCaseProvider, removeSelectedPdfUseCaseProvider, validatePdfSelectionUseCaseProvider);
  }

  public static ReorderViewModel newInstance(MergeSessionRepository mergeSessionRepository,
      UpdatePdfOrderUseCase updatePdfOrderUseCase,
      RemoveSelectedPdfUseCase removeSelectedPdfUseCase,
      ValidatePdfSelectionUseCase validatePdfSelectionUseCase) {
    return new ReorderViewModel(mergeSessionRepository, updatePdfOrderUseCase, removeSelectedPdfUseCase, validatePdfSelectionUseCase);
  }
}
