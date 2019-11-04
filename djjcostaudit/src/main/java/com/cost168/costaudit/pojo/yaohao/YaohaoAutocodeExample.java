package com.cost168.costaudit.pojo.yaohao;

import java.util.ArrayList;
import java.util.List;

public class YaohaoAutocodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YaohaoAutocodeExample() {
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

        public Criteria andLunNumBIsNull() {
            addCriterion("lun_num_b is null");
            return (Criteria) this;
        }

        public Criteria andLunNumBIsNotNull() {
            addCriterion("lun_num_b is not null");
            return (Criteria) this;
        }

        public Criteria andLunNumBEqualTo(Integer value) {
            addCriterion("lun_num_b =", value, "lunNumB");
            return (Criteria) this;
        }

        public Criteria andLunNumBNotEqualTo(Integer value) {
            addCriterion("lun_num_b <>", value, "lunNumB");
            return (Criteria) this;
        }

        public Criteria andLunNumBGreaterThan(Integer value) {
            addCriterion("lun_num_b >", value, "lunNumB");
            return (Criteria) this;
        }

        public Criteria andLunNumBGreaterThanOrEqualTo(Integer value) {
            addCriterion("lun_num_b >=", value, "lunNumB");
            return (Criteria) this;
        }

        public Criteria andLunNumBLessThan(Integer value) {
            addCriterion("lun_num_b <", value, "lunNumB");
            return (Criteria) this;
        }

        public Criteria andLunNumBLessThanOrEqualTo(Integer value) {
            addCriterion("lun_num_b <=", value, "lunNumB");
            return (Criteria) this;
        }

        public Criteria andLunNumBIn(List<Integer> values) {
            addCriterion("lun_num_b in", values, "lunNumB");
            return (Criteria) this;
        }

        public Criteria andLunNumBNotIn(List<Integer> values) {
            addCriterion("lun_num_b not in", values, "lunNumB");
            return (Criteria) this;
        }

        public Criteria andLunNumBBetween(Integer value1, Integer value2) {
            addCriterion("lun_num_b between", value1, value2, "lunNumB");
            return (Criteria) this;
        }

        public Criteria andLunNumBNotBetween(Integer value1, Integer value2) {
            addCriterion("lun_num_b not between", value1, value2, "lunNumB");
            return (Criteria) this;
        }

        public Criteria andCiNumBIsNull() {
            addCriterion("ci_num_b is null");
            return (Criteria) this;
        }

        public Criteria andCiNumBIsNotNull() {
            addCriterion("ci_num_b is not null");
            return (Criteria) this;
        }

        public Criteria andCiNumBEqualTo(Integer value) {
            addCriterion("ci_num_b =", value, "ciNumB");
            return (Criteria) this;
        }

        public Criteria andCiNumBNotEqualTo(Integer value) {
            addCriterion("ci_num_b <>", value, "ciNumB");
            return (Criteria) this;
        }

        public Criteria andCiNumBGreaterThan(Integer value) {
            addCriterion("ci_num_b >", value, "ciNumB");
            return (Criteria) this;
        }

        public Criteria andCiNumBGreaterThanOrEqualTo(Integer value) {
            addCriterion("ci_num_b >=", value, "ciNumB");
            return (Criteria) this;
        }

        public Criteria andCiNumBLessThan(Integer value) {
            addCriterion("ci_num_b <", value, "ciNumB");
            return (Criteria) this;
        }

        public Criteria andCiNumBLessThanOrEqualTo(Integer value) {
            addCriterion("ci_num_b <=", value, "ciNumB");
            return (Criteria) this;
        }

        public Criteria andCiNumBIn(List<Integer> values) {
            addCriterion("ci_num_b in", values, "ciNumB");
            return (Criteria) this;
        }

        public Criteria andCiNumBNotIn(List<Integer> values) {
            addCriterion("ci_num_b not in", values, "ciNumB");
            return (Criteria) this;
        }

        public Criteria andCiNumBBetween(Integer value1, Integer value2) {
            addCriterion("ci_num_b between", value1, value2, "ciNumB");
            return (Criteria) this;
        }

