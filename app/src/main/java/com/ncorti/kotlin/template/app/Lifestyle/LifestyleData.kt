package com.ncorti.kotlin.template.app.Lifestyle


class LifestyleData {
    companion object {
        // Static list of lifestyles
        private val lifestyles = arrayListOf(
            Lifestyle(1, "Active", "Engage in physical activities daily."),
            Lifestyle(2, "Homebody", "Stay Home, work on your mental health and meditate."),
            Lifestyle(3, "Social", "Prioritize social interactions.")
            // Add more lifestyles as needed
        )

        // Function to get the list of lifestyles
        fun getList(): ArrayList<Lifestyle> {
            return lifestyles
        }


    }
}