package in.hideandseek.vaxvision.common.Utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import androidx.annotation.NonNull;

import java.util.List;

public class MaterialSpinnerAdapter extends ArrayAdapter<String> {

    private Filter mFilter;

    public MaterialSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);

        mFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                results.values = objects;
                results.count = objects.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return mFilter;
    }
}
