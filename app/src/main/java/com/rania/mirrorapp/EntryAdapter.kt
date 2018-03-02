package com.rania.mirrorapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.rania.mirrorapp.model.EntryModel
import com.rania.mirrorapp.model.Priority
import com.rania.mirrorapp.service.CardCalc
import com.rania.mirrorapp.service.DatePrettyPrinter
import kotlinx.android.synthetic.main.recycler_view_item_row.view.*
import java.util.*

class EntryAdapter(private val context: Context, models: Array<LinkedList<EntryModel>>) :
        RecyclerView.Adapter<EntryAdapter.ViewHolder>() {

    class ViewHolder(context: Context, private val view: View) :
            RecyclerView.ViewHolder(view),
            View.OnClickListener {

        private val datePrettyPrinter = DatePrettyPrinter(context)
        private val personalityCardMsg = context.resources.getString(R.string.personality_card_msg)
        private val relationshipCardMsg = context.resources.getString(R.string.relationship_card_msg)
        private lateinit var entry: EntryModel

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            // nothing here
        }

        fun bindEntry(mainModel: EntryModel, entryModel: EntryModel) {
            entry = entryModel
            view.person_name.text = entryModel.nameOfPerson
            view.entry.text = datePrettyPrinter.prettify(entry)
            when(entry.priority) {
                Priority.APP_USER -> {
                    view.card_preview.text = String.format(personalityCardMsg, CardCalc.personal(entry))
                    view.priority.text = "(you)"
                }
                Priority.FAVORITE -> {
                    view.card_preview.text = String.format(relationshipCardMsg, CardCalc.relationship(entry, mainModel))
                    view.priority.text = "<3"
                }
                else -> {
                    view.card_preview.text = String.format(personalityCardMsg, CardCalc.personal(entry))
                }
            }
        }
    }

    private val mainBirthdayModel = models[Priority.APP_USER.ordinal][0]
    val dataset = ArrayList<EntryModel>()

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
            ViewHolder(context, parent.inflate(R.layout.recycler_view_item_row))
}
