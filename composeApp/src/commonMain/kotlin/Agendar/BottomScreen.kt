package Agendar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator

class BottomScreen :Screen{

    @Composable
    override fun Content(){
        TabNavigator(
            Home,
            tabDisposable = {
                listOf(Home, Perfil,MisCitas)
            }
        ){
            Scaffold(
                topBar = {
                    TopAppBar(title = { Text(it.current.options.title)})
                },
                bottomBar = {
                    BottomNavigation{
                        val tabNavigator= LocalTabNavigator.current
                        BottomNavigationItem(
                            selected= tabNavigator.current.key==Home.key,
                            label = { Text(Home.options.title)},
                            icon = { Icon(painter=Home.options.icon!!,contentDescription=null)},
                            onClick = {tabNavigator.current=Home}
                        )
                        BottomNavigationItem(
                            selected= tabNavigator.current.key==Perfil.key,
                            label = { Text(Perfil.options.title)},
                            icon = { Icon(painter=Perfil.options.icon!!,contentDescription=null)},
                            onClick = {tabNavigator.current=Perfil}
                        )
                        BottomNavigationItem(
                            selected= tabNavigator.current.key==MisCitas.key,
                            label = { Text(MisCitas.options.title)},
                            icon = { Icon(painter=MisCitas.options.icon!!,contentDescription=null)},
                            onClick = {tabNavigator.current=MisCitas}
                        )
                    }
                }
            ){ CurrentTab()}
        }
    }
}