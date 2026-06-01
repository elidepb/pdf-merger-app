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
public final class PdfMergerRepositoryImpl_Factory implements Factory<PdfMergerRepositoryImpl> {
  @Override
  public PdfMergerRepositoryImpl get() {
    return newInstance();
  }

  public static PdfMergerRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PdfMergerRepositoryImpl newInstance() {
    return new PdfMergerRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final PdfMergerRepositoryImpl_Factory INSTANCE = new PdfMergerRepositoryImpl_Factory();
  }
}
