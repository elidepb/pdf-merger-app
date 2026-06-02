package com.app.fusionarpdfs.domain.usecase;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class OpenMergedPdfUseCase_Factory implements Factory<OpenMergedPdfUseCase> {
  private final Provider<Context> contextProvider;

  public OpenMergedPdfUseCase_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OpenMergedPdfUseCase get() {
    return newInstance(contextProvider.get());
  }

  public static OpenMergedPdfUseCase_Factory create(Provider<Context> contextProvider) {
    return new OpenMergedPdfUseCase_Factory(contextProvider);
  }

  public static OpenMergedPdfUseCase newInstance(Context context) {
    return new OpenMergedPdfUseCase(context);
  }
}
