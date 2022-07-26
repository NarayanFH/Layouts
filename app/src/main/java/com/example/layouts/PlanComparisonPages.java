package com.example.layouts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.layouts.databinding.ActivityPlanComparisonPagesBinding;
import com.example.layouts.databinding.BottomSheetDialogBinding;
import com.example.layouts.databinding.BottomSheetPlansInfoBinding;
import com.example.layouts.modals.BasicProModal;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanComparisonPages extends AppCompatActivity {
    ActivityPlanComparisonPagesBinding mBinding;
    Integer first_page;
    Integer second_page;
    BasicProModal calledModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ArrayList<Integer> page_list = getIntent().getIntegerArrayListExtra("pageNumbers");

        setContentView(R.layout.activity_plan_comparison_pages);
        mBinding = ActivityPlanComparisonPagesBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Integer> pages = (ArrayList<Integer>) args.getSerializable("ARRAYLIST");

        first_page = pages.get(0);
        second_page = pages.get(1);
        getProData();

//        if(first_page ==0 || second_page ==0) {
//            mBinding.imvTaxFilingInfo1.setVisibility(View.GONE);
//            mBinding.imvRebalancingInfo1.setVisibility(View.GONE);
//            mBinding.imvWealthInfo1.setVisibility(View.GONE);
//            mBinding.imvTaxFilingInfo2.setVisibility(View.GONE);
//            mBinding.imvRebalancingInfo2.setVisibility(View.GONE);
//            mBinding.imvWealthInfo2.setVisibility(View.GONE);
//        } else {
//            mBinding.imvTaxFilingInfo1.setVisibility(View.VISIBLE);
//            mBinding.imvRebalancingInfo1.setVisibility(View.VISIBLE);
//            mBinding.imvWealthInfo1.setVisibility(View.VISIBLE);
//            mBinding.imvTaxFilingInfo2.setVisibility(View.VISIBLE);
//            mBinding.imvRebalancingInfo2.setVisibility(View.VISIBLE);
//            mBinding.imvWealthInfo2.setVisibility(View.VISIBLE);
//        }

        mBinding.imvBilledDurInfo1.setOnClickListener(v -> showBottomSheetDialog1());
        mBinding.imvBilledDurInfo2.setOnClickListener(v -> showBottomSheetDialog2());

        mBinding.imvWealthInfo1.setOnClickListener(v -> showBottomSheetWealthDialog1());
        mBinding.imvWealthInfo2.setOnClickListener(v -> showBottomSheetWealthDialog2());

        mBinding.imvTaxFilingInfo1.setOnClickListener(v -> showBottomSheetTaxFilingDialog1());
        mBinding.imvTaxFilingInfo2.setOnClickListener(v -> showBottomSheetTaxFilingDialog2());
        mBinding.imvRebalancingInfo1.setOnClickListener(v -> showBottomSheetRebalancingPFDialog1());
        mBinding.imvRebalancingInfo2.setOnClickListener(v -> showBottomSheetRebalancingPFDialog2());


    }

    private void showBottomSheetRebalancingPFDialog2() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        mBottomSheetBinding.tvBSContent.setText(calledModal.data.plan_details.plannedDetailsArrays.get(second_page).priceWealth.meta.app);
        mBottomSheetBinding.tvBSHeadLine.setText("Re-balancing Portfolio");
        bottomSheetDialog.show();
    }

    private void showBottomSheetRebalancingPFDialog1() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        mBottomSheetBinding.tvBSContent.setText(calledModal.data.plan_details.plannedDetailsArrays.get(first_page).priceWealth.meta.app);
        mBottomSheetBinding.tvBSHeadLine.setText("Re-balancing Portfolio");
        bottomSheetDialog.show();
    }

    private void showBottomSheetTaxFilingDialog2() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        mBottomSheetBinding.tvBSContent.setText(calledModal.data.plan_details.plannedDetailsArrays.get(second_page).priceWealth.meta.app);
        mBottomSheetBinding.tvBSHeadLine.setText("Tax Filing");
        bottomSheetDialog.show();
    }

    private void showBottomSheetTaxFilingDialog1() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        mBottomSheetBinding.tvBSContent.setText(calledModal.data.plan_details.plannedDetailsArrays.get(first_page).priceWealth.meta.app);
        mBottomSheetBinding.tvBSHeadLine.setText("Tax Filing");
        bottomSheetDialog.show();
    }

    private void showBottomSheetWealthDialog2() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());
        mBottomSheetBinding.tvBSContent.setText(calledModal.data.plan_details.plannedDetailsArrays.get(second_page).priceWealth.meta.app);
        mBottomSheetBinding.tvBSHeadLine.setText("Private Wealth Management");
        bottomSheetDialog.show();
    }

    private void showBottomSheetWealthDialog1() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());
        mBottomSheetBinding.tvBSContent.setText(calledModal.data.plan_details.plannedDetailsArrays.get(first_page).priceWealth.meta.app);
        mBottomSheetBinding.tvBSHeadLine.setText("Private Wealth Management");
        bottomSheetDialog.show();
    }

    private void showBottomSheetDialog1() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetDialogBinding mBottomSheetBinding = BottomSheetDialogBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        if (calledModal.data.plan_details.plannedDetailsArrays.get(first_page).amount.isQuarterly == 0) {
            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be Rs. 1,499 \n every six months.");
            mBottomSheetBinding.tvQ1Price.setText("H1 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(first_page).amount.total);
            mBottomSheetBinding.tvQ2Price.setText("H2 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(first_page).amount.total);
            mBottomSheetBinding.tvQ3Price.setVisibility(View.INVISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.INVISIBLE);
            bottomSheetDialog.show();
        } else if (calledModal.data.plan_details.plannedDetailsArrays.get(first_page).amount.isQuarterly == 1) {
            mBottomSheetBinding.tvQ3Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvBSPlanName.setText(calledModal.data.plan_details.plannedDetailsArrays.get(first_page).category_name);
            mBottomSheetBinding.tvBSPrice.setText(" | " + calledModal.data.plan_details.plannedDetailsArrays.get(first_page).monthly_amount);
            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be quarterly, that is once in every three months.");
            mBottomSheetBinding.tvQ1Price.setText("Q1 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(first_page).amount.Q1);
            mBottomSheetBinding.tvQ2Price.setText("Q2 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(first_page).amount.Q2);
            mBottomSheetBinding.tvQ3Price.setText("Q3 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(first_page).amount.Q3);
            mBottomSheetBinding.tvQ4Price.setText("Q4 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(first_page).amount.Q4);
            bottomSheetDialog.show();
        }
    }

    private void showBottomSheetDialog2() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetDialogBinding mBottomSheetBinding = BottomSheetDialogBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        if (calledModal.data.plan_details.plannedDetailsArrays.get(second_page).amount.isQuarterly == 0) {
            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be Rs. 1,499 \n every six months.");
            mBottomSheetBinding.tvQ1Price.setText("H1 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(second_page).amount.total);
            mBottomSheetBinding.tvQ2Price.setText("H2 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(second_page).amount.total);
            mBottomSheetBinding.tvQ3Price.setVisibility(View.INVISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.INVISIBLE);
            bottomSheetDialog.show();
        } else if (calledModal.data.plan_details.plannedDetailsArrays.get(second_page).amount.isQuarterly == 1) {
            mBottomSheetBinding.tvQ3Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvBSPlanName.setText(calledModal.data.plan_details.plannedDetailsArrays.get(second_page).category_name);
            mBottomSheetBinding.tvBSPrice.setText(" | " + calledModal.data.plan_details.plannedDetailsArrays.get(second_page).monthly_amount);
            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be quarterly, that is once in every three months.");
            mBottomSheetBinding.tvQ1Price.setText("Q1 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(second_page).amount.Q1);
            mBottomSheetBinding.tvQ2Price.setText("Q2 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(second_page).amount.Q2);
            mBottomSheetBinding.tvQ3Price.setText("Q3 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(second_page).amount.Q3);
            mBottomSheetBinding.tvQ4Price.setText("Q4 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(second_page).amount.Q4);
            bottomSheetDialog.show();
        }
    }

    private void getProData() {
//        mBinding.progressBar.setIndeterminate(true);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.fintoo.in/restapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroFitAPI retrofitAPI = retrofit.create(RetroFitAPI.class);
        Call<BasicProModal> call = retrofitAPI.getProData();
        call.enqueue(new Callback<BasicProModal>() {
            @Override
            public void onResponse(Call<BasicProModal> call, Response<BasicProModal> response) {

                Toast.makeText(PlanComparisonPages.this, "Data Loaded", Toast.LENGTH_SHORT).show();

                BasicProModal responseFromAPI = response.body();
                calledModal = response.body();

                String durationValue1 = "No Data";
                Integer apiVal1 = responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).payment_frequency;
                if (apiVal1 == 6) {
                    durationValue1 = "Billed Half Yearly";
                } else if (apiVal1 == 1)
                    durationValue1 = "Monthly";
                else if (apiVal1 == 3)
                    durationValue1 = "Quarterly";
                else
                    durationValue1 = "No calculation added for:" + durationValue1;

                String plan_type_value1 = "";
                if (responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).plan_type.equals("robo_advisory")) {
                    plan_type_value1 = "Robo Advisory";
                } else
                    plan_type_value1 = "Chat with Experts";


                String durationValue2 = "No Data";
                Integer apiVal2 = responseFromAPI.data.plan_details.plannedDetailsArrays.get(second_page).payment_frequency;
                if (apiVal2 == 6) {
                    durationValue2 = "Billed Half Yearly";
                } else if (apiVal2 == 1)
                    durationValue2 = "Monthly";
                else if (apiVal2 == 3)
                    durationValue2 = "Quarterly";
                else
                    durationValue2 = "No calculation added for:" + durationValue2;

                String plan_type_value2 = "";
                if (responseFromAPI.data.plan_details.plannedDetailsArrays.get(second_page).plan_type.equals("robo_advisory")) {
                    plan_type_value2 = "Robo Advisory";
                } else
                    plan_type_value2 = "Chat with Experts";

//                setPortFolioValue.setText(responseFromAPI.data.portfolio_value);
                System.out.println("Response From APi  ......" + responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).monthly_amount);
                System.out.println("Response From APi  ......" + responseFromAPI.data.plan_details.plannedDetailsArrays.get(1).monthly_amount);
                mBinding.tvPlanName1Comp.setText(responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).category_name);
                mBinding.tvPlanName2Comp.setText(responseFromAPI.data.plan_details.plannedDetailsArrays.get(second_page).category_name);
                mBinding.tvBilledDurationComp1.setText(durationValue1);
                mBinding.tvBilledDurationComp2.setText(durationValue2);
                mBinding.tvPlanTypeCompVal1.setText(plan_type_value1);
                mBinding.tvPlanTypeCompVal2.setText(plan_type_value2);
                mBinding.tvPlanPrice1Comp.setText("₹" + responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).monthly_amount);
                mBinding.tvPlanPrice2Comp.setText("₹ " + responseFromAPI.data.plan_details.plannedDetailsArrays.get(second_page).monthly_amount);


                // 1st Page Plan value
                BasicProModal.PlannedDetailsArray val = responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page);
                if (responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).financialPlanning.financialPlanningData == 1) {
//                  mBinding.tvPlanTypeCompVal1.setText("❌");
                    mBinding.tvFinancialPlanningCompVal1.setText("✔");

                } else {
                    mBinding.tvFinancialPlanningCompVal1.setText("❌");
                }

                if (responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).priceWealth.priceWealthData == 1) {
//                  mBinding.tvPlanTypeCompVal1.setText("❌");
                    mBinding.tvPrivateWealthCompVal1.setText("✔");
                } else {
                    mBinding.tvPrivateWealthCompVal1.setText("❌");
                }

                if (val.taxFilling.taxFillingData == 1) {
                    mBinding.tvTaxFilingVal1.setText("✔");
                } else {
                    mBinding.tvTaxFilingVal1.setText("❌");
                }

                if (val.rebalancingPortfolio.rebalancingPortfolioData == 1) {
                    mBinding.tvRebalancingPFCompVal1.setText("✔");
                } else {
                    mBinding.tvRebalancingPFCompVal1.setText("❌");
                }

                if (val.riskManagement.riskManagementData == 1) {
                    mBinding.tvRiskManageCompVal1.setText("✔");
                } else {
                    mBinding.tvRiskManageCompVal1.setText("❌");

                }
                if (val.chatWithExpert.chatWithExpertData == 1) {
                    mBinding.tvChatWithExpertCompVal1.setText("✔");
                } else {
                    mBinding.tvChatWithExpertCompVal1.setText("❌");
                }
                if (val.taxAdvisory.taxAdvisoryData == 1) {
                    mBinding.tvTaxAdvisoryCompVal1.setText("✔");
                } else {
                    mBinding.tvTaxAdvisoryCompVal1.setText("❌");
                }


                if (val.reviewFrequencyData.equals("1")) {
                    mBinding.tvReviewFrequencyCompVal1.setText("Monthly");
                } else if (val.reviewFrequencyData.equals("3")) {
                    mBinding.tvReviewFrequencyCompVal1.setText("Quarterly");
                } else {
                    mBinding.tvReviewFrequencyCompVal1.setText("Half Yearly");
                }


                if (first_page == 6 || first_page == 0) {
                    mBinding.imvBilledDurInfo1.setVisibility(View.INVISIBLE);
                } else {
                    mBinding.imvBilledDurInfo1.setVisibility(View.VISIBLE);
                }


                //2nd page
                BasicProModal.PlannedDetailsArray val2 = responseFromAPI.data.plan_details.plannedDetailsArrays.get(second_page);
                if (responseFromAPI.data.plan_details.plannedDetailsArrays.get(second_page).financialPlanning.financialPlanningData == 1) {
                    mBinding.tvFinancialPlanningCompVal2.setText("✔");

                } else {
                    mBinding.tvFinancialPlanningCompVal2.setText("❌");
                }

                if (responseFromAPI.data.plan_details.plannedDetailsArrays.get(second_page).priceWealth.priceWealthData == 1) {
                    mBinding.tvPrivateWealthCompVal2.setText("✔");

                } else {
                    mBinding.tvPrivateWealthCompVal2.setText("❌");
                }


                if (val2.taxFilling.taxFillingData == 1) {
                    mBinding.tvTaxFilingVal2.setText("✔");
                } else {
                    mBinding.tvTaxFilingVal2.setText("❌");
                }

                if (val2.rebalancingPortfolio.rebalancingPortfolioData == 1) {
                    mBinding.tvRebalancingPFCompVal2.setText("✔");
                } else {
                    mBinding.tvRebalancingPFCompVal2.setText("❌");
                }

                if (val2.riskManagement.riskManagementData == 1) {
                    mBinding.tvRiskManageCompVal2.setText("✔");
                } else {
                    mBinding.tvRiskManageCompVal2.setText("❌");

                }
                if (val2.chatWithExpert.chatWithExpertData == 1) {
                    mBinding.tvChatWithExpertCompVal2.setText("✔");
                } else {
                    mBinding.tvChatWithExpertCompVal2.setText("❌");
                }
                if (val2.taxAdvisory.taxAdvisoryData == 1) {
                    mBinding.tvTaxAdvisoryCompVal2.setText("✔");
                } else {
                    mBinding.tvTaxAdvisoryCompVal2.setText("❌");
                }

                if (val2.reviewFrequencyData.equals("1")) {
                    mBinding.tvReviewFrequencyCompVal2.setText("Monthly");
                } else if (val2.reviewFrequencyData.equals("3")) {
                    mBinding.tvReviewFrequencyCompVal2.setText("Quarterly");
                } else {
                    mBinding.tvReviewFrequencyCompVal2.setText("Half Yearly");
                }

                if (second_page == 6 || second_page == 0) {
                    mBinding.imvBilledDurInfo2.setVisibility(View.INVISIBLE);
                } else {
                    mBinding.imvBilledDurInfo2.setVisibility(View.VISIBLE);
                }
                // 2nd page data end

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<BasicProModal> call, Throwable t) {
                progressDialog.dismiss();
//                setPortFolioValue.setText("Error found is : " + t.getMessage());
                System.out.println("Error From APi  ......" + t.getMessage());
            }
        });

    }

}