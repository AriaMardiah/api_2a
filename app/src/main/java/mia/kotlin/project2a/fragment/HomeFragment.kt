package mia.kotlin.project2a.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import mia.kotlin.project2a.adapter.ProdukXiaomiAdapter
import mia.kotlin.project2a.config.Network
import mia.kotlin.project2a.databinding.FragmentHomeBinding
import mia.kotlin.project2a.model.ProdukModel
import mia.kotlin.project2a.model.SlidersModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    //private val binding by lazy{ FragmentHomeBinding.inflate(layoutInflater)}
    private lateinit var binding: FragmentHomeBinding
    private lateinit var produkXiaomiAdapter : ProdukXiaomiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getApiSlider()
        getApiProduk()
    }

    private fun getApiProduk(){
        val produk = Network.service().getProduk()
        produk.enqueue(object : Callback<ProdukModel>{
            override fun onResponse(call: Call<ProdukModel>, response: Response<ProdukModel>) {
                if (response.isSuccessful){
                    val hasil = response.body()
                    if (hasil!!.success==1){
                        //tanmpilkan data produk
                        setRecXiaomi(hasil.list_xiaomi)
                    }

                }else{
                    Toast.makeText(activity,"Gagal Terhubung ke API",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ProdukModel>, t: Throwable) {
            }
        })
    }

    private fun setRecXiaomi(data: ArrayList<ProdukModel.DataXiaomi>){
        produkXiaomiAdapter= ProdukXiaomiAdapter(data)
        binding.rvXiaomi.adapter = produkXiaomiAdapter
    }

    //sebuah fungsi/method mengambil data dari API
    private fun getApiSlider() {
        val sliders = Network.service().getSlider()
        sliders.enqueue(object : retrofit2.Callback<SlidersModel> {
            override fun onResponse(
                call: retrofit2.Call<SlidersModel>,
                response: Response<SlidersModel>
            ) {
                if (response.isSuccessful) {
                    val hasil = response.body()

                    //tampilkan data sliders
                    setSliders(hasil!!.data)

                }
            }

            override fun onFailure(call: retrofit2.Call<SlidersModel>, t: Throwable) {

            }
        })
    }

    private fun setSliders(sliders: List<SlidersModel.Result>){
        val imageAdd = ArrayList <SlideModel>()
        for (slide in sliders){
            imageAdd.add(SlideModel(slide.image,slide.deskripsi))
        }
        binding.imageSlider.setImageList(imageAdd,ScaleTypes.FIT)
    }
}