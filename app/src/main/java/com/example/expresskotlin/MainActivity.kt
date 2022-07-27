package com.example.expresskotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.expresskotlin.databinding.ActivityMainBinding
import com.example.expresskotlin.eventbus.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,R.id.searchFragment, R.id.categoriaEstabFragment, R.id.estabFragment,
            R.id.produtosFragment, R.id.produtoDetalheFragment,
            R.id.navigation_mapa,
            R.id.navigation_carrinho,
            R.id.navigation_perfil,R.id.editarPerfilFragment,R.id.atualizarPassFragment,
            R.id.myAddressFragment,R.id.meusPedidosFragment,R.id.carteiraFragment))
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this);
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSearchClickEvent(event: SearchClick?) {
        // Do something
        if (event?.success == true){
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigate(R.id.searchFragment)

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCategoriaEstabClickEvent(event: CategoriaEstabClick?) {
        // Do something
        if (event?.success == true){
            val bundle = Bundle()
            bundle.putSerializable("menucategoria", event.menuCategoria) // Serializable Object
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
                navController.navigate(R.id.categoriaEstabFragment, bundle)

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEstabClickEvent(event: EstabClick?) {
        // Do something
        if (event?.success == true){
            val bundle = Bundle()
            bundle.putSerializable("estabelecimento", event.estabelecimento) // Serializable Object
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigate(R.id.estabFragment, bundle)

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEstabCatClickEvent(event: EstabCatClick?) {
        // Do something
        if (event?.success == true){
            val bundle = Bundle()
            bundle.putSerializable("estabTitulo", event.estabTitulo) // Serializable Object
            bundle.putSerializable("menuCatProdutos", event.menuCatProdutos) // Serializable Object
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigate(R.id.produtosFragment, bundle)

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onProdutoClickEvent(event: ProdutoClick?) {
        // Do something
        if (event?.success == true){
            val bundle = Bundle()
            bundle.putSerializable("estabTitulo", event.estabTitulo) // Serializable Object
            bundle.putSerializable("produto", event.produto) // Serializable Object
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigate(R.id.produtoDetalheFragment, bundle)

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPerfilSettingsClickEvent(event: PerfilSettingsClick?) {
        // Do something
        if (event?.success == true){

            val bundle = Bundle()
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            when(event.position){
                //Atualizar Perfil
                0 ->{
                    bundle.putSerializable("mToolbarTitle", event.titulo)
                    navController.navigate(R.id.editarPerfilFragment, bundle)

                }
                //Atualizar Palavra-Passe
                1 ->{
                    bundle.putSerializable("mToolbarTitle", event.titulo)
                    navController.navigate(R.id.atualizarPassFragment, bundle)
                }
                //Meus Endereços
                2 ->{
                    bundle.putSerializable("mToolbarTitle", event.titulo)
                    navController.navigate(R.id.myAddressFragment, bundle)
                }
                //Pedidos
                3 ->{
                    bundle.putSerializable("mToolbarTitle", event.titulo)
                    navController.navigate(R.id.meusPedidosFragment, bundle)

                }
                //Carteira
                4 ->{
                    bundle.putSerializable("mToolbarTitle", event.titulo)
                    navController.navigate(R.id.carteiraFragment, bundle)

                }
            }



        }
    }


}