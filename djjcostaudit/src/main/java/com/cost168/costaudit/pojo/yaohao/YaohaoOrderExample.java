package com.cost168.costaudit.pojo.yaohao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YaohaoOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YaohaoOrderExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeIsNull() {
            addCriterion("yaohao_time is null");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeIsNotNull() {
            addCriterion("yaohao_time is not null");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeEqualTo(Date value) {
            addCriterion("yaohao_time =", value, "yaohaoTime");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeNotEqualTo(Date value) {
            addCriterion("yaohao_time <>", value, "yaohaoTime");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeGreaterThan(Date value) {
            addCriterion("yaohao_time >", value, "yaohaoTime");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("yaohao_time >=", value, "yaohaoTime");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeLessThan(Date value) {
            addCriterion("yaohao_time <", value, "yaohaoTime");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeLessThanOrEqualTo(Date value) {
            addCriterion("yaohao_time <=", value, "yaohaoTime");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeIn(List<Date> values) {
            addCriterion("yaohao_time in", values, "yaohaoTime");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeNotIn(List<Date> values) {
            addCriterion("yaohao_time not in", values, "yaohaoTime");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeBetween(Date value1, Date value2) {
            addCriterion("yaohao_time between", value1, value2, "yaohaoTime");
            return (Criteria) this;
        }

        public Criteria andYaohaoTimeNotBetween(Date value1, Date value2) {
            addCriterion("yaohao_time not between", value1, value2, "yaohaoTime");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearIsNull() {
            addCriterion("yaohao_year is null");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearIsNotNull() {
            addCriterion("yaohao_year is not null");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearEqualTo(String value) {
            addCriterion("yaohao_year =", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearNotEqualTo(String value) {
            addCriterion("yaohao_year <>", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearGreaterThan(String value) {
            addCriterion("yaohao_year >", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearGreaterThanOrEqualTo(String value) {
            addCriterion("yaohao_year >=", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearLessThan(String value) {
            addCriterion("yaohao_year <", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearLessThanOrEqualTo(String value) {
            addCriterion("yaohao_year <=", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearLike(String value) {
            addCriterion("yaohao_year like", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearNotLike(String value) {
            addCriterion("yaohao_year not like", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearIn(List<String> values) {
            addCriterion("yaohao_year in", values, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearNotIn(List<String> values) {
            addCriterion("yaohao_year not in", values, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearBetween(String value1, String value2) {
            addCriterion("yaohao_year between", value1, value2, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearNotBetween(String value1, String value2) {
            addCriterion("yaohao_year not between", value1, value2, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeIsNull() {
            addCriterion("yaohao_grade is null");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeIsNotNull() {
            addCriterion("yaohao_grade is not null");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeEqualTo(String value) {
            addCriterion("yaohao_grade =", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeNotEqualTo(String value) {
            addCriterion("yaohao_grade <>", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeGreaterThan(String value) {
            addCriterion("yaohao_grade >", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeGreaterThanOrEqualTo(String value) {
            addCriterion("yaohao_grade >=", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeLessThan(String value) {
            addCriterion("yaohao_grade <", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeLessThanOrEqualTo(String value) {
            addCriterion("yaohao_grade <=", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeLike(String value) {
            addCriterion("yaohao_grade like", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeNotLike(String value) {
            addCriterion("yaohao_grade not like", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeIn(List<String> values) {
            addCriterion("yaohao_grade in", values, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeNotIn(List<String> values) {
            addCriterion("yaohao_grade not in", values, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeBetween(String value1, String value2) {
            addCriterion("yaohao_grade between", value1, value2, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeNotBetween(String value1, String value2) {
            addCriterion("yaohao_grade not between", value1, value2, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumIsNull() {
            addCriterion("enterprise_num is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumIsNotNull() {
            addCriterion("enterprise_num is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumEqualTo(Integer value) {
            addCriterion("enterprise_num =", value, "enterpriseNum");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumNotEqualTo(Integer value) {
            addCriterion("enterprise_num <>", value, "enterpriseNum");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumGreaterThan(Integer value) {
            addCriterion("enterprise_num >", value, "enterpriseNum");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("enterprise_num >=", value, "enterpriseNum");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumLessThan(Integer value) {
            addCriterion("enterprise_num <", value, "enterpriseNum");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumLessThanOrEqualTo(Integer value) {
            addCriterion("enterprise_num <=", value, "enterpriseNum");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumIn(List<Integer> values) {
            addCriterion("enterprise_num in", values, "enterpriseNum");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumNotIn(List<Integer> values) {
            addCriterion("enterprise_num not in", values, "enterpriseNum");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumBetween(Integer value1, Integer value2) {
            addCriterion("enterprise_num between", value1, value2, "enterpriseNum");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNumNotBetween(Integer value1, Integer value2) {
            addCriterion("enterprise_num not between", value1, value2, "enterpriseNum");
            return (Criteria) this;
        }

        public Criteria andWinbidNumIsNull() {
            addCriterion("winbid_num is null");
            return (Criteria) this;
        }

        public Criteria andWinbidNumIsNotNull() {
            addCriterion("winbid_num is not null");
            return (Criteria) this;
        }

        public Criteria andWinbidNumEqualTo(Integer value) {
            addCriterion("winbid_num =", value, "winbidNum");
            return (Criteria) this;
        }

        public Criteria andWinbidNumNotEqualTo(Integer value) {
            addCriterion("winbid_num <>", value, "winbidNum");
            return (Criteria) this;
        }

        public Criteria andWinbidNumGreaterThan(Integer value) {
            addCriterion("winbid_num >", value, "winbidNum");
            return (Criteria) this;
        }

        public Criteria andWinbidNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("winbid_num >=", value, "winbidNum");
            return (Criteria) this;
        }

        public Criteria andWinbidNumLessThan(Integer value) {
            addCriterion("winbid_num <", value, "winbidNum");
            return (Criteria) this;
        }

        public Criteria andWinbidNumLessThanOrEqualTo(Integer value) {
            addCriterion("winbid_num <=", value, "winbidNum");
            return (Criteria) this;
        }

        public Criteria andWinbidNumIn(List<Integer> values) {
            addCriterion("winbid_num in", values, "winbidNum");
            return (Criteria) this;
        }

        public Criteria andWinbidNumNotIn(List<Integer> values) {
            addCriterion("winbid_num not in", values, "winbidNum");
            return (Criteria) this;
        }

        public Criteria andWinbidNumBetween(Integer value1, Integer value2) {
            addCriterion("winbid_num between", value1, value2, "winbidNum");
            return (Criteria) this;
        }

        public Criteria andWinbidNumNotBetween(Integer value1, Integer value2) {
            addCriterion("winbid_num not between", value1, value2, "winbidNum");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalIsNull() {
            addCriterion("service_amount_total is null");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalIsNotNull() {
            addCriterion("service_amount_total is not null");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalEqualTo(BigDecimal value) {
            addCriterion("service_amount_total =", value, "serviceAmountTotal");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalNotEqualTo(BigDecimal value) {
            addCriterion("service_amount_total <>", value, "serviceAmountTotal");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalGreaterThan(BigDecimal value) {
            addCriterion("service_amount_total >", value, "serviceAmountTotal");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("service_amount_total >=", value, "serviceAmountTotal");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalLessThan(BigDecimal value) {
            addCriterion("service_amount_total <", value, "serviceAmountTotal");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("service_amount_total <=", value, "serviceAmountTotal");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalIn(List<BigDecimal> values) {
            addCriterion("service_amount_total in", values, "serviceAmountTotal");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalNotIn(List<BigDecimal> values) {
            addCriterion("service_amount_total not in", values, "serviceAmountTotal");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("service_amount_total between", value1, value2, "serviceAmountTotal");
            return (Criteria) this;
        }

        public Criteria andServiceAmountTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("service_amount_total not between", value1, value2, "serviceAmountTotal");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalIsNull() {
            addCriterion("decide_amount_total is null");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalIsNotNull() {
            addCriterion("decide_amount_total is not null");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalEqualTo(BigDecimal value) {
            addCriterion("decide_amount_total =", value, "decideAmountTotal");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalNotEqualTo(BigDecimal value) {
            addCriterion("decide_amount_total <>", value, "decideAmountTotal");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalGreaterThan(BigDecimal value) {
            addCriterion("decide_amount_total >", value, "decideAmountTotal");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("decide_amount_total >=", value, "decideAmountTotal");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalLessThan(BigDecimal value) {
            addCriterion("decide_amount_total <", value, "decideAmountTotal");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("decide_amount_total <=", value, "decideAmountTotal");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalIn(List<BigDecimal> values) {
            addCriterion("decide_amount_total in", values, "decideAmountTotal");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalNotIn(List<BigDecimal> values) {
            addCriterion("decide_amount_total not in", values, "decideAmountTotal");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("decide_amount_total between", value1, value2, "decideAmountTotal");
            return (Criteria) this;
        }

        public Criteria andDecideAmountTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("decide_amount_total not between", value1, value2, "decideAmountTotal");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("creater is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("creater is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("creater =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("creater <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("creater >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("creater >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("creater <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("creater <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("creater like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("creater not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("creater in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("creater not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("creater between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("creater not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonIsNull() {
            addCriterion("working_person is null");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonIsNotNull() {
            addCriterion("working_person is not null");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonEqualTo(String value) {
            addCriterion("working_person =", value, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonNotEqualTo(String value) {
            addCriterion("working_person <>", value, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonGreaterThan(String value) {
            addCriterion("working_person >", value, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonGreaterThanOrEqualTo(String value) {
            addCriterion("working_person >=", value, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonLessThan(String value) {
            addCriterion("working_person <", value, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonLessThanOrEqualTo(String value) {
            addCriterion("working_person <=", value, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonLike(String value) {
            addCriterion("working_person like", value, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonNotLike(String value) {
            addCriterion("working_person not like", value, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonIn(List<String> values) {
            addCriterion("working_person in", values, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonNotIn(List<String> values) {
            addCriterion("working_person not in", values, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonBetween(String value1, String value2) {
            addCriterion("working_person between", value1, value2, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andWorkingPersonNotBetween(String value1, String value2) {
            addCriterion("working_person not between", value1, value2, "workingPerson");
            return (Criteria) this;
        }

        public Criteria andLunNumberIsNull() {
            addCriterion("lun_number is null");
            return (Criteria) this;
        }

        public Criteria andLunNumberIsNotNull() {
            addCriterion("lun_number is not null");
            return (Criteria) this;
        }

        public Criteria andLunNumberEqualTo(Integer value) {
            addCriterion("lun_number =", value, "lunNumber");
            return (Criteria) this;
        }

        public Criteria andLunNumberNotEqualTo(Integer value) {
            addCriterion("lun_number <>", value, "lunNumber");
            return (Criteria) this;
        }

        public Criteria andLunNumberGreaterThan(Integer value) {
            addCriterion("lun_number >", value, "lunNumber");
            return (Criteria) this;
        }

        public Criteria andLunNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("lun_number >=", value, "lunNumber");
            return (Criteria) this;
        }

        public Criteria andLunNumberLessThan(Integer value) {
            addCriterion("lun_number <", value, "lunNumber");
            return (Criteria) this;
        }

        public Criteria andLunNumberLessThanOrEqualTo(Integer value) {
            addCriterion("lun_number <=", value, "lunNumber");
            return (Criteria) this;
        }

        public Criteria andLunNumberIn(List<Integer> values) {
            addCriterion("lun_number in", values, "lunNumber");
            return (Criteria) this;
        }

        public Criteria andLunNumberNotIn(List<Integer> values) {
            addCriterion("lun_number not in", values, "lunNumber");
            return (Criteria) this;
        }

        public Criteria andLunNumberBetween(Integer value1, Integer value2) {
            addCriterion("lun_number between", value1, value2, "lunNumber");
            return (Criteria) this;
        }

        public Criteria andLunNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("lun_number not between", value1, value2, "lunNumber");
            return (Criteria) this;
        }

        public Criteria andCiNumberIsNull() {
            addCriterion("ci_number is null");
            return (Criteria) this;
        }

        public Criteria andCiNumberIsNotNull() {
            addCriterion("ci_number is not null");
            return (Criteria) this;
        }

        public Criteria andCiNumberEqualTo(Integer value) {
            addCriterion("ci_number =", value, "ciNumber");
            return (Criteria) this;
        }

        public Criteria andCiNumberNotEqualTo(Integer value) {
            addCriterion("ci_number <>", value, "ciNumber");
            return (Criteria) this;
        }

        public Criteria andCiNumberGreaterThan(Integer value) {
            addCriterion("ci_number >", value, "ciNumber");
            return (Criteria) this;
        }

        public Criteria andCiNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("ci_number >=", value, "ciNumber");
            return (Criteria) this;
        }

        public Criteria andCiNumberLessThan(Integer value) {
            addCriterion("ci_number <", value, "ciNumber");
            return (Criteria) this;
        }

        public Criteria andCiNumberLessThanOrEqualTo(Integer value) {
            addCriterion("ci_number <=", value, "ciNumber");
            return (Criteria) this;
        }

        public Criteria andCiNumberIn(List<Integer> values) {
            addCriterion("ci_number in", values, "ciNumber");
            return (Criteria) this;
        }

        public Criteria andCiNumberNotIn(List<Integer> values) {
            addCriterion("ci_number not in", values, "ciNumber");
            return (Criteria) this;
        }

        public Criteria andCiNumberBetween(Integer value1, Integer value2) {
            addCriterion("ci_number between", value1, value2, "ciNumber");
            return (Criteria) this;
        }

        public Criteria andCiNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("ci_number not between", value1, value2, "ciNumber");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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