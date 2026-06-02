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
public final class ShareMergedPdfUseCase_Factory implements Factory<ShareMergedPdfUseCase> {
  private final Provider<Context> contextProvider;

  public ShareMergedPdfUseCase_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ShareMergedPdfUseCase get() {
    return newInstance(contextProvider.get());
  }

  public static ShareMergedPdfUseCase_Factory create(Provider<Context> contextProvider) {
    return new ShareMergedPdfUseCase_Factory(contextProvider);
  }

  public static ShareMergedPdfUseCase newInstance(Context context) {
    return new ShareMergedPdfUseCase(context);
  }
}
