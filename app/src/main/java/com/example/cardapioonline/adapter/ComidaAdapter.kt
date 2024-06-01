import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cardapioonline.databinding.ItemComidaBinding
import com.example.cardapioonline.model.Comida

class ComidaAdapter(private val comidas: List<Comida>) : RecyclerView.Adapter<ComidaAdapter.ComidaViewHolder>() {

    class ComidaViewHolder(val binding: ItemComidaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComidaViewHolder {
        val binding = ItemComidaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComidaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComidaViewHolder, position: Int) {
        val comida = comidas[position]
        holder.binding.tvComida.text = comida.nome
        holder.binding.tvValorUnitario.text = comida.valorUnitario.toString()
        holder.binding.tvQuantidade.text = comida.quantidade.toString()
        holder.binding.tvValorTotal.text = (comida.quantidade * comida.valorUnitario).toString()

        holder.binding.btnMais.setOnClickListener {
            comida.quantidade++
            holder.binding.tvQuantidade.text = comida.quantidade.toString()
            holder.binding.tvValorTotal.text = (comida.quantidade * comida.valorUnitario).toString()
        }

        holder.binding.btnMenos.setOnClickListener {
            if (comida.quantidade > 0) {
                comida.quantidade--
                holder.binding.tvQuantidade.text = comida.quantidade.toString()
                holder.binding.tvValorTotal.text = (comida.quantidade * comida.valorUnitario).toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return comidas.size
    }
}
