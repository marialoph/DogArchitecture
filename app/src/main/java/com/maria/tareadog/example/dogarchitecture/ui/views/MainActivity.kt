package com.santi.pmdm.virgen.dogapicleanarchitecture.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmdm.virgen.dogapi.ui.adapter.DogAdapter
import com.santi.pmdm.virgen.dogapicleanarchitecture.R
import com.santi.pmdm.virgen.dogapicleanarchitecture.databinding.ActivityMainBinding
import com.santi.pmdm.virgen.dogapicleanarchitecture.ui.modelview.DogViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: DogAdapter
    val dogViewModel: DogViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mySearch.setOnQueryTextListener(this)
        registerLiveData()  //Se observan cambios
        loadDada() //se cargan todos los datos.
        initRecyclerView()  //inicializamos el recyclerView.
        initEvent()

    }

    private fun initEvent() {
        binding.btnDelete.setOnClickListener{
            dogViewModel.delete()
        }
    }

    private fun loadDada() {
        dogViewModel.list()

    }


    private fun registerLiveData() {
        dogViewModel.dogListLiveData.observe(
            this, {  myList->
                adapter.dogRepository = myList!!
                binding.myRecyclerPpal.adapter = adapter
                adapter.notifyDataSetChanged()
            })


        dogViewModel.progressBarLiveData.observe(
            this, { visible ->
                binding.progressBar.isVisible = visible
                Log.i("TAG-DOGS","ProgressBar esta $visible")
            }
        )


        //Se observa un cambio en el search.

        dogViewModel.breed.observe(
            this, { bread->
                dogViewModel.listForBreed(bread)
                hideKeyBoard()

            }
        )
    }


    //MODIFICADO
    private fun initRecyclerView(){
        binding.myRecyclerPpal.layoutManager = LinearLayoutManager(this)
        adapter = DogAdapter{dogId -> dogViewModel.deleteDog(dogId)}
        binding.myRecyclerPpal.adapter
    }



    //Este método, es llamado cuando se escribe algo en el campo y se pulsa.

    override fun onQueryTextSubmit(query: String?): Boolean {

        if (!query.isNullOrEmpty())
            dogViewModel.searchByBreed(query!!)
        return true
    }


    //Cualquier cambio, llamará a este método.
    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()) {
            dogViewModel.list()
            hideKeyBoard()
        }
        return true
    }




    //Método que cierra el teclado.
    private fun hideKeyBoard() {
        val imn = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(binding.myLayoutPpal.windowToken, 0)
    }


}