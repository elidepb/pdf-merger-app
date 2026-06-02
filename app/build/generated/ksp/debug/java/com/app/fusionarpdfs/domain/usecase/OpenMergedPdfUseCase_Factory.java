package com.app.fusionarpdfs.domain.usecase;

import android.content.Context;
import com.app.fusionarpdfs.domain.repository.PdfFileRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class OpenMergedPdfUseCase_Factory implements Factory<OpenMergedPdfUseCase> {
  private final Provider<Context> contextProvider;

  private final Provider<PdfFileRepository> pdfFileRepositoryProvider;

  public OpenMergedPdfUseCase_Factory(Provider<Context> contextProvider,
      Provider<PdfFileRepository> pdfFileRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.pdfFileRepositoryProvider = pdfFileRepositoryProvider;
  }

  @Override
  public OpenMergedPdfUseCase get() {
    return newInstance(contextProvider.get(), pdfFileRepositoryProvider.get());
  }

  public static OpenMergedPdfUseCase_Factory create(Provider<Context> contextProvider,
      Provider<PdfFileRepository> pdfFileRepositoryProvider) {
    return new OpenMergedPdfUseCase_Factory(contextProvider, pdfFileRepositoryProvider);
  }

  public static OpenMergedPdfUseCase newInstance(Context context,
      PdfFileRepository pdfFileRepository) {
    return new OpenMergedPdfUseCase(context, pdfFileRepository);
  }
}
