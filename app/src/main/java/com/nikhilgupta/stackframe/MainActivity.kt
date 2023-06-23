package com.nikhilgupta.stackframe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.nikhilgupta.stackframe.databinding.ActivityMainBinding
import kotlin.math.exp

class  MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var bottomSheet1: BottomSheetBehavior<*>
    private lateinit var bottomSheet2: BottomSheetBehavior<*>
    private lateinit var bottomSheet3: BottomSheetBehavior<*>

    private val expanded = BottomSheetBehavior.STATE_EXPANDED
    private val collapsed = BottomSheetBehavior.STATE_COLLAPSED
    private val hidden = BottomSheetBehavior.STATE_HIDDEN

    private val bottomSheet1Callback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when(newState) {
                expanded -> {
                    supportActionBar?.title = "SCHEDULE TRIP"
                    bottomSheet2.state = collapsed
                }
                collapsed -> {
                    bottomSheet2.state = hidden
                }
                hidden -> {
                    supportActionBar?.title = "StackFrame"
                    bottomSheetInitState()
                }
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {

        }
    }
    private val bottomSheet2Callback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when(newState) {
                expanded -> {
                    supportActionBar?.title = "SEATS"
                    bottomSheet3.state = collapsed
                    binding.bt2.expandedView.visibility = View.VISIBLE
                    binding.bt2.collapsedView.visibility = View.INVISIBLE
                }
                collapsed -> {
                    bottomSheet3.state = hidden
                    binding.bt2.expandedView.visibility = View.INVISIBLE
                    binding.bt2.collapsedView.visibility = View.VISIBLE
                }
                hidden -> {

                }
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {

        }
    }
    private val bottomSheet3Callback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when(newState) {
                expanded -> {
                    supportActionBar?.title = "PAY"
                    binding.bt3.expandedView.visibility = View.VISIBLE
                    binding.bt3.collapsedView.visibility = View.INVISIBLE
                    binding.bt2.stepDoneView.visibility = View.VISIBLE
                    binding.bt2.expandedView.visibility = View.INVISIBLE
                }
                collapsed -> {
                    binding.bt3.expandedView.visibility = View.INVISIBLE
                    binding.bt3.collapsedView.visibility = View.VISIBLE
                    binding.bt2.stepDoneView.visibility = View.INVISIBLE
                    binding.bt2.expandedView.visibility = View.VISIBLE
                }
                hidden -> {
                    binding.bt2.stepDoneView.visibility = View.INVISIBLE
                }
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bottomSheet1 = BottomSheetBehavior.from(binding.bt1.bottomSheet)
        bottomSheet2 = BottomSheetBehavior.from(binding.bt2.bottomSheet2)
        bottomSheet3 = BottomSheetBehavior.from(binding.bt3.bottomSheet3)
        bottomSheetInitState()

        bottomSheet1.addBottomSheetCallback(bottomSheet1Callback)
        bottomSheet2.addBottomSheetCallback(bottomSheet2Callback)
        bottomSheet3.addBottomSheetCallback(bottomSheet3Callback)

        binding.button.setOnClickListener {
            bottomSheet1.state = expanded
        }

        binding.bt1.bottomSheet.setOnClickListener {
            bottomSheet1.state = if (bottomSheet1.state != expanded) expanded else hidden
        }
        binding.bt2.bottomSheet2.setOnClickListener {
            bottomSheet2.state = if (bottomSheet2.state != expanded) expanded else collapsed
        }
        binding.bt3.bottomSheet3.setOnClickListener {
            bottomSheet3.state = if (bottomSheet3.state != expanded) expanded else collapsed
        }
        binding.bt1.proceed.setOnClickListener {
            bottomSheet2.state = expanded
        }
        binding.bt2.proceed.setOnClickListener {
            bottomSheet3.state = expanded
        }
    }

    private fun bottomSheetInitState() {
        bottomSheet1.state = hidden
        bottomSheet2.state = hidden
        bottomSheet3.state = hidden
    }
}