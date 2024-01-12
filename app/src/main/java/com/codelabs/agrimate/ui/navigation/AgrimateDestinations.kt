package com.codelabs.agrimate.ui.navigation

import com.codelabs.agrimate.ui.navigation.DestinationsArg.LAND_ACTIVITY_ID_ARG
import com.codelabs.agrimate.ui.navigation.DestinationsArg.LAND_DETAIL_ACTIVITY_ID_ARG
import com.codelabs.agrimate.ui.navigation.DestinationsArg.LAND_DETAIL_PROBLEM_ID_ARG
import com.codelabs.agrimate.ui.navigation.DestinationsArg.LAND_ID_ARG
import com.codelabs.agrimate.ui.navigation.DestinationsArg.NOTIFICATION_ID_ARG
import com.codelabs.agrimate.ui.navigation.DestinationsArg.PLANT_DISEASE_ID_ARG

object DestinationsArg {
    const val NEWS_ID_ARG = "newsId"
    const val NOTIFICATION_ID_ARG = "notificationId"
    const val CAPITAL_ID_ARG = "capitalId"
    const val CHAT_ID_ARG = "chatId"
    const val COLLECTOR_ID_ARG = "collectorId"
    const val DISCUSSION_ID_ARG = "discussionId"
    const val LAND_ID_ARG = "landId"
    const val LAND_DETAIL_ID_ARG = "landDetailId"
    const val LAND_DETAIL_ACTIVITY_ID_ARG = "landDetailActivityId"
    const val LAND_DETAIL_PROBLEM_ID_ARG = "landDetailReportId"
    const val LAND_ACTIVITY_ID_ARG = "landActivityId"
    const val PLANT_DISEASE_ID_ARG = "plantDiseaseId"
}

sealed class AGRoute(val route: String) {
    object OnBoarding : AGRoute("on-boarding") {
        object Splash : AGRoute("${this.route}/splash")
        object GetStarted : AGRoute("${this.route}get-started")
    }

    object Auth : AGRoute("auth") {
        object SignIn : AGRoute("${this.route}/signin")
        object SignUp : AGRoute("${this.route}/signup") {
            object Farmer : AGRoute("${this.route}/farmer")
            object Collector : AGRoute("${this.route}/collector")
        }

        object ForgotPassword : AGRoute("${this.route}/forgot-password")
        object EmailVerification :
            AGRoute("${this.route}/verification")
    }

    object Farmer : AGRoute("farmer") {
        object Main : AGRoute("${this.route}/main") {

            /**
             * Home
             */
            object Home : AGRoute("${this.route}/home") {
                object Explorer : AGRoute("${this.route}/explore") {
                    object HelpPlant : AGRoute("${this.route}/help-plant") {
                        object PlantRecommendation : AGRoute("${this.route}/plant-recommendation")
                        object Calculator : AGRoute("${this.route}/calculator")
                        object PlantHealth : AGRoute("${this.route}/plant-health") {
                            object SpreadOfDisease : AGRoute("${this.route}/spread-of-disease")
                            object CheckDisease : AGRoute("${this.route}/check-disease")
                            object DiseaseDetail :
                                AGRoute("${this.route}/disease/$PLANT_DISEASE_ID_ARG")
                        }
                    }

                    object HelpHarvest : AGRoute("${this.route}/help-harvest") {
                        object Capital : AGRoute("${this.route}/market-price")
                        object Discussion : AGRoute("${this.route}/collector")
                    }

                    object Capital : AGRoute("${this.route}/capital")
                    object Discussion : AGRoute("${this.route}/discussion")
                }
            }

            /**
             * Land
             */
            object Land : AGRoute("${this.route}/land") {
                object Detail : AGRoute("${this.route}/$LAND_ID_ARG") {
                    object Activity : AGRoute("${this.route}/activity") {
                        object Detail : AGRoute("${this.route}/$LAND_ACTIVITY_ID_ARG") {
                            object Activity : AGRoute("${this.route}/activity") {
                                object Detail :
                                    AGRoute("${this.route}/$LAND_DETAIL_ACTIVITY_ID_ARG")

                                object Add : AGRoute("${this.route}/add")
                            }

                            object Problem : AGRoute("${this.route}/problem") {
                                object Detail : AGRoute("${this.route}/$LAND_DETAIL_PROBLEM_ID_ARG")
                                object Add : AGRoute("${this.route}/add")
                            }
                        }

                        object Add : AGRoute("${this.route}/add")
                        object Edit : AGRoute("${this.route}/edit/$LAND_ACTIVITY_ID_ARG")
                    }
                }

                object Add : AGRoute("${this.route}/add")
            }

            /**
             * Chat
             */
            object Chat : AGRoute("${this.route}/chat")

            /**
             * Profile
             */
            object Profile : AGRoute("${this.route}/profile")


            /**
             * Other
             */
            object Notification : AGRoute("${this.route}/notification")

            object WeatherForecast : AGRoute("${this.route}/weather-forecast")

            object News : AGRoute("${this.route}/news") {
                object Detail : AGRoute("${this.route}/$NOTIFICATION_ID_ARG")
            }
        }
    }

    object Collector : AGRoute("collector")
}