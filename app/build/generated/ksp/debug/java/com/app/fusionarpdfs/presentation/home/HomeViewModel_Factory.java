package com.app.fusionarpdfs.presentation.home;

import com.app.fusionarpdfs.domain.repository.MergeSessionRepository;
import com.app.fusionarpdfs.domain.usecase.AddPdfsFromUrisUseCase;
import com.app.fusionarpdfs.domain.usecase.ClearPdfSelectionUseCase;
import com.app.fusionarpdfs.domain.usecase.RemoveSelectedPdfUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<MergeSessionRepository> mergeSessionRepositoryProvider;

  private final Provider<AddPdfsFromUrisUseCase> addPdfsFromUrisUseCaseProvider;

  private final Provider<RemoveSelectedPdfUseCase> removeSelectedPdfUseCaseProvider;

  private final Provider<ClearPdfSelectionUseCase> clearPdfSelectionUseCaseProvider;

  private final Provider<ValidatePdfSelectionUseCase> validatePdfSelectionUseCaseProvider;

  public HomeViewModel_Factory(Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<AddPdfsFromUrisUseCase> addPdfsFromUrisUseCaseProvider,
      Provider<RemoveSelectedPdfUseCase> removeSelectedPdfUseCaseProvider,
      Provider<ClearPdfSelectionUseCase> clearPdfSelectionUseCaseProvider,
      Provider<ValidatePdfSelectionUseCase> validatePdfSelectionUseCaseProvider) {
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
    this.addPdfsFromUrisUseCaseProvider = addPdfsFromUrisUseCaseProvider;
    this.removeSelectedPdfUseCaseProvider = removeSelectedPdfUseCaseProvider;
    this.clearPdfSelectionUseCaseProvider = clearPdfSelectionUseCaseProvider;
    this.validatePdfSelectionUseCaseProvider = validatePdfSelectionUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(mergeSessionRepositoryProvider.get(), addPdfsFromUrisUseCaseProvider.get(), removeSelectedPdfUseCaseProvider.get(), clearPdfSelectionUseCaseProvider.get(), validatePdfSelectionUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<AddPdfsFromUrisUseCase> addPdfsFromUrisUseCaseProvider,
      Provider<RemoveSelectedPdfUseCase> removeSelectedPdfUseCaseProvider,
      Provider<ClearPdfSelectionUseCase> clearPdfSelectionUseCaseProvider,
      Provider<ValidatePdfSelectionUseCase> validatePdfSelectionUseCaseProvider) {
    return new HomeViewModel_Factory(mergeSessionRepositoryProvider, addPdfsFromUrisUseCaseProvider, removeSelectedPdfUseCaseProvider, clearPdfSelectionUseCaseProvider, validatePdfSelectionUseCaseProvider);
  }

  public static HomeViewModel newInstance(MergeSessionRepository mergeSessionRepository,
      AddPdfsFromUrisUseCase addPdfsFromUrisUseCase,
      RemoveSelectedPdfUseCase removeSelectedPdfUseCase,
      ClearPdfSelectionUseCase clearPdfSelectionUseCase,
      ValidatePdfSelectionUseCase validatePdfSelectionUseCase) {
    return new HomeViewModel(mergeSessionRepository, addPdfsFromUrisUseCase, removeSelectedPdfUseCase, clearPdfSelectionUseCase, validatePdfSelectionUseCase);
  }
}
