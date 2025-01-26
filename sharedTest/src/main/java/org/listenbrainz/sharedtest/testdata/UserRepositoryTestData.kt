package org.listenbrainz.sharedtest.testdata

import com.google.gson.Gson
import org.listenbrainz.android.model.CurrentPins
import org.listenbrainz.android.model.Listens
import org.listenbrainz.android.model.Payload
import org.listenbrainz.android.model.createdForYou.CreatedForYouPayload
import org.listenbrainz.android.model.createdForYou.CreatedForYouPlaylist
import org.listenbrainz.android.model.createdForYou.CreatedForYouPlaylists
import org.listenbrainz.android.model.user.AllPinnedRecordings
import org.listenbrainz.android.model.user.TopAlbums
import org.listenbrainz.android.model.user.TopArtists
import org.listenbrainz.android.model.user.TopSongs
import org.listenbrainz.android.model.user.UserFeedback
import org.listenbrainz.android.model.user.UserListeningActivity
import org.listenbrainz.android.model.user.UserSimilarity
import org.listenbrainz.android.model.user.UserSimilarityPayload
import org.listenbrainz.sharedtest.utils.ResourceString.all_pins
import org.listenbrainz.sharedtest.utils.ResourceString.current_pins
import org.listenbrainz.sharedtest.utils.ResourceString.globalListeningActivity
import org.listenbrainz.sharedtest.utils.ResourceString.loved_hated_songs
import org.listenbrainz.sharedtest.utils.ResourceString.topAlbums
import org.listenbrainz.sharedtest.utils.ResourceString.topSongs
import org.listenbrainz.sharedtest.utils.ResourceString.top_artists
import org.listenbrainz.sharedtest.utils.ResourceString.userListeningActivity

object UserRepositoryTestData {
    val listenCountTestData: Listens = Listens(
        payload = Payload(
            count = 3252,
            latest_listen_ts = 0,
            listens = listOf(),
            user_id = "Jasjeet"
        )
    )

    val createdForYouPlaylistsTestData: CreatedForYouPayload = CreatedForYouPayload(
        playlists = listOf(
            CreatedForYouPlaylists(
                playlist = CreatedForYouPlaylist(
                title = "Playlist 1",
                )
            )
        )
    )

    val userSimilarityTestData: UserSimilarityPayload = UserSimilarityPayload(
        userSimilarity = UserSimilarity(
            similarity = 0.2580655f,
            username = "Shubhi"
        )
    )

    val currentPinsTestData: CurrentPins?
        get() = Gson().fromJson(current_pins, CurrentPins::class.java)

    val allPinsTestData: AllPinnedRecordings?
        get() = Gson().fromJson(all_pins, AllPinnedRecordings::class.java)

    val topArtistsTestData: TopArtists?
        get() = Gson().fromJson(top_artists, TopArtists::class.java)

    val lovedHatedSongsTestData: UserFeedback?
        get() = Gson().fromJson(loved_hated_songs, UserFeedback::class.java)

    val listeningActivityTestData: UserListeningActivity?
        get() = Gson().fromJson(userListeningActivity, UserListeningActivity::class.java)

    val globalListeningActivityTestData: UserListeningActivity?
        get() = Gson().fromJson(globalListeningActivity, UserListeningActivity::class.java)

    val topAlbumsTestData: TopAlbums?
        get() = Gson().fromJson(topAlbums, TopAlbums::class.java)

    val topSongsTestData: TopSongs?
        get() = Gson().fromJson(topSongs, TopSongs::class.java)
}