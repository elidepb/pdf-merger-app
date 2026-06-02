package com.app.fusionarpdfs.domain.usecase;

import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository;
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
public final class GetMergeResultUseCase_Factory implements Factory<GetMergeResultUseCase> {
  private final Provider<MergeSessionRepository> mergeSessionRepositoryProvider;

  private final Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider;

  public GetMergeResultUseCase_Factory(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider) {
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
    this.mergeHistoryRepositoryProvider = mergeHistoryRepositoryProvider;
  }

  @Override
  public GetMergeResultUseCase get() {
    return newInstance(mergeSessionRepositoryProvider.get(), mergeHistoryRepositoryProvider.get());
  }

  public static GetMergeResultUseCase_Factory create(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider,
      Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider) {
    return new GetMergeResultUseCase_Factory(mergeSessionRepositoryProvider, mergeHistoryRepositoryProvider);
  }

  public static GetMergeResultUseCase newInstance(MergeSessionRepository mergeSessionRepository,
      MergeHistoryRepository mergeHistoryRepository) {
    return new GetMergeResultUseCase(mergeSessionRepository, mergeHistoryRepository);
  }
}
