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
public final class UriPermissionDataSource_Factory implements Factory<UriPermissionDataSource> {
  private final Provider<ContentResolver> contentResolverProvider;

  public UriPermissionDataSource_Factory(Provider<ContentResolver> contentResolverProvider) {
    this.contentResolverProvider = contentResolverProvider;
  }

  @Override
  public UriPermissionDataSource get() {
    return newInstance(contentResolverProvider.get());
  }

  public static UriPermissionDataSource_Factory create(
      Provider<ContentResolver> contentResolverProvider) {
    return new UriPermissionDataSource_Factory(contentResolverProvider);
  }

  public static UriPermissionDataSource newInstance(ContentResolver contentResolver) {
    return new UriPermissionDataSource(contentResolver);
  }
}
