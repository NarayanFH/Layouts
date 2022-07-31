//package com.example.layouts;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.AppCompatImageView;
//import androidx.appcompat.widget.AppCompatTextView;
//import androidx.cardview.widget.CardView;
//import androidx.viewpager.widget.PagerAdapter;
//
//import com.example.layouts.Models.PlansModel;
//
//import java.util.List;
//import java.util.Objects;
//
//public class ViewPagerAdapter extends PagerAdapter {
//
//    // Context object
//    Context context;
//
//    // Array of images
//    int[] plansSerialNumbers;
//    List<PlansModel> plansModel;
//
//    // Layout Inflater
//    LayoutInflater mLayoutInflater;
//
//    // Viewpager Constructor
//    public ViewPagerAdapter(Context context, int[] plansSerialNumbers, List<PlansModel> plansModel) {
//        this.context = context;
//        this.plansModel= plansModel;
//        this.plansSerialNumbers = plansSerialNumbers;
//        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//        // return the number of images
//        return plansSerialNumbers.length;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == ((LinearLayout) object);
//    }
//
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
//        // inflating the item.xml
//        View itemView = mLayoutInflater.inflate(R.layout.plans_recyler, container, false);
//        ivCorrectFinancialPlanning = itemView.findViewById(R.id.ivCorrectFinancialPlanning);
//        tvFinancialPlanning = itemView.findViewById(R.id.tvFinancialPlanning);
//        viewFinancialPlanning = itemView.findViewById(R.id.viewFinancialPlanning);
//        ivWealthManagement = itemView.findViewById(R.id.ivWealthManagement);
//        tvWealthManagement = itemView.findViewById(R.id.tvWealthManagement);
//        ivInfoMoneyWealth = itemView.findViewById(R.id.ivInfoMoneyWealth);
//        viewWealthManagement = itemView.findViewById(R.id.viewWealthManagement);
//        ivTaxAdvisory = itemView.findViewById(R.id.ivTaxAdvisory);
//        tvTaxAdvisory = itemView.findViewById(R.id.tvTaxAdvisory);
//        ivInfoTaxAdvisory = itemView.findViewById(R.id.ivInfoTaxAdvisory);
//        viewTaxAdvisory = itemView.findViewById(R.id.viewTaxAdvisory);
//        ivTaxFiling = itemView.findViewById(R.id.ivTaxFiling);
//        tvTaxFiling = itemView.findViewById(R.id.tvTaxFiling);
//        ivInfoMoneyTaxFilling = itemView.findViewById(R.id.ivInfoMoneyTaxFilling);
//        viewTaxFiling = itemView.findViewById(R.id.viewTaxFiling);
//        ivRiskManagement = itemView.findViewById(R.id.ivRiskManagement);
//        tvRiskManagement = itemView.findViewById(R.id.tvRiskManagement);
//        viewRiskManagement = itemView.findViewById(R.id.viewRiskManagement);
//        ivRebalancing = itemView.findViewById(R.id.ivRebalancing);
//        tvRebalancing = itemView.findViewById(R.id.tvRebalancing);
//        ivInfoMoneyRebalancing = itemView.findViewById(R.id.ivInfoMoneyRebalancing);
//        viewRebalancing = itemView.findViewById(R.id.viewRebalancing);
//        ivReviewFrequency = itemView.findViewById(R.id.ivReviewFrequency);
//        tvReviewFrequency = itemView.findViewById(R.id.tvReviewFrequency);
//        tvFrequency = itemView.findViewById(R.id.tvFrequency);
//        viewReviewFrequency = itemView.findViewById(R.id.viewReviewFrequency);
//        ivChat = itemView.findViewById(R.id.ivChat);
//        tvChat = itemView.findViewById(R.id.tvChat);
//        // referencing the image view from the item.xml file
//        if (plansSerialNumbers[position] ==0) {
//
//        }
//
//
//        // setting the image in the imageView
////        cardView.setImageResource(images[position]);
//
//        // Adding the View
//        Objects.requireNonNull(container).addView(itemView);
//
//        return itemView;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//
//        container.removeView((LinearLayout) object);
//    }
//}
