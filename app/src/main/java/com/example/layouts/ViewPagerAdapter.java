package com.example.layouts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.layouts.Models.PlansModel;
import com.example.layouts.databinding.BottomSheetPlansInfoBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ViewPagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater mLayoutInflater;
    List<PlansModel> plansModel;
    int clickCompare = 1;
    boolean checkPlansIcon = false;
    List<Integer> comparePages = new ArrayList<>();
    public ViewPagerAdapter(Context context, ArrayList<PlansModel> plansModel) {
        this.context = context;
        this.plansModel = plansModel;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return plansModel.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ConstraintLayout) object);
    }

    public void showSelected(int shouldShow) {
        if (shouldShow == 1) {
            clickCompare = 1;
            System.out.println("Click Compare:" + clickCompare);
        } else if (shouldShow == 0) {
            clickCompare = 0;
            int var = clickCompare;
            System.out.println("Click Compare:" + clickCompare);
        }
        notifyAll();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        AppCompatImageView ivCorrectFinancialPlanning, ivWealthManagement, ivInfoMoneyWealth, ivTaxAdvisory,
                ivInfoTaxAdvisory, ivTaxFiling, ivInfoMoneyTaxFilling, ivRiskManagement, ivRebalancing,
                ivInfoMoneyRebalancing, ivReviewFrequency, ivChat, ivCheckPlanComparison;
        AppCompatTextView tvFinancialPlanning, tvWealthManagement, tvTaxAdvisory, tvTaxFiling, tvRiskManagement,
                tvRebalancing, tvReviewFrequency, tvFrequency, tvChat;
        View viewFinancialPlanning, viewWealthManagement, viewTaxAdvisory, viewTaxFiling, viewRiskManagement,
                viewRebalancing, viewReviewFrequency;
//        CheckBox ivCheckPlanComparison;
        System.out.println("Plans mooooooodlelll" + plansModel.get(position).getCategory_name());

        View view = mLayoutInflater.inflate(R.layout.plans_recyler, container, false);
        PlansModel model = plansModel.get(position);

        ivCorrectFinancialPlanning = view.findViewById(R.id.ivCorrectFinancialPlanning);
        tvFinancialPlanning = view.findViewById(R.id.tvFinancialPlanning);
        viewFinancialPlanning = view.findViewById(R.id.viewFinancialPlanning);
        ivWealthManagement = view.findViewById(R.id.ivWealthManagement);
        tvWealthManagement = view.findViewById(R.id.tvWealthManagement);
        ivInfoMoneyWealth = view.findViewById(R.id.ivInfoMoneyWealth);
        viewWealthManagement = view.findViewById(R.id.viewWealthManagement);
        ivTaxAdvisory = view.findViewById(R.id.ivTaxAdvisory);
        tvTaxAdvisory = view.findViewById(R.id.tvTaxAdvisory);
        ivInfoTaxAdvisory = view.findViewById(R.id.ivInfoTaxAdvisory);
        viewTaxAdvisory = view.findViewById(R.id.viewTaxAdvisory);
        ivTaxFiling = view.findViewById(R.id.ivTaxFiling);
        tvTaxFiling = view.findViewById(R.id.tvTaxFiling);
        ivInfoMoneyTaxFilling = view.findViewById(R.id.ivInfoMoneyTaxFilling);
        viewTaxFiling = view.findViewById(R.id.viewTaxFiling);
        ivRiskManagement = view.findViewById(R.id.ivRiskManagement);
        tvRiskManagement = view.findViewById(R.id.tvRiskManagement);
        viewRiskManagement = view.findViewById(R.id.viewRiskManagement);
        ivRebalancing = view.findViewById(R.id.ivRebalancing);
        tvRebalancing = view.findViewById(R.id.tvRebalancing);
        ivInfoMoneyRebalancing = view.findViewById(R.id.ivInfoMoneyRebalancing);
        viewRebalancing = view.findViewById(R.id.viewRebalancing);
        ivReviewFrequency = view.findViewById(R.id.ivReviewFrequency);
        tvReviewFrequency = view.findViewById(R.id.tvReviewFrequency);
        tvFrequency = view.findViewById(R.id.tvFrequency);
        viewReviewFrequency = view.findViewById(R.id.viewReviewFrequency);
        ivChat = view.findViewById(R.id.ivChat);
        tvChat = view.findViewById(R.id.tvChat);
        ivCheckPlanComparison = view.findViewById(R.id.ivCheckPlanComparison);

        ivInfoMoneyWealth.setOnClickListener(v -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(context));
            bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

            mBottomSheetBinding.tvBSContent.setText("The wealth management service offered under this plan includes only Direct Mutual Funds.");
            mBottomSheetBinding.tvBSHeadLine.setText("Private Wealth Management");
            bottomSheetDialog.show();
        });


        ivCheckPlanComparison.setOnClickListener(v -> {
            checkPlansIcon = !checkPlansIcon;
            if (checkPlansIcon == true) {
                System.out.println("Checked");
                ivCheckPlanComparison.setBackgroundResource(R.drawable.correct);
                comparePages.add(position);
                System.out.println(comparePages);
                System.out.println(comparePages.size());
                if (comparePages.size() >= 2) {
                    Intent intent = new Intent(context, PlanComparisonPages.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", (Serializable) comparePages);
                    args.putInt("ARRAYLIS1", comparePages.get(1));
                    intent.putExtra("BUNDLE", args);
                    context.startActivity(intent);
                    comparePages.clear();
                }
            } else {
                System.out.println("Un Checked");
                System.out.println(comparePages);
                ivCheckPlanComparison.setBackgroundResource(R.drawable.check_mark_small);
                comparePages.remove(Integer.valueOf(position));
            }
        });

        if (clickCompare == 1) {
            ivCheckPlanComparison.setVisibility(View.VISIBLE);

        } else if (clickCompare == 0) {
            ivCheckPlanComparison.setVisibility(View.INVISIBLE);
        }


        ivInfoMoneyTaxFilling.setOnClickListener(v -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(context));
            bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

            mBottomSheetBinding.tvBSContent.setText("The service offered under this plan includes only tax filing for self.");
            mBottomSheetBinding.tvBSHeadLine.setText("Tax Filing");
            bottomSheetDialog.show();
        });

        ivInfoMoneyRebalancing.setOnClickListener(v -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(context));
            bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

            mBottomSheetBinding.tvBSContent.setText("Under this plan, we offer you the advisory and execution support required to rebalance your portfolio.");
            mBottomSheetBinding.tvBSHeadLine.setText("Re-balancing Portfolio");
            bottomSheetDialog.show();
        });

        if (comparePages.contains(Integer.valueOf(position))) {
            ivCheckPlanComparison.setBackgroundResource(R.drawable.correct);
            checkPlansIcon = true;
            if (checkPlansIcon) {
                System.out.println("Checked");
                ivCheckPlanComparison.setBackgroundResource(R.drawable.correct);
            }
        } else {
            checkPlansIcon = false;
            ivCheckPlanComparison.setBackgroundResource(R.drawable.check_mark_small);
        }


