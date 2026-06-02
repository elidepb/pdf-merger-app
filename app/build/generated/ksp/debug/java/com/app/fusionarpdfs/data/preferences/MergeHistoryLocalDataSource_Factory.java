package com.app.fusionarpdfs.data.preferences;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class MergeHistoryLocalDataSource_Factory implements Factory<MergeHistoryLocalDataSource> {
  private final Provider<Context> contextProvider;

  public MergeHistoryLocalDataSource_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public MergeHistoryLocalDataSource get() {
    return newInstance(contextProvider.get());
  }

  public static MergeHistoryLocalDataSource_Factory create(Provider<Context> contextProvider) {
    return new MergeHistoryLocalDataSource_Factory(contextProvider);
  }

  public static MergeHistoryLocalDataSource newInstance(Context context) {
    return new MergeHistoryLocalDataSource(context);
  }
}
