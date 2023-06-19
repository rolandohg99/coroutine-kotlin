package pe.com.coroutines_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val retrofit = RetrofitHelper.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Main = Hilo principal
        //IO = Retrofit
        //Default = Procesar informacion

        lifecycleScope.launch(Dispatchers.IO) {

            val response :Response<SuperHeroDataResponse> =  retrofit.getSuperHeroes("a")

            //if(response.isSuccessful){
                //Toast.makeText(this@MainActivity,"200",Toast.LENGTH_SHORT).show()
            //}

            //usar el hilo principal para mostrar el toast en la ui
            //Metodo 1
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    Toast.makeText(this@MainActivity,"200",Toast.LENGTH_SHORT).show()
                }
            }
            //Metodo 2
            /*if(response.isSuccessful){
                runOnUiThread{
                    Toast.makeText(this@MainActivity,"200",Toast.LENGTH_SHORT).show()
                }
            }*/
        }

        //waitForCoroutines()

    }

    private fun waitForCoroutines(){
        //multiple servicios async con coroutines
        lifecycleScope.launch(Dispatchers.IO) {

            /*val response1 = async { retrofit.getSuperHeroes("a") }
            val response2 = async { retrofit.getSuperHeroes("a") }
            val response3 = async { retrofit.getSuperHeroes("a") }
            val response4 = async { retrofit.getSuperHeroes("a") }

            val result = response1.await()
            val result2 = response2.await()
            val result3 = response3.await()
            val result4 = response4.await()*/

            val reponses = listOf(
                async { retrofit.getSuperHeroes("a") },
                async { retrofit.getSuperHeroes("a") },
                async { retrofit.getSuperHeroes("a") },
                async { retrofit.getSuperHeroes("a") }
            )

            val result = reponses.awaitAll()

        }
    }

}