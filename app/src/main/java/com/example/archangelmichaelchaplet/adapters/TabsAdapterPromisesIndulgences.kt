import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.archangelmichaelchaplet.controllers.IndulgencesPiusTab
import com.example.archangelmichaelchaplet.controllers.PromisesIndulgencesFragment
import com.example.archangelmichaelchaplet.controllers.PromisesMichaelTab

class TabsAdapterPromisesIndulgences(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2 // Number of tabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PromisesMichaelTab() // Replace with your first tab fragment
            1 -> IndulgencesPiusTab() // Replace with your second tab fragment
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
