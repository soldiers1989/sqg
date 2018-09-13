package com.soft.tbk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbkUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbkUserExample() {
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

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdPathIsNull() {
            addCriterion("PARENT_ID_PATH is null");
            return (Criteria) this;
        }

        public Criteria andParentIdPathIsNotNull() {
            addCriterion("PARENT_ID_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdPathEqualTo(String value) {
            addCriterion("PARENT_ID_PATH =", value, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andParentIdPathNotEqualTo(String value) {
            addCriterion("PARENT_ID_PATH <>", value, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andParentIdPathGreaterThan(String value) {
            addCriterion("PARENT_ID_PATH >", value, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andParentIdPathGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ID_PATH >=", value, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andParentIdPathLessThan(String value) {
            addCriterion("PARENT_ID_PATH <", value, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andParentIdPathLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ID_PATH <=", value, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andParentIdPathLike(String value) {
            addCriterion("PARENT_ID_PATH like", value, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andParentIdPathNotLike(String value) {
            addCriterion("PARENT_ID_PATH not like", value, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andParentIdPathIn(List<String> values) {
            addCriterion("PARENT_ID_PATH in", values, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andParentIdPathNotIn(List<String> values) {
            addCriterion("PARENT_ID_PATH not in", values, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andParentIdPathBetween(String value1, String value2) {
            addCriterion("PARENT_ID_PATH between", value1, value2, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andParentIdPathNotBetween(String value1, String value2) {
            addCriterion("PARENT_ID_PATH not between", value1, value2, "parentIdPath");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIsNull() {
            addCriterion("USER_NICKNAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIsNotNull() {
            addCriterion("USER_NICKNAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNicknameEqualTo(String value) {
            addCriterion("USER_NICKNAME =", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotEqualTo(String value) {
            addCriterion("USER_NICKNAME <>", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameGreaterThan(String value) {
            addCriterion("USER_NICKNAME >", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NICKNAME >=", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLessThan(String value) {
            addCriterion("USER_NICKNAME <", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLessThanOrEqualTo(String value) {
            addCriterion("USER_NICKNAME <=", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLike(String value) {
            addCriterion("USER_NICKNAME like", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotLike(String value) {
            addCriterion("USER_NICKNAME not like", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIn(List<String> values) {
            addCriterion("USER_NICKNAME in", values, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotIn(List<String> values) {
            addCriterion("USER_NICKNAME not in", values, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameBetween(String value1, String value2) {
            addCriterion("USER_NICKNAME between", value1, value2, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotBetween(String value1, String value2) {
            addCriterion("USER_NICKNAME not between", value1, value2, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameIsNull() {
            addCriterion("USER_RELNAME is null");
            return (Criteria) this;
        }

        public Criteria andUserRelnameIsNotNull() {
            addCriterion("USER_RELNAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserRelnameEqualTo(String value) {
            addCriterion("USER_RELNAME =", value, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameNotEqualTo(String value) {
            addCriterion("USER_RELNAME <>", value, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameGreaterThan(String value) {
            addCriterion("USER_RELNAME >", value, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_RELNAME >=", value, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameLessThan(String value) {
            addCriterion("USER_RELNAME <", value, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameLessThanOrEqualTo(String value) {
            addCriterion("USER_RELNAME <=", value, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameLike(String value) {
            addCriterion("USER_RELNAME like", value, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameNotLike(String value) {
            addCriterion("USER_RELNAME not like", value, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameIn(List<String> values) {
            addCriterion("USER_RELNAME in", values, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameNotIn(List<String> values) {
            addCriterion("USER_RELNAME not in", values, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameBetween(String value1, String value2) {
            addCriterion("USER_RELNAME between", value1, value2, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserRelnameNotBetween(String value1, String value2) {
            addCriterion("USER_RELNAME not between", value1, value2, "userRelname");
            return (Criteria) this;
        }

        public Criteria andUserEmialIsNull() {
            addCriterion("USER_EMIAL is null");
            return (Criteria) this;
        }

        public Criteria andUserEmialIsNotNull() {
            addCriterion("USER_EMIAL is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmialEqualTo(String value) {
            addCriterion("USER_EMIAL =", value, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserEmialNotEqualTo(String value) {
            addCriterion("USER_EMIAL <>", value, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserEmialGreaterThan(String value) {
            addCriterion("USER_EMIAL >", value, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserEmialGreaterThanOrEqualTo(String value) {
            addCriterion("USER_EMIAL >=", value, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserEmialLessThan(String value) {
            addCriterion("USER_EMIAL <", value, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserEmialLessThanOrEqualTo(String value) {
            addCriterion("USER_EMIAL <=", value, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserEmialLike(String value) {
            addCriterion("USER_EMIAL like", value, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserEmialNotLike(String value) {
            addCriterion("USER_EMIAL not like", value, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserEmialIn(List<String> values) {
            addCriterion("USER_EMIAL in", values, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserEmialNotIn(List<String> values) {
            addCriterion("USER_EMIAL not in", values, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserEmialBetween(String value1, String value2) {
            addCriterion("USER_EMIAL between", value1, value2, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserEmialNotBetween(String value1, String value2) {
            addCriterion("USER_EMIAL not between", value1, value2, "userEmial");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("USER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("USER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("USER_PHONE =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("USER_PHONE <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("USER_PHONE >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PHONE >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("USER_PHONE <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("USER_PHONE <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("USER_PHONE like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("USER_PHONE not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("USER_PHONE in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("USER_PHONE not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("USER_PHONE between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("USER_PHONE not between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserQqIsNull() {
            addCriterion("USER_QQ is null");
            return (Criteria) this;
        }

        public Criteria andUserQqIsNotNull() {
            addCriterion("USER_QQ is not null");
            return (Criteria) this;
        }

        public Criteria andUserQqEqualTo(String value) {
            addCriterion("USER_QQ =", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotEqualTo(String value) {
            addCriterion("USER_QQ <>", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqGreaterThan(String value) {
            addCriterion("USER_QQ >", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqGreaterThanOrEqualTo(String value) {
            addCriterion("USER_QQ >=", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqLessThan(String value) {
            addCriterion("USER_QQ <", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqLessThanOrEqualTo(String value) {
            addCriterion("USER_QQ <=", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqLike(String value) {
            addCriterion("USER_QQ like", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotLike(String value) {
            addCriterion("USER_QQ not like", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqIn(List<String> values) {
            addCriterion("USER_QQ in", values, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotIn(List<String> values) {
            addCriterion("USER_QQ not in", values, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqBetween(String value1, String value2) {
            addCriterion("USER_QQ between", value1, value2, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotBetween(String value1, String value2) {
            addCriterion("USER_QQ not between", value1, value2, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdIsNull() {
            addCriterion("USER_PWSSWD is null");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdIsNotNull() {
            addCriterion("USER_PWSSWD is not null");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdEqualTo(String value) {
            addCriterion("USER_PWSSWD =", value, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdNotEqualTo(String value) {
            addCriterion("USER_PWSSWD <>", value, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdGreaterThan(String value) {
            addCriterion("USER_PWSSWD >", value, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PWSSWD >=", value, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdLessThan(String value) {
            addCriterion("USER_PWSSWD <", value, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdLessThanOrEqualTo(String value) {
            addCriterion("USER_PWSSWD <=", value, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdLike(String value) {
            addCriterion("USER_PWSSWD like", value, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdNotLike(String value) {
            addCriterion("USER_PWSSWD not like", value, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdIn(List<String> values) {
            addCriterion("USER_PWSSWD in", values, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdNotIn(List<String> values) {
            addCriterion("USER_PWSSWD not in", values, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdBetween(String value1, String value2) {
            addCriterion("USER_PWSSWD between", value1, value2, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPwsswdNotBetween(String value1, String value2) {
            addCriterion("USER_PWSSWD not between", value1, value2, "userPwsswd");
            return (Criteria) this;
        }

        public Criteria andUserPypwIsNull() {
            addCriterion("USER_PYPW is null");
            return (Criteria) this;
        }

        public Criteria andUserPypwIsNotNull() {
            addCriterion("USER_PYPW is not null");
            return (Criteria) this;
        }

        public Criteria andUserPypwEqualTo(String value) {
            addCriterion("USER_PYPW =", value, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserPypwNotEqualTo(String value) {
            addCriterion("USER_PYPW <>", value, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserPypwGreaterThan(String value) {
            addCriterion("USER_PYPW >", value, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserPypwGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PYPW >=", value, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserPypwLessThan(String value) {
            addCriterion("USER_PYPW <", value, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserPypwLessThanOrEqualTo(String value) {
            addCriterion("USER_PYPW <=", value, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserPypwLike(String value) {
            addCriterion("USER_PYPW like", value, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserPypwNotLike(String value) {
            addCriterion("USER_PYPW not like", value, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserPypwIn(List<String> values) {
            addCriterion("USER_PYPW in", values, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserPypwNotIn(List<String> values) {
            addCriterion("USER_PYPW not in", values, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserPypwBetween(String value1, String value2) {
            addCriterion("USER_PYPW between", value1, value2, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserPypwNotBetween(String value1, String value2) {
            addCriterion("USER_PYPW not between", value1, value2, "userPypw");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("USER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("USER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(Integer value) {
            addCriterion("USER_TYPE =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(Integer value) {
            addCriterion("USER_TYPE <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(Integer value) {
            addCriterion("USER_TYPE >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("USER_TYPE >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(Integer value) {
            addCriterion("USER_TYPE <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("USER_TYPE <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<Integer> values) {
            addCriterion("USER_TYPE in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<Integer> values) {
            addCriterion("USER_TYPE not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(Integer value1, Integer value2) {
            addCriterion("USER_TYPE between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("USER_TYPE not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserMsgIsNull() {
            addCriterion("USER_MSG is null");
            return (Criteria) this;
        }

        public Criteria andUserMsgIsNotNull() {
            addCriterion("USER_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andUserMsgEqualTo(String value) {
            addCriterion("USER_MSG =", value, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserMsgNotEqualTo(String value) {
            addCriterion("USER_MSG <>", value, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserMsgGreaterThan(String value) {
            addCriterion("USER_MSG >", value, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserMsgGreaterThanOrEqualTo(String value) {
            addCriterion("USER_MSG >=", value, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserMsgLessThan(String value) {
            addCriterion("USER_MSG <", value, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserMsgLessThanOrEqualTo(String value) {
            addCriterion("USER_MSG <=", value, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserMsgLike(String value) {
            addCriterion("USER_MSG like", value, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserMsgNotLike(String value) {
            addCriterion("USER_MSG not like", value, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserMsgIn(List<String> values) {
            addCriterion("USER_MSG in", values, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserMsgNotIn(List<String> values) {
            addCriterion("USER_MSG not in", values, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserMsgBetween(String value1, String value2) {
            addCriterion("USER_MSG between", value1, value2, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserMsgNotBetween(String value1, String value2) {
            addCriterion("USER_MSG not between", value1, value2, "userMsg");
            return (Criteria) this;
        }

        public Criteria andUserLevelIsNull() {
            addCriterion("USER_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andUserLevelIsNotNull() {
            addCriterion("USER_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andUserLevelEqualTo(String value) {
            addCriterion("USER_LEVEL =", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelNotEqualTo(String value) {
            addCriterion("USER_LEVEL <>", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelGreaterThan(String value) {
            addCriterion("USER_LEVEL >", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelGreaterThanOrEqualTo(String value) {
            addCriterion("USER_LEVEL >=", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelLessThan(String value) {
            addCriterion("USER_LEVEL <", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelLessThanOrEqualTo(String value) {
            addCriterion("USER_LEVEL <=", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelLike(String value) {
            addCriterion("USER_LEVEL like", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelNotLike(String value) {
            addCriterion("USER_LEVEL not like", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelIn(List<String> values) {
            addCriterion("USER_LEVEL in", values, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelNotIn(List<String> values) {
            addCriterion("USER_LEVEL not in", values, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelBetween(String value1, String value2) {
            addCriterion("USER_LEVEL between", value1, value2, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelNotBetween(String value1, String value2) {
            addCriterion("USER_LEVEL not between", value1, value2, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserImgurlIsNull() {
            addCriterion("USER_IMGURL is null");
            return (Criteria) this;
        }

        public Criteria andUserImgurlIsNotNull() {
            addCriterion("USER_IMGURL is not null");
            return (Criteria) this;
        }

        public Criteria andUserImgurlEqualTo(String value) {
            addCriterion("USER_IMGURL =", value, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andUserImgurlNotEqualTo(String value) {
            addCriterion("USER_IMGURL <>", value, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andUserImgurlGreaterThan(String value) {
            addCriterion("USER_IMGURL >", value, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andUserImgurlGreaterThanOrEqualTo(String value) {
            addCriterion("USER_IMGURL >=", value, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andUserImgurlLessThan(String value) {
            addCriterion("USER_IMGURL <", value, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andUserImgurlLessThanOrEqualTo(String value) {
            addCriterion("USER_IMGURL <=", value, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andUserImgurlLike(String value) {
            addCriterion("USER_IMGURL like", value, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andUserImgurlNotLike(String value) {
            addCriterion("USER_IMGURL not like", value, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andUserImgurlIn(List<String> values) {
            addCriterion("USER_IMGURL in", values, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andUserImgurlNotIn(List<String> values) {
            addCriterion("USER_IMGURL not in", values, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andUserImgurlBetween(String value1, String value2) {
            addCriterion("USER_IMGURL between", value1, value2, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andUserImgurlNotBetween(String value1, String value2) {
            addCriterion("USER_IMGURL not between", value1, value2, "userImgurl");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andUserOpenidIsNull() {
            addCriterion("USER_OPENID is null");
            return (Criteria) this;
        }

        public Criteria andUserOpenidIsNotNull() {
            addCriterion("USER_OPENID is not null");
            return (Criteria) this;
        }

        public Criteria andUserOpenidEqualTo(String value) {
            addCriterion("USER_OPENID =", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidNotEqualTo(String value) {
            addCriterion("USER_OPENID <>", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidGreaterThan(String value) {
            addCriterion("USER_OPENID >", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("USER_OPENID >=", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidLessThan(String value) {
            addCriterion("USER_OPENID <", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidLessThanOrEqualTo(String value) {
            addCriterion("USER_OPENID <=", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidLike(String value) {
            addCriterion("USER_OPENID like", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidNotLike(String value) {
            addCriterion("USER_OPENID not like", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidIn(List<String> values) {
            addCriterion("USER_OPENID in", values, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidNotIn(List<String> values) {
            addCriterion("USER_OPENID not in", values, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidBetween(String value1, String value2) {
            addCriterion("USER_OPENID between", value1, value2, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidNotBetween(String value1, String value2) {
            addCriterion("USER_OPENID not between", value1, value2, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountIsNull() {
            addCriterion("USER_ALIPAY_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountIsNotNull() {
            addCriterion("USER_ALIPAY_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountEqualTo(String value) {
            addCriterion("USER_ALIPAY_ACCOUNT =", value, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountNotEqualTo(String value) {
            addCriterion("USER_ALIPAY_ACCOUNT <>", value, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountGreaterThan(String value) {
            addCriterion("USER_ALIPAY_ACCOUNT >", value, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ALIPAY_ACCOUNT >=", value, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountLessThan(String value) {
            addCriterion("USER_ALIPAY_ACCOUNT <", value, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountLessThanOrEqualTo(String value) {
            addCriterion("USER_ALIPAY_ACCOUNT <=", value, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountLike(String value) {
            addCriterion("USER_ALIPAY_ACCOUNT like", value, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountNotLike(String value) {
            addCriterion("USER_ALIPAY_ACCOUNT not like", value, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountIn(List<String> values) {
            addCriterion("USER_ALIPAY_ACCOUNT in", values, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountNotIn(List<String> values) {
            addCriterion("USER_ALIPAY_ACCOUNT not in", values, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountBetween(String value1, String value2) {
            addCriterion("USER_ALIPAY_ACCOUNT between", value1, value2, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andUserAlipayAccountNotBetween(String value1, String value2) {
            addCriterion("USER_ALIPAY_ACCOUNT not between", value1, value2, "userAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andAgentFlagIsNull() {
            addCriterion("AGENT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAgentFlagIsNotNull() {
            addCriterion("AGENT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAgentFlagEqualTo(Integer value) {
            addCriterion("AGENT_FLAG =", value, "agentFlag");
            return (Criteria) this;
        }

        public Criteria andAgentFlagNotEqualTo(Integer value) {
            addCriterion("AGENT_FLAG <>", value, "agentFlag");
            return (Criteria) this;
        }

        public Criteria andAgentFlagGreaterThan(Integer value) {
            addCriterion("AGENT_FLAG >", value, "agentFlag");
            return (Criteria) this;
        }

        public Criteria andAgentFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("AGENT_FLAG >=", value, "agentFlag");
            return (Criteria) this;
        }

        public Criteria andAgentFlagLessThan(Integer value) {
            addCriterion("AGENT_FLAG <", value, "agentFlag");
            return (Criteria) this;
        }

        public Criteria andAgentFlagLessThanOrEqualTo(Integer value) {
            addCriterion("AGENT_FLAG <=", value, "agentFlag");
            return (Criteria) this;
        }

        public Criteria andAgentFlagIn(List<Integer> values) {
            addCriterion("AGENT_FLAG in", values, "agentFlag");
            return (Criteria) this;
        }

        public Criteria andAgentFlagNotIn(List<Integer> values) {
            addCriterion("AGENT_FLAG not in", values, "agentFlag");
            return (Criteria) this;
        }

        public Criteria andAgentFlagBetween(Integer value1, Integer value2) {
            addCriterion("AGENT_FLAG between", value1, value2, "agentFlag");
            return (Criteria) this;
        }

        public Criteria andAgentFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("AGENT_FLAG not between", value1, value2, "agentFlag");
            return (Criteria) this;
        }

        public Criteria andAgentAreaIsNull() {
            addCriterion("AGENT_AREA is null");
            return (Criteria) this;
        }

        public Criteria andAgentAreaIsNotNull() {
            addCriterion("AGENT_AREA is not null");
            return (Criteria) this;
        }

        public Criteria andAgentAreaEqualTo(String value) {
            addCriterion("AGENT_AREA =", value, "agentArea");
            return (Criteria) this;
        }

        public Criteria andAgentAreaNotEqualTo(String value) {
            addCriterion("AGENT_AREA <>", value, "agentArea");
            return (Criteria) this;
        }

        public Criteria andAgentAreaGreaterThan(String value) {
            addCriterion("AGENT_AREA >", value, "agentArea");
            return (Criteria) this;
        }

        public Criteria andAgentAreaGreaterThanOrEqualTo(String value) {
            addCriterion("AGENT_AREA >=", value, "agentArea");
            return (Criteria) this;
        }

        public Criteria andAgentAreaLessThan(String value) {
            addCriterion("AGENT_AREA <", value, "agentArea");
            return (Criteria) this;
        }

        public Criteria andAgentAreaLessThanOrEqualTo(String value) {
            addCriterion("AGENT_AREA <=", value, "agentArea");
            return (Criteria) this;
        }

        public Criteria andAgentAreaLike(String value) {
            addCriterion("AGENT_AREA like", value, "agentArea");
            return (Criteria) this;
        }

        public Criteria andAgentAreaNotLike(String value) {
            addCriterion("AGENT_AREA not like", value, "agentArea");
            return (Criteria) this;
        }

        public Criteria andAgentAreaIn(List<String> values) {
            addCriterion("AGENT_AREA in", values, "agentArea");
            return (Criteria) this;
        }

        public Criteria andAgentAreaNotIn(List<String> values) {
            addCriterion("AGENT_AREA not in", values, "agentArea");
            return (Criteria) this;
        }

        public Criteria andAgentAreaBetween(String value1, String value2) {
            addCriterion("AGENT_AREA between", value1, value2, "agentArea");
            return (Criteria) this;
        }

        public Criteria andAgentAreaNotBetween(String value1, String value2) {
            addCriterion("AGENT_AREA not between", value1, value2, "agentArea");
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