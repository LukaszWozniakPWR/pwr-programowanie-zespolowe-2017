package com.pwr.zespolowe2016.cardgame.sockets

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.pwr.zespolowe2016.cardgame.other.BaseActivity

abstract class SocketApiActivity : BaseActivity() {

     open protected val apiCallback: SocketAidlCallback.Stub = EmptyApiCallback()

    private val serviceConnection = SocketApiServiceConnection()
    var socketApi: SocketAidlApi? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        socketApi?.registerCallback(apiCallback)
    }

    override fun onStart() {
        super.onStart()
        bindService(SocketService.getIntent(this), serviceConnection, Context.BIND_AUTO_CREATE)
        socketApi?.registerCallback(apiCallback)
    }

    override fun onStop() {
        super.onStop()
        socketApi?.unregisterCallback(apiCallback)
        unbindService(serviceConnection)
    }

    override fun onResume() {
        super.onResume()
        socketApi?.registerCallback(apiCallback)
    }

    override fun onPause() {
        super.onPause()
        socketApi?.unregisterCallback(apiCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        socketApi?.unregisterCallback(apiCallback)
    }

    open protected fun onServiceConnected() { /* NO-OP */ }

    inner class SocketApiServiceConnection : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            with(SocketAidlApi.Stub.asInterface(service)) {
                socketApi = this
                registerCallback(apiCallback)
                onServiceConnected()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            socketApi = null
        }

    }
}