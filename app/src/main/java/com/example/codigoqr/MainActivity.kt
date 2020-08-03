package com.example.codigoqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnScan -> {
                intentIntegrator
                    .setBeepEnabled(true).initiateScan()
            }
            R.id.btnGenerate ->{
                val barCodeEncoder = BarcodeEncoder()
                val bitmap = barCodeEncoder.encodeBitmap(etTexto.text.toString(),
                    BarcodeFormat.QR_CODE, 400,400)

                //mmostrar el códig en el IV
                imageView.setImageBitmap(bitmap)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(intentResult!=null){
            if(intentResult.contents != null){
                etTexto.setText(intentResult.contents)
            }else{
                Toast.makeText(this, "No se pudo :(", Toast.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    //es parte de la librería que sirve para leer los códigos
    lateinit var intentIntegrator: IntentIntegrator



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentIntegrator = IntentIntegrator(this)
        btnGenerate.setOnClickListener(this)
        btnScan.setOnClickListener(this)

    }
}
