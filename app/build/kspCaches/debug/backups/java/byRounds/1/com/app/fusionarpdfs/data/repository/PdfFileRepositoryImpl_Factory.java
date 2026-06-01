package com.app.fusionarpdfs.data.repository;

import com.app.fusionarpdfs.data.datasource.PdfContentDataSource;
import com.app.fusionarpdfs.data.datasource.UriPermissionDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class PdfFileRepositoryImpl_Factory implements Factory<PdfFileRepositoryImpl> {
  private final Provider<PdfContentDataSource> pdfContentDataSourceProvider;

  private final Provider<UriPermissionDataSource> uriPermissionDataSourceProvider;

  public PdfFileRepositoryImpl_Factory(Provider<PdfContentDataSource> pdfContentDataSourceProvider,
      Provider<UriPermissionDataSource> uriPermissionDataSourceProvider) {
    this.pdfContentDataSourceProvider = pdfContentDataSourceProvider;
    this.uriPermissionDataSourceProvider = uriPermissionDataSourceProvider;
  }

  @Override
  public PdfFileRepositoryImpl get() {
    return newInstance(pdfContentDataSourceProvider.get(), uriPermissionDataSourceProvider.get());
  }

  public static PdfFileRepositoryImpl_Factory create(
      Provider<PdfContentDataSource> pdfContentDataSourceProvider,
      Provider<UriPermissionDataSource> uriPermissionDataSourceProvider) {
    return new PdfFileRepositoryImpl_Factory(pdfContentDataSourceProvider, uriPermissionDataSourceProvider);
  }

  public static PdfFileRepositoryImpl newInstance(PdfContentDataSource pdfContentDataSource,
      UriPermissionDataSource uriPermissionDataSource) {
    return new PdfFileRepositoryImpl(pdfContentDataSource, uriPermissionDataSource);
  }
}
