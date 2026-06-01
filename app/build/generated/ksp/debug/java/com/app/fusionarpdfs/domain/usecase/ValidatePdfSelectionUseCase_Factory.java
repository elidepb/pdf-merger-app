package com.app.fusionarpdfs.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class ValidatePdfSelectionUseCase_Factory implements Factory<ValidatePdfSelectionUseCase> {
  @Override
  public ValidatePdfSelectionUseCase get() {
    return newInstance();
  }

  public static ValidatePdfSelectionUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ValidatePdfSelectionUseCase newInstance() {
    return new ValidatePdfSelectionUseCase();
  }

  private static final class InstanceHolder {
    private static final ValidatePdfSelectionUseCase_Factory INSTANCE = new ValidatePdfSelectionUseCase_Factory();
  }
}
