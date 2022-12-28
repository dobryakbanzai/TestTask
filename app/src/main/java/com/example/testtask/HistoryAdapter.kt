package com.example.testtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.RequestViewHolder>(){
    private var requestList: ArrayList<RequestModel> = ArrayList()

    fun addItems(items: ArrayList<RequestModel>){
        this.requestList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder = RequestViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.request_card_adapter, parent, false)
    )

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val request = requestList[position]
        holder.bindView(request)
    }

    override fun getItemCount(): Int {
        return requestList.size
    }

    class RequestViewHolder(var view: View): RecyclerView.ViewHolder(view){

        private var id = view.findViewById<TextView>(R.id.ID)

        private var binnum = view.findViewById<TextView>(R.id.binnum)

        private var len = view.findViewById<TextView>(R.id.lenght)
        private var lun = view.findViewById<TextView>(R.id.luhn)

        private var scheme = view.findViewById<TextView>(R.id.schemes)

        private var type = view.findViewById<TextView>(R.id.types)

        private var brand = view.findViewById<TextView>(R.id.brnd)

        private var prepaid = view.findViewById<TextView>(R.id.prep)

        private var country_emoji = view.findViewById<TextView>(R.id.emoj)
        private var country_name = view.findViewById<TextView>(R.id.countname)
        private var country_coord = view.findViewById<TextView>(R.id.coords)

        private var bank_name = view.findViewById<TextView>(R.id.bname)
        private var bank_city = view.findViewById<TextView>(R.id.bcity)
        private var bank_site = view.findViewById<TextView>(R.id.bsite)
        private var bank_phone = view.findViewById<TextView>(R.id.bphone)





        fun bindView(rm: RequestModel){
            id.text = rm.id.toString()

            binnum.text = rm.binnum

            len.text = rm.number_len
            lun.text = rm.number_lun

            scheme.text = rm.scheme

            type.text = rm.type

            brand.text = rm.brand

            prepaid.text = rm.prepaid

            country_emoji.text = rm.country_emoji
            country_name.text = rm.country_name
            country_coord.text = rm.country_coord

            bank_name.text = rm.bank_name
            bank_city.text = rm.bank_city
            bank_site.text = rm.bank_site
            bank_phone.text = rm.bank_phone


        }
    }
}