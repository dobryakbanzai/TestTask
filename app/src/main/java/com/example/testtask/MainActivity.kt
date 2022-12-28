package com.example.testtask


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.testtask.databinding.ActivityMainBinding
import org.json.JSONObject
import com.android.volley.Request



const val URL = "https://lookup.binlist.net/"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sqliteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sqliteHelper = SQLiteHelper(this)


        binding.button.setOnClickListener{
            getRes()
        }

        binding.history.setOnClickListener{
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }

        binding.number.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:${binding.number.text}")
            startActivity(intent)
        }
        binding.site.setOnClickListener {

            val uri = Uri.parse("http://${binding.site.text}")
            val launchBrowser = Intent(Intent.ACTION_VIEW, uri)
            startActivity(launchBrowser)
        }

        binding.coord.setOnClickListener {
            println("aaa")
            val strs = binding.coord.text.split(" ").toTypedArray()
            println("=> " + strs[0] + " " + strs[1])
            val geoLocation = Uri.parse("geo:0,0?q=${strs[0]},${strs[1]}(${binding.cname.text})")
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = geoLocation
            }
            startActivity(intent)
//            if (intent.resolveActivity(packageManager) != null) {
//                startActivity(intent)
//            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun getRes(){

        val txtBin = binding.editTextBin.text

        val urlGet = "$URL" +
                    "$txtBin"


        println(urlGet)

        val queue = Volley.newRequestQueue(this)


        val stringRequest = StringRequest(Request.Method.GET,
            urlGet,
            {
                response ->
                val obj = JSONObject(response)

                //number
                if (obj.getJSONObject("number").has("length")){
                    binding.len.setText(obj.getJSONObject("number").get("length").toString())
                } else{
                    binding.len.setText("null")
                }

                if (obj.getJSONObject("number").has("luhn")){
                    binding.luh.setText(obj.getJSONObject("number").get("luhn").toString())
                }else{
                    binding.luh.setText("null")
                }

                //scheme
                if(obj.has("scheme")){
                    binding.scheme.setText(obj.get("scheme").toString())
                }else{
                    binding.scheme.setText("null")
                }

                //brand
                if(obj.has("brand")){
                    binding.brand.setText(obj.get("brand").toString())
                }else{
                    binding.brand.setText("null")
                }

                //type
                if(obj.has("type")){
                    binding.type.setText(obj.get("type").toString())
                }else{
                    binding.type.setText("null")
                }


                //prepaid
                if(obj.has("prepaid")){
                    binding.prepaid.setText(obj.get("prepaid").toString())
                }else{
                    binding.prepaid.setText("null")
                }

                //country
                if (obj.getJSONObject("country").has("emoji")){
                    binding.emoji.setText(obj.getJSONObject("country").get("emoji").toString())
                }else{
                    binding.emoji.setText("null")
                }

                if (obj.getJSONObject("country").has("name")){
                    binding.cname.setText(obj.getJSONObject("country").get("name").toString())
                }else{
                    binding.cname.setText("null")
                }

                if (obj.getJSONObject("country").has("latitude")){
                    binding.coord.setText(obj.getJSONObject("country").get("latitude").toString() + " " + obj.getJSONObject("country").get("longitude").toString())
                }else{
                    binding.coord.setText("null")
                }


                //bank
                if (obj.getJSONObject("bank").has("name")){
                    binding.name.setText(obj.getJSONObject("bank").get("name").toString())
                }else{
                    binding.name.setText("null")
                }

                if (obj.getJSONObject("bank").has("url")){
                    binding.site.setText(obj.getJSONObject("bank").get("url").toString())
                }else{
                    binding.site.setText("null")
                }

                if (obj.getJSONObject("bank").has("city")){
                    binding.city.setText(obj.getJSONObject("bank").get("city").toString())
                }else{
                    binding.city.setText("null")
                }
                if (obj.getJSONObject("bank").has("phone")){
                    binding.number.setText(obj.getJSONObject("bank").get("phone").toString())
                }else{
                    binding.number.setText("null")
                }

                Thread.sleep(1_000)

                addReq()



                Log.d("MyLog", "Response ")


            },
            {
                Log.d("MyLog", "Volley error $it")
            }
            )

        queue.add(stringRequest)
    }

    fun addReq(){
        val request = RequestModel(
            binnum = binding.editTextBin.text.toString(),

            number_len = binding.len.text.toString(),
            number_lun = binding.luh.text.toString(),

            scheme = binding.scheme.text.toString(),

            type = binding.type.text.toString(),

            brand = binding.brand.text.toString(),

            prepaid = binding.prepaid.text.toString(),

            country_emoji = binding.emoji.text.toString(),
            country_name = binding.cname.text.toString(),
            country_coord = binding.coord.text.toString(),

            bank_name = binding.name.text.toString(),
            bank_city = binding.city.text.toString(),
            bank_site = binding.site.text.toString(),
            bank_phone = binding.number.text.toString()

        )

        val status = sqliteHelper.insertRequest(request)

        if (status > -1){
            Toast.makeText(this, "Request added to history...", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this, "Failed adding...", Toast.LENGTH_SHORT).show()
        }
    }
}


