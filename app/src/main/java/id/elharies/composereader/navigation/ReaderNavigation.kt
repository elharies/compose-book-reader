package id.elharies.composereader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.elharies.composereader.model.Book
import id.elharies.composereader.screen.details.BookDetailScreen
import id.elharies.composereader.screen.details.FakeDetailViewModel
import id.elharies.composereader.screen.details.IDetailViewModel
import id.elharies.composereader.screen.home.FakeHomeViewModel
import id.elharies.composereader.screen.home.HomeScreen
import id.elharies.composereader.screen.home.IHomeViewModel
import id.elharies.composereader.screen.login.FakeLoginViewModel
import id.elharies.composereader.screen.login.ILoginViewModel
import id.elharies.composereader.screen.login.LoginScreen
import id.elharies.composereader.screen.search.FakeSearchViewModel
import id.elharies.composereader.screen.search.ISearchViewModel
import id.elharies.composereader.screen.search.SearchScreen
import id.elharies.composereader.screen.splash.FakeSplashViewModel
import id.elharies.composereader.screen.splash.ISplashViewModel
import id.elharies.composereader.screen.splash.SplashScreen
import id.elharies.composereader.screen.update.UpdateScreen
import id.elharies.composereader.utils.constans.KeyArguments

@Composable
fun ReaderNavigation(
    loginVm: ILoginViewModel = FakeLoginViewModel(),
    splashVm: ISplashViewModel = FakeSplashViewModel(),
    homeVm: IHomeViewModel = FakeHomeViewModel()
) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = ReaderRoute.Splash.nameScreen) {
        composable(route = ReaderRoute.Splash.nameScreen) {
            SplashScreen(navController = nav, viewModel = splashVm)
        }
        composable(route = ReaderRoute.Login.nameScreen) {
            LoginScreen(navController = nav, loginViewModel = loginVm)
        }
        composable(route = ReaderRoute.Home.nameScreen) {
            HomeScreen(
                navController = nav,
                vmHome = homeVm
            )
        }
        composable(route = ReaderRoute.Search.nameScreen) {
            SearchScreen(navController = nav)
        }
        composable(route = ReaderRoute.Stats.nameScreen) {

        }
        composable(
            route = "${ReaderRoute.Update.nameScreen}/{${KeyArguments.keyBook}}",
            arguments = listOf(
                navArgument(KeyArguments.keyBook) {
                    type = NavType.StringType
                },
            )
        ) {
            val bookId = it.arguments?.getString(KeyArguments.keyBook) ?: ""
            UpdateScreen(navController = nav, bookId = bookId)
        }
        composable(
            route = "${ReaderRoute.Detail.nameScreen}/{${KeyArguments.keyBook}}",
            arguments = listOf(
                navArgument(KeyArguments.keyBook) {
                    type = NavType.StringType
                },
            )
        ) {
            val bookId = it.arguments?.getString(KeyArguments.keyBook) ?: ""
            BookDetailScreen(navController = nav, bookId = bookId)
        }
    }
}