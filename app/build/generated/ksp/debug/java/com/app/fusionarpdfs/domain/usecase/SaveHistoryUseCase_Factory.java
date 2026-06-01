package com.app.fusionarpdfs.domain.usecase;

import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository;
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
public final class SaveHistoryUseCase_Factory implements Factory<SaveHistoryUseCase> {
  private final Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider;

  public SaveHistoryUseCase_Factory(
      Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider) {
    this.mergeHistoryRepositoryProvider = mergeHistoryRepositoryProvider;
  }

  @Override
  public SaveHistoryUseCase get() {
    return newInstance(mergeHistoryRepositoryProvider.get());
  }

  public static SaveHistoryUseCase_Factory create(
      Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider) {
    return new SaveHistoryUseCase_Factory(mergeHistoryRepositoryProvider);
  }

  public static SaveHistoryUseCase newInstance(MergeHistoryRepository mergeHistoryRepository) {
    return new SaveHistoryUseCase(mergeHistoryRepository);
  }
}
