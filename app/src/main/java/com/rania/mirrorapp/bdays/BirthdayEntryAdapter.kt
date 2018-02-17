package com.rania.mirrorapp.bdays

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.rania.mirrorapp.R
import com.rania.mirrorapp.model.BirthdayModel
import com.rania.mirrorapp.model.Priority
import com.rania.mirrorapp.util.Calculator
import com.rania.mirrorapp.util.DatePrettyPrinter
import kotlinx.android.synthetic.main.recycler_view_item_row.view.*
import java.util.*

class BirthdayEntryAdapter(private val context: Context, private val models: Array<LinkedList<BirthdayModel>>) :
        RecyclerView.Adapter<BirthdayEntryAdapter.ViewHolder>() {

    class ViewHolder(context: Context, private val view: View) :
            RecyclerView.ViewHolder(view),
            View.OnClickListener {

        private val datePrettyPrinter = DatePrettyPrinter(context)
        private val personalityCardMsg = context.resources.getString(R.string.personality_card_msg)
        private val relationshipCardMsg = context.resources.getString(R.string.relationship_card_msg)
        private lateinit var birthday: BirthdayModel

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            // nothing here
        }

        fun bindEntry(mainModel: BirthdayModel, birthdayModel: BirthdayModel) {
            birthday = birthdayModel
            view.person_name.text = birthdayModel.nameOfPerson
            view.birthday.text = datePrettyPrinter.prettify(birthday)
            when(birthday.priority) {
                Priority.APP_USER -> {
                    view.card_preview.text = String.format(personalityCardMsg, Calculator.personalityCard(birthday))
                    view.priority.text = "(you)"
                }
                Priority.FAVORITE -> {
                    view.card_preview.text = String.format(relationshipCardMsg, Calculator.relationshipCard(birthday, mainModel))
                    view.priority.text = "<3"
                }
                else -> {
                    view.card_preview.text = String.format(relationshipCardMsg, Calculator.relationshipCard(birthday, mainModel))
                }
            }
        }

        private fun isMainUser() = birthday.priority == Priority.APP_USER

    }

    private val mainBirthdayModel = models[Priority.APP_USER.ordinal][0]
    private val dataset = ArrayList<BirthdayModel>()

    init {
        models.forEach {
            dataset.addAll(it)
        }
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindEntry(mainBirthdayModel, dataset[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            BirthdayEntryAdapter.ViewHolder(context, parent.inflate(R.layout.recycler_view_item_row))
}
