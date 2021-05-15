package in.hideandseek.vaxvision.screens.sessions.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.libapi.sessions.model.Session;

public class SessionsAdapter extends RecyclerView.Adapter<SessionsAdapter.SessionsViewHolder> {

    private ArrayList<Session> sessions;
    private SimpleDateFormat dateSrcFormat;
    private SimpleDateFormat dateTargetFormat;
    private SimpleDateFormat timeSrcFormat;
    private SimpleDateFormat timeTargetFormat;

    private enum FeeType {
        FREE("Free"),
        PAID("Paid ");

        private String value;

        FeeType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }


    }

    public SessionsAdapter(ArrayList<Session> sessions) {
        this.sessions = sessions;

        dateSrcFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateTargetFormat = new SimpleDateFormat("MMM dd, yyyy");
        timeSrcFormat = new SimpleDateFormat("HH:mm:ss");
        timeTargetFormat = new SimpleDateFormat("hh:mm aa");
    }

    @NonNull
    @Override
    public SessionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_session, parent, false);
        SessionsViewHolder viewHolder = new SessionsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SessionsViewHolder holder, int position) {
        Session session = sessions.get(position);
        holder.mCenterName.setText(session.getName());
        holder.mAddress.setText(session.getAddress());
        String date = "";
        String fromTime = "";
        String toTime = "";
        try {
            date = dateTargetFormat.format(dateSrcFormat.parse(session.getDate()));
            fromTime = timeTargetFormat.format(timeSrcFormat.parse(session.getFrom()));
            toTime = timeTargetFormat.format(timeSrcFormat.parse(session.getTo()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.mDateTime.setText(date + " from " + fromTime + " to " + toTime);

        holder.mVacName.setText(session.getVaccine());
        holder.mDose1.setText(session.getAvailableCapacityDose1().toString());
        holder.mDose2.setText(session.getAvailableCapacityDose2().toString());

        String feeType = session.getFeeType();
        String fee = session.getFee();

        if (FeeType.FREE.getValue().equals(feeType))
            holder.mFee.setText(FeeType.FREE.getValue());
        else
            holder.mFee.setText(FeeType.PAID.getValue() + " " + fee);

        holder.mAge.setText("Available for age " + session.getMinAgeLimit().toString() + " and above");
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    class SessionsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_center_name)
        AppCompatTextView mCenterName;

        @BindView(R.id.tv_center_address)
        AppCompatTextView mAddress;

        @BindView(R.id.tv_datetime)
        AppCompatTextView mDateTime;

        @BindView(R.id.tv_vacname)
        AppCompatTextView mVacName;

        @BindView(R.id.tv_dose1)
        AppCompatTextView mDose1;

        @BindView(R.id.tv_dose2)
        AppCompatTextView mDose2;

        @BindView(R.id.tv_fee)
        AppCompatTextView mFee;

        @BindView(R.id.tv_min_age)
        AppCompatTextView mAge;

        @BindView(R.id.fl_register_cowin)
        FrameLayout mRegisterCowinBtn;

        public SessionsViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
