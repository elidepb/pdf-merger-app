package com.app.fusionarpdfs.domain.usecase;

import com.app.fusionarpdfs.domain.repository.MergeSessionRepository;
import com.app.fusionarpdfs.domain.repository.PdfFileRepository;
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
public final class RemoveSelectedPdfUseCase_Factory implements Factory<RemoveSelectedPdfUseCase> {
  private final Provider<MergeSessionRepository> mergeSessionRepositoryProvider;

  private final Provider<PdfFileRepository> pdfFileRepositoryProvider;

  public RemoveSelectedPdfUseCase_Factory(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<PdfFileRepository> pdfFileRepositoryProvider) {
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
    this.pdfFileRepositoryProvider = pdfFileRepositoryProvider;
  }

  @Override
  public RemoveSelectedPdfUseCase get() {
    return newInstance(mergeSessionRepositoryProvider.get(), pdfFileRepositoryProvider.get());
  }

  public static RemoveSelectedPdfUseCase_Factory create(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<PdfFileRepository> pdfFileRepositoryProvider) {
    return new RemoveSelectedPdfUseCase_Factory(mergeSessionRepositoryProvider, pdfFileRepositoryProvider);
  }

  public static RemoveSelectedPdfUseCase newInstance(MergeSessionRepository mergeSessionRepository,
      PdfFileRepository pdfFileRepository) {
    return new RemoveSelectedPdfUseCase(mergeSessionRepository, pdfFileRepository);
  }
}
