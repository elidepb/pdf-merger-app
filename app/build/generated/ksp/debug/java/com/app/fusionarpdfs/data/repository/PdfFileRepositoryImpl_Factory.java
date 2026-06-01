package com.app.fusionarpdfs.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
  @Override
  public PdfFileRepositoryImpl get() {
    return newInstance();
  }

  public static PdfFileRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PdfFileRepositoryImpl newInstance() {
    return new PdfFileRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final PdfFileRepositoryImpl_Factory INSTANCE = new PdfFileRepositoryImpl_Factory();
  }
}
