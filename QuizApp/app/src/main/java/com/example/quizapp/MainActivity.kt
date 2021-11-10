package com.example.quizapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.quizapp.Repository.Repository
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var nhFragment : FragmentContainerView
    private lateinit var viewModel: MainViewModel

    companion object {
        const val TAG = "MAIN ACTIVITY"
        const val CONTACT_PERMISSION_CODE = 440
        const val EXTERNAL_PERMISSION_CODE = 442
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()
        topAppBar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            drawerLayout.close()
            when (menuItem.itemId) {
                R.id.miHome -> findNavController(R.id.nhFragment).navigate(R.id.homeFragment)
                R.id.miQuizTime -> findNavController(R.id.nhFragment).navigate(R.id.startFragment)
                R.id.miProfile -> findNavController(R.id.nhFragment).navigate(R.id.profileFragment)
                R.id.miListOfQuestions -> findNavController(R.id.nhFragment).navigate(R.id.questionListFragment)
                R.id.miNewQuestion -> findNavController(R.id.nhFragment).navigate(R.id.newQuestionFragment)
            }
            true

        }
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer{ response ->
            if(response.isSuccessful){
                Log.i(TAG,"res: ${response.body()?.response_code.toString()}")
                QuizController.listOfRawQuestions = response.body()?.results!!
                Log.i(TAG,"QUIZ: ${QuizController.listOfRawQuestions[0]}")
                Log.i(TAG,"QUIZ: ${QuizController.listOfRawQuestions.size}")
            }
            else{
                Log.i(TAG,"res: ${response.errorBody().toString()}")
            }

        })
    }

    private fun initializeView() {
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        topAppBar = findViewById(R.id.topAppBar)
        nhFragment = findViewById(R.id.nhFragment)
    }

}





