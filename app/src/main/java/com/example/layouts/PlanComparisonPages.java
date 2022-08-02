package com.example.layouts;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.layouts.Interface.RetroFitAPI;
import com.example.layouts.Models.PlansModel;
import com.example.layouts.Utils.NetworkClass;
import com.example.layouts.databinding.ActivityPlanComparisonPagesBinding;
import com.example.layouts.databinding.BottomSheetDialogBinding;
import com.example.layouts.databinding.BottomSheetPlansInfoBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanComparisonPages extends AppCompatActivity {
    ActivityPlanComparisonPagesBinding mBinding;
    Integer first_page;
    Integer second_page;
    ArrayList<PlansModel> plansModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_comparison_pages);
        mBinding = ActivityPlanComparisonPagesBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Integer> pages = (ArrayList<Integer>) args.getSerializable("ARRAYLIST");
//        ArrayList<PlansModel> plansModels = (ArrayList<PlansModel>) args.getSerializable("PlansModel");
        first_page = pages.get(0);
        second_page = pages.get(1);

        androidx.appcompat.widget.Toolbar mToolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Toast.makeText(getApplicationContext(),"Back to Pricing page",Toast.LENGTH_SHORT).show();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        });


        getProData();
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
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PlanComparisonPages.this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(PlanComparisonPages.this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        mBottomSheetBinding.tvBSContent.setText("Under this plan, we offer you the advisory and execution support required to rebalance your portfolio. PF ");
        mBottomSheetBinding.tvBSHeadLine.setText("Re-balancing Portfolio");
        bottomSheetDialog.show();
    }

    private void showBottomSheetRebalancingPFDialog1() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        mBottomSheetBinding.tvBSContent.setText("Under this plan, we offer you the advisory and execution support required to rebalance your portfolio.");
        mBottomSheetBinding.tvBSHeadLine.setText("Re-balancing Portfolio");
        bottomSheetDialog.show();
    }

    private void showBottomSheetTaxFilingDialog2() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        mBottomSheetBinding.tvBSContent.setText("Under this plan, we offer you the advisory and execution support required to rebalance your portfolio.");
        mBottomSheetBinding.tvBSHeadLine.setText("Tax Filing");
        bottomSheetDialog.show();
    }

    private void showBottomSheetTaxFilingDialog1() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        mBottomSheetBinding.tvBSContent.setText("Under this plan, we offer you the advisory and execution support required to rebalance your portfolio.");
        mBottomSheetBinding.tvBSHeadLine.setText("Tax Filing");
        bottomSheetDialog.show();
    }

    private void showBottomSheetWealthDialog2() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());
        mBottomSheetBinding.tvBSContent.setText("Under this plan, we offer you the advisory and execution support required to rebalance your portfolio.");
        mBottomSheetBinding.tvBSHeadLine.setText("Private Wealth Management");
        bottomSheetDialog.show();
    }

    private void showBottomSheetWealthDialog1() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());
        mBottomSheetBinding.tvBSContent.setText("Under this plan, we offer you the advisory and execution support required to rebalance your portfolio.");
        mBottomSheetBinding.tvBSHeadLine.setText("Private Wealth Management");
        bottomSheetDialog.show();
    }

    private void showBottomSheetDialog1() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetDialogBinding mBottomSheetBinding = BottomSheetDialogBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        if (plansModel.get(first_page).amount.getIsquaterly() == 0) {
            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be Rs. 1,499 \n every six months.");
            mBottomSheetBinding.tvQ1Price.setText("H1 - ₹ " + plansModel.get(first_page).amount.getTotal());
            mBottomSheetBinding.tvQ2Price.setText("H2 - ₹ " + plansModel.get(first_page).amount.getTotal());
            mBottomSheetBinding.tvQ3Price.setVisibility(View.INVISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.INVISIBLE);
            bottomSheetDialog.show();
        } else if (plansModel.get(first_page).amount.getIsquaterly() == 1) {
            mBottomSheetBinding.tvQ3Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvBSPlanName.setText(plansModel.get(first_page).getCategory_name());
            mBottomSheetBinding.tvBSPrice.setText(" | " + plansModel.get(first_page).getMonthly_amount());
            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be quarterly, that is once in every three months.");
            mBottomSheetBinding.tvQ1Price.setText("Q1 - ₹ " + plansModel.get(first_page).amount.getQ1());
            mBottomSheetBinding.tvQ2Price.setText("Q2 - ₹ " + plansModel.get(first_page).amount.getQ2());
            mBottomSheetBinding.tvQ3Price.setText("Q3 - ₹ " + plansModel.get(first_page).amount.getQ3());
            mBottomSheetBinding.tvQ4Price.setText("Q4 - ₹ " + plansModel.get(first_page).amount.getQ4());
            bottomSheetDialog.show();
        }
    }

    private void showBottomSheetDialog2() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetDialogBinding mBottomSheetBinding = BottomSheetDialogBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        if (plansModel.get(second_page).amount.getIsquaterly() == 0) {
            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be Rs. 1,499 \n every six months.");
            mBottomSheetBinding.tvQ1Price.setText("H1 - ₹ " + plansModel.get(second_page).amount.getTotal());
            mBottomSheetBinding.tvQ2Price.setText("H2 - ₹ " + plansModel.get(second_page).amount.getTotal());
            mBottomSheetBinding.tvQ3Price.setVisibility(View.INVISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.INVISIBLE);
            bottomSheetDialog.show();
        } else if (plansModel.get(second_page).amount.getIsquaterly() == 1) {
            mBottomSheetBinding.tvQ3Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvBSPlanName.setText(plansModel.get(second_page).getCategory_name());
            mBottomSheetBinding.tvBSPrice.setText(" | " + plansModel.get(second_page).getMonthly_amount());
            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be quarterly, that is once in every three months.");
            mBottomSheetBinding.tvQ1Price.setText("Q1 - ₹ " + plansModel.get(second_page).amount.getQ1());
            mBottomSheetBinding.tvQ2Price.setText("Q2 - ₹ " + plansModel.get(second_page).amount.getQ2());
            mBottomSheetBinding.tvQ3Price.setText("Q3 - ₹ " + plansModel.get(second_page).amount.getQ3());
            mBottomSheetBinding.tvQ4Price.setText("Q4 - ₹ " + plansModel.get(second_page).amount.getQ4());
            bottomSheetDialog.show();
        }
    }


    private void getProData() {
        if (first_page == 0) {
            mBinding.imvTaxFilingInfo1.setVisibility(View.GONE);
            mBinding.imvRebalancingInfo1.setVisibility(View.GONE);
            mBinding.imvWealthInfo1.setVisibility(View.GONE);

        } else {
            mBinding.imvTaxFilingInfo1.setVisibility(View.VISIBLE);
            mBinding.imvRebalancingInfo1.setVisibility(View.VISIBLE);
            mBinding.imvWealthInfo1.setVisibility(View.VISIBLE);

        }

        if (second_page == 0) {
            mBinding.imvTaxFilingInfo2.setVisibility(View.GONE);
            mBinding.imvRebalancingInfo2.setVisibility(View.GONE);
            mBinding.imvWealthInfo2.setVisibility(View.GONE);

        } else {
            mBinding.imvTaxFilingInfo2.setVisibility(View.VISIBLE);
            mBinding.imvRebalancingInfo2.setVisibility(View.VISIBLE);
            mBinding.imvWealthInfo2.setVisibility(View.VISIBLE);
        }

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

        NetworkClass.apiClient().create(RetroFitAPI.class).getpricingdetails().enqueue(new Callback<ResponseBody>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                assert response.body() != null;
                Log.e("GetResponse", response.body().toString());
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    Log.e("jsonObject", jsonObject.toString());
                    Gson gson = new Gson();
                    Type typeToken = new TypeToken<List<PlansModel>>() {
                    }.getType();
                    plansModel = gson.fromJson(jsonObject.getJSONObject("data").getJSONObject("plan_details").getJSONArray("plandetails").toString(), typeToken);
                    Log.e("plansModel", plansModel.toString());

                    // Upper Texts
                    String durationValue1 = "";
                    Integer paymentFrequencyValue1 = plansModel.get(first_page).getPayment_frequency();
                    if (paymentFrequencyValue1 == 6) {
                        durationValue1 = "Billed Half Yearly";
                    } else if (paymentFrequencyValue1 == 1)
                        durationValue1 = "Billed Monthly";
                    else if (paymentFrequencyValue1 == 3)
                        durationValue1 = "BilledQuarterly";

                    String durationValue2 = "";
                    Integer paymentFrequencyValue2 = plansModel.get(second_page).getPayment_frequency();
                    if (paymentFrequencyValue2 == 6) {
                        durationValue2 = "Billed Half Yearly";
                    } else if (paymentFrequencyValue2 == 1)
                        durationValue2 = "Billed Monthly";
                    else if (paymentFrequencyValue2 == 3)
                        durationValue2 = "Billed Quarterly";

                    mBinding.tvPlanName1Comp.setText(plansModel.get(first_page).getCategory_name());
                    mBinding.tvPlanName2Comp.setText(plansModel.get(second_page).getCategory_name());
                    mBinding.tvPlanPriceComp1.setText("₹ " + plansModel.get(first_page).getMonthly_amount());
                    mBinding.tvPlanPriceComp2.setText("₹ " + plansModel.get(second_page).getMonthly_amount());
                    mBinding.tvBilledDurationComp1.setText(durationValue1);
                    mBinding.tvBilledDurationComp2.setText(durationValue2);


                    // //** First field comparison
                    String planTypeVal1 = "";
                    if (plansModel.get(first_page).getPlan_type().equals("robo_advisory")) {
                        planTypeVal1 = "Robo Advisory";
                    } else
                        planTypeVal1 = "Chat with Experts";
                    mBinding.tvPlanTypeCompVal1.setText(planTypeVal1);


                    if (plansModel.get(first_page).getFinancial_planning().getData() == 1) {
//                  mBinding.tvPlanTypeCompVal1.setText("❌");
                        mBinding.tvFinancialPlanningCompVal1.setText("✔");

                    } else {
                        mBinding.tvFinancialPlanningCompVal1.setText("❌");
                    }

                    if (plansModel.get(first_page).getPrivate_wealth_managment().getData() == 1) {
//                  mBinding.tvPlanTypeCompVal1.setText("❌");
                        mBinding.tvPrivateWealthCompVal1.setText("✔");
                    } else {
                        mBinding.tvPrivateWealthCompVal1.setText("❌");
                    }

                    if (plansModel.get(first_page).getTax_filling().getData() == 1) {
                        mBinding.tvTaxFilingVal1.setText("✔");
                    } else {
                        mBinding.tvTaxFilingVal1.setText("❌");
                    }

                    if (plansModel.get(first_page).getRebalancing_portfolio().getData() == 1) {
                        mBinding.tvRebalancingPFCompVal1.setText("✔");
                    } else {
                        mBinding.tvRebalancingPFCompVal1.setText("❌");
                    }

                    if (plansModel.get(first_page).getRisk_management().getData() == 1) {
                        mBinding.tvRiskManageCompVal1.setText("✔");
                    } else {
                        mBinding.tvRiskManageCompVal1.setText("❌");

                    }
                    if (plansModel.get(first_page).getChat_with_expert().getData() == 1) {
                        mBinding.tvChatWithExpertCompVal1.setText("✔");
                    } else {
                        mBinding.tvChatWithExpertCompVal1.setText("❌");
                    }
                    if (plansModel.get(first_page).getTax_advisory().getData() == 1) {
                        mBinding.tvTaxAdvisoryCompVal1.setText("✔");
                    } else {
                        mBinding.tvTaxAdvisoryCompVal1.setText("❌");
                    }


                    if (plansModel.get(first_page).getReview_frequency().equals("1")) {
                        mBinding.tvReviewFrequencyCompVal1.setText("Monthly");
                    } else if (plansModel.get(first_page).getReview_frequency().equals("3")) {
                        mBinding.tvReviewFrequencyCompVal1.setText("Quarterly");
                    } else {
                        mBinding.tvReviewFrequencyCompVal1.setText("Half Yearly");
                    }

                    if (first_page == 6 || first_page == 0) {
                        mBinding.imvBilledDurInfo1.setVisibility(View.INVISIBLE);
                    } else {
                        mBinding.imvBilledDurInfo1.setVisibility(View.VISIBLE);
                    }

                    //** Second field comparison

                    String planTypeVal2 = "";
                    if (plansModel.get(second_page).getPlan_type().equals("robo_advisory")) {
                        planTypeVal2 = "Robo Advisory";
                    } else
                        planTypeVal2 = "Chat with Experts";
                    mBinding.tvPlanTypeCompVal1.setText(planTypeVal2);


                    if (plansModel.get(second_page).getFinancial_planning().getData() == 1) {
//                  mBinding.tvPlanTypeCompVal1.setText("❌");
                        mBinding.tvFinancialPlanningCompVal2.setText("✔");

                    } else {
                        mBinding.tvFinancialPlanningCompVal2.setText("❌");
                    }

                    if (plansModel.get(second_page).getPrivate_wealth_managment().getData() == 1) {
//                  mBinding.tvPlanTypeCompVal1.setText("❌");
                        mBinding.tvPrivateWealthCompVal2.setText("✔");
                    } else {
                        mBinding.tvPrivateWealthCompVal2.setText("❌");
                    }

                    if (plansModel.get(first_page).getTax_filling().getData() == 1) {
                        mBinding.tvTaxFilingVal1.setText("✔");
                    } else {
                        mBinding.tvTaxFilingVal1.setText("❌");
                    }

                    if (plansModel.get(second_page).getRebalancing_portfolio().getData() == 1) {
                        mBinding.tvRebalancingPFCompVal2.setText("✔");
                    } else {
                        mBinding.tvRebalancingPFCompVal2.setText("❌");
                    }

                    if (plansModel.get(second_page).getRisk_management().getData() == 1) {
                        mBinding.tvRiskManageCompVal2.setText("✔");
                    } else {
                        mBinding.tvRiskManageCompVal2.setText("❌");

                    }
                    if (plansModel.get(second_page).getChat_with_expert().getData() == 1) {
                        mBinding.tvChatWithExpertCompVal2.setText("✔");
                    } else {
                        mBinding.tvChatWithExpertCompVal2.setText("❌");
                    }
                    if (plansModel.get(second_page).getTax_advisory().getData() == 1) {
                        mBinding.tvTaxAdvisoryCompVal2.setText("✔");
                    } else {
                        mBinding.tvTaxAdvisoryCompVal2.setText("❌");
                    }


                    if (plansModel.get(second_page).getReview_frequency().equals("1")) {
                        mBinding.tvReviewFrequencyCompVal2.setText("Monthly");
                    } else if (plansModel.get(first_page).getReview_frequency().equals("3")) {
                        mBinding.tvReviewFrequencyCompVal2.setText("Quarterly");
                    } else {
                        mBinding.tvReviewFrequencyCompVal2.setText("Half Yearly");
                    }

                    if (second_page == 6 || second_page == 0) {
                        mBinding.imvBilledDurInfo2.setVisibility(View.INVISIBLE);
                    } else {
                        mBinding.imvBilledDurInfo2.setVisibility(View.VISIBLE);
                    }


                    progressDialog.dismiss();
                } catch (Exception e) {
                    Log.e("error", e.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                System.out.println("Error From APi  ......" + t.getMessage());
            }
        });
    }
}