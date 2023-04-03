package com.quesada.a10_clase_shareviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import com.quesada.a10_clase_shareviewmodel.ui.lastname.LastNameFragment
import com.quesada.a10_clase_shareviewmodel.ui.money.MoneyFragment
import com.quesada.a10_clase_shareviewmodel.ui.name.NameFragment

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var backButton: Button
    private lateinit var nextButton: Button
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind()
        fragmentManager = supportFragmentManager
        //Initial State
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, NameFragment())
            .commit()
        backButton.visibility = View.GONE
        displayPhase(viewModel.currentPhase())
        addListeners()


    }

    private fun bind() {
        backButton = findViewById(R.id.back_action)
        nextButton = findViewById(R.id.next_action)
    }

    private fun addListeners() {
        backButton.setOnClickListener {
            viewModel.previousPhase()
            displayPhase(viewModel.currentPhase())
        }
        nextButton.setOnClickListener {
            viewModel.nextPhase()
            displayPhase(viewModel.currentPhase())
        }
    }

    private fun displayPhase(phase:Int) {
        val trans = fragmentManager.beginTransaction()
        when(phase) {
            1 -> {
                trans
                    .replace(R.id.fragmentContainerView, NameFragment())
                    .commit()
                backButton.visibility = View.GONE
            }
            2 -> {
                trans
                    .replace(R.id.fragmentContainerView, LastNameFragment())
                    .commit()
                backButton.visibility = View.VISIBLE
            }
            3-> {
                trans
                .replace(R.id.fragmentContainerView, MoneyFragment())
                .commit()
                backButton.visibility = View.VISIBLE
                nextButton.visibility = View.VISIBLE
            }
        }
    }
}