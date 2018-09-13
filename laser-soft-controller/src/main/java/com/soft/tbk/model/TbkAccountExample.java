package com.soft.tbk.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbkAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbkAccountExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAccountAmountIsNull() {
            addCriterion("ACCOUNT_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountIsNotNull() {
            addCriterion("ACCOUNT_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT =", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNotEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT <>", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountGreaterThan(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT >", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT >=", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountLessThan(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT <", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT <=", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountIn(List<BigDecimal> values) {
            addCriterion("ACCOUNT_AMOUNT in", values, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNotIn(List<BigDecimal> values) {
            addCriterion("ACCOUNT_AMOUNT not in", values, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACCOUNT_AMOUNT between", value1, value2, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACCOUNT_AMOUNT not between", value1, value2, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountAIsNull() {
            addCriterion("ACCOUNT_AMOUNT_A is null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountAIsNotNull() {
            addCriterion("ACCOUNT_AMOUNT_A is not null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountAEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_A =", value, "accountAmountA");
            return (Criteria) this;
        }

        public Criteria andAccountAmountANotEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_A <>", value, "accountAmountA");
            return (Criteria) this;
        }

        public Criteria andAccountAmountAGreaterThan(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_A >", value, "accountAmountA");
            return (Criteria) this;
        }

        public Criteria andAccountAmountAGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_A >=", value, "accountAmountA");
            return (Criteria) this;
        }

        public Criteria andAccountAmountALessThan(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_A <", value, "accountAmountA");
            return (Criteria) this;
        }

        public Criteria andAccountAmountALessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_A <=", value, "accountAmountA");
            return (Criteria) this;
        }

        public Criteria andAccountAmountAIn(List<BigDecimal> values) {
            addCriterion("ACCOUNT_AMOUNT_A in", values, "accountAmountA");
            return (Criteria) this;
        }

        public Criteria andAccountAmountANotIn(List<BigDecimal> values) {
            addCriterion("ACCOUNT_AMOUNT_A not in", values, "accountAmountA");
            return (Criteria) this;
        }

        public Criteria andAccountAmountABetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACCOUNT_AMOUNT_A between", value1, value2, "accountAmountA");
            return (Criteria) this;
        }

        public Criteria andAccountAmountANotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACCOUNT_AMOUNT_A not between", value1, value2, "accountAmountA");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFIsNull() {
            addCriterion("ACCOUNT_AMOUNT_F is null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFIsNotNull() {
            addCriterion("ACCOUNT_AMOUNT_F is not null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_F =", value, "accountAmountF");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFNotEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_F <>", value, "accountAmountF");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFGreaterThan(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_F >", value, "accountAmountF");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_F >=", value, "accountAmountF");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFLessThan(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_F <", value, "accountAmountF");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_F <=", value, "accountAmountF");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFIn(List<BigDecimal> values) {
            addCriterion("ACCOUNT_AMOUNT_F in", values, "accountAmountF");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFNotIn(List<BigDecimal> values) {
            addCriterion("ACCOUNT_AMOUNT_F not in", values, "accountAmountF");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACCOUNT_AMOUNT_F between", value1, value2, "accountAmountF");
            return (Criteria) this;
        }

        public Criteria andAccountAmountFNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACCOUNT_AMOUNT_F not between", value1, value2, "accountAmountF");
            return (Criteria) this;
        }

        public Criteria andAccountAmountEIsNull() {
            addCriterion("ACCOUNT_AMOUNT_E is null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountEIsNotNull() {
            addCriterion("ACCOUNT_AMOUNT_E is not null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountEEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_E =", value, "accountAmountE");
            return (Criteria) this;
        }

        public Criteria andAccountAmountENotEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_E <>", value, "accountAmountE");
            return (Criteria) this;
        }

        public Criteria andAccountAmountEGreaterThan(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_E >", value, "accountAmountE");
            return (Criteria) this;
        }

        public Criteria andAccountAmountEGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_E >=", value, "accountAmountE");
            return (Criteria) this;
        }

        public Criteria andAccountAmountELessThan(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_E <", value, "accountAmountE");
            return (Criteria) this;
        }

        public Criteria andAccountAmountELessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACCOUNT_AMOUNT_E <=", value, "accountAmountE");
            return (Criteria) this;
        }

        public Criteria andAccountAmountEIn(List<BigDecimal> values) {
            addCriterion("ACCOUNT_AMOUNT_E in", values, "accountAmountE");
            return (Criteria) this;
        }

        public Criteria andAccountAmountENotIn(List<BigDecimal> values) {
            addCriterion("ACCOUNT_AMOUNT_E not in", values, "accountAmountE");
            return (Criteria) this;
        }

        public Criteria andAccountAmountEBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACCOUNT_AMOUNT_E between", value1, value2, "accountAmountE");
            return (Criteria) this;
        }

        public Criteria andAccountAmountENotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACCOUNT_AMOUNT_E not between", value1, value2, "accountAmountE");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}