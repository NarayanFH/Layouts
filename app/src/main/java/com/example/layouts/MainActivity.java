package com.example.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = findViewById(R.id.basicProText);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(MainActivity.this, PlansSelectorPage.class);
                startActivity(send);
            }
        });

    }
}

//right
//        OnGestureRegisterListener onGestureRegisterListenerRightArrow = new OnGestureRegisterListener(this) {
//            @Override
//            public void onSwipeRight(View view) {
//
//
//            }
//
//            @Override
//            public void onSwipeLeft(View view) {
//
//            }
//
//            public void onClick(View view) {
//
//                rightSwipeView();
//                if (pagePosition > 5) {
//                    mBinding.imvRightArrow.setVisibility(View.INVISIBLE);
//                } else {
//                    pagePosition = pagePosition + 1;
//                    System.out.println("Moved Right");
//                    System.out.println(pagePosition);
//                    getCalledData();
//                }
//                if (pagePosition > 0)
//                    mBinding.imvLeftArrow.setVisibility(View.VISIBLE);
//            }
//        };
//        mBinding.cvServiceOffer.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//            }
//        });
//
//        mBinding.imvRightArrow.setOnTouchListener(onGestureRegisterListenerRightArrow);
//        if (pagePosition <= 0) {
//            Toast.makeText(BasicProPlan.this, "First Page", Toast.LENGTH_SHORT).show();
//            mBinding.imvLeftArrow.setVisibility(View.GONE);
//
//        }
//
//        //left arrow
//        OnGestureRegisterListener onGestureRegisterListenerLeft = new OnGestureRegisterListener(this) {
//            @Override
//            public void onSwipeRight(View view) {
//            }
//
//            @Override
//            public void onSwipeLeft(View view) {
//                leftSwipeView();
//            }
//
//            public void onClick(View view) {
//
//                leftSwipeView();
//                // Do something
////                if (pagePosition <= 0) {
////                    Toast.makeText(BasicProPlan.this, "First Page", Toast.LENGTH_SHORT).show();
////
////                } else
////                    pagePosition = pagePosition - 1;
////                System.out.println("Moved Left");
////                System.out.println(pagePosition);
////                getCalledData();
//            }
//
//            public boolean onLongClick(View view) {
//                // Do something
//                return true;
//            }
//        };
//        mBinding.imvLeftArrow.setOnTouchListener(onGestureRegisterListenerLeft);