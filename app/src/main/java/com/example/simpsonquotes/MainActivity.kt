package com.example.simpsonquotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simpsonquotes.UI.MainViewModel
import com.example.simpsonquotes.UI.MainViewModleFactory
import com.example.simpsonquotes.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {


    private lateinit var binding :ActivityMainBinding
    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = MainViewModleFactory(applicationContext)
        viewmodel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)



        viewmodel.quote.observe(this, Observer {
            binding.twQuote.text = it
        })

        viewmodel.character.observe(this,Observer{
            binding.twChar.text = it
        })

        viewmodel.imgUrl.observe(this, Observer {
            Picasso.with(this).load(it).error(R.drawable.wifi).into(binding.imgChar)


        })
        binding.nextQuote.setOnClickListener {
            viewmodel.nextQuote()
        }




    }
}