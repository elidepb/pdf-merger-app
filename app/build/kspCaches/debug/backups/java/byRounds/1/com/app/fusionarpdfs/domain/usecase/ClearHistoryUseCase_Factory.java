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
public final class ClearHistoryUseCase_Factory implements Factory<ClearHistoryUseCase> {
  private final Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider;

  public ClearHistoryUseCase_Factory(
      Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider) {
    this.mergeHistoryRepositoryProvider = mergeHistoryRepositoryProvider;
  }

  @Override
  public ClearHistoryUseCase get() {
    return newInstance(mergeHistoryRepositoryProvider.get());
  }

  public static ClearHistoryUseCase_Factory create(
      Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider) {
    return new ClearHistoryUseCase_Factory(mergeHistoryRepositoryProvider);
  }

  public static ClearHistoryUseCase newInstance(MergeHistoryRepository mergeHistoryRepository) {
    return new ClearHistoryUseCase(mergeHistoryRepository);
  }
}
