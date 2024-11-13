package com.avgames.jumpingdino.utils

import android.annotation.SuppressLint
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import kotlin.math.sqrt

object MicHelper {
    @SuppressLint("MissingPermission")
    fun startListning(onAudioReceived: (rms: Double) -> Unit) {
        val bufferSize = AudioRecord.getMinBufferSize(
            44100, // Sample rate in Hz
            AudioFormat.CHANNEL_IN_MONO, // Channel configuration
            AudioFormat.ENCODING_PCM_16BIT // Audio encoding
        )

        val audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC, // Use MIC as audio source
            44100,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize
        )

        val buffer = ShortArray(bufferSize)

        audioRecord.startRecording()

        Thread {
            while (true) {
                val read = audioRecord.read(buffer, 0, buffer.size)
                if (read > 0) {
                    val rms = calculateRMS(buffer)
                    onAudioReceived(rms)
                    Log.e("lucifer", "Loudness (RMS): $rms")
                }
            }
        }.start()
    }

    fun calculateRMS(buffer: ShortArray): Double {
        var sum = 0.0
        for (sample in buffer) {
            sum += sample * sample
        }
        return sqrt(sum / buffer.size)
    }

}