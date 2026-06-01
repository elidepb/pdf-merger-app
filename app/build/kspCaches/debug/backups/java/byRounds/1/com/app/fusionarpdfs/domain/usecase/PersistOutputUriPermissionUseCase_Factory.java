package com.app.fusionarpdfs.domain.usecase;

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
public final class PersistOutputUriPermissionUseCase_Factory implements Factory<PersistOutputUriPermissionUseCase> {
  private final Provider<PdfFileRepository> pdfFileRepositoryProvider;

  public PersistOutputUriPermissionUseCase_Factory(
      Provider<PdfFileRepository> pdfFileRepositoryProvider) {
    this.pdfFileRepositoryProvider = pdfFileRepositoryProvider;
  }

  @Override
  public PersistOutputUriPermissionUseCase get() {
    return newInstance(pdfFileRepositoryProvider.get());
  }

  public static PersistOutputUriPermissionUseCase_Factory create(
      Provider<PdfFileRepository> pdfFileRepositoryProvider) {
    return new PersistOutputUriPermissionUseCase_Factory(pdfFileRepositoryProvider);
  }

  public static PersistOutputUriPermissionUseCase newInstance(PdfFileRepository pdfFileRepository) {
    return new PersistOutputUriPermissionUseCase(pdfFileRepository);
  }
}
