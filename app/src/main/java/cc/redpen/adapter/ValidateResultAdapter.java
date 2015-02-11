package cc.redpen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.redpen.R;
import cc.redpen.model.entity.Error;

import java.util.ArrayList;
import java.util.List;

import static cc.redpen.Application.getContext;

public class ValidateResultAdapter extends RecyclerView.Adapter<ValidateResultAdapter.ViewHolder> {

    private final List<Error> dataSet;

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.linenumber_textview)
        TextView lineNumberTextView;

        @InjectView(R.id.message_textview)
        TextView messageTextView;

        @InjectView(R.id.sentence_textview)
        TextView sentenceTextView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    public ValidateResultAdapter(List<Error> dataSet) {
        this.dataSet = new ArrayList<>(dataSet);
    }

    @Override
    public ValidateResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_fragment_main, parent, false);
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.recyclerview_item_motion);
        view.startAnimation(anim);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Error item = dataSet.get(position);
        holder.messageTextView.setText(item.getMessage());
        holder.lineNumberTextView.setText(getContext().getString(R.string.label_line_textview, item.getLineNum()));
        holder.sentenceTextView.setText(getContext().getString(R.string.label_sentence_textview, item.getSentence()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}