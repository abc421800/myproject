package com.cost168.costaudit.pojo.work;

import java.util.ArrayList;
import java.util.List;

public class WorkCalendarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkCalendarExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("year like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("year not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andWeekdaysIsNull() {
            addCriterion("weekdays is null");
            return (Criteria) this;
        }

        public Criteria andWeekdaysIsNotNull() {
            addCriterion("weekdays is not null");
            return (Criteria) this;
        }

        public Criteria andWeekdaysEqualTo(String value) {
            addCriterion("weekdays =", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysNotEqualTo(String value) {
            addCriterion("weekdays <>", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysGreaterThan(String value) {
            addCriterion("weekdays >", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysGreaterThanOrEqualTo(String value) {
            addCriterion("weekdays >=", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysLessThan(String value) {
            addCriterion("weekdays <", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysLessThanOrEqualTo(String value) {
            addCriterion("weekdays <=", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysLike(String value) {
            addCriterion("weekdays like", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysNotLike(String value) {
            addCriterion("weekdays not like", value, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysIn(List<String> values) {
            addCriterion("weekdays in", values, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysNotIn(List<String> values) {
            addCriterion("weekdays not in", values, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysBetween(String value1, String value2) {
            addCriterion("weekdays between", value1, value2, "weekdays");
            return (Criteria) this;
        }

        public Criteria andWeekdaysNotBetween(String value1, String value2) {
            addCriterion("weekdays not between", value1, value2, "weekdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysIsNull() {
            addCriterion("excepdays is null");
            return (Criteria) this;
        }

        public Criteria andExcepdaysIsNotNull() {
            addCriterion("excepdays is not null");
            return (Criteria) this;
        }

        public Criteria andExcepdaysEqualTo(String value) {
            addCriterion("excepdays =", value, "excepdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysNotEqualTo(String value) {
            addCriterion("excepdays <>", value, "excepdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysGreaterThan(String value) {
            addCriterion("excepdays >", value, "excepdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysGreaterThanOrEqualTo(String value) {
            addCriterion("excepdays >=", value, "excepdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysLessThan(String value) {
            addCriterion("excepdays <", value, "excepdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysLessThanOrEqualTo(String value) {
            addCriterion("excepdays <=", value, "excepdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysLike(String value) {
            addCriterion("excepdays like", value, "excepdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysNotLike(String value) {
            addCriterion("excepdays not like", value, "excepdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysIn(List<String> values) {
            addCriterion("excepdays in", values, "excepdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysNotIn(List<String> values) {
            addCriterion("excepdays not in", values, "excepdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysBetween(String value1, String value2) {
            addCriterion("excepdays between", value1, value2, "excepdays");
            return (Criteria) this;
        }

        public Criteria andExcepdaysNotBetween(String value1, String value2) {
            addCriterion("excepdays not between", value1, value2, "excepdays");
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