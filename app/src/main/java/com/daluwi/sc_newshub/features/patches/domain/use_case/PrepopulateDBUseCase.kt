package com.daluwi.sc_newshub.features.patches.domain.use_case

import com.daluwi.sc_newshub.features.patches.domain.models.Channel
import com.daluwi.sc_newshub.features.patches.domain.models.Patch
import com.daluwi.sc_newshub.features.patches.domain.models.Version
import com.daluwi.sc_newshub.features.patches.domain.models.Wave
import com.daluwi.sc_newshub.features.patches.domain.repository.PatchRepository

/**
 * TODO For testing purposes only. Delete after implementing proper loading of live patches from official sources
 */
class PrepopulateDBUseCase(
    private val repository: PatchRepository
) {
    suspend operator fun invoke() {
        repository.deleteAll()
        repository.insertAll(
            listOf(
                Patch(
                    "https://robertsspaceindustries.com/spectrum/community/SC/forum/190048/thread/star-citizen-alpha-4-3-2-live-release-notes",
                    Channel.Live,
                    Version(4, 3, 2),
                    "10452200",
                    pinned = true
                ),
                Patch(
                    "https://robertsspaceindustries.com/spectrum/community/SC/forum/190048/thread/star-citizen-alpha-4-3-0-live-hotfix-central",
                    Channel.Hotfix,
                    Version(4, 3, 2),
                    "10480022",
                    pinned = true
                ),
                Patch(
                    "https://robertsspaceindustries.com/spectrum/community/SC/forum/190048/thread/star-citizen-alpha-4-3-2-ptu-patch-notes-6",
                    Channel.PTU(Wave.AllBackers),
                    Version(4, 3, 2),
                    "10446088"
                ),
                Patch(
                    "https://robertsspaceindustries.com/spectrum/community/SC/forum/190048/thread/star-citizen-alpha-4-3-2-ptu-patch-notes-7",
                    Channel.PTU(Wave.AllBackers),
                    Version(4, 3, 2),
                    "10452200"
                ),
                Patch(
                    "https://robertsspaceindustries.com/spectrum/community/SC/forum/190048/thread/star-citizen-alpha-4-3-2-ptu-patch-notes-5",
                    Channel.PTU(Wave.AllBackers),
                    Version(4, 3, 2),
                    "10438443"
                ),
                Patch(
                    "https://robertsspaceindustries.com/spectrum/community/SC/forum/190048/thread/star-citizen-alpha-4-3-2-ptu-patch-notes-4",
                    Channel.PTU(Wave.AllBackers),
                    Version(4, 3, 2),
                    "10433370"
                ),
                Patch(
                    "https://robertsspaceindustries.com/spectrum/community/SC/forum/190048/thread/star-citizen-alpha-4-3-2-ptu-patch-notes-3",
                    Channel.PTU(Wave.AllBackers),
                    Version(4, 3, 2),
                    "10422586"
                ),
                Patch(
                    "https://robertsspaceindustries.com/spectrum/community/SC/forum/190048/thread/star-citizen-alpha-4-3-2-ptu-patch-notes-2",
                    Channel.PTU(Wave.AllBackers),
                    Version(4, 3, 2),
                    "10407435"
                ),
            )
        )
    }
}