package com.example.layouts;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.layouts.Interface.RetroFitAPI;
import com.example.layouts.Models.PlansModel;
import com.example.layouts.Utils.NetworkClass;
import com.example.layouts.databinding.ActivityPlansSelectorPageBinding;
import com.example.layouts.databinding.BottomSheetDialogBinding;
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

public class PlansSelectorPage extends AppCompatActivity {
    ActivityPlansSelectorPageBinding mBinding;
    ArrayList<PlansModel> plansModel = new ArrayList<>();
    int pagePosition = 0;
    boolean showCheckBox = true;
    ViewPagerAdapter mViewPagerAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_selector_page);

        mBinding = ActivityPlansSelectorPageBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        getData();

        androidx.appcompat.widget.Toolbar mToolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Home Page",Toast.LENGTH_SHORT).show();
            }
        });

        mBinding.imvBilledDurInfo.setOnClickListener(v -> showBottomSheetDialog());
        mBinding.viewPagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float
                    positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pagePosition = position;
                System.out.println("Poooooosition:" + position);
                changeDataOnPlansScrolled(position);
                getImagePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.tvWantToComparePlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public synchronized void onClick(View v) {
                if (showCheckBox) {
                    showCheckBox = false;
                    mBinding.tvWantToComparePlans.setText("Select any two plans");
                    mViewPagerAdapter.showSelected(1);
                } else {
                    showCheckBox = true;
                    mBinding.tvWantToComparePlans.setText("Want to compare plans?");
                    mViewPagerAdapter.showSelected(0);
                }
            }
        });
    }

    private void changeDataOnPlansScrolled(int position) {

        String durationValue = "";
        Integer paymentFrequencyValue1 = plansModel.get(pagePosition).getPayment_frequency();
        if (paymentFrequencyValue1 == 6) {
            durationValue = "Billed Half Yearly";
        } else if (paymentFrequencyValue1 == 1)
            durationValue = "Billed Monthly";
        else if (paymentFrequencyValue1 == 3)
            durationValue = "Billed Quarterly";

        String planTypeValue = "";
        if (plansModel.get(position).getPlan_type().equals("robo_advisory")) {
            planTypeValue = "Robo Advisory (Chat with Experts)";
        } else
            planTypeValue = "Check out our excellent group of trained Wealth Managers";

        if(pagePosition ==6) {
            mBinding.tvPlanName.setText(plansModel.get(position).getCategory_name());
            mBinding.tvPriceText.setText(plansModel.get(position).getMonthly_amount());
            mBinding.tvPriceDuration.setVisibility(View.INVISIBLE);
            mBinding.imvBilledDurInfo.setVisibility(View.INVISIBLE);
            mBinding.tvBilledDuration.setText("Get a completely customised \nfinancial plan according to \nyour unique requirement");
        }
        else if (pagePosition ==0) {
            mBinding.tvPlanName.setText(plansModel.get(position).getCategory_name());
            mBinding.tvPriceText.setText("₹ " +plansModel.get(position).getMonthly_amount());
            mBinding.imvBilledDurInfo.setVisibility(View.INVISIBLE);
            mBinding.tvPriceDuration.setVisibility(View.VISIBLE);
            mBinding.tvBilledDuration.setText(durationValue);
        }
        else {
            mBinding.tvPlanName.setText(plansModel.get(position).getCategory_name());
            mBinding.tvPriceText.setText("₹ " +plansModel.get(position).getMonthly_amount());
            mBinding.imvBilledDurInfo.setVisibility(View.VISIBLE);
            mBinding.tvPriceDuration.setVisibility(View.VISIBLE);
            mBinding.tvBilledDuration.setText(durationValue);
        }

        mBinding.tvPricePortfolioSize.setText("₹ " + plansModel.get(position).getPortfolio_size());
        mBinding.tvRoboAdvisoryExperts.setText(planTypeValue);

    }

    private void showBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        BottomSheetDialogBinding mBottomSheetBinding = BottomSheetDialogBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(mBottomSheetBinding.getRoot());

        if (plansModel.get(pagePosition).getAmount().getIsquaterly() == 0) {

            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be Rs. 1,499 \n every six months.");
            mBottomSheetBinding.tvQ1Price.setText("H1 - ₹ " + plansModel.get(pagePosition).getAmount().getTotal());
            mBottomSheetBinding.tvQ2Price.setText("H2 - ₹ " + plansModel.get(pagePosition).getAmount().getTotal());
            mBottomSheetBinding.tvQ3Price.setVisibility(View.INVISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.INVISIBLE);
            bottomSheetDialog.show();
        } else if (plansModel.get(pagePosition).getAmount().getIsquaterly() == 1) {
            mBottomSheetBinding.tvQ3Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvQ4Price.setVisibility(View.VISIBLE);
            mBottomSheetBinding.tvBSPlanName.setText(plansModel.get(pagePosition).getCategory_name());
            mBottomSheetBinding.tvBSPrice.setText(" | " + plansModel.get(pagePosition).getMonthly_amount());
            mBottomSheetBinding.tvPaymentTermText.setText("The payment term for this plan will be quarterly, that is once in every three months.");
            mBottomSheetBinding.tvQ1Price.setText("Q1 - ₹ " + plansModel.get(pagePosition).getAmount().getQ1());
            mBottomSheetBinding.tvQ2Price.setText("Q2 - ₹ " + plansModel.get(pagePosition).getAmount().getQ2());
            mBottomSheetBinding.tvQ3Price.setText("Q3 - ₹ " + plansModel.get(pagePosition).getAmount().getQ3());
            mBottomSheetBinding.tvQ4Price.setText("Q4 - ₹ " + plansModel.get(pagePosition).getAmount().getQ4());
            bottomSheetDialog.show();
        }
    }

    private void getImagePosition(int position) {
        switch (position) {
            case 0:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.my_subscription_bg);
                break;
            case 1:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.flower);
                break;
            case 2:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.classic);
                break;
            case 3:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.classic_pro);
                break;
            case 4:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.premium);
                break;
            case 5:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.elite);
                break;
            case 6:
                mBinding.imvPlanImages.setBackgroundResource(R.drawable.elite_prime);
                break;
        }
    }

    private void getData() {
        ProgressDialog progressDialog = new ProgressDialog(PlansSelectorPage.this);
        progressDialog.setMessage("Please Wait...");
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
                    ArrayList<PlansModel> fetchedModel = gson.fromJson(jsonObject.getJSONObject("data").getJSONObject("plan_details").getJSONArray("plandetails").toString(), typeToken);
                    Log.e("plansModel", plansModel.toString());

                    ViewPager mViewPager;
                    mViewPager = mBinding.viewPagerMain;
                    mViewPagerAdapter = new ViewPagerAdapter(PlansSelectorPage.this, fetchedModel);
                    mViewPager.setAdapter(mViewPagerAdapter);
                    mViewPager.setPadding(90, 0, 90, 0);
                    progressDialog.dismiss();
                } catch (Exception e) {
                    Log.e("error", e.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }
}