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
public final class PdfContentDataSource_Factory implements Factory<PdfContentDataSource> {
  private final Provider<ContentResolver> contentResolverProvider;

  public PdfContentDataSource_Factory(Provider<ContentResolver> contentResolverProvider) {
    this.contentResolverProvider = contentResolverProvider;
  }

  @Override
  public PdfContentDataSource get() {
    return newInstance(contentResolverProvider.get());
  }

  public static PdfContentDataSource_Factory create(
      Provider<ContentResolver> contentResolverProvider) {
    return new PdfContentDataSource_Factory(contentResolverProvider);
  }

  public static PdfContentDataSource newInstance(ContentResolver contentResolver) {
    return new PdfContentDataSource(contentResolver);
  }
}
