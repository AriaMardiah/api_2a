package mia.kotlin.project2a.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mia.kotlin.project2a.databinding.ItemMakananBinding
import mia.kotlin.project2a.model.ProdukModel

class ProdukCategoriMakananAdapter (
    val results : ArrayList<ProdukModel.DataKategoriMakanan>
):RecyclerView.Adapter<ProdukCategoriMakananAdapter.viewHolder>() {
    lateinit var binding: ItemMakananBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProdukCategoriMakananAdapter.viewHolder {
        binding = ItemMakananBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdukCategoriMakananAdapter.viewHolder, position: Int) {
        val result = results[position]
        Picasso.get().load(result.image).into(holder.imgGambar)
        holder.tvNamaProduk.text = result.nama
        holder.tvRating.text = result.rating
        holder.tvJarak.text = result.jarak
    }

    override fun getItemCount() = results.size

    class viewHolder(binding: ItemMakananBinding):
        RecyclerView.ViewHolder(binding.root) {
            //inisialisasi
            val imgGambar = binding.imgGambar
            val tvNamaProduk = binding.tvNamaProduk
            val tvRating = binding.tvRating
            val tvJarak = binding.tvJarak

    }
}