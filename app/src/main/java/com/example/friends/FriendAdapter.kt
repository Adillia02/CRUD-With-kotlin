package com.example.friends

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.content_items.*


class FriendAdapter(private val context: Context, private val items: List<Friend>) :
    RecyclerView.Adapter<FriendAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        context, LayoutInflater.from(context).inflate(
            R.layout.content_items, parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position))

    }

    override fun getItemCount(): Int = items.size
    class ViewHolder(val context: Context, override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: Friend) {
            tv_name.text = item.nama
            tv_email.text = item.email
            tv_telpon.text = item.telp
            Glide.with(context).load(item.image).into(tv_img_profile)
            ll_btn.setOnClickListener {
                listener(item)
            }
        }


        private fun listener(item: Friend) {
            val dialogTest = AlertDialog.Builder(context!!)
            dialogTest.setTitle("silahkan pilih")
            val dialogItems = arrayOf("edit", "delete")
            dialogTest.setItems(dialogItems) { dialog, which ->
                when (which) {
                    0 -> changeEdit(item)
                    1 -> changeDelete()
                }
            }
            dialogTest.show()
        }

        private fun changeEdit(item: Friend) {
            (context!! as MainActivity).editListFriends()

        }
//        private fun validasiUpdate(friend: Friend){
////            db.temanDao?.updateTeman(item)
//
//            db = AppDatabase.getAppDataBase(context!!)
//            temanDao = db?.friendDao()
//            temanDao?.updateTeman(friend)
//        }

        private fun changeDelete() {
            val dialogShow = AlertDialog.Builder(context!!)
            dialogShow.setTitle("Data Akan Dihapus")
            val dialogArray = arrayOf("Ya", "Tidak")
            dialogShow.setItems(dialogArray){
                dialog, which ->
                when (which) {
                    0 -> suksesDelete()
                    1 -> cancelDelete()
                }
            }
            dialogShow.show()
        }

        private fun suksesDelete(){
            (context!! as MainActivity).viewListFriends()
        }

        private fun cancelDelete(){
            (context!! as MainActivity).viewListFriends()
        }

    }
}