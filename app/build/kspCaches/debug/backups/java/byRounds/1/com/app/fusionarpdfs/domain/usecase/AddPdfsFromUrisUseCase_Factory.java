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
public final class AddPdfsFromUrisUseCase_Factory implements Factory<AddPdfsFromUrisUseCase> {
  private final Provider<PdfFileRepository> pdfFileRepositoryProvider;

  private final Provider<MergeSessionRepository> mergeSessionRepositoryProvider;

  public AddPdfsFromUrisUseCase_Factory(Provider<PdfFileRepository> pdfFileRepositoryProvider,
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider) {
    this.pdfFileRepositoryProvider = pdfFileRepositoryProvider;
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
  }

  @Override
  public AddPdfsFromUrisUseCase get() {
    return newInstance(pdfFileRepositoryProvider.get(), mergeSessionRepositoryProvider.get());
  }

  public static AddPdfsFromUrisUseCase_Factory create(
      Provider<PdfFileRepository> pdfFileRepositoryProvider,
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider) {
    return new AddPdfsFromUrisUseCase_Factory(pdfFileRepositoryProvider, mergeSessionRepositoryProvider);
  }

  public static AddPdfsFromUrisUseCase newInstance(PdfFileRepository pdfFileRepository,
      MergeSessionRepository mergeSessionRepository) {
    return new AddPdfsFromUrisUseCase(pdfFileRepository, mergeSessionRepository);
  }
}
