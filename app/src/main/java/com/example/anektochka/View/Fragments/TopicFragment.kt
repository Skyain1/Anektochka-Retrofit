package com.example.anektochka.View.Fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.anektochka.Model.Item.Post
import com.example.anektochka.R
import com.example.anektochka.ViewModels.TopicViewModel
import com.example.anektochka.databinding.FragmentTopicBinding


class TopicFragment : Fragment() {

    private lateinit var binding: FragmentTopicBinding
    private val vm : TopicViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTopicBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btGeneral.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.generalFragment)
        }
        binding.general.setOnClickListener {
            vm.getJoke("general")
        }
        binding.knock.setOnClickListener {
            vm.getJoke("knock-knock")
        }
        binding.programming.setOnClickListener {
            vm.getJoke("programming")
        }
        vm.post.observe(viewLifecycleOwner){
            showcustomdialog(it)
        }
    }

    private fun showcustomdialog(post: Post) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.viewholder_post)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val title = dialog.findViewById<TextView>(R.id.title)
        val setup = dialog.findViewById<TextView>(R.id.setup)
        val punch = dialog.findViewById<TextView>(R.id.punch)
        val mote = dialog.findViewById<ImageButton>(R.id.more)
        mote.visibility = View.GONE
        title.text = post.type
        setup.text = post.setup
        punch.text = post.punchline
        val slideInTopAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_top)
        dialog.window?.decorView?.startAnimation(slideInTopAnimation)
        dialog.show()
    }
}