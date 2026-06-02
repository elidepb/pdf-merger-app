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
public final class UserPreferencesLocalDataSource_Factory implements Factory<UserPreferencesLocalDataSource> {
  private final Provider<Context> contextProvider;

  public UserPreferencesLocalDataSource_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public UserPreferencesLocalDataSource get() {
    return newInstance(contextProvider.get());
  }

  public static UserPreferencesLocalDataSource_Factory create(Provider<Context> contextProvider) {
    return new UserPreferencesLocalDataSource_Factory(contextProvider);
  }

  public static UserPreferencesLocalDataSource newInstance(Context context) {
    return new UserPreferencesLocalDataSource(context);
  }
}
