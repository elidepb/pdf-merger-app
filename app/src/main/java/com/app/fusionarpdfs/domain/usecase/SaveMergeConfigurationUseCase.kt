package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.domain.model.MergeConfiguration
import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import javax.inject.Inject

class SaveMergeConfigurationUseCase @Inject constructor(
    private val mergeSessionRepository: MergeSessionRepository,
) {

    operator fun invoke(configuration: MergeConfiguration) {
        mergeSessionRepository.setMergeConfiguration(configuration)
    }
}
