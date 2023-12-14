package net.azarquiel.acb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import net.azarquiel.acb.R
import net.azarquiel.acb.model.Jugador

class DetalleJugadorWebView : AppCompatActivity() {
    private lateinit var jugador: Jugador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_jugador_web_view)

        jugador = intent.getSerializableExtra("jugador") as Jugador

        val wvJugador = findViewById<WebView>(R.id.wvJugador)

        // https://stackoverflow.com/questions/4066438/android-webview-how-to-handle-redirects-in-app-instead-of-opening-a-browser
        wvJugador.webViewClient = WebViewClient()


        wvJugador.loadUrl(jugador.link!!)
    }
}