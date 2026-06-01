package com.app.fusionarpdfs.data.datasource;

import android.content.ContentResolver;
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
public final class PdfMergerDataSource_Factory implements Factory<PdfMergerDataSource> {
  private final Provider<ContentResolver> contentResolverProvider;

  private final Provider<MergeErrorMapper> mergeErrorMapperProvider;

  public PdfMergerDataSource_Factory(Provider<ContentResolver> contentResolverProvider,
      Provider<MergeErrorMapper> mergeErrorMapperProvider) {
    this.contentResolverProvider = contentResolverProvider;
    this.mergeErrorMapperProvider = mergeErrorMapperProvider;
  }

  @Override
  public PdfMergerDataSource get() {
    return newInstance(contentResolverProvider.get(), mergeErrorMapperProvider.get());
  }

  public static PdfMergerDataSource_Factory create(
      Provider<ContentResolver> contentResolverProvider,
      Provider<MergeErrorMapper> mergeErrorMapperProvider) {
    return new PdfMergerDataSource_Factory(contentResolverProvider, mergeErrorMapperProvider);
  }

  public static PdfMergerDataSource newInstance(ContentResolver contentResolver,
      MergeErrorMapper mergeErrorMapper) {
    return new PdfMergerDataSource(contentResolver, mergeErrorMapper);
  }
}
