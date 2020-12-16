/*
 * Copyright (c) 2020 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.features.call

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.isVisible
import im.vector.app.R
import im.vector.app.databinding.ViewCallControlsBinding

import org.matrix.android.sdk.api.session.call.CallState
import org.webrtc.PeerConnection

class CallControlsView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val views: ViewCallControlsBinding

    var interactionListener: InteractionListener? = null

    init {
        inflate(context, R.layout.view_call_controls, this)
        // layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        views = ViewCallControlsBinding.bind(this)

        views.ringingControlAccept.setOnClickListener { acceptIncomingCall() }
        views.ringingControlDecline.setOnClickListener { declineIncomingCall() }
        views.ivEndCall.setOnClickListener { endOngoingCall() }
        views.muteIcon.setOnClickListener { toggleMute() }
        views.videoToggleIcon.setOnClickListener { toggleVideo() }
        views.ivLeftMiniControl.setOnClickListener { returnToChat() }
        views.ivMore.setOnClickListener { moreControlOption() }
    }

    private fun acceptIncomingCall() {
        interactionListener?.didAcceptIncomingCall()
    }

    private fun declineIncomingCall() {
        interactionListener?.didDeclineIncomingCall()
    }

    private fun endOngoingCall() {
        interactionListener?.didEndCall()
    }

    private fun toggleMute() {
        interactionListener?.didTapToggleMute()
    }

    private fun toggleVideo() {
        interactionListener?.didTapToggleVideo()
    }

    private fun returnToChat() {
        interactionListener?.returnToChat()
    }

    private fun moreControlOption() {
        interactionListener?.didTapMore()
    }

    fun updateForState(state: VectorCallViewState) {
        val callState = state.callState.invoke()
        if (state.isAudioMuted) {
            views.muteIcon.setImageResource(R.drawable.ic_microphone_off)
            views.muteIcon.contentDescription = resources.getString(R.string.a11y_unmute_microphone)
        } else {
            views.muteIcon.setImageResource(R.drawable.ic_microphone_on)
            views.muteIcon.contentDescription = resources.getString(R.string.a11y_mute_microphone)
        }
        if (state.isVideoEnabled) {
            views.videoToggleIcon.setImageResource(R.drawable.ic_video)
            views.videoToggleIcon.contentDescription = resources.getString(R.string.a11y_stop_camera)
        } else {
          views.videoToggleIcon.setImageResource(R.drawable.ic_video_off)
            views.videoToggleIcon.contentDescription = resources.getString(R.string.a11y_start_camera)
        }

        when (callState) {
            is CallState.Idle,
            is CallState.Dialing,
            is CallState.Answering    -> {
                views.ringingControls.isVisible = true
                views.ringingControlAccept.isVisible = false
                views.ringingControlDecline.isVisible = true
                views.connectedControls.isVisible = false
            }
            is CallState.LocalRinging -> {
                views.ringingControls.isVisible = true
                views.ringingControlAccept.isVisible = true
                views.ringingControlDecline.isVisible = true
                views.connectedControls.isVisible = false
            }
            is CallState.Connected    -> {
                if (callState.iceConnectionState == PeerConnection.PeerConnectionState.CONNECTED) {
                    views.ringingControls.isVisible = false
                    views.connectedControls.isVisible = true
                    views.videoToggleIcon.isVisible = state.isVideoCall
                } else {
                    views.ringingControls.isVisible = true
                    views.ringingControlAccept.isVisible = false
                    views.ringingControlDecline.isVisible = true
                    views.connectedControls.isVisible = false
                }
            }
            is CallState.Terminated,
            null                      -> {
                views.ringingControls.isVisible = false
                views.connectedControls.isVisible = false
            }
        }
    }

    interface InteractionListener {
        fun didAcceptIncomingCall()
        fun didDeclineIncomingCall()
        fun didEndCall()
        fun didTapToggleMute()
        fun didTapToggleVideo()
        fun returnToChat()
        fun didTapMore()
    }
}
