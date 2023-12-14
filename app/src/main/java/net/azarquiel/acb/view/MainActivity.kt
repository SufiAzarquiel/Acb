package net.azarquiel.acb.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.acb.R
import net.azarquiel.acb.adapter.AdapterJugadores
import net.azarquiel.acb.databinding.ActivityMainBinding
import net.azarquiel.acb.model.JugadorWithEquipo
import net.azarquiel.acb.viewmodel.JugadorViewModel
import net.azarquiel.alltricks.util.Util

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var jugadores: List<JugadorWithEquipo>
    private lateinit var filterType: String
    private lateinit var searchView: SearchView
    private lateinit var jugadorViewModel: JugadorViewModel
    private lateinit var adapterJugadores: AdapterJugadores
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        filterType = "jugador"

        Util.inyecta(this, "acb.sqlite")
        initRV()
        getDatos()
    }

    private fun initRV() {
        adapterJugadores = AdapterJugadores(this, R.layout.row_jugador)
        binding.contentjugadores.rvJugadores.adapter = adapterJugadores
        binding.contentjugadores.rvJugadores.layoutManager = LinearLayoutManager(this)
    }

    private fun getDatos() {
        jugadorViewModel = ViewModelProvider(this).get(JugadorViewModel::class.java)
        jugadorViewModel.getJugadoresWithEquipo().observe(this, Observer { jugadores ->
            // Update the cached copy of the jugadors in the adapter.
            this.jugadores = jugadores
            jugadores?.let { adapterJugadores.setJugadores(it) }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.search)
        // Filter
        searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search jugador..."
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_filtertype -> {
                if (filterType == "escudo") {
                    filterType = "jugador"
                    item.setIcon(R.drawable.player)
                }
                else {
                    filterType = "escudo"
                    item.setIcon(R.drawable.team)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Filter
    override fun onQueryTextChange(query: String): Boolean {
        val original = ArrayList<JugadorWithEquipo>(jugadores)
        if (filterType == "jugador")
            adapterJugadores.setJugadores(original.filter { jugadorWE -> jugadorWE.jugador.nombre.contains(query,true) })
        else if (filterType == "escudo")
            adapterJugadores.setJugadores(original.filter { jugadorWE -> jugadorWE.equipo.nombre.contains(query,true) })
        return false
    }

    override fun onQueryTextSubmit(text: String): Boolean {
        return false
    }

    fun onClickJugador(v: android.view.View) {
        var jugadorWE = v.tag as JugadorWithEquipo
        val intent = android.content.Intent(this, DetalleJugadorActivity::class.java)
        intent.putExtra("jugadorWithEquipo", jugadorWE)
        startActivity(intent)
    }
}