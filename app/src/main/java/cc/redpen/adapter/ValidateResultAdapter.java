package cc.redpen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.redpen.R;
import cc.redpen.model.entity.Error;

import java.util.ArrayList;
import java.util.List;

public class ValidateResultAdapter extends RecyclerView.Adapter<ValidateResultAdapter.ViewHolder> {

    private List<Error> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.message_textview)
        TextView messageTextView;

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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_fragment_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Error item = dataSet.get(position);
        holder.messageTextView.setText(item.getMessage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}