        public Criteria andCiNumBNotBetween(Integer value1, Integer value2) {
            addCriterion("ci_num_b not between", value1, value2, "ciNumB");
            return (Criteria) this;
        }

        public Criteria andCodeBIsNull() {
            addCriterion("code_b is null");
            return (Criteria) this;
        }

        public Criteria andCodeBIsNotNull() {
            addCriterion("code_b is not null");
            return (Criteria) this;
        }

        public Criteria andCodeBEqualTo(String value) {
            addCriterion("code_b =", value, "codeB");
            return (Criteria) this;
        }

        public Criteria andCodeBNotEqualTo(String value) {
            addCriterion("code_b <>", value, "codeB");
            return (Criteria) this;
        }

        public Criteria andCodeBGreaterThan(String value) {
            addCriterion("code_b >", value, "codeB");
            return (Criteria) this;
        }

        public Criteria andCodeBGreaterThanOrEqualTo(String value) {
            addCriterion("code_b >=", value, "codeB");
            return (Criteria) this;
        }

        public Criteria andCodeBLessThan(String value) {
            addCriterion("code_b <", value, "codeB");
            return (Criteria) this;
        }

        public Criteria andCodeBLessThanOrEqualTo(String value) {
            addCriterion("code_b <=", value, "codeB");
            return (Criteria) this;
        }

        public Criteria andCodeBLike(String value) {
            addCriterion("code_b like", value, "codeB");
            return (Criteria) this;
        }

        public Criteria andCodeBNotLike(String value) {
            addCriterion("code_b not like", value, "codeB");
            return (Criteria) this;
        }

        public Criteria andCodeBIn(List<String> values) {
            addCriterion("code_b in", values, "codeB");
            return (Criteria) this;
        }

        public Criteria andCodeBNotIn(List<String> values) {
            addCriterion("code_b not in", values, "codeB");
            return (Criteria) this;
        }

        public Criteria andCodeBBetween(String value1, String value2) {
            addCriterion("code_b between", value1, value2, "codeB");
            return (Criteria) this;
        }

        public Criteria andCodeBNotBetween(String value1, String value2) {
            addCriterion("code_b not between", value1, value2, "codeB");
            return (Criteria) this;
        }

        public Criteria andLunNumAIsNull() {
            addCriterion("lun_num_a is null");
            return (Criteria) this;
        }

        public Criteria andLunNumAIsNotNull() {
            addCriterion("lun_num_a is not null");
            return (Criteria) this;
        }

        public Criteria andLunNumAEqualTo(Integer value) {
            addCriterion("lun_num_a =", value, "lunNumA");
            return (Criteria) this;
        }

        public Criteria andLunNumANotEqualTo(Integer value) {
            addCriterion("lun_num_a <>", value, "lunNumA");
            return (Criteria) this;
        }

        public Criteria andLunNumAGreaterThan(Integer value) {
            addCriterion("lun_num_a >", value, "lunNumA");
            return (Criteria) this;
        }

        public Criteria andLunNumAGreaterThanOrEqualTo(Integer value) {
            addCriterion("lun_num_a >=", value, "lunNumA");
            return (Criteria) this;
        }

        public Criteria andLunNumALessThan(Integer value) {
            addCriterion("lun_num_a <", value, "lunNumA");
            return (Criteria) this;
        }

        public Criteria andLunNumALessThanOrEqualTo(Integer value) {
            addCriterion("lun_num_a <=", value, "lunNumA");
            return (Criteria) this;
        }

        public Criteria andLunNumAIn(List<Integer> values) {
            addCriterion("lun_num_a in", values, "lunNumA");
            return (Criteria) this;
        }

        public Criteria andLunNumANotIn(List<Integer> values) {
            addCriterion("lun_num_a not in", values, "lunNumA");
            return (Criteria) this;
        }

        public Criteria andLunNumABetween(Integer value1, Integer value2) {
            addCriterion("lun_num_a between", value1, value2, "lunNumA");
            return (Criteria) this;
        }

