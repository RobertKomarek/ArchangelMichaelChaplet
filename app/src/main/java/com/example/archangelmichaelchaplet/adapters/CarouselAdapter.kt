// CarouselAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.archangelmichaelchaplet.databinding.ItemCarouselBinding
import com.example.archangelmichaelchaplet.models.CarouselItem

class CarouselAdapter(private var items: ArrayList<CarouselItem>) :
    RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CarouselItem) {
                binding.imageRosary.setImageResource(item.imageResId)
                binding.textRosary.text = item.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCarouselBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // Function to update the data in the adapter
    fun updateData(newItemList: ArrayList<CarouselItem>) {
        items = newItemList
        notifyDataSetChanged()
    }
}


/*import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.archangelmichaelchaplet.models.CarouselItem
import com.example.archangelmichaelchaplet.databinding.ItemCarouselBinding

class CarouselAdapter(private val items: List<CarouselItem>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    //private var currentPage = 0
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = items[position]
        val inflater = LayoutInflater.from(container.context)
        val binding = ItemCarouselBinding.inflate(inflater, container, false)
        // Bind data to your views here
        //binding.Item = item
        container.addView(binding.root)
        return binding.root
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCarouselBinding.inflate(inflater, parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = items.size

    inner class CarouselViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CarouselItem) {
            // Bind data to views
            binding.imageRosary.setImageResource(item.imageResId)
            binding.textRosary.text = item.text
            // Set click listeners for buttons and handle navigation
            *//*binding.btnStart.setOnClickListener {
                // Handle start button click
            }
            binding.btnForward.setOnClickListener {
                // Handle forward button click
            }
            binding.btnBackward.setOnClickListener {
                // Handle backward button click
            }*//*
        }
    }
}*/
