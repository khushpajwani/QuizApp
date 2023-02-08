package com.app.quizapp.adapter

/*
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.quizapp.BR
import com.app.quizapp.databinding.QuizItemBinding
import com.app.quizapp.interfaces.ItemClickListInterface

class QuizAdapter(
    var quizList: ArrayList<com.app.quizapp.response.Result>,
    val context: Context,
    private val itemClickListInterface: ItemClickListInterface
) :
     RecyclerView.Adapter<QuizAdapter.BudgetViewHolder>() {

    class BudgetViewHolder(val itemLayoutBinding: QuizItemBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {
        fun bind(quizList: com.app.quizapp.response.Result, position: Int, itemClickListInterface: ItemClickListInterface) {
            var answersList: ArrayList<String> = ArrayList()
            answersList.add(quizList.correct_answer)
            for (i in quizList.incorrect_answers) {
                answersList.add(i)
            }
            Log.e("TAG", "bind: ${answersList.shuffled()}")
            itemLayoutBinding.setVariable(BR.questions, quizList)
            itemLayoutBinding.setVariable(BR.answers, answersList.  shuffled())
            itemLayoutBinding.setVariable(BR.position, position)
            itemLayoutBinding.setVariable(BR.itemClickListInterface, itemClickListInterface)
            itemLayoutBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: QuizItemBinding =
            QuizItemBinding.inflate(layoutInflater, parent, false)
        return BudgetViewHolder(itemBinding)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        holder.bind(quizList[position], position, itemClickListInterface)
    }

    override fun getItemCount(): Int {
        return quizList.size
    }
}*/
