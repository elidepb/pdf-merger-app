package com.app.fusionarpdfs.domain.usecase;

import com.app.fusionarpdfs.domain.repository.PdfMergerRepository;
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
public final class MergePdfUseCase_Factory implements Factory<MergePdfUseCase> {
  private final Provider<PdfMergerRepository> pdfMergerRepositoryProvider;

  public MergePdfUseCase_Factory(Provider<PdfMergerRepository> pdfMergerRepositoryProvider) {
    this.pdfMergerRepositoryProvider = pdfMergerRepositoryProvider;
  }

  @Override
  public MergePdfUseCase get() {
    return newInstance(pdfMergerRepositoryProvider.get());
  }

  public static MergePdfUseCase_Factory create(
      Provider<PdfMergerRepository> pdfMergerRepositoryProvider) {
    return new MergePdfUseCase_Factory(pdfMergerRepositoryProvider);
  }

  public static MergePdfUseCase newInstance(PdfMergerRepository pdfMergerRepository) {
    return new MergePdfUseCase(pdfMergerRepository);
  }
}
