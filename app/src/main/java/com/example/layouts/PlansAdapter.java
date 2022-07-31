//package com.example.layouts;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.AppCompatImageView;
//import androidx.appcompat.widget.AppCompatTextView;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.viewpager.widget.PagerAdapter;
//
//import com.example.layouts.Models.PlansModel;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//class PlansAdapter extends PagerAdapter {
//
//    // Context object
//    Context context;
//    ArrayList<PlansModel> plansModel;
//    LayoutInflater mLayoutInflater;
//    // Viewpager Constructor
//    public PlansAdapter(Context context, ArrayList<PlansModel> plansModel) {
//        this.context = context;
//        this.plansModel = plansModel;
//        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//        // return the number of images
////        return plansModel.size();
//        return plansModel.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == ((ConstraintLayout) object);
//
////        return view.equals(object);
//    }
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
//        AppCompatImageView ivCorrectFinancialPlanning, ivWealthManagement, ivInfoMoneyWealth, ivTaxAdvisory,
//                ivInfoTaxAdvisory, ivTaxFiling, ivInfoMoneyTaxFilling, ivRiskManagement, ivRebalancing,
//                ivInfoMoneyRebalancing, ivReviewFrequency, ivChat;
//        AppCompatTextView tvFinancialPlanning, tvWealthManagement, tvTaxAdvisory, tvTaxFiling, tvRiskManagement,
//                tvRebalancing, tvReviewFrequency, tvFrequency, tvChat;
//        View viewFinancialPlanning, viewWealthManagement, viewTaxAdvisory, viewTaxFiling, viewRiskManagement,
//                viewRebalancing, viewReviewFrequency;
//        CheckBox checkboxPlanComparison;
//
//        View view = mLayoutInflater.inflate(R.layout.plans_recyler, container, false);
//
//
////        View view = mLayoutInflater.from(context).inflate(R.layout.plans_recyler, container, false);
//        PlansModel model = plansModel.get(position);
//
//        ivCorrectFinancialPlanning = view.findViewById(R.id.ivCorrectFinancialPlanning);
//        tvFinancialPlanning = view.findViewById(R.id.tvFinancialPlanning);
//        viewFinancialPlanning = view.findViewById(R.id.viewFinancialPlanning);
//        ivWealthManagement = view.findViewById(R.id.ivWealthManagement);
//        tvWealthManagement = view.findViewById(R.id.tvWealthManagement);
//        ivInfoMoneyWealth = view.findViewById(R.id.ivInfoMoneyWealth);
//        viewWealthManagement = view.findViewById(R.id.viewWealthManagement);
//        ivTaxAdvisory = view.findViewById(R.id.ivTaxAdvisory);
//        tvTaxAdvisory = view.findViewById(R.id.tvTaxAdvisory);
//        ivInfoTaxAdvisory = view.findViewById(R.id.ivInfoTaxAdvisory);
//        viewTaxAdvisory = view.findViewById(R.id.viewTaxAdvisory);
//        ivTaxFiling = view.findViewById(R.id.ivTaxFiling);
//        tvTaxFiling = view.findViewById(R.id.tvTaxFiling);
//        ivInfoMoneyTaxFilling = view.findViewById(R.id.ivInfoMoneyTaxFilling);
//        viewTaxFiling = view.findViewById(R.id.viewTaxFiling);
//        ivRiskManagement = view.findViewById(R.id.ivRiskManagement);
//        tvRiskManagement = view.findViewById(R.id.tvRiskManagement);
//        viewRiskManagement = view.findViewById(R.id.viewRiskManagement);
//        ivRebalancing = view.findViewById(R.id.ivRebalancing);
//        tvRebalancing = view.findViewById(R.id.tvRebalancing);
//        ivInfoMoneyRebalancing = view.findViewById(R.id.ivInfoMoneyRebalancing);
//        viewRebalancing = view.findViewById(R.id.viewRebalancing);
//        ivReviewFrequency = view.findViewById(R.id.ivReviewFrequency);
//        tvReviewFrequency = view.findViewById(R.id.tvReviewFrequency);
//        tvFrequency = view.findViewById(R.id.tvFrequency);
//        viewReviewFrequency = view.findViewById(R.id.viewReviewFrequency);
//        ivChat = view.findViewById(R.id.ivChat);
//        tvChat = view.findViewById(R.id.tvChat);
//        checkboxPlanComparison = view.findViewById(R.id.checkboxPlanComparison);
//
//
//        if (model.getIsCheckVisible() == 1) {
//            checkboxPlanComparison.setVisibility(View.VISIBLE);
//        } else {
//            checkboxPlanComparison.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getFinancial_planning().getData() == 0) {
//            ivCorrectFinancialPlanning.setVisibility(View.GONE);
//            tvFinancialPlanning.setVisibility(View.GONE);
//            viewFinancialPlanning.setVisibility(View.GONE);
//        }
//
//        if (model.getPrivate_wealth_managment().getData() == 0) {
//            ivInfoMoneyWealth.setVisibility(View.GONE);
//            ivWealthManagement.setVisibility(View.GONE);
//            tvWealthManagement.setVisibility(View.GONE);
//            viewWealthManagement.setVisibility(View.GONE);
//        }
//
//        if (model.getTax_advisory().getData() == 0) {
//            ivInfoTaxAdvisory.setVisibility(View.GONE);
//            ivTaxAdvisory.setVisibility(View.GONE);
//            tvTaxAdvisory.setVisibility(View.GONE);
//            viewTaxAdvisory.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getTax_filling().getData() == 0) {
//            ivInfoMoneyTaxFilling.setVisibility(View.GONE);
//            ivTaxFiling.setVisibility(View.GONE);
//            tvTaxFiling.setVisibility(View.GONE);
//            viewTaxFiling.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getRisk_management().getData() == 0) {
//            ivRiskManagement.setVisibility(View.GONE);
//            ivRiskManagement.setVisibility(View.GONE);
//            viewRiskManagement.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getRebalancing_portfolio().getData() == 0) {
//            ivRebalancing.setVisibility(View.GONE);
//            tvRebalancing.setVisibility(View.GONE);
//            ivInfoMoneyRebalancing.setVisibility(View.GONE);
//            viewRebalancing.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getReview_frequency().equalsIgnoreCase("0")) {
//            ivReviewFrequency.setVisibility(View.GONE);
//            tvReviewFrequency.setVisibility(View.GONE);
//            tvFrequency.setVisibility(View.GONE);
//            viewReviewFrequency.setVisibility(View.GONE);
//        }
//
//        if (plansModel.get(position).getChat_with_expert().getData() == 0) {
//            ivChat.setVisibility(View.GONE);
//            tvChat.setVisibility(View.GONE);
//        }
//
//        // Adding the View
//        Objects.requireNonNull(container).addView(view,position);
////        container.addView(view, position);
//
//        return view;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position,@NonNull Object object) {
//
//        container.removeView((ConstraintLayout) object);
//    }
//}
//