        public Criteria andLunNumANotBetween(Integer value1, Integer value2) {
            addCriterion("lun_num_a not between", value1, value2, "lunNumA");
            return (Criteria) this;
        }

        public Criteria andCiNumAIsNull() {
            addCriterion("ci_num_a is null");
            return (Criteria) this;
        }

        public Criteria andCiNumAIsNotNull() {
            addCriterion("ci_num_a is not null");
            return (Criteria) this;
        }

        public Criteria andCiNumAEqualTo(Integer value) {
            addCriterion("ci_num_a =", value, "ciNumA");
            return (Criteria) this;
        }

        public Criteria andCiNumANotEqualTo(Integer value) {
            addCriterion("ci_num_a <>", value, "ciNumA");
            return (Criteria) this;
        }

        public Criteria andCiNumAGreaterThan(Integer value) {
            addCriterion("ci_num_a >", value, "ciNumA");
            return (Criteria) this;
        }

        public Criteria andCiNumAGreaterThanOrEqualTo(Integer value) {
            addCriterion("ci_num_a >=", value, "ciNumA");
            return (Criteria) this;
        }

        public Criteria andCiNumALessThan(Integer value) {
            addCriterion("ci_num_a <", value, "ciNumA");
            return (Criteria) this;
        }

        public Criteria andCiNumALessThanOrEqualTo(Integer value) {
            addCriterion("ci_num_a <=", value, "ciNumA");
            return (Criteria) this;
        }

        public Criteria andCiNumAIn(List<Integer> values) {
            addCriterion("ci_num_a in", values, "ciNumA");
            return (Criteria) this;
        }

        public Criteria andCiNumANotIn(List<Integer> values) {
            addCriterion("ci_num_a not in", values, "ciNumA");
            return (Criteria) this;
        }

        public Criteria andCiNumABetween(Integer value1, Integer value2) {
            addCriterion("ci_num_a between", value1, value2, "ciNumA");
            return (Criteria) this;
        }

        public Criteria andCiNumANotBetween(Integer value1, Integer value2) {
            addCriterion("ci_num_a not between", value1, value2, "ciNumA");
            return (Criteria) this;
        }

        public Criteria andCodeAIsNull() {
            addCriterion("code_a is null");
            return (Criteria) this;
        }

        public Criteria andCodeAIsNotNull() {
            addCriterion("code_a is not null");
            return (Criteria) this;
        }

        public Criteria andCodeAEqualTo(String value) {
            addCriterion("code_a =", value, "codeA");
            return (Criteria) this;
        }

        public Criteria andCodeANotEqualTo(String value) {
            addCriterion("code_a <>", value, "codeA");
            return (Criteria) this;
        }

        public Criteria andCodeAGreaterThan(String value) {
            addCriterion("code_a >", value, "codeA");
            return (Criteria) this;
        }

        public Criteria andCodeAGreaterThanOrEqualTo(String value) {
            addCriterion("code_a >=", value, "codeA");
            return (Criteria) this;
        }

        public Criteria andCodeALessThan(String value) {
            addCriterion("code_a <", value, "codeA");
            return (Criteria) this;
        }

        public Criteria andCodeALessThanOrEqualTo(String value) {
            addCriterion("code_a <=", value, "codeA");
            return (Criteria) this;
        }

        public Criteria andCodeALike(String value) {
            addCriterion("code_a like", value, "codeA");
            return (Criteria) this;
        }

        public Criteria andCodeANotLike(String value) {
            addCriterion("code_a not like", value, "codeA");
            return (Criteria) this;
        }

        public Criteria andCodeAIn(List<String> values) {
            addCriterion("code_a in", values, "codeA");
            return (Criteria) this;
        }

        public Criteria andCodeANotIn(List<String> values) {
            addCriterion("code_a not in", values, "codeA");
            return (Criteria) this;
        }

        public Criteria andCodeABetween(String value1, String value2) {
            addCriterion("code_a between", value1, value2, "codeA");
            return (Criteria) this;
        }

        public Criteria andCodeANotBetween(String value1, String value2) {
            addCriterion("code_a not between", value1, value2, "codeA");
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