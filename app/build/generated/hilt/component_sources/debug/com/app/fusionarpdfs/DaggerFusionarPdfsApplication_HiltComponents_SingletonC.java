package com.app.fusionarpdfs;

import android.app.Activity;
import android.app.Service;
import android.content.ContentResolver;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.app.fusionarpdfs.data.datasource.MergeErrorMapper;
import com.app.fusionarpdfs.data.datasource.PdfContentDataSource;
import com.app.fusionarpdfs.data.datasource.PdfMergerDataSource;
import com.app.fusionarpdfs.data.datasource.UriPermissionDataSource;
import com.app.fusionarpdfs.data.preferences.MergeHistoryLocalDataSource;
import com.app.fusionarpdfs.data.preferences.UserPreferencesLocalDataSource;
import com.app.fusionarpdfs.data.repository.MergeHistoryRepositoryImpl;
import com.app.fusionarpdfs.data.repository.MergeSessionRepositoryImpl;
import com.app.fusionarpdfs.data.repository.PdfFileRepositoryImpl;
import com.app.fusionarpdfs.data.repository.PdfMergerRepositoryImpl;
import com.app.fusionarpdfs.data.repository.UserPreferencesRepositoryImpl;
import com.app.fusionarpdfs.di.AppModule_ProvideContentResolverFactory;
import com.app.fusionarpdfs.domain.usecase.AddPdfsFromUrisUseCase;
import com.app.fusionarpdfs.domain.usecase.ClearHistoryUseCase;
import com.app.fusionarpdfs.domain.usecase.ClearPdfSelectionUseCase;
import com.app.fusionarpdfs.domain.usecase.DeleteHistoryItemUseCase;
import com.app.fusionarpdfs.domain.usecase.ExecuteMergeFromSessionUseCase;
import com.app.fusionarpdfs.domain.usecase.GetMergeResultUseCase;
import com.app.fusionarpdfs.domain.usecase.MergePdfUseCase;
import com.app.fusionarpdfs.domain.usecase.OpenMergedPdfUseCase;
import com.app.fusionarpdfs.domain.usecase.PersistOutputUriPermissionUseCase;
import com.app.fusionarpdfs.domain.usecase.RemoveSelectedPdfUseCase;
import com.app.fusionarpdfs.domain.usecase.SaveHistoryUseCase;
import com.app.fusionarpdfs.domain.usecase.SaveMergeConfigurationUseCase;
import com.app.fusionarpdfs.domain.usecase.ShareMergedPdfUseCase;
import com.app.fusionarpdfs.domain.usecase.StartNewMergeUseCase;
import com.app.fusionarpdfs.domain.usecase.UpdatePdfOrderUseCase;
import com.app.fusionarpdfs.domain.usecase.ValidateMergeConfigurationUseCase;
import com.app.fusionarpdfs.domain.usecase.ValidatePdfSelectionUseCase;
import com.app.fusionarpdfs.presentation.AppViewModel;
import com.app.fusionarpdfs.presentation.AppViewModel_HiltModules;
import com.app.fusionarpdfs.presentation.history.HistoryViewModel;
import com.app.fusionarpdfs.presentation.history.HistoryViewModel_HiltModules;
import com.app.fusionarpdfs.presentation.home.HomeViewModel;
import com.app.fusionarpdfs.presentation.home.HomeViewModel_HiltModules;
import com.app.fusionarpdfs.presentation.preview.PreviewViewModel;
import com.app.fusionarpdfs.presentation.preview.PreviewViewModel_HiltModules;
import com.app.fusionarpdfs.presentation.progress.ProgressViewModel;
import com.app.fusionarpdfs.presentation.progress.ProgressViewModel_HiltModules;
import com.app.fusionarpdfs.presentation.reorder.ReorderViewModel;
import com.app.fusionarpdfs.presentation.reorder.ReorderViewModel_HiltModules;
import com.app.fusionarpdfs.presentation.result.ResultViewModel;
import com.app.fusionarpdfs.presentation.result.ResultViewModel_HiltModules;
import com.app.fusionarpdfs.presentation.settings.SettingsViewModel;
import com.app.fusionarpdfs.presentation.settings.SettingsViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerFusionarPdfsApplication_HiltComponents_SingletonC {
  private DaggerFusionarPdfsApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public FusionarPdfsApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements FusionarPdfsApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public FusionarPdfsApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements FusionarPdfsApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public FusionarPdfsApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements FusionarPdfsApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public FusionarPdfsApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements FusionarPdfsApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public FusionarPdfsApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements FusionarPdfsApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public FusionarPdfsApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements FusionarPdfsApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public FusionarPdfsApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements FusionarPdfsApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public FusionarPdfsApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends FusionarPdfsApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends FusionarPdfsApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends FusionarPdfsApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends FusionarPdfsApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(8).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_AppViewModel, AppViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_history_HistoryViewModel, HistoryViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_home_HomeViewModel, HomeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_preview_PreviewViewModel, PreviewViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_progress_ProgressViewModel, ProgressViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_reorder_ReorderViewModel, ReorderViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_result_ResultViewModel, ResultViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_settings_SettingsViewModel, SettingsViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_app_fusionarpdfs_presentation_history_HistoryViewModel = "com.app.fusionarpdfs.presentation.history.HistoryViewModel";

      static String com_app_fusionarpdfs_presentation_preview_PreviewViewModel = "com.app.fusionarpdfs.presentation.preview.PreviewViewModel";

      static String com_app_fusionarpdfs_presentation_progress_ProgressViewModel = "com.app.fusionarpdfs.presentation.progress.ProgressViewModel";

      static String com_app_fusionarpdfs_presentation_home_HomeViewModel = "com.app.fusionarpdfs.presentation.home.HomeViewModel";

      static String com_app_fusionarpdfs_presentation_reorder_ReorderViewModel = "com.app.fusionarpdfs.presentation.reorder.ReorderViewModel";

      static String com_app_fusionarpdfs_presentation_result_ResultViewModel = "com.app.fusionarpdfs.presentation.result.ResultViewModel";

      static String com_app_fusionarpdfs_presentation_settings_SettingsViewModel = "com.app.fusionarpdfs.presentation.settings.SettingsViewModel";

      static String com_app_fusionarpdfs_presentation_AppViewModel = "com.app.fusionarpdfs.presentation.AppViewModel";

      @KeepFieldType
      HistoryViewModel com_app_fusionarpdfs_presentation_history_HistoryViewModel2;

      @KeepFieldType
      PreviewViewModel com_app_fusionarpdfs_presentation_preview_PreviewViewModel2;

      @KeepFieldType
      ProgressViewModel com_app_fusionarpdfs_presentation_progress_ProgressViewModel2;

      @KeepFieldType
      HomeViewModel com_app_fusionarpdfs_presentation_home_HomeViewModel2;

      @KeepFieldType
      ReorderViewModel com_app_fusionarpdfs_presentation_reorder_ReorderViewModel2;

      @KeepFieldType
      ResultViewModel com_app_fusionarpdfs_presentation_result_ResultViewModel2;

      @KeepFieldType
      SettingsViewModel com_app_fusionarpdfs_presentation_settings_SettingsViewModel2;

      @KeepFieldType
      AppViewModel com_app_fusionarpdfs_presentation_AppViewModel2;
    }
  }

  private static final class ViewModelCImpl extends FusionarPdfsApplication_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AppViewModel> appViewModelProvider;

    private Provider<HistoryViewModel> historyViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<PreviewViewModel> previewViewModelProvider;

    private Provider<ProgressViewModel> progressViewModelProvider;

    private Provider<ReorderViewModel> reorderViewModelProvider;

    private Provider<ResultViewModel> resultViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private DeleteHistoryItemUseCase deleteHistoryItemUseCase() {
      return new DeleteHistoryItemUseCase(singletonCImpl.mergeHistoryRepositoryImplProvider.get());
    }

    private ClearHistoryUseCase clearHistoryUseCase() {
      return new ClearHistoryUseCase(singletonCImpl.mergeHistoryRepositoryImplProvider.get());
    }

    private OpenMergedPdfUseCase openMergedPdfUseCase() {
      return new OpenMergedPdfUseCase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));
    }

    private ShareMergedPdfUseCase shareMergedPdfUseCase() {
      return new ShareMergedPdfUseCase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));
    }

    private AddPdfsFromUrisUseCase addPdfsFromUrisUseCase() {
      return new AddPdfsFromUrisUseCase(singletonCImpl.pdfFileRepositoryImplProvider.get(), singletonCImpl.mergeSessionRepositoryImplProvider.get());
    }

    private RemoveSelectedPdfUseCase removeSelectedPdfUseCase() {
      return new RemoveSelectedPdfUseCase(singletonCImpl.mergeSessionRepositoryImplProvider.get(), singletonCImpl.pdfFileRepositoryImplProvider.get());
    }

    private ClearPdfSelectionUseCase clearPdfSelectionUseCase() {
      return new ClearPdfSelectionUseCase(singletonCImpl.mergeSessionRepositoryImplProvider.get(), singletonCImpl.pdfFileRepositoryImplProvider.get());
    }

    private ValidateMergeConfigurationUseCase validateMergeConfigurationUseCase() {
      return new ValidateMergeConfigurationUseCase(new ValidatePdfSelectionUseCase());
    }

    private SaveMergeConfigurationUseCase saveMergeConfigurationUseCase() {
      return new SaveMergeConfigurationUseCase(singletonCImpl.mergeSessionRepositoryImplProvider.get());
    }

    private PersistOutputUriPermissionUseCase persistOutputUriPermissionUseCase() {
      return new PersistOutputUriPermissionUseCase(singletonCImpl.pdfFileRepositoryImplProvider.get());
    }

    private MergePdfUseCase mergePdfUseCase() {
      return new MergePdfUseCase(singletonCImpl.pdfMergerRepositoryImplProvider.get());
    }

    private ExecuteMergeFromSessionUseCase executeMergeFromSessionUseCase() {
      return new ExecuteMergeFromSessionUseCase(singletonCImpl.mergeSessionRepositoryImplProvider.get(), validateMergeConfigurationUseCase(), mergePdfUseCase());
    }

    private SaveHistoryUseCase saveHistoryUseCase() {
      return new SaveHistoryUseCase(singletonCImpl.mergeHistoryRepositoryImplProvider.get());
    }

    private UpdatePdfOrderUseCase updatePdfOrderUseCase() {
      return new UpdatePdfOrderUseCase(singletonCImpl.mergeSessionRepositoryImplProvider.get());
    }

    private GetMergeResultUseCase getMergeResultUseCase() {
      return new GetMergeResultUseCase(singletonCImpl.mergeSessionRepositoryImplProvider.get(), singletonCImpl.mergeHistoryRepositoryImplProvider.get());
    }

    private StartNewMergeUseCase startNewMergeUseCase() {
      return new StartNewMergeUseCase(clearPdfSelectionUseCase(), singletonCImpl.mergeSessionRepositoryImplProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.appViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.historyViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.previewViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.progressViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.reorderViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.resultViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(8).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_AppViewModel, ((Provider) appViewModelProvider)).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_history_HistoryViewModel, ((Provider) historyViewModelProvider)).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_home_HomeViewModel, ((Provider) homeViewModelProvider)).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_preview_PreviewViewModel, ((Provider) previewViewModelProvider)).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_progress_ProgressViewModel, ((Provider) progressViewModelProvider)).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_reorder_ReorderViewModel, ((Provider) reorderViewModelProvider)).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_result_ResultViewModel, ((Provider) resultViewModelProvider)).put(LazyClassKeyProvider.com_app_fusionarpdfs_presentation_settings_SettingsViewModel, ((Provider) settingsViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_app_fusionarpdfs_presentation_progress_ProgressViewModel = "com.app.fusionarpdfs.presentation.progress.ProgressViewModel";

      static String com_app_fusionarpdfs_presentation_reorder_ReorderViewModel = "com.app.fusionarpdfs.presentation.reorder.ReorderViewModel";

      static String com_app_fusionarpdfs_presentation_AppViewModel = "com.app.fusionarpdfs.presentation.AppViewModel";

      static String com_app_fusionarpdfs_presentation_history_HistoryViewModel = "com.app.fusionarpdfs.presentation.history.HistoryViewModel";

      static String com_app_fusionarpdfs_presentation_result_ResultViewModel = "com.app.fusionarpdfs.presentation.result.ResultViewModel";

      static String com_app_fusionarpdfs_presentation_home_HomeViewModel = "com.app.fusionarpdfs.presentation.home.HomeViewModel";

      static String com_app_fusionarpdfs_presentation_settings_SettingsViewModel = "com.app.fusionarpdfs.presentation.settings.SettingsViewModel";

      static String com_app_fusionarpdfs_presentation_preview_PreviewViewModel = "com.app.fusionarpdfs.presentation.preview.PreviewViewModel";

      @KeepFieldType
      ProgressViewModel com_app_fusionarpdfs_presentation_progress_ProgressViewModel2;

      @KeepFieldType
      ReorderViewModel com_app_fusionarpdfs_presentation_reorder_ReorderViewModel2;

      @KeepFieldType
      AppViewModel com_app_fusionarpdfs_presentation_AppViewModel2;

      @KeepFieldType
      HistoryViewModel com_app_fusionarpdfs_presentation_history_HistoryViewModel2;

      @KeepFieldType
      ResultViewModel com_app_fusionarpdfs_presentation_result_ResultViewModel2;

      @KeepFieldType
      HomeViewModel com_app_fusionarpdfs_presentation_home_HomeViewModel2;

      @KeepFieldType
      SettingsViewModel com_app_fusionarpdfs_presentation_settings_SettingsViewModel2;

      @KeepFieldType
      PreviewViewModel com_app_fusionarpdfs_presentation_preview_PreviewViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.app.fusionarpdfs.presentation.AppViewModel 
          return (T) new AppViewModel(singletonCImpl.userPreferencesRepositoryImplProvider.get());

          case 1: // com.app.fusionarpdfs.presentation.history.HistoryViewModel 
          return (T) new HistoryViewModel(singletonCImpl.mergeHistoryRepositoryImplProvider.get(), viewModelCImpl.deleteHistoryItemUseCase(), viewModelCImpl.clearHistoryUseCase(), viewModelCImpl.openMergedPdfUseCase(), viewModelCImpl.shareMergedPdfUseCase());

          case 2: // com.app.fusionarpdfs.presentation.home.HomeViewModel 
          return (T) new HomeViewModel(singletonCImpl.mergeSessionRepositoryImplProvider.get(), viewModelCImpl.addPdfsFromUrisUseCase(), viewModelCImpl.removeSelectedPdfUseCase(), viewModelCImpl.clearPdfSelectionUseCase(), new ValidatePdfSelectionUseCase());

          case 3: // com.app.fusionarpdfs.presentation.preview.PreviewViewModel 
          return (T) new PreviewViewModel(singletonCImpl.mergeSessionRepositoryImplProvider.get(), singletonCImpl.userPreferencesRepositoryImplProvider.get(), viewModelCImpl.validateMergeConfigurationUseCase(), viewModelCImpl.saveMergeConfigurationUseCase(), viewModelCImpl.persistOutputUriPermissionUseCase());

          case 4: // com.app.fusionarpdfs.presentation.progress.ProgressViewModel 
          return (T) new ProgressViewModel(singletonCImpl.mergeSessionRepositoryImplProvider.get(), viewModelCImpl.executeMergeFromSessionUseCase(), viewModelCImpl.saveHistoryUseCase());

          case 5: // com.app.fusionarpdfs.presentation.reorder.ReorderViewModel 
          return (T) new ReorderViewModel(singletonCImpl.mergeSessionRepositoryImplProvider.get(), viewModelCImpl.updatePdfOrderUseCase(), viewModelCImpl.removeSelectedPdfUseCase(), new ValidatePdfSelectionUseCase());

          case 6: // com.app.fusionarpdfs.presentation.result.ResultViewModel 
          return (T) new ResultViewModel(viewModelCImpl.savedStateHandle, viewModelCImpl.getMergeResultUseCase(), viewModelCImpl.openMergedPdfUseCase(), viewModelCImpl.shareMergedPdfUseCase(), viewModelCImpl.startNewMergeUseCase());

          case 7: // com.app.fusionarpdfs.presentation.settings.SettingsViewModel 
          return (T) new SettingsViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.userPreferencesRepositoryImplProvider.get(), viewModelCImpl.clearHistoryUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends FusionarPdfsApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends FusionarPdfsApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends FusionarPdfsApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<UserPreferencesLocalDataSource> userPreferencesLocalDataSourceProvider;

    private Provider<UserPreferencesRepositoryImpl> userPreferencesRepositoryImplProvider;

    private Provider<MergeHistoryLocalDataSource> mergeHistoryLocalDataSourceProvider;

    private Provider<MergeHistoryRepositoryImpl> mergeHistoryRepositoryImplProvider;

    private Provider<MergeSessionRepositoryImpl> mergeSessionRepositoryImplProvider;

    private Provider<ContentResolver> provideContentResolverProvider;

    private Provider<PdfContentDataSource> pdfContentDataSourceProvider;

    private Provider<UriPermissionDataSource> uriPermissionDataSourceProvider;

    private Provider<PdfFileRepositoryImpl> pdfFileRepositoryImplProvider;

    private Provider<MergeErrorMapper> mergeErrorMapperProvider;

    private Provider<PdfMergerDataSource> pdfMergerDataSourceProvider;

    private Provider<PdfMergerRepositoryImpl> pdfMergerRepositoryImplProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.userPreferencesLocalDataSourceProvider = DoubleCheck.provider(new SwitchingProvider<UserPreferencesLocalDataSource>(singletonCImpl, 1));
      this.userPreferencesRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<UserPreferencesRepositoryImpl>(singletonCImpl, 0));
      this.mergeHistoryLocalDataSourceProvider = DoubleCheck.provider(new SwitchingProvider<MergeHistoryLocalDataSource>(singletonCImpl, 3));
      this.mergeHistoryRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<MergeHistoryRepositoryImpl>(singletonCImpl, 2));
      this.mergeSessionRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<MergeSessionRepositoryImpl>(singletonCImpl, 4));
      this.provideContentResolverProvider = DoubleCheck.provider(new SwitchingProvider<ContentResolver>(singletonCImpl, 7));
      this.pdfContentDataSourceProvider = DoubleCheck.provider(new SwitchingProvider<PdfContentDataSource>(singletonCImpl, 6));
      this.uriPermissionDataSourceProvider = DoubleCheck.provider(new SwitchingProvider<UriPermissionDataSource>(singletonCImpl, 8));
      this.pdfFileRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<PdfFileRepositoryImpl>(singletonCImpl, 5));
      this.mergeErrorMapperProvider = DoubleCheck.provider(new SwitchingProvider<MergeErrorMapper>(singletonCImpl, 11));
      this.pdfMergerDataSourceProvider = DoubleCheck.provider(new SwitchingProvider<PdfMergerDataSource>(singletonCImpl, 10));
      this.pdfMergerRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<PdfMergerRepositoryImpl>(singletonCImpl, 9));
    }

    @Override
    public void injectFusionarPdfsApplication(FusionarPdfsApplication fusionarPdfsApplication) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.app.fusionarpdfs.data.repository.UserPreferencesRepositoryImpl 
          return (T) new UserPreferencesRepositoryImpl(singletonCImpl.userPreferencesLocalDataSourceProvider.get());

          case 1: // com.app.fusionarpdfs.data.preferences.UserPreferencesLocalDataSource 
          return (T) new UserPreferencesLocalDataSource(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.app.fusionarpdfs.data.repository.MergeHistoryRepositoryImpl 
          return (T) new MergeHistoryRepositoryImpl(singletonCImpl.mergeHistoryLocalDataSourceProvider.get());

          case 3: // com.app.fusionarpdfs.data.preferences.MergeHistoryLocalDataSource 
          return (T) new MergeHistoryLocalDataSource(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.app.fusionarpdfs.data.repository.MergeSessionRepositoryImpl 
          return (T) new MergeSessionRepositoryImpl();

          case 5: // com.app.fusionarpdfs.data.repository.PdfFileRepositoryImpl 
          return (T) new PdfFileRepositoryImpl(singletonCImpl.pdfContentDataSourceProvider.get(), singletonCImpl.uriPermissionDataSourceProvider.get());

          case 6: // com.app.fusionarpdfs.data.datasource.PdfContentDataSource 
          return (T) new PdfContentDataSource(singletonCImpl.provideContentResolverProvider.get());

          case 7: // android.content.ContentResolver 
          return (T) AppModule_ProvideContentResolverFactory.provideContentResolver(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 8: // com.app.fusionarpdfs.data.datasource.UriPermissionDataSource 
          return (T) new UriPermissionDataSource(singletonCImpl.provideContentResolverProvider.get());

          case 9: // com.app.fusionarpdfs.data.repository.PdfMergerRepositoryImpl 
          return (T) new PdfMergerRepositoryImpl(singletonCImpl.pdfMergerDataSourceProvider.get(), singletonCImpl.mergeErrorMapperProvider.get());

          case 10: // com.app.fusionarpdfs.data.datasource.PdfMergerDataSource 
          return (T) new PdfMergerDataSource(singletonCImpl.provideContentResolverProvider.get(), singletonCImpl.mergeErrorMapperProvider.get());

          case 11: // com.app.fusionarpdfs.data.datasource.MergeErrorMapper 
          return (T) new MergeErrorMapper();

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
