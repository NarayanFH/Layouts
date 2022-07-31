//package com.example.layouts.adapters;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Parcelable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.ScrollView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.AppCompatImageView;
//import androidx.appcompat.widget.AppCompatTextView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.layouts.Models.PlansModel;
//import com.example.layouts.R;
//import com.example.layouts.databinding.BottomSheetPlansInfoBinding;
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PlansRecyclerAdapter extends RecyclerView.Adapter<PlansRecyclerAdapter.ViewHolder> {
//    Context context;
//    List<PlansModel> plansModel;
//    List<PlansModel> selectedplansModel = new ArrayList<>();
////    AppCompatTextView tvBasicStatic, tvBasic, tvInvestmentPortfolio;
////    ScrollView scrollviewPlan;
//    int currentPosition = 0;
//
//    public PlansRecyclerAdapter(Context context, List<PlansModel> plansModel) {
//        this.context = context;
//        this.plansModel = plansModel;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.plans_recyler, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        System.out.println(position);
//
//        currentPosition = holder.getAdapterPosition();
//        System.out.println("Position:"+currentPosition);
//
//
//        if (plansModel.get(position).getIsCheckVisible() == 1) {
//            holder.checkboxPlanComparison.setVisibility(View.VISIBLE);
//        } else {
//            holder.checkboxPlanComparison.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getFinancial_planning().getData() == 0) {
//            holder.ivCorrectFinancialPlanning.setVisibility(View.GONE);
//            holder.tvFinancialPlanning.setVisibility(View.GONE);
//            holder.viewFinancialPlanning.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getPrivate_wealth_managment().getData() == 0) {
//            holder.ivInfoMoneyWealth.setVisibility(View.GONE);
//            holder.ivWealthManagement.setVisibility(View.GONE);
//            holder.tvWealthManagement.setVisibility(View.GONE);
//            holder.viewWealthManagement.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getTax_advisory().getData() == 0) {
//            holder.ivInfoTaxAdvisory.setVisibility(View.GONE);
//            holder.ivTaxAdvisory.setVisibility(View.GONE);
//            holder.tvTaxAdvisory.setVisibility(View.GONE);
//            holder.viewTaxAdvisory.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getTax_filling().getData() == 0) {
//            holder.ivInfoMoneyTaxFilling.setVisibility(View.GONE);
//            holder.ivTaxFiling.setVisibility(View.GONE);
//            holder.tvTaxFiling.setVisibility(View.GONE);
//            holder.viewTaxFiling.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getRisk_management().getData() == 0) {
//            holder.ivRiskManagement.setVisibility(View.GONE);
//            holder.tvRiskManagement.setVisibility(View.GONE);
//            holder.viewRiskManagement.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getRebalancing_portfolio().getData() == 0) {
//            holder.ivRebalancing.setVisibility(View.GONE);
//            holder.tvRebalancing.setVisibility(View.GONE);
//            holder.ivInfoMoneyRebalancing.setVisibility(View.GONE);
//            holder.viewRebalancing.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getReview_frequency().equalsIgnoreCase("0")) {
//            holder.ivReviewFrequency.setVisibility(View.GONE);
//            holder.tvReviewFrequency.setVisibility(View.GONE);
//            holder.tvFrequency.setVisibility(View.GONE);
//            holder.viewReviewFrequency.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getChat_with_expert().getData() == 0) {
//            holder.ivChat.setVisibility(View.GONE);
//            holder.tvChat.setVisibility(View.GONE);
//        }
//
//        holder.ivInfoMoneyWealth.setOnClickListener(v -> {
//            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
//            BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(context));
//            bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());
//
//            mBottomSheetBinding.tvBSHeadLine.setText("Private Wealth Management");
//            mBottomSheetBinding.tvBSContent.setText("The wealth management service offered under this plan includes only Direct Mutual Funds.");
//
////            mBottomSheetBinding.ivClose.setOnClickListener(v12 -> bottomSheetDialog.dismiss());
//
//            bottomSheetDialog.show();
//        });
//
////        holder.ivInfoMoneyTaxFilling.setOnClickListener(v -> {
////            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
////            BottomSheetLayoutBinding mBottomSheetBinding = BottomSheetLayoutBinding.inflate(LayoutInflater.from(context));
////            bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());
////
////            mBottomSheetBinding.tvTitle.setText("Tax Filing");
////            mBottomSheetBinding.tvDescription.setText("The service offered under this plan includes only tax filing for self.");
////
////            mBottomSheetBinding.ivClose.setOnClickListener(v13 -> bottomSheetDialog.dismiss());
////
////            bottomSheetDialog.show();
////        });
////
////        holder.ivInfoMoneyRebalancing.setOnClickListener(v -> {
////            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
////            BottomSheetLayoutBinding mBottomSheetBinding = BottomSheetLayoutBinding.inflate(LayoutInflater.from(context));
////            bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());
////
////            mBottomSheetBinding.tvTitle.setText("Rebalancing of Portfolio");
////            mBottomSheetBinding.tvDescription.setText("Under this plan, we offer you the advisory and execution support required to rebalance your portfolio.");
////
////            mBottomSheetBinding.ivClose.setOnClickListener(v14 -> bottomSheetDialog.dismiss());
////
////            bottomSheetDialog.show();
////        });
//
////        holder.checkboxPlanComparison.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////            @Override
////            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
////                if (isChecked) {
////                    selectedplansModel.add(plansModel.get(holder.getAdapterPosition()));
////                    if (selectedplansModel.size() == 2) {
////                        Intent intent = new Intent(context, ComparisonActivity.class);
////                        intent.putExtra("selected_list", (Parcelable) selectedplansModel);
////                        context.startActivity(intent);
////                    }
////                } else {
////                    if (selectedplansModel != null && selectedplansModel.size() > 0) {
////                        for (int i = 0; i < selectedplansModel.size(); i++) {
////                            if (selectedplansModel.get(i).getPlan_id().equalsIgnoreCase(plansModel.get(holder.getAdapterPosition()).getPlan_id())) {
////                                selectedplansModel.remove(i);
////                            }
////                        }
////                    }
////                }
////            }
////        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return plansModel.size();
//    }
//
//    protected class ViewHolder extends RecyclerView.ViewHolder {
//        AppCompatImageView ivCorrectFinancialPlanning, ivWealthManagement, ivInfoMoneyWealth, ivTaxAdvisory,
//                ivInfoTaxAdvisory, ivTaxFiling, ivInfoMoneyTaxFilling, ivRiskManagement, ivRebalancing,
//                ivInfoMoneyRebalancing, ivReviewFrequency, ivChat;
//        AppCompatTextView tvFinancialPlanning, tvWealthManagement, tvTaxAdvisory, tvTaxFiling, tvRiskManagement,
//                tvRebalancing, tvReviewFrequency, tvFrequency, tvChat;
//        View viewFinancialPlanning, viewWealthManagement, viewTaxAdvisory, viewTaxFiling, viewRiskManagement,
//                viewRebalancing, viewReviewFrequency;
//        CheckBox checkboxPlanComparison;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            ivCorrectFinancialPlanning = itemView.findViewById(R.id.ivCorrectFinancialPlanning);
//            tvFinancialPlanning = itemView.findViewById(R.id.tvFinancialPlanning);
//            viewFinancialPlanning = itemView.findViewById(R.id.viewFinancialPlanning);
//            ivWealthManagement = itemView.findViewById(R.id.ivWealthManagement);
//            tvWealthManagement = itemView.findViewById(R.id.tvWealthManagement);
//            ivInfoMoneyWealth = itemView.findViewById(R.id.ivInfoMoneyWealth);
//            viewWealthManagement = itemView.findViewById(R.id.viewWealthManagement);
//            ivTaxAdvisory = itemView.findViewById(R.id.ivTaxAdvisory);
//            tvTaxAdvisory = itemView.findViewById(R.id.tvTaxAdvisory);
//            ivInfoTaxAdvisory = itemView.findViewById(R.id.ivInfoTaxAdvisory);
//            viewTaxAdvisory = itemView.findViewById(R.id.viewTaxAdvisory);
//            ivTaxFiling = itemView.findViewById(R.id.ivTaxFiling);
//            tvTaxFiling = itemView.findViewById(R.id.tvTaxFiling);
//            ivInfoMoneyTaxFilling = itemView.findViewById(R.id.ivInfoMoneyTaxFilling);
//            viewTaxFiling = itemView.findViewById(R.id.viewTaxFiling);
//            ivRiskManagement = itemView.findViewById(R.id.ivRiskManagement);
//            tvRiskManagement = itemView.findViewById(R.id.tvRiskManagement);
//            viewRiskManagement = itemView.findViewById(R.id.viewRiskManagement);
//            ivRebalancing = itemView.findViewById(R.id.ivRebalancing);
//            tvRebalancing = itemView.findViewById(R.id.tvRebalancing);
//            ivInfoMoneyRebalancing = itemView.findViewById(R.id.ivInfoMoneyRebalancing);
//            viewRebalancing = itemView.findViewById(R.id.viewRebalancing);
//            ivReviewFrequency = itemView.findViewById(R.id.ivReviewFrequency);
//            tvReviewFrequency = itemView.findViewById(R.id.tvReviewFrequency);
//            tvFrequency = itemView.findViewById(R.id.tvFrequency);
//            viewReviewFrequency = itemView.findViewById(R.id.viewReviewFrequency);
//            ivChat = itemView.findViewById(R.id.ivChat);
//            tvChat = itemView.findViewById(R.id.tvChat);
//            checkboxPlanComparison = itemView.findViewById(R.id.checkboxPlanComparison);
//        }
//    }
//
//    public void showSelected(int shouldShow) {
//        if (shouldShow == 1) {
//            if (plansModel != null && plansModel.size() > 0) {
//                for (int i = 0; i < plansModel.size(); i++) {
//                    plansModel.get(i).setIsCheckVisible(1);
//                }
//            }
//        } else {
//            if (plansModel != null && plansModel.size() > 0) {
//                for (int i = 0; i < plansModel.size(); i++) {
//                    plansModel.get(i).setIsCheckVisible(0);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//}
