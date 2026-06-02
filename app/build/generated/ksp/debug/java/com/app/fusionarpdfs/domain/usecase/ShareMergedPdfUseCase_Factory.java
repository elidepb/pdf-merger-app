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
public final class ShareMergedPdfUseCase_Factory implements Factory<ShareMergedPdfUseCase> {
  private final Provider<Context> contextProvider;

  private final Provider<PdfFileRepository> pdfFileRepositoryProvider;

  public ShareMergedPdfUseCase_Factory(Provider<Context> contextProvider,
      Provider<PdfFileRepository> pdfFileRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.pdfFileRepositoryProvider = pdfFileRepositoryProvider;
  }

  @Override
  public ShareMergedPdfUseCase get() {
    return newInstance(contextProvider.get(), pdfFileRepositoryProvider.get());
  }

  public static ShareMergedPdfUseCase_Factory create(Provider<Context> contextProvider,
      Provider<PdfFileRepository> pdfFileRepositoryProvider) {
    return new ShareMergedPdfUseCase_Factory(contextProvider, pdfFileRepositoryProvider);
  }

  public static ShareMergedPdfUseCase newInstance(Context context,
      PdfFileRepository pdfFileRepository) {
    return new ShareMergedPdfUseCase(context, pdfFileRepository);
  }
}
