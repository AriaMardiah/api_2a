package mia.kotlin.project2a.model

data class ProdukModel(
    val success : Int,
    val message : String,
    val list_xiaomi : ArrayList<DataXiaomi>,
    val list_produk : ArrayList<DataKategoriProduk>,
    val list_makanan : ArrayList<DataKategoriMakanan>
){
    data class DataXiaomi(
        val image : String,
        val nama : String,
        val deskripsi : String,
        val harga : String,
        val seller : String,
        val rating : String,
        val terjual : String,
        val lokasi : String
    )

    data class DataKategoriProduk(
        val image : String,
        val nama : String,
        val deskripsi : String,
        val harga : String,
        val seller : String,
        val rating : String,
        val terjual : String,
        val lokasi : String
    )

    data class DataKategoriMakanan(
        val image : String,
        val nama : String,
        val rating : String,
        val jarak : String
    )

}
