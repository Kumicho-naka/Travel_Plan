package com.example.travelplanner

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelplanner.ui.MainScreen
import com.example.travelplanner.ui.PlanCreateScreen
import com.example.travelplanner.ui.PlanDeleteScreen
import com.example.travelplanner.ui.PlanResultScreen
import com.example.travelplanner.ui.WebViewScreen
import com.example.travelplanner.viewModel.PlanViewModel
import com.example.travelplanner.viewModelInterface.MainScreenData
import com.example.travelplanner.viewModelInterface.PlanCreateData
import com.example.travelplanner.viewModelInterface.PlanDeleteData
import com.example.travelplanner.viewModelInterface.PlanResultData
import com.example.travelplanner.viewModelInterface.WebViewData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val planViewModel: PlanViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp(planViewModel)
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainApp(planViewModel: PlanViewModel){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main"){
        /**
         * メイン画面に移行
         **/
        composable("main") {
            MainScreen(
                MainScreenData(
                    navigateToPlanCreate = { navController.navigate("plan_creation") },
                    navigateToPlanResult = { navController.navigate("plan_result") },
                    navigateToPlanDelete = { navController.navigate("plan_delete") }
                )
            )
        }

        /**
        * プラン作成画面に移行
        **/
        composable("plan_creation") {
            PlanCreateScreen(
                PlanCreateData(
                    addPlan = { plan -> planViewModel.addPlan(plan) },
                    navigateToMain = { navController.navigate("main") },
                    navigateToPlanResult = { navController.navigate("plan_result") }
                )
            )
        }

        /**
        * プラン確認画面に移行
        **/
        composable("plan_result") {
            PlanResultScreen(
                planResultData = PlanResultData(
                    updatePlan = { plan -> planViewModel.updatePlan(plan)},
                    plans = planViewModel.getAllPlans(),
                    navigateToMain = { navController.navigate("main")},
                    navigateToWebView = { destination -> navController.navigate("webview/$destination")}
                )
            )
        }

        /**
        * プランのネットワーク情報画面に移行
        **/
        composable("webview/{destination}") { navBackStackEntry ->
            val destination = navBackStackEntry.arguments?.getString("destination") ?: ""
            WebViewScreen(
                destination = destination,
                webViewData = WebViewData(
                    updatePlan = { plan -> planViewModel.updatePlan(plan)},
                    plans = planViewModel.getAllPlans(),
                    navigateToMain = { navController.navigate("main") },
                    navigateToResult = {  navController.popBackStack() }
                )
            )
        }

        /**
        * プラン削除画面に移行
        **/
        composable("plan_delete") {
            PlanDeleteScreen(
                planDeleteData = PlanDeleteData(
                    removePlan = { plan -> planViewModel.removePlan(plan)},
                    getAllPlans = { -> planViewModel.getAllPlans()},
                    navigateToMain = { -> navController.navigate("main") }
                )
            )
        }
    }
}