package com.app.fusionarpdfs.domain.usecase;

import com.app.fusionarpdfs.domain.repository.MergeSessionRepository;
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
public final class SaveMergeConfigurationUseCase_Factory implements Factory<SaveMergeConfigurationUseCase> {
  private final Provider<MergeSessionRepository> mergeSessionRepositoryProvider;

  public SaveMergeConfigurationUseCase_Factory(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider) {
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
  }

  @Override
  public SaveMergeConfigurationUseCase get() {
    return newInstance(mergeSessionRepositoryProvider.get());
  }

  public static SaveMergeConfigurationUseCase_Factory create(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider) {
    return new SaveMergeConfigurationUseCase_Factory(mergeSessionRepositoryProvider);
  }

  public static SaveMergeConfigurationUseCase newInstance(
      MergeSessionRepository mergeSessionRepository) {
    return new SaveMergeConfigurationUseCase(mergeSessionRepository);
  }
}
