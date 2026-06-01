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
public final class MergeHistoryRepositoryImpl_Factory implements Factory<MergeHistoryRepositoryImpl> {
  @Override
  public MergeHistoryRepositoryImpl get() {
    return newInstance();
  }

  public static MergeHistoryRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MergeHistoryRepositoryImpl newInstance() {
    return new MergeHistoryRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final MergeHistoryRepositoryImpl_Factory INSTANCE = new MergeHistoryRepositoryImpl_Factory();
  }
}
