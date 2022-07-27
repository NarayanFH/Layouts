package com.example.layouts;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.layouts.Interface.RetroFitAPI;
import com.example.layouts.Models.PlansModel;
import com.example.layouts.databinding.ActivityPlansSelectorPageBinding;
import com.example.layouts.databinding.BottomSheetDialogBinding;
import com.example.layouts.databinding.BottomSheetPlansInfoBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlansSelectorPage extends AppCompatActivity {
    ActivityPlansSelectorPageBinding mBinding;
    int pagePosition = 0;
    boolean checkCompare = false;
    boolean checkPlansIcon = false;
    List<Integer> comparePages = new ArrayList<>();
    PlansModel calledModal;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_selector_page);

        mBinding = ActivityPlansSelectorPageBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mBinding.tvWantToComparePlans.setOnClickListener(v -> {
            checkCompare = !checkCompare;
            if (checkCompare) {
                mBinding.tvWantToComparePlans.setText("Select any two plans");
                mBinding.imvCheckIconComparePlans.setVisibility(View.VISIBLE);
            } else {
                mBinding.tvWantToComparePlans.setText("Want to compare plans?");
                mBinding.imvCheckIconComparePlans.setVisibility(View.INVISIBLE);
            }
        });

        mBinding.imvBilledDurInfo.setOnClickListener(v -> showBottomSheetDialog());
        mBinding.imvWealthInfo.setOnClickListener(v -> showBottomSheetWealthDialog());
        mBinding.imvTaxPlanInfo.setOnClickListener(v -> showBottomSheetTaxFilingDialog());
        mBinding.imvRebalancingInfo.setOnClickListener(v -> showBottomSheetRebalancingPFDialog());
        getProData();
        // calling api data

        //Main swipe *****
        OnGestureRegisterListener onGestureRegisterListenerMain = new OnGestureRegisterListener(this) {
            @Override
            public void onSwipeRight(View view) {
                System.out.println("Swipped Right");
                rightSwipeView();
            }

            @Override
            public void onSwipeLeft(View view) {
                System.out.println("Swipped Left");
                leftSwipeView();
            }

            public void onClick(View view) {
            }
        };
        mBinding.cvServiceOffer.setOnTouchListener(onGestureRegisterListenerMain);


        mBinding.imvCheckIconComparePlans.setOnClickListener(v -> {
            checkPlansIcon = !checkPlansIcon;
            if (checkPlansIcon == true) {
                System.out.println("Checked");
                mBinding.imvCheckIconComparePlans.setBackgroundResource(R.drawable.correct);
//                mBinding.imvCheckIconComparePlans.setBackgroundColor(Color.rgb(255, 0, 0));
                comparePages.add(pagePosition);
                System.out.println(comparePages);
                System.out.println(comparePages.size());
                if (comparePages.size() >= 2) {
                    Intent intent = new Intent(PlansSelectorPage.this, PlanComparisonPages.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", (Serializable) comparePages);
                    intent.putExtra("BUNDLE", args);
                    startActivity(intent);
                    comparePages.clear();
                }
            } else {
                System.out.println("Un Checked");
                System.out.println(comparePages);
                mBinding.imvCheckIconComparePlans.setBackgroundResource(R.drawable.check_mark_small);

                comparePages.remove(Integer.valueOf(pagePosition));


            }
        });
    }

    private void rightSwipeView() {
        if (pagePosition > 5) {
            Toast.makeText(PlansSelectorPage.this, "Last Plan", Toast.LENGTH_SHORT).show();
        } else {
            pagePosition = pagePosition + 1;
            System.out.println("Moved Right");
            System.out.println(pagePosition);
            getCalledData();
        }
        if (pagePosition > 0) {
        }
    }

    private void leftSwipeView() {
        if (pagePosition <= 0) {
            Toast.makeText(PlansSelectorPage.this, "First Plan", Toast.LENGTH_SHORT).show();

        } else
            pagePosition = pagePosition - 1;
        System.out.println("Moved Left");
        System.out.println(pagePosition);
        getCalledData();
    }

    private void getCalledData() {
        getImagePosition();
        if (comparePages.contains(Integer.valueOf(pagePosition))) {
//            mBinding.imvCheckIconComparePlans.setBackgroundResource(R.drawable.correct);
            checkCompare = true;
            checkPlansIcon = true;
            if (checkPlansIcon) {
                System.out.println("Checked");
                mBinding.imvCheckIconComparePlans.setBackgroundResource(R.drawable.correct);
            }
        } else {
            checkCompare = false;
            checkPlansIcon = false;
            mBinding.imvCheckIconComparePlans.setBackgroundResource(R.drawable.check_mark_small);
        }
        mBinding.cvServiceOffer.setVisibility(View.GONE);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
//        calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).category_name
        String durationValue = "No Data";
        Integer apiVal = calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).payment_frequency;

        if (apiVal == 6) {
            durationValue = "Billed Half Yearly";
        } else if (apiVal == 1)
            durationValue = "Billed Monthly";
        else if (apiVal == 3)
            durationValue = "Billed Quarterly";
        else
            durationValue = "No calculation added for:" + durationValue;

        String plan_type_value = "";
        if (calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).plan_type.equals("robo_advisory")) {
            plan_type_value = "Robo Advisory (Chat with Experts)";
        } else
            plan_type_value = "Check out our excellent group of trained Wealth Managers";

        System.out.println("Response From APi  ......" + calledModal.data.plan_details.plannedDetailsArrays.get(1).monthly_amount);
        mBinding.tvPlanName.setText(calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).category_name);

        if (pagePosition == 6) {
            mBinding.tvPriceText.setText(calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).monthly_amount);
            mBinding.tvPriceDuration.setVisibility(View.GONE);
            mBinding.imvBilledDurInfo.setVisibility(View.GONE);
            mBinding.tvBilledDuration.setText("Get a completely \ncustomised financial plan \naccording to your unique \nrequirement");
        } else if (pagePosition == 0) {
            mBinding.imvBilledDurInfo.setVisibility(View.INVISIBLE);
            mBinding.tvPriceDuration.setVisibility(View.VISIBLE);
            mBinding.tvBilledDuration.setText(durationValue);
            mBinding.tvPriceText.setText("₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).monthly_amount);
        } else {
            mBinding.tvPriceDuration.setVisibility(View.VISIBLE);
            mBinding.imvBilledDurInfo.setVisibility(View.VISIBLE);
            mBinding.tvBilledDuration.setText(durationValue);
            mBinding.tvPriceText.setText("₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).monthly_amount);
        }
        mBinding.tvPricePortfolioSize.setText("₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).portfolio_size);
//                mBinding.tvBilledDuration.setText(durationValue);
        mBinding.tvRoboAdvisoryExperts.setText(plan_type_value);
        PlansModel.PlannedDetailsArray val = calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition);

        if (calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).priceWealth.priceWealthData == 1) {
            mBinding.tvPrivateWealthManagement.setVisibility(View.VISIBLE);
            mBinding.imvWealthInfo.setVisibility(View.VISIBLE);
            mBinding.viewLinePrivateWealth.setVisibility(View.VISIBLE);
            mBinding.imvCheckPrivateWealthPlan.setVisibility(View.VISIBLE);
        } else {
            mBinding.tvPrivateWealthManagement.setVisibility(View.GONE);
            mBinding.imvWealthInfo.setVisibility(View.GONE);
            mBinding.viewLinePrivateWealth.setVisibility(View.GONE);
            mBinding.imvCheckPrivateWealthPlan.setVisibility(View.GONE);
        }
        if (val.taxFilling.taxFillingData == 1) {
            mBinding.tvTaxPlanning.setVisibility(View.VISIBLE);
            mBinding.imvTaxPlanInfo.setVisibility(View.VISIBLE);
            mBinding.viewLineTaxPlan.setVisibility(View.VISIBLE);
            mBinding.imvCheckTaxPlan.setVisibility(View.VISIBLE);
        } else {
            mBinding.tvTaxPlanning.setVisibility(View.GONE);
            mBinding.imvTaxPlanInfo.setVisibility(View.GONE);
            mBinding.viewLineTaxPlan.setVisibility(View.GONE);
            mBinding.imvCheckTaxPlan.setVisibility(View.GONE);
        }

        if (val.rebalancingPortfolio.rebalancingPortfolioData == 1) {
            mBinding.tvReBalancingPF.setVisibility(View.VISIBLE);
            mBinding.imvRebalancingInfo.setVisibility(View.VISIBLE);
            mBinding.viewLineRebalancingPlan.setVisibility(View.VISIBLE);
            mBinding.imvRebalancingPlan.setVisibility(View.VISIBLE);
        } else {
            mBinding.tvReBalancingPF.setVisibility(View.GONE);
            mBinding.imvRebalancingInfo.setVisibility(View.GONE);
            mBinding.viewLineRebalancingPlan.setVisibility(View.GONE);
            mBinding.imvRebalancingPlan.setVisibility(View.GONE);
        }

        if (val.reviewFrequencyData.equals("1")) {
            mBinding.tvReviewFrequencyPlan.setVisibility(View.VISIBLE);
            mBinding.tvReviewFrequencyDuration.setVisibility(View.VISIBLE);
            mBinding.viewLineReviewPlan.setVisibility(View.VISIBLE);
            mBinding.imvCheckReviewPlan.setVisibility(View.VISIBLE);
        } else {
            mBinding.tvReviewFrequencyPlan.setVisibility(View.GONE);
            mBinding.tvReviewFrequencyDuration.setVisibility(View.GONE);
            mBinding.viewLineReviewPlan.setVisibility(View.GONE);
            mBinding.imvCheckReviewPlan.setVisibility(View.GONE);
        }
        progressDialog.dismiss();
//                mBinding.progressBar.setIndeterminate(false);
        mBinding.cvServiceOffer.setVisibility(View.VISIBLE);

    }

    private void showBottomSheetRebalancingPFDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        mBottomSheetBinding.tvBSContent.setText(calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).priceWealth.meta.app);
        mBottomSheetBinding.tvBSHeadLine.setText("Re-balancing Portfolio");
        bottomSheetDialog.show();
    }

    private void showBottomSheetTaxFilingDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        mBottomSheetBinding.tvBSContent.setText(calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).priceWealth.meta.app);
        mBottomSheetBinding.tvBSHeadLine.setText("Tax Filing");
        bottomSheetDialog.show();
    }

    private void getImagePosition() {
        switch (pagePosition) {
            case 0:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.my_subscription_bg);
                break;
            case 1:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.flower);
            case 2:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.classic);
            case 3:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.classic_pro);
            case 4:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.premium);
            case 5:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.elite);
            case 6:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.elite);
        }
    }

    private void showBottomSheetDialog() {
//        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
//        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
//
//        bottomSheetDialog.show();
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetDialogBinding mBottomSheetBinding = BottomSheetDialogBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        if (calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).amount.isQuarterly == 0) {

            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be Rs. 1,499 \n every six months.");
            mBottomSheetBinding.tvQ1Price.setText("H1 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).amount.total);
            mBottomSheetBinding.tvQ2Price.setText("H2 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).amount.total);
            mBottomSheetBinding.tvQ3Price.setVisibility(View.INVISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.INVISIBLE);
            bottomSheetDialog.show();
        } else if (calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).amount.isQuarterly == 1) {
            mBottomSheetBinding.tvQ3Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvBSPlanName.setText(calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).category_name);
            mBottomSheetBinding.tvBSPrice.setText(" | " + calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).monthly_amount);
            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be quarterly, that is once in every three months.");
            mBottomSheetBinding.tvQ1Price.setText("Q1 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).amount.Q1);
            mBottomSheetBinding.tvQ2Price.setText("Q2 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).amount.Q2);
            mBottomSheetBinding.tvQ3Price.setText("Q3 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).amount.Q3);
            mBottomSheetBinding.tvQ4Price.setText("Q4 - ₹ " + calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).amount.Q4);
            bottomSheetDialog.show();
        }
    }

    private void showBottomSheetWealthDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetPlansInfoBinding mBottomSheetBinding = BottomSheetPlansInfoBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        mBottomSheetBinding.tvBSContent.setText(calledModal.data.plan_details.plannedDetailsArrays.get(pagePosition).priceWealth.meta.app);
        mBottomSheetBinding.tvBSHeadLine.setText("Private Wealth Management");
        bottomSheetDialog.show();
