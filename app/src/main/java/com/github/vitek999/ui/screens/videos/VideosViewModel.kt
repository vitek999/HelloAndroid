package com.github.vitek999.ui.screens.videos

import androidx.lifecycle.viewModelScope
import com.github.vitek999.base.BaseViewModel
import com.github.vitek999.data.videos.VideosRepository
import com.github.vitek999.ui.screens.videos.models.VideosAction
import com.github.vitek999.ui.screens.videos.models.VideosEvent
import com.github.vitek999.ui.screens.videos.models.VideosState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(private val repository: VideosRepository) :
    BaseViewModel<VideosState, VideosAction, VideosEvent>(initialState = VideosState()) {

    override fun obtainEvent(viewEvent: VideosEvent) {
        when (viewEvent) {
            VideosEvent.ScreenShown -> fetchVideos()
            is VideosEvent.VideoClicked -> obtainVideoClicked(viewEvent.videoId)
            VideosEvent.ClearAction -> clearAction()
        }
    }

    private fun obtainVideoClicked(videoId: Long) {
        viewModelScope.launch {
            viewAction = VideosAction.OpenVideoPage(videoId)
        }
    }

    private fun clearAction() {
        viewModelScope.launch {
            viewAction = null
        }
    }

    private fun fetchVideos() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            val videos = repository.findAll()
            viewState = viewState.copy(items = videos, isLoading = false)
        }
    }
}