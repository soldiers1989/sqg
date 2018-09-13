package com.soft.tbk.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbkOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbkOrderExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("PID is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("PID is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(String value) {
            addCriterion("PID =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(String value) {
            addCriterion("PID <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(String value) {
            addCriterion("PID >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(String value) {
            addCriterion("PID >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(String value) {
            addCriterion("PID <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(String value) {
            addCriterion("PID <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLike(String value) {
            addCriterion("PID like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotLike(String value) {
            addCriterion("PID not like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<String> values) {
            addCriterion("PID in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<String> values) {
            addCriterion("PID not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(String value1, String value2) {
            addCriterion("PID between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(String value1, String value2) {
            addCriterion("PID not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andItemImageIsNull() {
            addCriterion("ITEM_IMAGE is null");
            return (Criteria) this;
        }

        public Criteria andItemImageIsNotNull() {
            addCriterion("ITEM_IMAGE is not null");
            return (Criteria) this;
        }

        public Criteria andItemImageEqualTo(String value) {
            addCriterion("ITEM_IMAGE =", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotEqualTo(String value) {
            addCriterion("ITEM_IMAGE <>", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageGreaterThan(String value) {
            addCriterion("ITEM_IMAGE >", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_IMAGE >=", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageLessThan(String value) {
            addCriterion("ITEM_IMAGE <", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageLessThanOrEqualTo(String value) {
            addCriterion("ITEM_IMAGE <=", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageLike(String value) {
            addCriterion("ITEM_IMAGE like", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotLike(String value) {
            addCriterion("ITEM_IMAGE not like", value, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageIn(List<String> values) {
            addCriterion("ITEM_IMAGE in", values, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotIn(List<String> values) {
            addCriterion("ITEM_IMAGE not in", values, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageBetween(String value1, String value2) {
            addCriterion("ITEM_IMAGE between", value1, value2, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemImageNotBetween(String value1, String value2) {
            addCriterion("ITEM_IMAGE not between", value1, value2, "itemImage");
            return (Criteria) this;
        }

        public Criteria andItemTitleIsNull() {
            addCriterion("ITEM_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andItemTitleIsNotNull() {
            addCriterion("ITEM_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andItemTitleEqualTo(String value) {
            addCriterion("ITEM_TITLE =", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotEqualTo(String value) {
            addCriterion("ITEM_TITLE <>", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleGreaterThan(String value) {
            addCriterion("ITEM_TITLE >", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_TITLE >=", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLessThan(String value) {
            addCriterion("ITEM_TITLE <", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLessThanOrEqualTo(String value) {
            addCriterion("ITEM_TITLE <=", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLike(String value) {
            addCriterion("ITEM_TITLE like", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotLike(String value) {
            addCriterion("ITEM_TITLE not like", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleIn(List<String> values) {
            addCriterion("ITEM_TITLE in", values, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotIn(List<String> values) {
            addCriterion("ITEM_TITLE not in", values, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleBetween(String value1, String value2) {
            addCriterion("ITEM_TITLE between", value1, value2, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotBetween(String value1, String value2) {
            addCriterion("ITEM_TITLE not between", value1, value2, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNull() {
            addCriterion("ITEM_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNotNull() {
            addCriterion("ITEM_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andItemPriceEqualTo(BigDecimal value) {
            addCriterion("ITEM_PRICE =", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotEqualTo(BigDecimal value) {
            addCriterion("ITEM_PRICE <>", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThan(BigDecimal value) {
            addCriterion("ITEM_PRICE >", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ITEM_PRICE >=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThan(BigDecimal value) {
            addCriterion("ITEM_PRICE <", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ITEM_PRICE <=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceIn(List<BigDecimal> values) {
            addCriterion("ITEM_PRICE in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotIn(List<BigDecimal> values) {
            addCriterion("ITEM_PRICE not in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ITEM_PRICE between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ITEM_PRICE not between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andSellerNameIsNull() {
            addCriterion("SELLER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSellerNameIsNotNull() {
            addCriterion("SELLER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSellerNameEqualTo(String value) {
            addCriterion("SELLER_NAME =", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotEqualTo(String value) {
            addCriterion("SELLER_NAME <>", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameGreaterThan(String value) {
            addCriterion("SELLER_NAME >", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameGreaterThanOrEqualTo(String value) {
            addCriterion("SELLER_NAME >=", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLessThan(String value) {
            addCriterion("SELLER_NAME <", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLessThanOrEqualTo(String value) {
            addCriterion("SELLER_NAME <=", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLike(String value) {
            addCriterion("SELLER_NAME like", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotLike(String value) {
            addCriterion("SELLER_NAME not like", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameIn(List<String> values) {
            addCriterion("SELLER_NAME in", values, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotIn(List<String> values) {
            addCriterion("SELLER_NAME not in", values, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameBetween(String value1, String value2) {
            addCriterion("SELLER_NAME between", value1, value2, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotBetween(String value1, String value2) {
            addCriterion("SELLER_NAME not between", value1, value2, "sellerName");
            return (Criteria) this;
        }

        public Criteria andTradeIdIsNull() {
            addCriterion("TRADE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTradeIdIsNotNull() {
            addCriterion("TRADE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTradeIdEqualTo(Long value) {
            addCriterion("TRADE_ID =", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotEqualTo(Long value) {
            addCriterion("TRADE_ID <>", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThan(Long value) {
            addCriterion("TRADE_ID >", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TRADE_ID >=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThan(Long value) {
            addCriterion("TRADE_ID <", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThanOrEqualTo(Long value) {
            addCriterion("TRADE_ID <=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdIn(List<Long> values) {
            addCriterion("TRADE_ID in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotIn(List<Long> values) {
            addCriterion("TRADE_ID not in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdBetween(Long value1, Long value2) {
            addCriterion("TRADE_ID between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotBetween(Long value1, Long value2) {
            addCriterion("TRADE_ID not between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andItemNumIsNull() {
            addCriterion("ITEM_NUM is null");
            return (Criteria) this;
        }

        public Criteria andItemNumIsNotNull() {
            addCriterion("ITEM_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andItemNumEqualTo(Integer value) {
            addCriterion("ITEM_NUM =", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotEqualTo(Integer value) {
            addCriterion("ITEM_NUM <>", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumGreaterThan(Integer value) {
            addCriterion("ITEM_NUM >", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ITEM_NUM >=", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumLessThan(Integer value) {
            addCriterion("ITEM_NUM <", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumLessThanOrEqualTo(Integer value) {
            addCriterion("ITEM_NUM <=", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumIn(List<Integer> values) {
            addCriterion("ITEM_NUM in", values, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotIn(List<Integer> values) {
            addCriterion("ITEM_NUM not in", values, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_NUM between", value1, value2, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_NUM not between", value1, value2, "itemNum");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNull() {
            addCriterion("PAY_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("PAY_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(BigDecimal value) {
            addCriterion("PAY_AMOUNT =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("PAY_AMOUNT <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(BigDecimal value) {
            addCriterion("PAY_AMOUNT >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PAY_AMOUNT >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(BigDecimal value) {
            addCriterion("PAY_AMOUNT <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PAY_AMOUNT <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<BigDecimal> values) {
            addCriterion("PAY_AMOUNT in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("PAY_AMOUNT not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PAY_AMOUNT between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PAY_AMOUNT not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionIsNull() {
            addCriterion("COMMISSION is null");
            return (Criteria) this;
        }

        public Criteria andCommissionIsNotNull() {
            addCriterion("COMMISSION is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionEqualTo(BigDecimal value) {
            addCriterion("COMMISSION =", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotEqualTo(BigDecimal value) {
            addCriterion("COMMISSION <>", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionGreaterThan(BigDecimal value) {
            addCriterion("COMMISSION >", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COMMISSION >=", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionLessThan(BigDecimal value) {
            addCriterion("COMMISSION <", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COMMISSION <=", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionIn(List<BigDecimal> values) {
            addCriterion("COMMISSION in", values, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotIn(List<BigDecimal> values) {
            addCriterion("COMMISSION not in", values, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COMMISSION between", value1, value2, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COMMISSION not between", value1, value2, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIsNull() {
            addCriterion("COMMISSION_RATE is null");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIsNotNull() {
            addCriterion("COMMISSION_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionRateEqualTo(BigDecimal value) {
            addCriterion("COMMISSION_RATE =", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotEqualTo(BigDecimal value) {
            addCriterion("COMMISSION_RATE <>", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateGreaterThan(BigDecimal value) {
            addCriterion("COMMISSION_RATE >", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COMMISSION_RATE >=", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateLessThan(BigDecimal value) {
            addCriterion("COMMISSION_RATE <", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COMMISSION_RATE <=", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIn(List<BigDecimal> values) {
            addCriterion("COMMISSION_RATE in", values, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotIn(List<BigDecimal> values) {
            addCriterion("COMMISSION_RATE not in", values, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COMMISSION_RATE between", value1, value2, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COMMISSION_RATE not between", value1, value2, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNull() {
            addCriterion("TRADE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNotNull() {
            addCriterion("TRADE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeEqualTo(Date value) {
            addCriterion("TRADE_TIME =", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotEqualTo(Date value) {
            addCriterion("TRADE_TIME <>", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThan(Date value) {
            addCriterion("TRADE_TIME >", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("TRADE_TIME >=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThan(Date value) {
            addCriterion("TRADE_TIME <", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThanOrEqualTo(Date value) {
            addCriterion("TRADE_TIME <=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIn(List<Date> values) {
            addCriterion("TRADE_TIME in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotIn(List<Date> values) {
            addCriterion("TRADE_TIME not in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeBetween(Date value1, Date value2) {
            addCriterion("TRADE_TIME between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotBetween(Date value1, Date value2) {
            addCriterion("TRADE_TIME not between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andEarningTimeIsNull() {
            addCriterion("EARNING_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEarningTimeIsNotNull() {
            addCriterion("EARNING_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEarningTimeEqualTo(Date value) {
            addCriterion("EARNING_TIME =", value, "earningTime");
            return (Criteria) this;
        }

        public Criteria andEarningTimeNotEqualTo(Date value) {
            addCriterion("EARNING_TIME <>", value, "earningTime");
            return (Criteria) this;
        }

        public Criteria andEarningTimeGreaterThan(Date value) {
            addCriterion("EARNING_TIME >", value, "earningTime");
            return (Criteria) this;
        }

        public Criteria andEarningTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("EARNING_TIME >=", value, "earningTime");
            return (Criteria) this;
        }

        public Criteria andEarningTimeLessThan(Date value) {
            addCriterion("EARNING_TIME <", value, "earningTime");
            return (Criteria) this;
        }

        public Criteria andEarningTimeLessThanOrEqualTo(Date value) {
            addCriterion("EARNING_TIME <=", value, "earningTime");
            return (Criteria) this;
        }

        public Criteria andEarningTimeIn(List<Date> values) {
            addCriterion("EARNING_TIME in", values, "earningTime");
            return (Criteria) this;
        }

        public Criteria andEarningTimeNotIn(List<Date> values) {
            addCriterion("EARNING_TIME not in", values, "earningTime");
            return (Criteria) this;
        }

        public Criteria andEarningTimeBetween(Date value1, Date value2) {
            addCriterion("EARNING_TIME between", value1, value2, "earningTime");
            return (Criteria) this;
        }

        public Criteria andEarningTimeNotBetween(Date value1, Date value2) {
            addCriterion("EARNING_TIME not between", value1, value2, "earningTime");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIsNull() {
            addCriterion("TRADE_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIsNotNull() {
            addCriterion("TRADE_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTradeStatusEqualTo(Integer value) {
            addCriterion("TRADE_STATUS =", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotEqualTo(Integer value) {
            addCriterion("TRADE_STATUS <>", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusGreaterThan(Integer value) {
            addCriterion("TRADE_STATUS >", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("TRADE_STATUS >=", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusLessThan(Integer value) {
            addCriterion("TRADE_STATUS <", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusLessThanOrEqualTo(Integer value) {
            addCriterion("TRADE_STATUS <=", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIn(List<Integer> values) {
            addCriterion("TRADE_STATUS in", values, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotIn(List<Integer> values) {
            addCriterion("TRADE_STATUS not in", values, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusBetween(Integer value1, Integer value2) {
            addCriterion("TRADE_STATUS between", value1, value2, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("TRADE_STATUS not between", value1, value2, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNull() {
            addCriterion("TRADE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNotNull() {
            addCriterion("TRADE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeEqualTo(String value) {
            addCriterion("TRADE_TYPE =", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotEqualTo(String value) {
            addCriterion("TRADE_TYPE <>", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThan(String value) {
            addCriterion("TRADE_TYPE >", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE_TYPE >=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThan(String value) {
            addCriterion("TRADE_TYPE <", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThanOrEqualTo(String value) {
            addCriterion("TRADE_TYPE <=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLike(String value) {
            addCriterion("TRADE_TYPE like", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotLike(String value) {
            addCriterion("TRADE_TYPE not like", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIn(List<String> values) {
            addCriterion("TRADE_TYPE in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotIn(List<String> values) {
            addCriterion("TRADE_TYPE not in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeBetween(String value1, String value2) {
            addCriterion("TRADE_TYPE between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotBetween(String value1, String value2) {
            addCriterion("TRADE_TYPE not between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andIncomeRateIsNull() {
            addCriterion("INCOME_RATE is null");
            return (Criteria) this;
        }

        public Criteria andIncomeRateIsNotNull() {
            addCriterion("INCOME_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeRateEqualTo(String value) {
            addCriterion("INCOME_RATE =", value, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andIncomeRateNotEqualTo(String value) {
            addCriterion("INCOME_RATE <>", value, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andIncomeRateGreaterThan(String value) {
            addCriterion("INCOME_RATE >", value, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andIncomeRateGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME_RATE >=", value, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andIncomeRateLessThan(String value) {
            addCriterion("INCOME_RATE <", value, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andIncomeRateLessThanOrEqualTo(String value) {
            addCriterion("INCOME_RATE <=", value, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andIncomeRateLike(String value) {
            addCriterion("INCOME_RATE like", value, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andIncomeRateNotLike(String value) {
            addCriterion("INCOME_RATE not like", value, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andIncomeRateIn(List<String> values) {
            addCriterion("INCOME_RATE in", values, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andIncomeRateNotIn(List<String> values) {
            addCriterion("INCOME_RATE not in", values, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andIncomeRateBetween(String value1, String value2) {
            addCriterion("INCOME_RATE between", value1, value2, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andIncomeRateNotBetween(String value1, String value2) {
            addCriterion("INCOME_RATE not between", value1, value2, "incomeRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateIsNull() {
            addCriterion("SUBSIDY_RATE is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateIsNotNull() {
            addCriterion("SUBSIDY_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateEqualTo(String value) {
            addCriterion("SUBSIDY_RATE =", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateNotEqualTo(String value) {
            addCriterion("SUBSIDY_RATE <>", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateGreaterThan(String value) {
            addCriterion("SUBSIDY_RATE >", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateGreaterThanOrEqualTo(String value) {
            addCriterion("SUBSIDY_RATE >=", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateLessThan(String value) {
            addCriterion("SUBSIDY_RATE <", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateLessThanOrEqualTo(String value) {
            addCriterion("SUBSIDY_RATE <=", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateLike(String value) {
            addCriterion("SUBSIDY_RATE like", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateNotLike(String value) {
            addCriterion("SUBSIDY_RATE not like", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateIn(List<String> values) {
            addCriterion("SUBSIDY_RATE in", values, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateNotIn(List<String> values) {
            addCriterion("SUBSIDY_RATE not in", values, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateBetween(String value1, String value2) {
            addCriterion("SUBSIDY_RATE between", value1, value2, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateNotBetween(String value1, String value2) {
            addCriterion("SUBSIDY_RATE not between", value1, value2, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeIsNull() {
            addCriterion("RERMINAL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeIsNotNull() {
            addCriterion("RERMINAL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeEqualTo(String value) {
            addCriterion("RERMINAL_TYPE =", value, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeNotEqualTo(String value) {
            addCriterion("RERMINAL_TYPE <>", value, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeGreaterThan(String value) {
            addCriterion("RERMINAL_TYPE >", value, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RERMINAL_TYPE >=", value, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeLessThan(String value) {
            addCriterion("RERMINAL_TYPE <", value, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeLessThanOrEqualTo(String value) {
            addCriterion("RERMINAL_TYPE <=", value, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeLike(String value) {
            addCriterion("RERMINAL_TYPE like", value, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeNotLike(String value) {
            addCriterion("RERMINAL_TYPE not like", value, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeIn(List<String> values) {
            addCriterion("RERMINAL_TYPE in", values, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeNotIn(List<String> values) {
            addCriterion("RERMINAL_TYPE not in", values, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeBetween(String value1, String value2) {
            addCriterion("RERMINAL_TYPE between", value1, value2, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andRerminalTypeNotBetween(String value1, String value2) {
            addCriterion("RERMINAL_TYPE not between", value1, value2, "rerminalType");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceIsNull() {
            addCriterion("ALIPAY_TOTAL_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceIsNotNull() {
            addCriterion("ALIPAY_TOTAL_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceEqualTo(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE =", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE <>", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE >", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE >=", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceLessThan(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE <", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE <=", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceIn(List<BigDecimal> values) {
            addCriterion("ALIPAY_TOTAL_PRICE in", values, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("ALIPAY_TOTAL_PRICE not in", values, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALIPAY_TOTAL_PRICE between", value1, value2, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALIPAY_TOTAL_PRICE not between", value1, value2, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateIsNull() {
            addCriterion("TOTAL_COMMISSION_RATE is null");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateIsNotNull() {
            addCriterion("TOTAL_COMMISSION_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateEqualTo(String value) {
            addCriterion("TOTAL_COMMISSION_RATE =", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateNotEqualTo(String value) {
            addCriterion("TOTAL_COMMISSION_RATE <>", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateGreaterThan(String value) {
            addCriterion("TOTAL_COMMISSION_RATE >", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateGreaterThanOrEqualTo(String value) {
            addCriterion("TOTAL_COMMISSION_RATE >=", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateLessThan(String value) {
            addCriterion("TOTAL_COMMISSION_RATE <", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateLessThanOrEqualTo(String value) {
            addCriterion("TOTAL_COMMISSION_RATE <=", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateLike(String value) {
            addCriterion("TOTAL_COMMISSION_RATE like", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateNotLike(String value) {
            addCriterion("TOTAL_COMMISSION_RATE not like", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateIn(List<String> values) {
            addCriterion("TOTAL_COMMISSION_RATE in", values, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateNotIn(List<String> values) {
            addCriterion("TOTAL_COMMISSION_RATE not in", values, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateBetween(String value1, String value2) {
            addCriterion("TOTAL_COMMISSION_RATE between", value1, value2, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateNotBetween(String value1, String value2) {
            addCriterion("TOTAL_COMMISSION_RATE not between", value1, value2, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeIsNull() {
            addCriterion("TOTAL_COMMISSION_FEE is null");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeIsNotNull() {
            addCriterion("TOTAL_COMMISSION_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeEqualTo(String value) {
            addCriterion("TOTAL_COMMISSION_FEE =", value, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeNotEqualTo(String value) {
            addCriterion("TOTAL_COMMISSION_FEE <>", value, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeGreaterThan(String value) {
            addCriterion("TOTAL_COMMISSION_FEE >", value, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeGreaterThanOrEqualTo(String value) {
            addCriterion("TOTAL_COMMISSION_FEE >=", value, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeLessThan(String value) {
            addCriterion("TOTAL_COMMISSION_FEE <", value, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeLessThanOrEqualTo(String value) {
            addCriterion("TOTAL_COMMISSION_FEE <=", value, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeLike(String value) {
            addCriterion("TOTAL_COMMISSION_FEE like", value, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeNotLike(String value) {
            addCriterion("TOTAL_COMMISSION_FEE not like", value, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeIn(List<String> values) {
            addCriterion("TOTAL_COMMISSION_FEE in", values, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeNotIn(List<String> values) {
            addCriterion("TOTAL_COMMISSION_FEE not in", values, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeBetween(String value1, String value2) {
            addCriterion("TOTAL_COMMISSION_FEE between", value1, value2, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionFeeNotBetween(String value1, String value2) {
            addCriterion("TOTAL_COMMISSION_FEE not between", value1, value2, "totalCommissionFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeIsNull() {
            addCriterion("SUBSIDY_FEE is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeIsNotNull() {
            addCriterion("SUBSIDY_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeEqualTo(String value) {
            addCriterion("SUBSIDY_FEE =", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeNotEqualTo(String value) {
            addCriterion("SUBSIDY_FEE <>", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeGreaterThan(String value) {
            addCriterion("SUBSIDY_FEE >", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBSIDY_FEE >=", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeLessThan(String value) {
            addCriterion("SUBSIDY_FEE <", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeLessThanOrEqualTo(String value) {
            addCriterion("SUBSIDY_FEE <=", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeLike(String value) {
            addCriterion("SUBSIDY_FEE like", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeNotLike(String value) {
            addCriterion("SUBSIDY_FEE not like", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeIn(List<String> values) {
            addCriterion("SUBSIDY_FEE in", values, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeNotIn(List<String> values) {
            addCriterion("SUBSIDY_FEE not in", values, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeBetween(String value1, String value2) {
            addCriterion("SUBSIDY_FEE between", value1, value2, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeNotBetween(String value1, String value2) {
            addCriterion("SUBSIDY_FEE not between", value1, value2, "subsidyFee");
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