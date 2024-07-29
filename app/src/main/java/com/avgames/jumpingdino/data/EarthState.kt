package com.avgames.jumpingdino.data

import androidx.compose.ui.graphics.Color
import com.avgames.jumpingdino.ui.theme.EarthColor

data class EarthState(
   val blockList: ArrayList<EarthModel> = arrayListOf(),
   val color: Color = EarthColor,
)
