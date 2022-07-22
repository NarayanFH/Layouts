package com.example.layouts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.layouts.databinding.ActivityPlanComparisonPagesBinding;
import com.example.layouts.modals.BasicProModal;

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
        System.out.println(first_page + " Values of pages 1 and 2" + second_page);

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
//        BasicProModal modal = new BasicProModal();
        // calling a method to create a post and passing our modal class.
        Call<BasicProModal> call = retrofitAPI.getProData();

        // on below line we are executing our method.
        call.enqueue(new Callback<BasicProModal>() {
            @Override
            public void onResponse(Call<BasicProModal> call, Response<BasicProModal> response) {

                Toast.makeText(PlanComparisonPages.this, "Data Loaded", Toast.LENGTH_SHORT).show();

                BasicProModal responseFromAPI = response.body();

//                JSONObject json = new JSONObject((Map) response.body());
                String durationValue = "No Data";

                Integer apiVal = responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).payment_frequency;

                if (apiVal == 6) {
                    durationValue = "Billed Half Yearly";
                } else if (apiVal == 1)
                    durationValue = "Monthly";
                else if (apiVal ==3)
                    durationValue = "Quarterly";
                else
                    durationValue = "No calculation added for:" + durationValue;

                String plan_type_value = "";
                if (responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).plan_type.equals("robo_advisory")) {
                    plan_type_value = "Robo Advisory (Chat with Experts)";
                } else
                    plan_type_value = "No calculation added for:" + plan_type_value;

//                setPortFolioValue.setText(responseFromAPI.data.portfolio_value);
                System.out.println("Response From APi  ......" + responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).monthly_amount);
                System.out.println("Response From APi  ......" + responseFromAPI.data.plan_details.plannedDetailsArrays.get(1).monthly_amount);
                mBinding.tvPlanName1Comp.setText(responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).category_name);
                mBinding.tvPlanName2Comp.setText(responseFromAPI.data.plan_details.plannedDetailsArrays.get(second_page).category_name);
                mBinding.tvPlanPrice1Comp.setText("₹ " + responseFromAPI.data.plan_details.plannedDetailsArrays.get(first_page).monthly_amount);
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
                    mBinding.tvPrivateWealthCompVal1.setText("✔ ⓘ");

                } else {
                    mBinding.tvPrivateWealthCompVal1.setText("❌ ⓘ");
                }


                if (val.taxFilling.taxFillingData == 1) {
                    mBinding.tvTaxFilingVal1.setText("✔ ⓘ");
                } else {
                    mBinding.tvTaxFilingVal2.setText("❌ ⓘ");
                }

                if (val.rebalancingPortfolio.rebalancingPortfolioData == 1) {
                    mBinding.tvRebalancingPFCompVal1.setText("✔ ⓘ");
                } else {
                    mBinding.tvRebalancingPFCompVal2.setText("❌ ⓘ");
                }

                if (val.riskManagement.equals("1")) {
                    mBinding.tvRiskManageCompVal1.setText("✔");
                } else {
                    mBinding.tvRiskManageCompVal1.setText("❌");

                }
                if (val.chatWithExpert.equals("1")) {
                    mBinding.tvChatWithExpertCompVal1.setText("✔");
                } else {
                    mBinding.tvChatWithExpertCompVal1.setText("❌");
                }
                if (val.taxAdvisory.equals("1")) {
                    mBinding.tvTaxAdvisoryCompVal1.setText("✔");
                } else {
                    mBinding.tvTaxAdvisoryCompVal1.setText("❌");
                }


                //2nd page
                BasicProModal.PlannedDetailsArray val2 = responseFromAPI.data.plan_details.plannedDetailsArrays.get(second_page);
                if (responseFromAPI.data.plan_details.plannedDetailsArrays.get(second_page).financialPlanning.financialPlanningData == 1) {
//                  mBinding.tvPlanTypeCompVal1.setText("❌");
                    mBinding.tvFinancialPlanningCompVal2.setText("✔");

                } else {
                    mBinding.tvFinancialPlanningCompVal2.setText("❌");
                }

                if (responseFromAPI.data.plan_details.plannedDetailsArrays.get(second_page).priceWealth.priceWealthData == 1) {
//                  mBinding.tvPlanTypeCompVal1.setText("❌");
                    mBinding.tvPrivateWealthCompVal2.setText("✔ ⓘ");

                } else {
                    mBinding.tvPrivateWealthCompVal2.setText("❌ ⓘ");
                }


                if (val2.taxFilling.taxFillingData == 1) {
                    mBinding.tvTaxFilingVal2.setText("✔ ⓘ");
                } else {
                    mBinding.tvTaxFilingVal2.setText("❌ ⓘ");
                }

                if (val2.rebalancingPortfolio.rebalancingPortfolioData == 1) {

                }

                if (val2.riskManagement.equals("1")) {
                    mBinding.tvRiskManageCompVal2.setText("✔");
                } else {
                    mBinding.tvRiskManageCompVal2.setText("❌");

                }
                if (val2.chatWithExpert.equals("1")) {
                    mBinding.tvChatWithExpertCompVal2.setText("✔");
                } else {
                    mBinding.tvChatWithExpertCompVal2.setText("❌");
                }
                if (val2.taxAdvisory.equals("1")) {
                    mBinding.tvTaxAdvisoryCompVal2.setText("✔");
                } else {
                    mBinding.tvTaxAdvisoryCompVal2.setText("❌");
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