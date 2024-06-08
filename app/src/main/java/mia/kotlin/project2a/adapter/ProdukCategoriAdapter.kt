package mia.kotlin.project2a.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mia.kotlin.project2a.databinding.ItemProdukBinding
import mia.kotlin.project2a.model.ProdukModel

class ProdukCategoriAdapter (
    val results : ArrayList<ProdukModel.DataKategoriProduk>
):RecyclerView.Adapter<ProdukCategoriAdapter.viewHolder>() {
    lateinit var binding: ItemProdukBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProdukCategoriAdapter.viewHolder {
        binding = ItemProdukBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdukCategoriAdapter.viewHolder, position: Int) {
        val result = results[position]
        Picasso.get().load(result.image).into(holder.imgGambar)
        holder.tvNamaProduk.text = result.nama
        holder.tvHarga.text = result.harga
        holder.tvSeller.text = result.seller
        holder.tvRating.text = result.rating
        holder.tvTerjual.text = result.terjual
        holder.tvLokasi.text = result.lokasi
    }

    override fun getItemCount() = results.size

    class viewHolder(binding: ItemProdukBinding):
        RecyclerView.ViewHolder(binding.root) {
            //inisialisasi
            val imgGambar = binding.imgGambar
            val tvNamaProduk = binding.tvNamaProduk
            val tvHarga = binding.tvHarga
            val tvSeller = binding.tvSeller
            val tvRating = binding.tvRating
            val tvTerjual = binding.tvTerjual
            val tvLokasi = binding.tvLokasi

    }
}