//        if (model.getIsCheckVisible() == 1) {
//            ivCheckPlanComparison.setVisibility(View.VISIBLE);
//        } else {
//            ivCheckPlanComparison.setVisibility(View.GONE);
//        }

        if (plansModel.get(position).getFinancial_planning().getData() == 0) {
            ivCorrectFinancialPlanning.setVisibility(View.GONE);
            tvFinancialPlanning.setVisibility(View.GONE);
            viewFinancialPlanning.setVisibility(View.GONE);
        }

        if (model.getPrivate_wealth_managment().getData() == 0) {
            ivInfoMoneyWealth.setVisibility(View.GONE);
            ivWealthManagement.setVisibility(View.GONE);
            tvWealthManagement.setVisibility(View.GONE);
            viewWealthManagement.setVisibility(View.GONE);
        }

        if (model.getTax_advisory().getData() == 0) {
            ivInfoTaxAdvisory.setVisibility(View.GONE);
            ivTaxAdvisory.setVisibility(View.GONE);
            tvTaxAdvisory.setVisibility(View.GONE);
            viewTaxAdvisory.setVisibility(View.GONE);
        }

        if (plansModel.get(position).getTax_filling().getData() == 0) {
            ivInfoMoneyTaxFilling.setVisibility(View.GONE);
            ivTaxFiling.setVisibility(View.GONE);
            tvTaxFiling.setVisibility(View.GONE);
            viewTaxFiling.setVisibility(View.GONE);
        }

        if (plansModel.get(position).getRisk_management().getData() == 0) {
            ivRiskManagement.setVisibility(View.GONE);
            tvRiskManagement.setVisibility(View.GONE);
            viewRiskManagement.setVisibility(View.GONE);
        }

        if (plansModel.get(position).getRebalancing_portfolio().getData() == 0) {
            ivRebalancing.setVisibility(View.GONE);
            tvRebalancing.setVisibility(View.GONE);
            ivInfoMoneyRebalancing.setVisibility(View.GONE);
            viewRebalancing.setVisibility(View.GONE);
        }

        if (plansModel.get(position).getReview_frequency().equalsIgnoreCase("0")) {
            ivReviewFrequency.setVisibility(View.GONE);
            tvReviewFrequency.setVisibility(View.GONE);
            tvFrequency.setVisibility(View.GONE);
            viewReviewFrequency.setVisibility(View.GONE);
        }
        if (plansModel.get(position).getChat_with_expert().getData() == 0) {
            ivChat.setVisibility(View.GONE);
            tvChat.setVisibility(View.GONE);
        }

        Objects.requireNonNull(container).addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((ConstraintLayout) object);
    }
}
