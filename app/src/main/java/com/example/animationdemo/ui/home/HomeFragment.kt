package com.example.animationdemo.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.animationdemo.TopupActivity
import com.example.animationdemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.threeButtons.llTopup.setOnClickListener() {
            animateView(binding.threeButtons.llTopup.parent as View)
        }

        binding.threeButtons.llAccountInfo.setOnClickListener() {
            animateView(binding.threeButtons.llAccountInfo.parent as View)
        }

        binding.threeButtons.llSend.setOnClickListener() {
            animateView(binding.threeButtons.llSend.parent as View)
        }
    }

    val REQUEST_CODE = 1991
    val result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val resultCode = it.resultCode

        if (resultCode === Activity.RESULT_OK) {
            // Define the x and y coordinates of the center of the circle

            // Define the radius of the circle
            val radius: Float =
                Math.max(binding.root.getWidth(), binding.root.getHeight()).toFloat()

            // Create the circular collapse animation
            val circularCollapse =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, radius, 0f)

            // Set the duration and interpolator for the animation
            circularCollapse.duration = 1000
            circularCollapse.interpolator = AccelerateDecelerateInterpolator()

            // Start the animation
            circularCollapse.start()

            // Finish the activity after the animation finishes
            circularCollapse.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    //  finish()
                }
            })
        }
    }




    var cx: Int = 0
    var cy: Int = 0
    private fun animateView(view: View) {


        cx  = (view.getLeft() + view.getRight()) / 2
        cy  = (view.getTop() + view.getBottom())

        val finalRadius: Float =
            Math.max(binding.root.getWidth(), binding.root.getHeight()).toFloat()

        // create the animator for this view (the start radius is zero)

        // create the animator for this view (the start radius is zero)
        val circularReveal =
            ViewAnimationUtils.createCircularReveal(binding.root, cx, cy, view.width.toFloat(), finalRadius)
        circularReveal.duration = 500
        circularReveal.interpolator = AccelerateInterpolator()

        // make the view visible and start the animation

        // make the view visible and start the animation
        view.visibility = View.VISIBLE
        circularReveal.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val intent = Intent(this@HomeFragment.context, TopupActivity::class.java)
                result.launch(intent)
            }
        })
        circularReveal.start()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}