package in.hideandseek.vaxvision.screens.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.libapi.calendar.model.Session;

public class SlotsAdapter extends RecyclerView.Adapter<SlotsAdapter.SlotViewHolder> {

    private ArrayList<Session> mSessions;

    public SlotsAdapter(ArrayList<Session> mSessions) {
        this.mSessions = mSessions;
    }

    @NonNull
    @Override
    public SlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_calendar_slot, parent, false);
        SlotViewHolder viewHolder = new SlotViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SlotViewHolder holder, int position) {
        holder.bindView(mSessions.get(position));
    }

    @Override
    public int getItemCount() {
        return mSessions.size();
    }

    class SlotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_dose1)
        AppCompatTextView dose1Tv;

        @BindView(R.id.tv_dose2)
        AppCompatTextView dose2Tv;

        @BindView(R.id.tv_vaccine)
        AppCompatTextView vaccineNameTv;

        @BindView(R.id.tv_min_age)
        AppCompatTextView ageTv;

        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(Session session) {
            dose1Tv.setText(session.getAvailableCapacityDose1().toString());
            dose2Tv.setText(session.getAvailableCapacityDose2().toString());
            vaccineNameTv.setText(session.getVaccine());
            ageTv.setText(session.getMinAgeLimit()+"+");
        }
    }
}
