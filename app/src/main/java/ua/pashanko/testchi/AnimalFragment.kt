package ua.pashanko.testchi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var animalAdapter: AnimalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_animal, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        animalAdapter = AnimalAdapter(emptyList())
        recyclerView.adapter = animalAdapter
        fetchAnimalImages()
        return view
    }

    private fun fetchAnimalImages() {
        val animalService = AnimalService.create()
        animalService.getAnimalImages().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    val images = response.body() ?: emptyList()
                    animalAdapter = AnimalAdapter(images)
                    recyclerView.adapter = animalAdapter
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}