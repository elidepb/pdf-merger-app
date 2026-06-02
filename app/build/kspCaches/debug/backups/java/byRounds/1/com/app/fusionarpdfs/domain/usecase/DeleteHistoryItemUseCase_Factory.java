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
public final class DeleteHistoryItemUseCase_Factory implements Factory<DeleteHistoryItemUseCase> {
  private final Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider;

  public DeleteHistoryItemUseCase_Factory(
      Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider) {
    this.mergeHistoryRepositoryProvider = mergeHistoryRepositoryProvider;
  }

  @Override
  public DeleteHistoryItemUseCase get() {
    return newInstance(mergeHistoryRepositoryProvider.get());
  }

  public static DeleteHistoryItemUseCase_Factory create(
      Provider<MergeHistoryRepository> mergeHistoryRepositoryProvider) {
    return new DeleteHistoryItemUseCase_Factory(mergeHistoryRepositoryProvider);
  }

  public static DeleteHistoryItemUseCase newInstance(
      MergeHistoryRepository mergeHistoryRepository) {
    return new DeleteHistoryItemUseCase(mergeHistoryRepository);
  }
}
