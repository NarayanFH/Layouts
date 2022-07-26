package com.example.layouts.modals;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BasicProModal {
    @SerializedName("data")
    public Data data;


    public class Data {

        @SerializedName("plan_details")
        public PlanDetails plan_details;
    }

    public class PlanDetails {

        @SerializedName("plandetails")
        public ArrayList<PlannedDetailsArray> plannedDetailsArrays;
    }

    public class PlannedDetailsArray {

        @SerializedName("category_name")
        public String category_name;

        @SerializedName("amount")
        public Amount amount;

        @SerializedName("portfolio_size")
        public String portfolio_size;

        @SerializedName("monthly_amount")
        public String monthly_amount;

        @SerializedName("plan_type")
        public String plan_type;

        @SerializedName("payment_frequency")
        public Integer payment_frequency;

        @SerializedName("financial_planning")
        public FinancialPlanning financialPlanning;

        @SerializedName("private_wealth_managment")
        public PriceWealthManagement priceWealth;

        @SerializedName("tax_advisory")
        public TaxAdvisory taxAdvisory;

        @SerializedName("tax_filling")
        public TaxFilling taxFilling;

        @SerializedName("risk_management")
        public RiskManagement riskManagement;

        @SerializedName("rebalancing_portfolio")
        public RebalancingPortfolio rebalancingPortfolio;

        @SerializedName("review_frequency")
        public String reviewFrequencyData;

        @SerializedName("chat_with_expert")
        public ChatWithExpert chatWithExpert;


    }

    public class  Amount{

        @SerializedName("isquaterly")
        public int isQuarterly;
        @SerializedName("Q1")
        public int Q1;
        @SerializedName("Q2")
        public int Q2;
        @SerializedName("Q3")
        public int Q3;
        @SerializedName("Q4")
        public int Q4;
        @SerializedName("total")
        public String total;
    }
    public class FinancialPlanning {

        @SerializedName("data")
        public int financialPlanningData;
    }
    public class PriceWealthManagement {

        @SerializedName("data")
        public int priceWealthData;

        @SerializedName("meta")
        public Meta meta;


    }
    public class TaxAdvisory {

        @SerializedName("data")
        public int taxAdvisoryData;
    }
    public class TaxFilling {

        @SerializedName("data")
        public int taxFillingData;
        
        @SerializedName("meta")
        public Meta meta;
    }
    public class RiskManagement {

        @SerializedName("data")
        public int riskManagementData;
    }
    public class RebalancingPortfolio {

        @SerializedName("data")
        public int rebalancingPortfolioData;

        @SerializedName("meta")
        public Meta meta;
    }

    public class ChatWithExpert {

        @SerializedName("data")
        public int chatWithExpertData;
    }


    public class Meta {

        @SerializedName("app")
        public String app;
    }

}
