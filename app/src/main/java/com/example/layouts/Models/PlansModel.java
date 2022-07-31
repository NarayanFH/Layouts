package com.example.layouts.Models;
//******/// New model

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlansModel {

    private int isCheckVisible;

    @SerializedName("plan_type")
    @Expose
    private String plan_type;

    @SerializedName("payment_frequency")
    @Expose
    private int payment_frequency;

    @SerializedName("plan_id")
    @Expose
    private String plan_id;

    @SerializedName("private_wealth_managment")
    @Expose
    private PrivateWealthManagment private_wealth_managment;

    @SerializedName("financial_planning")
    @Expose
    private FinancialPlanning financial_planning;

    @SerializedName("monthly_amount")
    @Expose
    private String monthly_amount;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("category_name")
    @Expose
    private String category_name;

    @SerializedName("chat_with_expert")
    @Expose
    private ChatWithExpert chat_with_expert;

    @SerializedName("rebalancing_portfolio")
    @Expose
    private RebalancingPortfolio rebalancing_portfolio;

    @SerializedName("tax_filling")
    @Expose
    private TaxFilling tax_filling;

    @SerializedName("tax_advisory")
    @Expose
    private TaxAdvisory tax_advisory;

    @SerializedName("risk_management")
    @Expose
    private RiskManagement risk_management;

    @SerializedName("amount")
    @Expose
    public Amount amount;

    @SerializedName("portfolio_size")
    @Expose
    private String portfolio_size;

    @SerializedName("review_frequency")
    @Expose
    private String review_frequency;

    public class PrivateWealthManagment {

        @SerializedName("data")
        @Expose
        private int data;

        @SerializedName("meta")
        @Expose
        private Meta meta;

        private class Meta {

            @SerializedName("app")
            @Expose
            private String app;

            public String getApp() {
                return app;
            }

            public void setApp(String app) {
                this.app = app;
            }
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }


    }

    public class FinancialPlanning {
        @SerializedName("data")
        @Expose
        private int data;

        @SerializedName("meta")
        @Expose
        private Meta meta;

        private class Meta {

            @SerializedName("app")
            @Expose
            private String app;

            public String getApp() {
                return app;
            }

            public void setApp(String app) {
                this.app = app;
            }
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }
    }

    public class ChatWithExpert {
        @SerializedName("data")
        @Expose
        private int data;

        @SerializedName("meta")
        @Expose
        private Meta meta;

        private class Meta {

            @SerializedName("app")
            @Expose
            private String app;

            public String getApp() {
                return app;
            }

            public void setApp(String app) {
                this.app = app;
            }
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }
    }

    public class RebalancingPortfolio {
        @SerializedName("data")
        @Expose
        private int data;

        @SerializedName("meta")
        @Expose
        private Meta meta;

        private class Meta {

            @SerializedName("app")
            @Expose
            private String app;

            public String getApp() {
                return app;
            }

            public void setApp(String app) {
                this.app = app;
            }
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }
    }

    public class TaxFilling {
        @SerializedName("data")
        @Expose
        private int data;

        @SerializedName("meta")
        @Expose
        private Meta meta;

        private class Meta {

            @SerializedName("app")
            @Expose
            private String app;

            public String getApp() {
                return app;
            }

            public void setApp(String app) {
                this.app = app;
            }
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }
    }

    public class TaxAdvisory {
        @SerializedName("data")
        @Expose
        private int data;

        @SerializedName("meta")
        @Expose
        private Meta meta;

        private class Meta {

            @SerializedName("app")
            @Expose
            private String app;

            public String getApp() {
                return app;
            }

            public void setApp(String app) {
                this.app = app;
            }
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }
    }

    public class RiskManagement {
        @SerializedName("data")
        @Expose
        private int data;

        @SerializedName("meta")
        @Expose
        private Meta meta;

        private class Meta {

            @SerializedName("app")
            @Expose
            private String app;

            public String getApp() {
                return app;
            }

            public void setApp(String app) {
                this.app = app;
            }
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }
    }

    public class Amount {
        @SerializedName("isquaterly")
        @Expose
        private int isquaterly;

        @SerializedName("Q1")
        @Expose
        private int Q1;

        @SerializedName("Q2")
        @Expose
        private int Q2;

        @SerializedName("Q3")
        @Expose
        private int Q3;

        @SerializedName("Q4")
        @Expose
        private int Q4;

        @SerializedName("total")
        @Expose
        private String total;

        public int getIsquaterly() {
            return isquaterly;
        }

        public void setIsquaterly(int isquaterly) {
            this.isquaterly = isquaterly;
        }

        public int getQ1() {
            return Q1;
        }

        public void setQ1(int q1) {
            Q1 = q1;
        }

        public int getQ2() {
            return Q2;
        }

        public void setQ2(int q2) {
            Q2 = q2;
        }

        public int getQ3() {
            return Q3;
        }

        public void setQ3(int q3) {
            Q3 = q3;
        }

        public int getQ4() {
            return Q4;
        }

        public void setQ4(int q4) {
            Q4 = q4;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }

    public int getPayment_frequency() {
        return payment_frequency;
    }

    public void setPayment_frequency(int payment_frequency) {
        this.payment_frequency = payment_frequency;
    }

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }

    public PrivateWealthManagment getPrivate_wealth_managment() {
        return private_wealth_managment;
    }

    public void setPrivate_wealth_managment(PrivateWealthManagment private_wealth_managment) {
        this.private_wealth_managment = private_wealth_managment;
    }

    public FinancialPlanning getFinancial_planning() {
        return financial_planning;
    }

    public void setFinancial_planning(FinancialPlanning financial_planning) {
        this.financial_planning = financial_planning;
    }

    public String getMonthly_amount() {
        return monthly_amount;
    }

    public void setMonthly_amount(String monthly_amount) {
        this.monthly_amount = monthly_amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public ChatWithExpert getChat_with_expert() {
        return chat_with_expert;
    }

    public void setChat_with_expert(ChatWithExpert chat_with_expert) {
        this.chat_with_expert = chat_with_expert;
    }

    public RebalancingPortfolio getRebalancing_portfolio() {
        return rebalancing_portfolio;
    }

    public void setRebalancing_portfolio(RebalancingPortfolio rebalancing_portfolio) {
        this.rebalancing_portfolio = rebalancing_portfolio;
    }

    public TaxFilling getTax_filling() {
        return tax_filling;
    }

    public void setTax_filling(TaxFilling tax_filling) {
        this.tax_filling = tax_filling;
    }

    public TaxAdvisory getTax_advisory() {
        return tax_advisory;
    }

    public void setTax_advisory(TaxAdvisory tax_advisory) {
        this.tax_advisory = tax_advisory;
    }

    public RiskManagement getRisk_management() {
        return risk_management;
    }

    public void setRisk_management(RiskManagement risk_management) {
        this.risk_management = risk_management;
    }

    public String getPortfolio_size() {
        return portfolio_size;
    }

    public void setPortfolio_size(String portfolio_size) {
        this.portfolio_size = portfolio_size;
    }

    public String getReview_frequency() {
        return review_frequency;
    }

    public void setReview_frequency(String review_frequency) {
        this.review_frequency = review_frequency;
    }

    public int getIsCheckVisible() {
        return isCheckVisible;
    }

    public void setIsCheckVisible(int isCheckVisible) {
        this.isCheckVisible = isCheckVisible;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }
}
