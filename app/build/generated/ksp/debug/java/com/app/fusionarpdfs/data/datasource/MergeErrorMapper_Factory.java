package com.app.fusionarpdfs.data.datasource;

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
public final class MergeErrorMapper_Factory implements Factory<MergeErrorMapper> {
  @Override
  public MergeErrorMapper get() {
    return newInstance();
  }

  public static MergeErrorMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MergeErrorMapper newInstance() {
    return new MergeErrorMapper();
  }

  private static final class InstanceHolder {
    private static final MergeErrorMapper_Factory INSTANCE = new MergeErrorMapper_Factory();
  }
}
