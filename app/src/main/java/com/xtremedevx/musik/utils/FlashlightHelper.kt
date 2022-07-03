package com.xtremedevx.musik.utils

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import kotlin.concurrent.fixedRateTimer


object FlashlightHelper {
private lateinit var cameraManager: CameraManager
private lateinit var cameraId: String

     fun initialize(context:Context) {
        cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraId = cameraManager.cameraIdList[0]
    }

    fun turnOn(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true)
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    fun turnOff(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false)
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    fun blink(){
        var isOn = false
        fixedRateTimer("default", false, 0L, 100) {
            if(isOn){
                turnOff()
                isOn = false
            }else{
                turnOn()
                isOn = true
            }
        }
    }



}