//        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
//        bottomSheetDialog.setContentView(R.layout.bottom_sheet_plans_info);
//        bottomSheetDialog.show();
    }

    private void getProData() {

        mBinding.cvServiceOffer.setVisibility(View.GONE);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.fintoo.in/restapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroFitAPI retrofitAPI = retrofit.create(RetroFitAPI.class);
        Call<PlansModel> call = retrofitAPI.getProData();
        getImagePosition();
        call.enqueue(new Callback<PlansModel>() {
            @Override
            public void onResponse(Call<PlansModel> call, Response<PlansModel> response) {

                PlansModel responseFromAPI = response.body();
                calledModal = response.body();

                String durationValue = "No Data";
                Integer apiVal = responseFromAPI.data.plan_details.plannedDetailsArrays.get(pagePosition).payment_frequency;

                if (apiVal == 6) {
                    durationValue = "Billed Half Yearly";
                } else if (apiVal == 1)
                    durationValue = "Billed Monthly";
                else if (apiVal == 3)
                    durationValue = "Billed Quarterly";
                else
                    durationValue = "No calculation added for:" + durationValue;

                String plan_type_value = "";
                if (responseFromAPI.data.plan_details.plannedDetailsArrays.get(pagePosition).plan_type.equals("robo_advisory")) {
                    plan_type_value = "Robo Advisory (Chat with Experts)";
                } else
                    plan_type_value = "Check out our excellent group of trained Wealth Managers";

                System.out.println("Response From APi  ......" + responseFromAPI.data.plan_details.plannedDetailsArrays.get(1).monthly_amount);
                System.out.println("String from API call:  ......" + responseFromAPI.data.plan_details.plannedDetailsArrays.get(1).priceWealth.meta.app);
                mBinding.tvPlanName.setText(responseFromAPI.data.plan_details.plannedDetailsArrays.get(pagePosition).category_name);

                if (pagePosition == 6) {
                    mBinding.tvPriceText.setText(responseFromAPI.data.plan_details.plannedDetailsArrays.get(pagePosition).monthly_amount);
                    mBinding.tvPriceDuration.setVisibility(View.GONE);
                    mBinding.imvBilledDurInfo.setVisibility(View.GONE);
                    mBinding.tvBilledDuration.setText("Get a completely \ncustomised financial plan \naccording to your unique \nrequirement");
                } else if (pagePosition == 0) {
                    mBinding.imvBilledDurInfo.setVisibility(View.INVISIBLE);
                    mBinding.tvPriceDuration.setVisibility(View.VISIBLE);
                    mBinding.tvBilledDuration.setText(durationValue);
                    mBinding.tvPriceText.setText("₹ " + responseFromAPI.data.plan_details.plannedDetailsArrays.get(pagePosition).monthly_amount);
                } else {
                    mBinding.tvPriceDuration.setVisibility(View.VISIBLE);
                    mBinding.imvBilledDurInfo.setVisibility(View.VISIBLE);
                    mBinding.tvBilledDuration.setText(durationValue);
                    mBinding.tvPriceText.setText("₹ " + responseFromAPI.data.plan_details.plannedDetailsArrays.get(pagePosition).monthly_amount);
                }
                mBinding.tvPricePortfolioSize.setText("₹ " + responseFromAPI.data.plan_details.plannedDetailsArrays.get(pagePosition).portfolio_size);
//                mBinding.tvBilledDuration.setText(durationValue);
                mBinding.tvRoboAdvisoryExperts.setText(plan_type_value);
                PlansModel.PlannedDetailsArray val = responseFromAPI.data.plan_details.plannedDetailsArrays.get(pagePosition);

                if (responseFromAPI.data.plan_details.plannedDetailsArrays.get(pagePosition).priceWealth.priceWealthData == 1) {
                    mBinding.tvPrivateWealthManagement.setVisibility(View.VISIBLE);
                    mBinding.imvWealthInfo.setVisibility(View.VISIBLE);
                    mBinding.viewLinePrivateWealth.setVisibility(View.VISIBLE);
                    mBinding.imvCheckPrivateWealthPlan.setVisibility(View.VISIBLE);
                }
                if (val.taxFilling.taxFillingData == 1) {
                    mBinding.tvTaxPlanning.setVisibility(View.VISIBLE);
                    mBinding.imvTaxPlanInfo.setVisibility(View.VISIBLE);
                    mBinding.viewLineTaxPlan.setVisibility(View.VISIBLE);
                    mBinding.imvCheckTaxPlan.setVisibility(View.VISIBLE);
                }

                if (val.rebalancingPortfolio.rebalancingPortfolioData == 1) {
                    mBinding.tvReBalancingPF.setVisibility(View.VISIBLE);
                    mBinding.imvRebalancingInfo.setVisibility(View.VISIBLE);
                    mBinding.viewLineRebalancingPlan.setVisibility(View.VISIBLE);
                    mBinding.imvRebalancingPlan.setVisibility(View.VISIBLE);
                }

                if (val.reviewFrequencyData.equals("1")) {
                    mBinding.tvReviewFrequencyPlan.setVisibility(View.VISIBLE);
                    mBinding.tvReviewFrequencyDuration.setVisibility(View.VISIBLE);
                    mBinding.viewLineReviewPlan.setVisibility(View.VISIBLE);
                    mBinding.imvCheckReviewPlan.setVisibility(View.VISIBLE);

                }
                progressDialog.dismiss();
//                mBinding.progressBar.setIndeterminate(false);
                mBinding.cvServiceOffer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<PlansModel> call, Throwable t) {
                progressDialog.dismiss();
                System.out.println("Error From APi  ......" + t.getMessage());
            }
        });

    }
}