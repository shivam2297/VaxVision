package in.hideandseek.vaxvision.screens.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.libapi.calendar.model.Center;
import in.hideandseek.vaxvision.common.libapi.calendar.model.Session;
import in.hideandseek.vaxvision.screens.calendar.viewmodel.CenterViewModel;
import in.hideandseek.vaxvision.screens.calendar.viewmodel.Filter;
import in.hideandseek.vaxvision.screens.sessions.adapter.SessionsAdapter;

public class CentersAdapter extends RecyclerView.Adapter<CentersAdapter.CentersViewHolder> {

    private ArrayList<CenterViewModel> mCenters;
    private Context mContext;

    public CentersAdapter(ArrayList<CenterViewModel> mCenters, Context mContext) {
        this.mCenters = mCenters;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CentersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_calendar_view, parent, false);
        CentersViewHolder viewHolder = new CentersViewHolder(view, mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CentersViewHolder holder, int position) {
        holder.bindView(mCenters.get(position));
    }

    @Override
    public int getItemCount() {
        return mCenters.size();
    }

    class CentersViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_tv)
        AppCompatTextView nameTv;

        @BindView(R.id.addr_tv)
        AppCompatTextView addrTv;

        @BindView(R.id.fee_tv)
        AppCompatTextView feeTv;

        @BindView(R.id.rv_date_1)
        RecyclerView rvDate1;

        @BindView(R.id.rv_date_2)
        RecyclerView rvDate2;

        @BindView(R.id.rv_date_3)
        RecyclerView rvDate3;

        @BindView(R.id.rv_date_4)
        RecyclerView rvDate4;

        @BindView(R.id.rv_date_5)
        RecyclerView rvDate5;

        @BindView(R.id.rv_date_6)
        RecyclerView rvDate6;

        @BindView(R.id.rv_date_7)
        RecyclerView rvDate7;

        private SlotsAdapter mAdapter1;
        private SlotsAdapter mAdapter2;
        private SlotsAdapter mAdapter3;
        private SlotsAdapter mAdapter4;
        private SlotsAdapter mAdapter5;
        private SlotsAdapter mAdapter6;
        private SlotsAdapter mAdapter7;

        private ArrayList<Session> mSessionDate1 = new ArrayList<>();
        private ArrayList<Session> mSessionDate2 = new ArrayList<>();
        private ArrayList<Session> mSessionDate3 = new ArrayList<>();
        private ArrayList<Session> mSessionDate4 = new ArrayList<>();
        private ArrayList<Session> mSessionDate5 = new ArrayList<>();
        private ArrayList<Session> mSessionDate6 = new ArrayList<>();
        private ArrayList<Session> mSessionDate7 = new ArrayList<>();


        public CentersViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mAdapter1 = new SlotsAdapter(mSessionDate1);
            mAdapter2 = new SlotsAdapter(mSessionDate2);
            mAdapter3 = new SlotsAdapter(mSessionDate3);
            mAdapter4 = new SlotsAdapter(mSessionDate4);
            mAdapter5 = new SlotsAdapter(mSessionDate5);
            mAdapter6 = new SlotsAdapter(mSessionDate6);
            mAdapter7 = new SlotsAdapter(mSessionDate7);

            rvDate1.setLayoutManager(new LinearLayoutManager(context));
            rvDate2.setLayoutManager(new LinearLayoutManager(context));
            rvDate3.setLayoutManager(new LinearLayoutManager(context));
            rvDate4.setLayoutManager(new LinearLayoutManager(context));
            rvDate5.setLayoutManager(new LinearLayoutManager(context));
            rvDate6.setLayoutManager(new LinearLayoutManager(context));
            rvDate7.setLayoutManager(new LinearLayoutManager(context));

            rvDate1.setAdapter(mAdapter1);
            rvDate2.setAdapter(mAdapter2);
            rvDate3.setAdapter(mAdapter3);
            rvDate4.setAdapter(mAdapter4);
            rvDate5.setAdapter(mAdapter5);
            rvDate6.setAdapter(mAdapter6);
            rvDate7.setAdapter(mAdapter7);
        }

        public void bindView(CenterViewModel center) {
            nameTv.setText(center.name);
            addrTv.setText(center.address);
            feeTv.setText(Filter.FEE_FILTER_FREE.equals(center.feeType) ? "FREE" : center.vaccineFees.get(0).getFee() + "+");

            mSessionDate1 = (ArrayList<Session>) center.sessions.get(0);
            mSessionDate2 = (ArrayList<Session>) center.sessions.get(1);
            mSessionDate3 = (ArrayList<Session>) center.sessions.get(2);
            mSessionDate4 = (ArrayList<Session>) center.sessions.get(3);
            mSessionDate5 = (ArrayList<Session>) center.sessions.get(4);
            mSessionDate6 = (ArrayList<Session>) center.sessions.get(5);
            mSessionDate7 = (ArrayList<Session>) center.sessions.get(6);

            mAdapter1.notifyDataSetChanged();
            mAdapter2.notifyDataSetChanged();
            mAdapter3.notifyDataSetChanged();
            mAdapter4.notifyDataSetChanged();
            mAdapter5.notifyDataSetChanged();
            mAdapter6.notifyDataSetChanged();
            mAdapter7.notifyDataSetChanged();
        }
    }

}
