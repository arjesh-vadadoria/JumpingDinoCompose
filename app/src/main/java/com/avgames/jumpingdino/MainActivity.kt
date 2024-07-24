package com.avgames.jumpingdino

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.avgames.jumpingdino.presentation.GameScene
import com.avgames.jumpingdino.presentation.deviceHeightInPixels
import com.avgames.jumpingdino.presentation.deviceWidthInPixels
import com.avgames.jumpingdino.presentation.distance_between_cactus
import com.avgames.jumpingdino.presentation.event.GameEvent
import com.avgames.jumpingdino.presentation.viewmodel.SceneViewModel
import com.avgames.jumpingdino.ui.theme.JumpingDinoTheme

class MainActivity : ComponentActivity() {

    private val sceneViewModel by viewModels<SceneViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var deviceMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(deviceMetrics)
        deviceWidthInPixels = deviceMetrics.widthPixels
        deviceHeightInPixels = deviceMetrics.heightPixels
        distance_between_cactus = (deviceWidthInPixels * 0.5f)
        sceneViewModel.onEvent(GameEvent.INTRO)
        sceneViewModel.onEvent(GameEvent.MOVE_CACTUS)
        setContent {
            JumpingDinoTheme {
                GameScene(
                    gameState = sceneViewModel.gameState,
                    onEvent = { sceneViewModel.onEvent(it) },
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JumpingDinoTheme {
    }
}