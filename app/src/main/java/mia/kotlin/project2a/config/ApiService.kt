package mi.example.project2a.config

import android.telecom.Call
import mia.kotlin.project2a.model.ProdukModel
import mia.kotlin.project2a.model.SlidersModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api_2a/index.php?folder=dashboard&file=sliders")
    fun getSlider(): retrofit2.Call<SlidersModel>

    @GET("api_2a/index.php?folder=dashboard&file=produk")
    fun getProduk(): retrofit2.Call<ProdukModel>

    @GET("api_2a/index.php?folder=produk&file=produkCategori")
    fun getProdukKategori(@Query("id_category")id_category:String, @Query("filter")filter:String): retrofit2.Call<ProdukModel>

    @GET("api_2a/index.php?folder=produk&file=makanan")
    fun getProdukMakanan(@Query("id_makanan")id_makanan:String, @Query("filter")filter:String): retrofit2.Call<ProdukModel>


}