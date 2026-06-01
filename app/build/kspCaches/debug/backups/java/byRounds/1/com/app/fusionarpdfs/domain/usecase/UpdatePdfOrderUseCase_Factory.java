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
public final class UpdatePdfOrderUseCase_Factory implements Factory<UpdatePdfOrderUseCase> {
  private final Provider<MergeSessionRepository> mergeSessionRepositoryProvider;

  public UpdatePdfOrderUseCase_Factory(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider) {
    this.mergeSessionRepositoryProvider = mergeSessionRepositoryProvider;
  }

  @Override
  public UpdatePdfOrderUseCase get() {
    return newInstance(mergeSessionRepositoryProvider.get());
  }

  public static UpdatePdfOrderUseCase_Factory create(
      Provider<MergeSessionRepository> mergeSessionRepositoryProvider) {
    return new UpdatePdfOrderUseCase_Factory(mergeSessionRepositoryProvider);
  }

  public static UpdatePdfOrderUseCase newInstance(MergeSessionRepository mergeSessionRepository) {
    return new UpdatePdfOrderUseCase(mergeSessionRepository);
  }
}
