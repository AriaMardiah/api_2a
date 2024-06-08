package mia.kotlin.project2a.produk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import mia.kotlin.project2a.adapter.ProdukCategoriMakananAdapter
import mia.kotlin.project2a.config.Network
import mia.kotlin.project2a.databinding.ActivityMakananBinding
import mia.kotlin.project2a.model.ProdukModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MakananActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakananBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakananBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        cari()
        getApiKategoriMakanan("1","")
    }

    fun getApiKategoriMakanan(id_makanan : String, filter : String){
        val kategoriMakanan = Network.service().getProdukMakanan(id_makanan,filter)
        kategoriMakanan.enqueue(object  : Callback<ProdukModel>{
            override fun onResponse(call: Call<ProdukModel>, response: Response<ProdukModel>) {
                if (response.isSuccessful){
                    val hasil = response.body()
                    if (hasil!!.success==1){
                        binding.imgNotFound.visibility = View.GONE
                        binding.rvMakanan.visibility = View.VISIBLE
                        //Tampilkan isi dari WEB API ke Adapter
                        val produkCategoriMakananAdapter = ProdukCategoriMakananAdapter(hasil.list_makanan)
                        binding.rvMakanan.adapter = produkCategoriMakananAdapter
                    }else{
                        binding.imgNotFound.visibility = View.VISIBLE
                        binding.rvMakanan.visibility = View.GONE
                    }
                }else{
                    Toast.makeText(this@MakananActivity,
                        "Gagal Terhubung ke WEB API",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ProdukModel>, t: Throwable) {
                Toast.makeText(this@MakananActivity,
                    "Gagal Terhubung ke server",
                    Toast.LENGTH_LONG).show()
            }
        })
    }

    fun cari(){
        binding.svFilter.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               getApiKategoriMakanan("1", newText.toString())
                return true
            }
        })
    }

}