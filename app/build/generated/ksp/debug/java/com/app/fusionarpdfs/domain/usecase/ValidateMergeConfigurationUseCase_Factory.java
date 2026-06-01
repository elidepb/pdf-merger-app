package com.app.fusionarpdfs.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ValidateMergeConfigurationUseCase_Factory implements Factory<ValidateMergeConfigurationUseCase> {
  private final Provider<ValidatePdfSelectionUseCase> validatePdfSelectionUseCaseProvider;

  public ValidateMergeConfigurationUseCase_Factory(
      Provider<ValidatePdfSelectionUseCase> validatePdfSelectionUseCaseProvider) {
    this.validatePdfSelectionUseCaseProvider = validatePdfSelectionUseCaseProvider;
  }

  @Override
  public ValidateMergeConfigurationUseCase get() {
    return newInstance(validatePdfSelectionUseCaseProvider.get());
  }

  public static ValidateMergeConfigurationUseCase_Factory create(
      Provider<ValidatePdfSelectionUseCase> validatePdfSelectionUseCaseProvider) {
    return new ValidateMergeConfigurationUseCase_Factory(validatePdfSelectionUseCaseProvider);
  }

  public static ValidateMergeConfigurationUseCase newInstance(
      ValidatePdfSelectionUseCase validatePdfSelectionUseCase) {
    return new ValidateMergeConfigurationUseCase(validatePdfSelectionUseCase);
  }
}
