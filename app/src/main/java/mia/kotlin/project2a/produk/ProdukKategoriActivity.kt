package mia.kotlin.project2a.produk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import mia.kotlin.project2a.R
import mia.kotlin.project2a.adapter.ProdukCategoriAdapter
import mia.kotlin.project2a.config.Network
import mia.kotlin.project2a.databinding.ActivityProdukKategoriBinding
import mia.kotlin.project2a.model.ProdukModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukKategoriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProdukKategoriBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdukKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        cari()
        getApiKategoriProduk("1","")
    }

    fun getApiKategoriProduk(id_category : String, filter : String){
        val kategoriProduk = Network.service().getProdukKategori(id_category,filter)
        kategoriProduk.enqueue(object  : Callback<ProdukModel>{
            override fun onResponse(call: Call<ProdukModel>, response: Response<ProdukModel>) {
                if (response.isSuccessful){
                    val hasil = response.body()
                    if (hasil!!.success==1){
                        binding.imgNotFound.visibility = View.GONE
                        binding.rvProduk.visibility = View.VISIBLE
                        //Tampilkan isi dari WEB API ke Adapter
                        val produkCategoriAdapter = ProdukCategoriAdapter(hasil.list_produk)
                        binding.rvProduk.adapter = produkCategoriAdapter
                    }else{
                        binding.imgNotFound.visibility = View.VISIBLE
                        binding.rvProduk.visibility = View.GONE
                    }
                }else{
                    Toast.makeText(this@ProdukKategoriActivity,
                        "Gagal Terhubung ke WEB API",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ProdukModel>, t: Throwable) {
               Toast.makeText(this@ProdukKategoriActivity,
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
                getApiKategoriProduk("1", newText.toString())
                return true
            }
        })
    }

}