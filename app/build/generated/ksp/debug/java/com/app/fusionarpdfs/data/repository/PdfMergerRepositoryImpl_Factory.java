package com.app.fusionarpdfs.data.repository;

import com.app.fusionarpdfs.data.datasource.MergeErrorMapper;
import com.app.fusionarpdfs.data.datasource.PdfMergerDataSource;
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
public final class PdfMergerRepositoryImpl_Factory implements Factory<PdfMergerRepositoryImpl> {
  private final Provider<PdfMergerDataSource> pdfMergerDataSourceProvider;

  private final Provider<MergeErrorMapper> mergeErrorMapperProvider;

  public PdfMergerRepositoryImpl_Factory(Provider<PdfMergerDataSource> pdfMergerDataSourceProvider,
      Provider<MergeErrorMapper> mergeErrorMapperProvider) {
    this.pdfMergerDataSourceProvider = pdfMergerDataSourceProvider;
    this.mergeErrorMapperProvider = mergeErrorMapperProvider;
  }

  @Override
  public PdfMergerRepositoryImpl get() {
    return newInstance(pdfMergerDataSourceProvider.get(), mergeErrorMapperProvider.get());
  }

  public static PdfMergerRepositoryImpl_Factory create(
      Provider<PdfMergerDataSource> pdfMergerDataSourceProvider,
      Provider<MergeErrorMapper> mergeErrorMapperProvider) {
    return new PdfMergerRepositoryImpl_Factory(pdfMergerDataSourceProvider, mergeErrorMapperProvider);
  }

  public static PdfMergerRepositoryImpl newInstance(PdfMergerDataSource pdfMergerDataSource,
      MergeErrorMapper mergeErrorMapper) {
    return new PdfMergerRepositoryImpl(pdfMergerDataSource, mergeErrorMapper);
  }
}
