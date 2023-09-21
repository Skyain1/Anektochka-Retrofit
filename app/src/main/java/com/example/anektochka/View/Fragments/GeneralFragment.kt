package com.example.anektochka.View.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.anektochka.Model.Item.Post
import com.example.anektochka.R
import com.example.anektochka.View.Adapters.PostAdapter
import com.example.anektochka.View.Listeners.Delete_Listener
import com.example.anektochka.View.Listeners.Swap_Listener
import com.example.anektochka.ViewModels.GeneralViewModel
import com.example.anektochka.databinding.FragmentGeneralBinding

class GeneralFragment : Fragment() {

    private lateinit var binding: FragmentGeneralBinding
    private  lateinit var jokes : MutableList<Post>
    private lateinit var adapter: PostAdapter

    private val vm : GeneralViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGeneralBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PostAdapter()
        adapter.attachListener(object : Swap_Listener{
            override fun onChapterClicked(post: Post) {
                val navController = findNavController()
                navController.navigate(R.id.topicFragment)
            }
        })
        adapter.attachDeleteListener(object :Delete_Listener{
            override fun onPostDeleted(post: Post) {
                adapter.deletePost(post)
            }

        })
        binding.recview.adapter = adapter

        vm.posts.observe(viewLifecycleOwner){
            jokes = it
            setValues()
            binding.swipeLayout.isRefreshing=false
        }
        if (vm.posts.value.isNullOrEmpty()){
            vm.setNewList()
        }

        binding.swipeLayout.setOnRefreshListener {
            vm.setNewList()
        }
    }
    private fun setValues(){
        jokes = jokes.sortedBy { it.ViewType }.toMutableList()
        if (jokes[0].ViewType!=-1){
            jokes.add(Post("place", "place", "place", 21, -1))
            jokes.add(Post("place", "place", "place", 21, 1))
        }
        jokes = jokes.sortedBy { it.ViewType }.toMutableList()
        adapter.setData(jokes)
    }

}