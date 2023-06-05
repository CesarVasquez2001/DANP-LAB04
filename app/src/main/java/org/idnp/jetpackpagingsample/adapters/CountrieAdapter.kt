package org.idnp.jetpackpagingsample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import org.idnp.jetpackpagingsample.R
import org.idnp.jetpackpagingsample.entities.Countrie

//class UserAdapter(diffCallback: DiffUtil.ItemCallback<User>) :
class UserAdapter : PagingDataAdapter<Countrie, CountrieViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountrieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_user, parent, false)
        return CountrieViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountrieViewHolder, position: Int) {
//        getItem(position)?.let { holder.bind(it) }
//        val item = getItem(position)
//        holder.bind(item)

        val item = getItem(position)
        item?.let { user ->
            holder.bind(user)
        }

    }

}

class DiffUtilCallBack : DiffUtil.ItemCallback<Countrie>() {
    override fun areItemsTheSame(oldItem: Countrie, newItem: Countrie): Boolean {
        return oldItem.cui == newItem.cui
    }

    override fun areContentsTheSame(oldItem: Countrie, newItem: Countrie): Boolean {
        return oldItem == newItem
    }
}